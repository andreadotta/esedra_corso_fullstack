package it.esedra.corso.shoppinglist.model;

public class ShoppingListBuilder {

	private String listName;

	public ShoppingListBuilder(String listName) {
		this.listName = listName;
	}

	public ShoppingListBuilder() {

	}

	public static ShoppingListBuilder builder() {
		return new ShoppingListBuilder();
	}

	public ShoppingList build() {
		return new ShoppingList(listName);
	}

	public ShoppingListBuilder listName(String listName) {
		this.listName = listName;
		return this;
	}

}
