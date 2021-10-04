import express from 'express';
import path from 'path';
import dotenv from 'dotenv'; //per leggere il file di configurazione .env

dotenv.config(); //carico il file.env 
const app = express();

const port = process.env.PORT;


app.use(express.static(path.dirname('') + '/public/'));

app.get('/', (req, res) => {
  res.sendFile(path.resolve(path.dirname('') + '/public/index.html'));
});

app.get('/contact', (req, res) => {
  res.sendFile(path.resolve(path.dirname('') + '/public/contact.html'));
});

app.listen(port, () => console.log(`in ascolto alla porta ${port}`));