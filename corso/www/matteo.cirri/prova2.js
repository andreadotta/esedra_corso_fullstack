// Node.js program to demonstrate the 
// response.setHeaders() Method
  
// Importing http module
var http = require('http');
  
// Setting up PORT
const PORT = process.env.PORT || 3000;
  
// Creating http Server
var httpServer = http.createServer(
           function(req, response) {
  
  // Setting up Headers
  response.setHeader('Alfa', 'Beta');
  response.setHeader('Alfa1', '');
  response.setHeader('Alfa2', 5);
  response.setHeader('Cookie-Setup', 
       ['Alfa=Beta', 'Beta=Romeo']);
  // response.setHeader('', 'Beta'); // Throws Error
  // response.setHeader(); // Throws Error
  
  // Checking and  printing the headers
  console.log("When Header is set an Array:", 
  response.getHeader('Cookie-Setup'));
  console.log("When Header is set an 'Beta':", 
  response.getHeader('Alfa'));
  console.log("When Header is set '':", 
  response.getHeader('Alfa1'));
  console.log("When Header is set number 5:", 
  response.getHeader('alfa2'));
  console.log("When Header is not set:", 
  response.getHeader('Content-Type'));
  
  // Getting the set Headers
  const headers = response.getHeaders();
    
  // Printing those headers
  console.log(headers);
  
  var Output = "Hello Geeksforgeeks..., "
      + "Available headers are:"
      + JSON.stringify(headers); 
  
  // Prints Output on the browser in response
  response.write(Output);
  response.end('ok');
});
  
// Listening to http Server
httpServer.listen(PORT, () => {
    console.log("Server is running at port 3000...");
});