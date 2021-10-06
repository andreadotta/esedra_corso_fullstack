import { html, render } from "https://unpkg.com/lit-html?module";

export default class ShoppingList extends HTMLElement {
  constructor() {
    super();
  }
  static get observedAttributes() {
    return ["data"];
  }
  connectedCallback() {
      const shadow = this.attachShadow({ mode: "open" });
      const template = document.createElement("template");
      template.innerHTML = `<div id='content'></div>`;
      shadow.appendChild(template.content.cloneNode(true));
      this.content = shadow.getElementById("content");

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

      this.render();

  }

  render() {
    const shoppingList = JSON.parse(this.getAttribute("data"));
    render(
      html` <!-- Wide card with share menu button -->
        <style>
          .demo-card-wide {
            margin-top: 15px
          }
          .demo-card-wide.mdl-card {
            width: 512px;
          }
          .demo-card-wide > .mdl-card__title {
            color: #fff;
            height: 176px;
            background: url("https://i.etsystatic.com/12589513/r/il/204e59/1660783355/il_794xN.1660783355_21w6.jpg") center / cover;
          }
          .demo-card-wide > .mdl-card__menu {
            color: #fff;
          }
        </style>


        <div class="demo-card-wide mdl-card mdl-shadow--2dp">
          <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Welcome</h2>
          </div>
          <div class="mdl-card__supporting-text">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris
            sagittis pellentesque lacus eleifend lacinia...
          </div>
          <div class="mdl-card__actions mdl-card--border">
            <a
              class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
            >
            ${shoppingList.listName}
            </a>
          </div>
          <div class="mdl-card__menu">
            <button
              class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect"
            >
              <i class="material-icons">share</i>
            </button>
          </div>
        </div>
        `,
      this.content
    );
  }
}
customElements.define("shopping-list", ShoppingList);
