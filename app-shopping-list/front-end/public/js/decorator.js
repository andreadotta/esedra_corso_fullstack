function logDecorator(f) {
  return function() {
     console.log("Inizio esecuzione");
     var result = f.apply(this, arguments);
     console.log("Fine esecuzione");
     return result;
  };
}

function somma(x, y) {
  return x + y;
}

var sommaConLog = logDecorator(somma);

console.log(sommaConLog(2, 3));
