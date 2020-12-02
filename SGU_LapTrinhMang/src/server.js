import express from "express";
import configRoutes from "./routes/api";
import http from "http";

// create a server HTTP
const app = express();
let server = http.createServer(app);

// Config route
configRoutes(app);

//get port params from $PATH
const PORT = process.env.PORT || process.env.APP_PORT;

//Listen server
server.listen(PORT, () => console.log(`App running at : ${PORT}`));
