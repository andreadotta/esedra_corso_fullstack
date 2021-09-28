// Node.js program to demonstrate the 
// response.setHeaders() Method
  
// Importing http module
var http = require('http');
// Setting up PORT
const PORT = process.env.PORT || 3000;
  
// Creating http Server
var httpServer = http.createServer(
       function(request, response) {
  
  // Setting up Headers
  response.setHeader('Content-Type', 'text/html');
  response.setHeader('Set-Cookie', ['type=ninja', 
  'language=javascript']);
  
  // Checking and  printing the headers
  console.log("When Header is set a string:", 
  response.getHeader('Content-Type'));
  console.log("When Header is set an Array:", 
  response.getHeader('Set-Cookie'));
    
  // Getting the set Headers
  const headers = response.getHeaders();
    
  // Printing those headers
  console.log(headers);
  
  // Prints Output on the browser in response
  response.writeHead(200, 
    { 'Content-Type': 'text/plain' });
  response.end('ok');
});
  
// Listening to http Server
httpServer.listen(PORT, () => {
    console.log("Server is running at port 3000...");
});