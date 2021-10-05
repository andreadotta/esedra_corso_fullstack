import { html, render } from "https://unpkg.com/lit-html?module";
import ShoppingList from "../components/shoppingList.js";
import LoadSpinner from "../components/spinner.js";

const template = document.createElement("template");
template.innerHTML = `<div id='content'></div>`;

export default class Home extends HTMLElement {
  constructor() {
    super();
    this.attachShadow({ mode: "open" });
    this.shadowRoot.appendChild(template.content.cloneNode(true));
    this.content = this.shadowRoot.getElementById("content");
    this.error = null;
    this.shoppingList = null;
  }

  connectedCallback() {
    async function getShoppingLists() {
      const data = await fetch("http://localhost:8081/shoppinglists");

      if (!data.ok) {
        throw new Error("Fetch shopping list error :" + data.status);
      }

      const shoppinglists = await data.json();

      return shoppinglists;
    }
    getShoppingLists()
      .then((data) => {
        this.error = null;
        this.shoppingList = data;
        this.render();
      })
      .catch((error) => {
        this.error = error;
        this.render();
      });
    this.render();
  }

  render() {
    if (this.error == null && this.shoppingList != null) {
      const template = this.shoppingList.map(
        (shoppingList) =>
          html`<shopping-list
            data="${JSON.stringify(shoppingList)}"
          ></shopping-list>`
      );
      render(template, this.content);
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
