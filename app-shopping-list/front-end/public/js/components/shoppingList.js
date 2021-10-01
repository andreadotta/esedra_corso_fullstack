export default class ShoppingList extends HTMLElement {

  constructor() {
    super();
  }
  static get observedAttributes() {
    return ['data'];
  }
  connectedCallback(){
    if (!this.rendered) {
      this.render();
      this.rendered = true;
    }
  }


  render() {
    var container = document.createElement('div');
    const shoppingList =  JSON.parse(this.getAttribute('data'));
    container.innerHTML = `<h2>${shoppingList.listName}</h2>`;
    this.append(container);
  }
}
customElements.define('shopping-list', ShoppingList);
