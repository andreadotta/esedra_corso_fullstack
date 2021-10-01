export default function Body(shoppingLists) {
  try {
   
    return shoppingLists
      .map((shoppingList) => `<shopping-list data='${JSON.stringify(shoppingList)}'></shopping-list>`)
      .join("");
  } catch (error) {
    return `<div>Errore di visualizzazione</div>`;
  }
}
