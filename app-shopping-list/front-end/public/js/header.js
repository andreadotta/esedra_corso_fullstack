import { html, render } from "https://unpkg.com/lit-html?module";

export default class Header extends HTMLElement {
  connectedCallback() {
    const shadow = this.attachShadow({ mode: "open" });
    const template = document.createElement("template");
    template.innerHTML = `<div id='header-content'></div>`;
    shadow.appendChild(template.content.cloneNode(true));
    this.content = shadow.getElementById("header-content");

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
    const template = html`
      <!-- Always shows a header, even in smaller screens. -->
      <div class="mdl-js-layout mdl-layout--fixed-header">
        <header class="mdl-layout__header">
          <div class="mdl-layout__header-row">
            <!-- Title -->
            <span class="mdl-layout-title">Title</span>
            <!-- Add spacer, to align navigation to the right -->
            <div class="mdl-layout-spacer"></div>
            <!-- Navigation. We hide it in small screens. -->
            <nav class="mdl-navigation mdl-layout--large-screen-only">
              <a class="mdl-navigation__link" href="">Link</a>
              <a class="mdl-navigation__link" href="">Link</a>
              <a class="mdl-navigation__link" href="">Link</a>
              <a class="mdl-navigation__link" href="">Link</a>
            </nav>
          </div>
        </header>
        <div class="mdl-layout__drawer">
          <span class="mdl-layout-title">Title</span>
          <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="">Link</a>
            <a class="mdl-navigation__link" href="">Link</a>
            <a class="mdl-navigation__link" href="">Link</a>
            <a class="mdl-navigation__link" href="">Link</a>
          </nav>
        </div>
        <main class="mdl-layout__content">
          <div class="page-content"><!-- Your content goes here --></div>
        </main>
      </div>
    `;
    render(template, this.content);

  }
}
customElements.define("app-header", Header);
