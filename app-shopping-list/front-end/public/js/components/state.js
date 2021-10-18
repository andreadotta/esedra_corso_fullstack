export const thestate = (function (c) {
  const a = [];
  return function () { a.push(c); return a;}
});