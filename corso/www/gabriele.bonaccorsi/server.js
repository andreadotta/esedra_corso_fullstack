const express = require('express');

const app = express();


const port = 1312;

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/test.html');
});

app.listen(port, () => console.log(`Server di Gabriele ${port}`));