let getIdByLink = (url) => {
  url = url.substring(0, url.length-5);
  let tmp = url.split("/");
  return url = tmp[tmp.length - 1];
};

module.exports = {
  getIdByLink
};