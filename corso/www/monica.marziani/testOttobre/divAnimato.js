import { html, render } from "https://unpkg.com/lit-html?module";

let divAnimato = html`<div
  id="cliccami"
  style="
            background-color: green;
            width: 150px;
            height: 150px;
            margin: 0 auto;
            margin-top: 300px;
          "
></div>`;
class DivAnimato extends HTMLElement {
  constructor() {
    super();
    const shadow = this.attachShadow({ mode: "open" });
    const template = document.createElement("template");
    template.innerHTML = `<div id=container></div>`;
    shadow.appendChild(template.content.cloneNode(true));
    this.content = shadow.getElementById("container");
    this.render();
  }
  connectedCallback() {
    let anim = this.shadowRoot.getElementById("cliccami");
    let changeColor = function (e) {
      console.log("click!");
      this.style.backgroundColor = "red";
    };
    anim.addEventListener("click", changeColor);
  }
  render() {
    render(divAnimato, this.content);
  }
}
customElements.define("div-animato", DivAnimato);
