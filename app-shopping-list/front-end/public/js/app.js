import Header from "./header.js";
import Footer from "./footer.js";
import ShoppingList from "./components/shoppingList.js";
import Spinner from "./components/spinner.js";
import Home from "./pages/home.js";
import Contact from "./pages/contact.js";

document.addEventListener("DOMContentLoaded", init, false);

function init() {
  var location = window.location.pathname;

  function getRoute(route) {
    switch (route) {
      case "/":
        new Home();
      case "/contact":
        new Contact();
    }
  }

  getRoute(location);


  console.log("Start App: (" + location + ")");
}
