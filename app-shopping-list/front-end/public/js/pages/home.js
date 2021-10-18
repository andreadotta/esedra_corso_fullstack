import { html, render } from "https://unpkg.com/lit-html?module";
import ShoppingList from "../components/shoppingList.js";
import LoadSpinner from "../components/spinner.js";
import { thestate } from "../components/state.js";
const template = document.createElement("template");
template.innerHTML = `<div id='home-content'></div>`;

export default class Home extends HTMLElement {
  constructor() {
    super();
    const shadow = this.attachShadow({ mode: "open" });
    shadow.appendChild(template.content.cloneNode(true));
    this.content = shadow.getElementById("home-content");
    this.error = null;
    this.shoppingList = null;

    const linkElem = document.createElement("link");
    linkElem.setAttribute("rel", "stylesheet");
    linkElem.setAttribute(
      "href",
      "https://code.getmdl.io/1.3.0/material.indigo-pink.min.css"
    );
    shadow.appendChild(linkElem);

    const linkElemIc = document.createElement("link");
    linkElemIc.setAttribute("rel", "stylesheet");
    linkElemIc.setAttribute(
      "href",
      "https://fonts.googleapis.com/icon?family=Material+Icons"
    );
    shadow.appendChild(linkElemIc);
  }

  static get observedAttributes() {
    return ["sl"];
  }

  // Respond to attribute changes.
  attributeChangedCallback(attr, oldValue, newValue) {
    this.render();
  }

  async connectedCallback() {
    this.setAttribute("sl", null);

    const hanldeError = function (err) {
      console.log(err);
      return {
        ok: false,
        statusText: err.message,
      };
    };
    async function getShoppingLists() {
      const data = await fetch("http://localhost:8081/shoppinglists").catch(
        hanldeError
      );

      if (!data.ok) {
        return {
          status: "ko",
          result: null,
          message: "Fetch shopping list error :" + data.statusText,
        };
      }
      const shoppinglists = await data.json();
      return {
        status: "ok",
        result: shoppinglists,
        message: null,
      };
    }
    //thestate(this);

    this.setAttribute("sl", JSON.stringify(await getShoppingLists()));
  }

  render() {
    this.shoppingList = JSON.parse(this.getAttribute("sl"));
    if (this.shoppingList != null && this.shoppingList.status != "ko") {
      const template = this.shoppingList.result.map(
        (shoppingList) =>
          html`<div class="mdl-cell mdl-cell--4-col">
            <shopping-list
              data="${JSON.stringify(shoppingList)}"
            ></shopping-list>
          </div>`
      );
      render(html`<div class="mdl-grid">${template}</div>`, this.content);
    } else if (this.shoppingList == null) {
      render(html`<load-spinner />`, this.content);
    } else {
      const errorMessage = html`<div>
        Errore di visualizzazione: ${this.shoppingList.message}
      </div>`;
      render(errorMessage, this.content);
    }
  }
}
customElements.define("home-page", Home);
