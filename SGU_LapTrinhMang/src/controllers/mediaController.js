import crypto from "crypto";
import {encode} from "utf8";
import fetch from "node-fetch";
import {app} from "./../config/app";

let time = (function() {
  return new Date().getTime().toString().substring(0, 10);
})();
// let time = "1606201372";
let get_hash256 = (string) => {
  return crypto.createHash("sha256").update(encode(string), "utf8").digest("hex");
};
let get_hmac512 = (string) => {
  return crypto.createHmac("sha512", app.SECRET_KEY).update(encode(string), "utf8").digest("hex");
};

let checkLinkAudio = async (id) => {
  let sha256 = await get_hash256("ctime=" + time + "id=" + id);
  let sig = await get_hmac512("/song/get-song-info" + sha256);
  let audioString = "https://zingmp3.vn/api/song/get-song-info?id=" + id + "&ctime=" + time + "&sig=" + sig + "&api_key=" + app.API_KEY;
  
  return new Promise((resolve, reject) => {
    const dataObj = fetch(audioString);
    dataObj
      .then( async (response) => {
        let data = await response.json();
        let data2 = await JSON.parse(JSON.stringify(data));
        let err = await data2.err;
        return resolve(err);
      })
      .catch(() => {
        return resolve("-1");
      });
  });
};

let checkLinkVideo = async (id) => {
  let sha256 = await get_hash256("ctime=" + time + "id=" + id);
  let sig = await get_hmac512("/video/get-video-detail" + sha256);
  let videoString = "https://zingmp3.vn/api/video/get-video-detail?id=" + id + "&ctime=" + time + "&sig=" + sig + "&api_key=" + app.API_KEY;
  
  return new Promise((resolve, reject) => {
    const dataObj = fetch(videoString);
    dataObj
      .then( async (response) => {
        let data = await response.json();
        let data2 = await JSON.parse(JSON.stringify(data));
        let err = await data2.err;
        return resolve(err);
      })
      .catch(() => {
        return resolve("-1");
      });
  });
};

module.exports = {
  checkLinkAudio,
  checkLinkVideo
};
