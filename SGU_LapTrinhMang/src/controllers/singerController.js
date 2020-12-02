import cheerio from "cheerio";
import fetch from "node-fetch";
import en from "./../../lang/en";
import _ from "lodash";
import crypto from "crypto";
import {encode} from "utf8";
import {media} from "./index";
import {app} from "./../config/app";
import {formatClean} from "../../features/formatClean";
import {getIdByLink} from "../../features/getIdByLink";

let infoSinger = async (req, res) => {
  let idSinger = req.query.id;
  let linkSinger = req.query.artist;
  let time = (function() {
    return new Date().getTime().toString().substring(0, 10);
  })();
  let get_hash256 = (string) => {
    return crypto.createHash("sha256").update(encode(string), "utf8").digest("hex");
  };
  let get_hmac512 = (string) => {
    return crypto.createHmac("sha512", app.SECRET_KEY).update(encode(string), "utf8").digest("hex");
  };

  var result = {};
  
  let getInfoSinger = (req, res) => {
    let infoString = "https://mp3.zing.vn" + linkSinger + "/tieu-su";
    return new Promise((resolve, reject) => {
      const dataObj = fetch(infoString);
      dataObj
      .then( async (response) => {
        let html = await response.text();
        let $ = await cheerio.load(html);
        let intro = await $("body div.entry").text();
        intro = formatClean(intro);
        let name = await $("body div.info-summary h1").text();
        let thumbnail = await $("body div.box-info-artist img").attr("src");
        result.name = await name;
        result.thumbnail = await thumbnail;
        result.info = await intro;
        return resolve(en.success.getInfoSinger);
      })
      .catch(() => {
        result.name = {};
        result.thumbnail = {};
        result.info = {};
        return resolve(en.fail.getInfoSinger);
      });
    });
  };

  // get amount page
  let getPage = (req, res, url) => {
    return new Promise((resolve, reject) => {
      const tmp = fetch(url);
      let index = 0;
      tmp
        .then( async response => {
          let html = await response.text();
          let $ = await cheerio.load(html);
          $("div.pagination ul li").each(function(i, item) {
            index = $(item).find("a").attr("href").split("&page=")[1];
          });
          return resolve(index);
        })
        .catch( () => {
  
        });
    });
  };

  // get songs in Album
  let getSongsInAlbum = async (req, res, id) => {
    let sha256 = get_hash256("ctime=" + time + "id=" + id);
    let sig = get_hmac512("/playlist/get-playlist-detail" + sha256);
    let albumString = "https://zingmp3.vn/api/playlist/get-playlist-detail?id=" + id + "&ctime=" + time + "&sig=" + sig + "&api_key=" + app.API_KEY;

    return new Promise((resolve, reject) => {
      const dataObj = fetch(albumString);
      dataObj
      .then( async (response) => {
        let data = await response.json();
        let data2 = await JSON.parse(JSON.stringify(data));
        let songs = [];
        let resultObj = await data2.data.song.items;
        for (let i in resultObj) {
          if (app.checkStatusLinkMedia == true) {
            let v = {
              id: resultObj[i].id,
              name: resultObj[i].title,
              thumbnail: resultObj[i].thumbnail,
              audio: ( await media.checkLinkAudio(resultObj[i].id) == "0") ? "https://zingmp3.vn/embed/song/" + resultObj[i].id : "",
            };
            songs.push(v);
          } else {
            let v = {
              id: resultObj[i].id,
              name: resultObj[i].title,
              thumbnail: resultObj[i].thumbnail,
              audio: "https://zingmp3.vn/embed/song/" + resultObj[i].id,
            };
            songs.push(v);
          }
        }
        return resolve(songs);
      })
      .catch(() => {
        return resolve("");
      });
    });
  }

  //get Album of singer from zingmp3.vn
  let getAlbumSinger = async () => {
    if (app.getDataMethod == "html") {
      var promiseArray = [];
      var index = 1;
      var albumAllPage = [];
      var amountPage = await getPage(req, res, "https://mp3.zing.vn"+ linkSinger +"/album");
      do {
        let searchAlbumString = "https://mp3.zing.vn"+ linkSinger +"/album?page=" + index;
        promiseArray.push(
          new Promise((resolve, reject) => {
            const dataObj = fetch(searchAlbumString);
            dataObj
              .then( async response => {
                let albumEachPage = [];
                let html = await response.text();
                let $ = await cheerio.load(html);
                $("div.row.fn-list div.album-item.fn-item").each(async function(i, item) {
                  let v = {
                    id: getIdByLink($(item).find("a").attr("href")),
                    name: $(item).find("a").attr("title"),
                    thumbnail: $(item).find("img").attr("src"),
                    // songs: await getSongsInAlbum(req, res, getIdByLink($(item).find("a").attr("href"))),
                    songs: "list bai hat",
                  };
                  albumEachPage.push(v);
                });
                resolve(albumEachPage);
              })
              .catch(() => {
                reject();
              });
          })
        );
      } while(++index <= amountPage);

      return new Promise((resolve, reject) => {
        Promise.all(promiseArray)
          .then( async data => {
            for (let i in data) {
              for (let j in data[i]) {
                albumAllPage.push(data[i][j]);
              }
            }
            result.album = await albumAllPage;
            return resolve(data);
          })
          .catch(() => {
            return resolve("sai cmnr");
          });
      });
    } else {
      let sha256 = get_hash256("ctime=" + time + "id=" + idSinger);
      let sig = get_hmac512("/playlist/get-list" + sha256);
      let searchAlbumString = "https://zingmp3.vn/api/playlist/get-list?id=" + idSinger + "&type=artist&start=0&count="+ app.count +"&sort=hot&ctime="+ time +"&sig="+ sig +"&api_key=" + app.API_KEY;
      return new Promise((resolve, reject) => {
        const dataObj = fetch(searchAlbumString);
        dataObj
          .then( async (response) => {
            let data = await response.json();
            let data2 = await JSON.parse(JSON.stringify(data));
            let albumResult = [];
            let resultObj = await data2.data.items;
            for (let i in resultObj) {
              let v = {
                id: resultObj[i].id,
                name: resultObj[i].title,
                thumbnail: resultObj[i].thumbnail,
                songs: (await getSongsInAlbum(req, res, resultObj[i].id)),
              };
              albumResult.push(v);
            }
            result.album = albumResult;
            return resolve(en.success.getAlbumSinger);
          })
          .catch(() => {
            result.album = {};
            return resolve(en.success.getAlbumSinger);
          });
      });
    }
  };

  //get MV of singer from zingmp3.vn
  let getVideoSinger = async () => {
    if (app.getDataMethod == "html") {
      var promiseArray = [];
      var index = 1;
      var videoAllPage = [];
      var amountPage = await getPage(req, res, "https://mp3.zing.vn"+ linkSinger +"/video");
      do {
        let searchMVString = "https://mp3.zing.vn"+ linkSinger +"/video?page=" + index;
        promiseArray.push(
          new Promise((resolve, reject) => {
            const dataObj = fetch(searchMVString);
            dataObj
              .then( async response => {
                let videoEachPage = [];
                let html = await response.text();
                let $ = await cheerio.load(html);
                $("div.row.fn-list div.video-item.fn-item").each(async function(i, item) {
                  let v = {
                    id: getIdByLink($(item).find("a").attr("href")),
                    name: $(item).find("a").attr("title"),
                    video: "https://zingmp3.vn/embed/video/" + getIdByLink($(item).find("a").attr("href")),
                    thumbnail: $(item).find("img").attr("src"), 
                  };
                  videoEachPage.push(v);
                });
                resolve(videoEachPage);
              })
              .catch(() => {
                reject();
              });
          })
        );
      } while(++index <= amountPage);

      return new Promise((resolve, reject) => {
        Promise.all(promiseArray)
          .then( async data => {
            for (let i in data) {
              for (let j in data[i]) {
                videoAllPage.push(data[i][j]);
              }
            }
            result.mv = await videoAllPage;
            return resolve(data);
          })
          .catch(() => {
            return resolve("sai cmnr");
          });
      });

    } else {
      let sha256 = get_hash256("ctime=" + time + "id=" + idSinger);
      let sig = get_hmac512("/video/get-list" + sha256);
      let searchMVString = "https://zingmp3.vn/api/video/get-list?id=" + idSinger + "&type=artist&start=0&count="+ app.count +"&sort=hot&ctime="+ time +"&sig="+ sig +"&api_key=" + app.API_KEY;
      // let searchMVString = searchPattern1 + "video&q="+ encodeURI(singName) + searchPattern2;
      return new Promise((resolve, reject) => {
        const dataObj = fetch(searchMVString);
        dataObj
          .then(async response => {
            let data = await response.json();
            let data2 = await JSON.parse(JSON.stringify(data));
            let resultObj = await data2.data.items;
            let videoResult = [];
            for (let i in resultObj) {
              if (app.checkStatusLinkMedia == true) {
                let v = {
                  id: resultObj[i].id,
                  name: resultObj[i].title,
                  video: (await media.checkLinkVideo(resultObj[i].id) == "0") ? "https://zingmp3.vn/embed/video/" + resultObj[i].id : "",
                  thumbnail: resultObj[i].thumbnail,
                };
                videoResult.push(v);
              } else {
                let v = {
                  id: resultObj[i].id,
                  name: resultObj[i].title,
                  video: "https://zingmp3.vn/embed/video/" + resultObj[i].id,
                  thumbnail: resultObj[i].thumbnail,
                };
                videoResult.push(v);
              }
            }
            result.mv = videoResult;
            return resolve(en.success.getVideoSinger);
          })
          .catch(() => {
            result.mv = {};
            return resolve(en.fail.getVideoSinger);
          });
      });
    }
  };

  //get Song of singer from zingmp3.vn
  let getAudioSinger = async () => {
    if (app.getDataMethod == "html") {
      var promiseArray = [];
      var index = 1;
      var audioAllPage = [];
      var amountPage = await getPage(req, res, "https://mp3.zing.vn"+ linkSinger +"/bai-hat");
      do {
        let searchAudioString = "https://mp3.zing.vn"+ linkSinger +"/bai-hat?page=" + index;
        promiseArray.push(
          new Promise((resolve, reject) => {
            const dataObj = fetch(searchAudioString);
            dataObj
              .then( async response => {
                let audioEachPage = [];
                let html = await response.text();
                let $ = await cheerio.load(html);
                $("div.list-item.full-width ul li.group.fn-song").each(async function(i, item) {
                  let v = {
                    id: $(item).attr("data-id"),
                    name: formatClean($(item).find("div h3 a").text()),
                    audio: "https://zingmp3.vn/embed/song/" + $(item).attr("data-id"),
                    thumbnail: "",
                  };
                  audioEachPage.push(v);
                });
                resolve(audioEachPage);
              })
              .catch(() => {
                reject();
              });
          })
        );
      } while(++index <= amountPage);

      return new Promise((resolve, reject) => {
        Promise.all(promiseArray)
          .then( async data => {
            for (let i in data) {
              for (let j in data[i]) {
                audioAllPage.push(data[i][j]);
              }
            }
            result.song = await audioAllPage;
            return resolve(data);
          })
          .catch(() => {
            return resolve("sai cmnr");
          });
      });
    } else {
      let sha256 = get_hash256("ctime=" + time + "id=" + idSinger);
      let sig = get_hmac512("/song/get-list" + sha256);
      let searchSongString = "https://zingmp3.vn/api/song/get-list?id=" + idSinger + "&type=artist&start=0&count="+ app.count +"&sort=hot&ctime="+ time +"&sig="+ sig +"&api_key=" + app.API_KEY;
      return new Promise((resolve, reject) => {
        const dataObj = fetch(searchSongString);
        dataObj
          .then(async response => {
            let data = await response.json();
            let data2 = await JSON.parse(JSON.stringify(data));
            let resultObj = await data2.data.items;
            let audioResult = [];
            for (let i in resultObj) {
              if (app.checkStatusLinkMedia == true) {
                let v = {
                  id: resultObj[i].id,
                  name: resultObj[i].title,
                  audio: (await media.checkLinkAudio(resultObj[i].id) == "0") ? "https://zingmp3.vn/embed/song/" + resultObj[i].id : "",
                  thumbnail: resultObj[i].thumbnail,
                };
                audioResult.push(v);
              } else {
                let v = {
                  id: resultObj[i].id,
                  name: resultObj[i].title,
                  audio: "https://zingmp3.vn/embed/song/" + resultObj[i].id,
                  thumbnail: resultObj[i].thumbnail,
                };
                audioResult.push(v);
              }
            }
            result.song = audioResult;
            return resolve(en.success.getAudioSinger);
          })
          .catch(() => {
            result.song = {};
            return resolve(en.fail.getAudioSinger);
          });
      });
    }
  };

  try {
      Promise.all([
        await getInfoSinger(req, res),
        await getAlbumSinger(req, res),
        await getVideoSinger(req, res),
        await getAudioSinger(req, res)
      ])
        .then(msg => console.log(msg))
        .catch(err => console.log(err));
  
      res.send({
        success: 'yes', 
        data: result
      });
      res.end();
  } catch (error) {
    console.log(error);
    res.end();
  }
  console.log("=========================================================");
};

module.exports = {
  infoSinger
};
