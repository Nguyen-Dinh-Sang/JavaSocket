import fetch from "node-fetch";
import crypto from "crypto";
import { encode } from "utf8";
import {media} from "./index";
import en from "./../../lang/en";
import {app} from "./../config/app";
import {formatClean} from "./../../features/formatClean"; 

let infoSong = async (req, res) => {
  let result = {};
  let idSong = req.query.id;
  let time = (function() {
    return new Date().getTime();
  })();
  let get_hash256 = (string) => {
    return crypto.createHash("sha256").update(encode(string), "utf8").digest("hex");;
  };
  let sha256 = get_hash256("ctime=" + time + "id=" + idSong);
  let get_hmac512 = (string) => {
    return crypto.createHmac("sha512", app.SECRET_KEY).update(encode(string), "utf8").digest("hex");
  };
  let sig = get_hmac512("/song/get-song-detail" + sha256);
  let searchSongString = "https://zingmp3.vn/api/song/get-song-detail?id=" + idSong + "&ctime=" + time + "&sig=" + sig + "&api_key=" + app.API_KEY;

  let getInfoSong = async () => {
    return new Promise((resolve, reject) => {
      const dataObj = fetch(searchSongString);
      dataObj
        .then(response => {
          return response.json();
        })
        .then(async data => {
          let dataParse = JSON.parse(JSON.stringify(data.data));
          result.id = (dataParse.hasOwnProperty("id")) ? dataParse.id : "";
          result.name = (dataParse.hasOwnProperty("title")) ? dataParse.title : "";
          if (app.checkStatusLinkMedia == true) {
            result.audio = (dataParse.hasOwnProperty("id") && (await media.checkLinkAudio(dataParse.id)) == "0") ? "https://zingmp3.vn/embed/song/" + dataParse.id : "";
            result.video = (dataParse.hasOwnProperty("id") && (await media.checkLinkVideo(dataParse.id)) == "0") ? "https://zingmp3.vn/embed/video/" + dataParse.id : "";
          } else {
            result.audio = (dataParse.hasOwnProperty("id")) ? "https://zingmp3.vn/embed/song/" + dataParse.id : "";
            // result.video = (dataParse.hasOwnProperty("id")) ? "https://zingmp3.vn/embed/video/" + dataParse.id : "";
            result.video = "";
          }
          // result.lyrics = (dataParse.hasOwnProperty("lyrics")) ? formatClean(dataParse.lyrics[0].content) : "";
          result.lyrics = (dataParse.hasOwnProperty("lyrics")) ? dataParse.lyrics[0].content : "";
          result.singerName = (dataParse.hasOwnProperty("artists_names")) ? dataParse.artists_names : "";
          if (dataParse.hasOwnProperty("composers")) {
            let data = dataParse.composers;
            let composerName = "";
            for (let i = 0; i < data.length; i++) {
              (i == data.length - 1) ? composerName += (data[i].name) : composerName += (data[i].name + ", ");
            }
            result.composerName = composerName;
          } else {
            result.composerName = "";
          }
          return resolve(en.success.getInfoSong);
        })
        .catch(() => {
          result = {};
          return resolve(en.fail.getInfoSong);
        });
    });
  };

  try {
    let _getInfoSong = await getInfoSong(req, res);
    console.log(_getInfoSong);
    res.send({
      success: 'yes',
      data: result
    });
    res.end();
  } catch (error) {
    res.send({
      success: 'no',
      data: {}
    })
  }

  console.log("=========================================================");
};
  
module.exports = {
  infoSong,
};
