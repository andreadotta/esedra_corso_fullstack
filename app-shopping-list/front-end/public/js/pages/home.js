export default function Home() {
  function renderPage(data) {
    document.querySelector("page").innerHTML = RenderBody(data);
  }
  function renderErrorPage(data) {
    document.querySelector("page").innerHTML = data;
  }

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
      renderPage(data);
    })
    .catch((error) => {
      renderErrorPage(error);
    });
}

function RenderBody(shoppingLists) {
  try {
    return shoppingLists
      .map(
        (shoppingList) =>
          `<shopping-list data='${JSON.stringify(
            shoppingList
          )}'></shopping-list>`
      )
      .join("");
  } catch (error) {
    return `<div>Errore di visualizzazione</div>`;
  }
}
