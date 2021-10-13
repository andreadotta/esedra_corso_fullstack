import { html, render } from "https://unpkg.com/lit-html?module";
import ShoppingList from "../components/shoppingList.js";
import LoadSpinner from "../components/spinner.js";

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

  connectedCallback() {
    async function getShoppingLists() {
      const data = await fetch("http://localhost:8081/shoppinglists");
      if (!data.ok) {
        return {
          status: "ko",
          result: null,
          message: "Fetch shopping list error :" + data.status,
        };
      }
      const shoppinglists = data.json();
      return {
        status: "ok",
        result: await shoppinglists,
        message: null,
      };
    }
    getShoppingLists().then((e) => {
      this.shoppingList = e.result;
      this.render();
    });
    this.render();
  }

  render() {
    if (this.error == null && this.shoppingList != null) {
      const template = this.shoppingList.map(
        (shoppingList) =>
          html`<div class="mdl-cell mdl-cell--4-col">
            <shopping-list
              data="${JSON.stringify(shoppingList)}"
            ></shopping-list>
          </div>`
      );
      render(html`<div class="mdl-grid">${template}</div>`, this.content);
    } else if (this.error == null && this.shoppingList == null) {
      render(html`<load-spinner />`, this.content);
    } else {
      const errorMessage = html`<div>
        Errore di visualizzazione: ${this.error}
      </div>`;
      render(errorMessage, this.content);
    }
  }
}
customElements.define("home-page", Home);
