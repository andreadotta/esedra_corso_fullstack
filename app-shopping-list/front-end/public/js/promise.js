const myPromise = new Promise((resolve, reject) => {
  setTimeout(() => {
    resolve('ho atteso 3000')
  }, 3000);
});

myPromise.then((result) => {
  console.log("Risultat 1" + result);
  return result;
}).then((result) => {
  console.log("Risultat 2" + result);
  return result;
})