const express = require("express");
const cors = require("cors");
const app = express();
const port = 3000;
app.use(cors());
app.get("/", (req, res) => {
  res.sendFile(__dirname + "/index.html");
});

app.get("/funzioni.html", (req, res) => {
  res.sendFile(__dirname + "/funzioni.html");
});

app.get("/funzioni2.html", (req, res) => {
  res.sendFile(__dirname + "/funzioni2.html");
});

app.get("/oggetti.html", (req, res) => {
  res.sendFile(__dirname + "/oggetti.html");
});

app.get("/array.html", (req, res) => {
  console.log("chiamata alla rotta array");
  res.sendFile(__dirname + "/array.html");
});

app.get("/js-struttura-dei-programmi.html", (req, res) => {
  res.sendFile(__dirname + "/js-struttura-dei-programmi.html");
});

app.get("/mm_array.html", (req, res) => {
  res.sendFile(__dirname + "/monica.marziani" + "/array.html");
});

app.get("/json", (req, res) => {
  res.sendFile(__dirname + "/json.html");
});

app.get("/esercizio", (req, res) => {
  res.sendFile(__dirname + "/testOttobre/esercizio.html");
});

app.listen(port, () => console.log(`in ascolto alla porta ${port}`));
