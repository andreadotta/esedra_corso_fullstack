function BottoneConsole() {
  const [shoppingList, setShoppingList] = React.useState(null);

  async function getShoppingLists() {
    const data = await fetch("http://localhost:8081/shoppinglists");

    if (!data.ok) {
      return {
        status: "ko",
        result: null,
        message: "Fetch shopping list error :" + data.statusText,
      };
    }
    const shoppinglists = await data.json();
    return {
      status: "ok",
      result: shoppinglists,
      message: null,
    };
  }

  getShoppingLists().then((data) => setShoppingList(data.result));

  return (
    <div>
      <button onClick={() => console.log("print")}>
        Pulsante che printa in console
      </button>
      <div>
        {shoppingList != null ? (
          <ShoppingLists data={shoppingList}></ShoppingLists>
        ) : (
          <div></div>
        )}
      </div>
    </div>
  );
}

function ShoppingLists(props) {
  return (
    <div>
      {props.data.map((shoopingList, i) => {
        return (
          <div key={i}>
            {shoopingList.listName}
            <table className="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
              <thead>
                <tr>
                  <th className="mdl-data-table__cell--non-numeric">Id</th>
                  <th>Name</th>
                  <th>Unit</th>
                </tr>
              </thead>
              <tbody>
                <ShoppingListProductsRow data={shoopingList.products} />
              </tbody>
            </table>
          </div>
        );
      })}
    </div>
  );
}

function ShoppingListProductsRow(props) {
  return props.data.map((product, i) => {
    return (
      <tr key={i}>
        <td className="mdl-data-table__cell--non-numeric">{product.id}</td>
        <td>{product.name}</td>
        <td>{product.unit}</td>
      </tr>
    );
  });
}

const e = React.createElement;
const contenitoreDom = document.querySelector("#contenitore");
ReactDOM.render(e(BottoneConsole), contenitoreDom);
