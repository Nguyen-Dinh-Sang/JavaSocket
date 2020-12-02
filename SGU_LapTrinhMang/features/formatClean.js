let formatClean = (string) => {
  // string = string.replace(/\n/g, "");
  string = string.replace(/<br>/g, "");
  string = string.replace(/\"/g, "'");
  string = string.replace(/\s+/g, ' ');
  string = string.trim();
  return string;
};

module.exports = {
  formatClean
};