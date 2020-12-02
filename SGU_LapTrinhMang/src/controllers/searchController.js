import {media} from "./index";
import fetch from "node-fetch";
import _ from "lodash";
import crypto from "crypto";
import {encode} from "utf8";
import en from "./../../lang/en";
import {app} from "./../config/app";

// var time = "1606298489";
let time = (function() {
  return new Date().getTime().toString().substring(0, 10);
})();
let get_hash256 = (string) => {
  return crypto.createHash("sha256").update(encode(string), "utf8").digest("hex");
};
let get_hmac512 = (string) => {
  return crypto.createHmac("sha512", app.SECRET_KEY).update(encode(string), "utf8").digest("hex");
};

// var sig = "2499564ba1914349812ef3e06559d181828740072e4ae484936f3eee520ef7f62358837d6494c0cf157baaec3f8e4de802906d5353019a9003f3f5aacdb41a97";
let sha256 = get_hash256("ctime=" + time);
let sig = get_hmac512("/search" + sha256);

let searchSinger = async (req, res) => {
  let singName = req.query.name;
  let result = {};
  let singerString = "https://zingmp3.vn/api/search?type=artist&q="+ encodeURI(singName) +"&start=0&count=" + app.count + "&ctime="+ time +"&sig="+ sig +"&api_key=" + app.API_KEY;
  let getListSinger = () => {
    return new Promise((resolve, reject) => {
      const dataObj = fetch(singerString);
      dataObj
        .then( async (response) => {
          let data = await response.json();
          let data2 = await JSON.parse(JSON.stringify(data));
          let resultObj = await data2.data.items;
          let listSinger = [];
          for (let i in resultObj) {
            let v = {
              id: resultObj[i].id,
              name: resultObj[i].name,
              thumbnail: resultObj[i].thumbnail,
              info: req.protocol + "://" + req.headers.host + "/info/sing?id=" + resultObj[i].id + "&artist=" + resultObj[i].link,
            }
            listSinger.push(v);
          }
          result = listSinger;
          return resolve(en.success.getListSinger);
        })
        .catch( err => {
          return resolve(en.fail.getListSinger);
        });
    });
  };

  try {
    let _getListSinger = await getListSinger(req, res);
    if (_.isEmpty(result)) {
      res.send(new Object());
    } else {
      res.send(result);
    }
    console.log(_getListSinger);
  } catch (err) {
    res.send({
      result: new Object()
    });
    res.end();
  }

  console.log("=========================================================");
};

let searchSong = async (req, res) => {
  let songName = req.query.name;
  var result = {};

  let getListSong = () => {
    return new Promise((resolve, reject) => {
      const dataObj = fetch("https://zingmp3.vn/api/search?type=song&q=" + encodeURI(songName) + "&start=0&count="+ app.count +"&ctime="+ time +"&sig="+ sig +"&api_key=" + app.API_KEY);
      dataObj
        .then( async (response) => {
          let data = await response.json();
          let data2 = await JSON.parse(JSON.stringify(data));
          let resultObj = await data2.data.items;
          let listSong = [];
          for (let i in resultObj) {
            if (app.checkStatusLinkMedia == true) {
              let v = {
                id: resultObj[i].id,
                name: resultObj[i].title,
                thumbnail: resultObj[i].thumbnail,
                artists_names: resultObj[i].artists_names,
                audio: (await media.checkLinkAudio(resultObj[i].id) == "0") ? "https://zingmp3.vn/embed/song/" + resultObj[i].id : "",
                video: (await media.checkLinkVideo(resultObj[i].id) == "0") ? "https://zingmp3.vn/embed/video/" + resultObj[i].id : "",
                info: req.protocol + "://" + req.headers.host + "/info/song?id=" + resultObj[i].id,
              }
              listSong.push(v);
            } else {
              let v = {
                id: resultObj[i].id,
                name: resultObj[i].title,
                thumbnail: resultObj[i].thumbnail,
                artists_names: resultObj[i].artists_names,
                audio: "https://zingmp3.vn/embed/song/" + resultObj[i].id,
                // video: "https://zingmp3.vn/embed/video/" + resultObj[i].id,
                video: "",
                info: req.protocol + "://" + req.headers.host + "/info/song?id=" + resultObj[i].id,
              }              
              listSong.push(v);
            }
          }
          result = listSong;
          return resolve(en.success.getListSong);
        })
        .catch( err => {
          result = {};
          return resolve(en.fail.getListSong);
        });
    });
  };

  try {
    let _getListSong = await getListSong(req, res);
    if (_.isEmpty(result)) {
      res.send(new Object());
    } else {
      res.send(result);
    }
    console.log(_getListSong);
  } catch (err) {
    console.log(err);
    res.end();
  }

  console.log("=========================================================");
};

module.exports = {
  searchSinger, searchSong
};
