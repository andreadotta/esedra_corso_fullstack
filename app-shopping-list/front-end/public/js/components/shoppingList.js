import {html, render} from 'https://unpkg.com/lit-html?module';

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
    const shoppingList =  JSON.parse(this.getAttribute('data'));
    render(html`<h2>${shoppingList.listName}</h2>`, this);
  }
}
customElements.define('shopping-list', ShoppingList);
