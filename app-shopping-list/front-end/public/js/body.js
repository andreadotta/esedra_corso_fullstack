export default async function Body(data) {
  try {
    const shoppingLists = await data;
    return shoppingLists
      .map((shoppingList) => `<div>${shoppingList.listName}</div>`)
      .join("");
  } catch (error) {
    return `<div>${error}`;
  }
}
