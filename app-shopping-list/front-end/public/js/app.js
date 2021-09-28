import Header from './header.js';
import Body from './body.js';
import Footer from './footer.js';
document.addEventListener("DOMContentLoaded", init, false);
function init() {
  console.log("Start App");

  const bodyData = {
    name: "Roberto",
    city: "Florence"
  } 
  document.querySelector("body").innerHTML = Header() + Body(bodyData) + Footer();
}
