var http = require('http');

var server = http.createServer(function(req, res){
    res.statusode = 200
    res.setHeader('Content-type', 'text/plain');
    res.end('Hello Node!')
});

server.listen(3000, '127.0.0.1');
console.log('Server run at http://127.0.0.1:3000');