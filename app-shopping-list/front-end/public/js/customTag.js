export default class CustomTag extends HTMLElement {

  constructor() {
    super();
  }
  static get observedAttributes() {
    return ['name'];
  }
  connectedCallback(){
    if (!this.rendered) {
      this.render();
      this.rendered = true;
    }
  }

  render() {
    var container = document.createElement('div');
    container.innerHTML = this.getAttribute('name');
    this.append(container);
  }
}
customElements.define('custom-tag', CustomTag);
