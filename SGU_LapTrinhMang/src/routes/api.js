import express from "express";
import {search, sing, song, media} from "./../controllers/index";

let router = express.Router();

let configRoutes = (app) => {
  router.get("/", (req, res) => {
    res.send("Cuộc sống mà 🙆‍♀️🙆‍♀️🙆‍♀️");
  } );
  
  //SING
  router.get("/search/sing",
    search.searchSinger
  );

  router.get("/info/sing",
    sing.infoSinger
  );

  //SONG
  router.get("/search/song",
    search.searchSong
  );

  router.get("/info/song",
    song.infoSong
  );

  app.use("/", router);

  //catch 404 error
  app.use( (req, res, next) => {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
  });

  app.use( (err, req, res, next) => {
    res.send("Ok. I'm fine 😒😒");
});
    
};

module.exports = configRoutes;
