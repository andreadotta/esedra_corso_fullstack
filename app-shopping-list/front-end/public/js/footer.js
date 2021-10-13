import { html, render } from "https://unpkg.com/lit-html?module";

export default class Footer extends HTMLElement {
  static style = `
  .footer {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    background-color: gray;
    color: white;
    text-align: center;
  }
  `;

  constructor() {
    super();
    this.attachShadow({ mode: "open" });
    const elem = document.createElement("div");
    const template = html`<div class="footer">
        <p>Footer</p>
      </div>
      <style>
        ${Footer.style}
      </style>`;
    this.shadowRoot.appendChild(elem);

    render(template, elem);
  }
}
customElements.define("app-footer", Footer);


