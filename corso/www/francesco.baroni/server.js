const express = require('express');

const app = express();


const port = 2220;

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/esercitazione16giugno.html');
});

app.listen(port, () => console.log(`Server esercitazione 16 giugno ${port}`));