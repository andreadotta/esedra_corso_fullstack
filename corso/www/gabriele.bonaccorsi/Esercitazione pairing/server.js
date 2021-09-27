const express = require('express');

const app = express();


const port = 2000;

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/spesa.html');
});

app.listen(port, () => console.log(`Server di esercitazione pairing ${port}`));