import Header from "./header.js";
import Body from "./body.js";
import Footer from "./footer.js";
import ShoppingList from "./components/shoppingList.js";


document.addEventListener("DOMContentLoaded", init, false);

function init() {
  const bodyData = getShoppingLists()
    .then((data) => {
      renderPage(data);
    })
    .catch((error) => {
      renderErrorPage(error);
    });

  document.querySelector("body").innerHTML = '<app-header></app-header> waiting<app-footer></app-footer>';

  console.log("Start App");
}

function renderPage(data) {
  document.querySelector("body").innerHTML = '<app-header></app-header>' + Body(data) + '<app-footer></app-footer>';
}
function renderErrorPage(data) {
  document.querySelector("body").innerHTML = '<app-header></app-header>' + data + '<app-footer></app-footer>';
}

async function getShoppingLists() {
  /*   fetch("http://localhost:8081/shoppinglists")
    .then((response) => response.json())
    .then((data) => console.log(data));
 */
  const data = await fetch("http://localhost:8081/shoppinglists");

  if (!data.ok) {
    throw new Error("Fetch shopping list error :" + data.status);
  }

  const shoppinglists = await data.json();

  return shoppinglists;
}
