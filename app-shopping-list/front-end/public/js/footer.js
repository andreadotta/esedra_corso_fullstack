const style = `
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

export default class Footer extends HTMLElement {
  connectedCallback() {
    this.attachShadow({ mode: "open" });
    const elem = document.createElement("div");
    elem.innerHTML = `<div class="footer">
    <p>Footer</p>
  </div>
    <style>${style}</style>`;
    this.shadowRoot.appendChild(elem.cloneNode(true));
  }
}
customElements.define("app-footer", Footer);
