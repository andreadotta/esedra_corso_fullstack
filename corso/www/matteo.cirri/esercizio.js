function promptNumber(question) {
    let a = (prompt(question));
    let b = Math.floor(getRandomArbitrary(0, 4));
    if (b == 0)
        throw new Error;
    console.log(a / b);
    
   

  }

  function getRandomArbitrary(min, max) {
    return Math.random() * (max - min) + min;
  }

  Math.floor(getRandomArbitrary(0, 4));

  try {
       promptNumber("Inserisci un numero");
       
  } catch (error) {
      console.log("errore" + error);
  }
 