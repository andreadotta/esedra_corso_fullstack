import Header from "./header.js";
import Footer from "./footer.js";
import ShoppingList from "./components/shoppingList.js";
import Spinner from "./components/spinner.js";
import Home from "./pages/home.js";
import Contact from "./pages/contact.js";
import { html, render } from "https://unpkg.com/lit-html?module";

document.addEventListener("DOMContentLoaded", init, false);

function init() {
  var location = window.location.pathname;

  function getRoute(route) {
    var routes = {
      "/": html`<home-page />`,
      "/contact": html`<contact-page />`,
    };
    return routes[route] || routes["/"];
  }

  render(getRoute(location), document.querySelector("app-page"));

  console.log("Start App: (" + location + ")");
}
