import express from 'express';
import path from 'path';

const app = express();

const port = 3000;

app.use(express.static(path.dirname('') + '/public/'));

app.get('/', (req, res) => {
  res.sendFile(path.resolve(path.dirname('') + '/public/index.html'));
});


app.listen(port, () => console.log(`in ascolto alla porta ${port}`));