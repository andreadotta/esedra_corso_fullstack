import Header from "./header.js";
import Body from "./body.js";
import Footer from "./footer.js";

document.addEventListener("DOMContentLoaded", init, false);

function init() {
  const bodyData = getShoppingLists();

  document.querySelector("body").innerHTML = Header() + Footer();

  Body(bodyData).then((body) => {
    document.querySelector("body").innerHTML = Header() + body + Footer();
  });

  console.log("Start App");
}

async function getShoppingLists() {
  /*   fetch("http://localhost:8081/shoppinglists")
    .then((response) => response.json())
    .then((data) => console.log(data));
 */
  const response = await fetch("http://localhost:8081/shoppinglists");

  if (!response.ok) {
    throw new Error("Fetch shopping list error :" + response.status);
  }

  const shoppinglists = await response.json();

  return shoppinglists;
}
