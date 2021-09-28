const express = require('express');

const app = express();


const port = 3000;

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html'); 
});

app.get('/pagina1.html', (req, res) => {
  res.sendFile(__dirname+'/pagina1.html');
});

app.get('/pagina2.html', (req, res) => {
  res.sendFile(__dirname+'/pagina2.html');
});

app.get('/pagina3.html', (req, res) => {
  res.sendFile(__dirname+'/pagina3.html');
});


app.listen(port, () => console.log(`in ascolto alla porta ${port} nella cartella `+__dirname));
