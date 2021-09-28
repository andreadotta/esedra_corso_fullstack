const express = require('express');

const app = express();


const port = 3000;

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

app.get('/oggetti', (req, res) => {
  res.sendFile(__dirname + '/oggetti.html');
});

app.get('/array', (req, res) => {
  res.sendFile(__dirname + '/array.html');
});



app.listen(port, () => console.log(`in ascolto alla porta ${port}`));