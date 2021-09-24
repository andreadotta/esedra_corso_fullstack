package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;

public class ShoppingListBuilder {

	private String listName;
	private BigInteger userId;

	public ShoppingListBuilder(String listName) {
		this.listName = listName;
	}

	public ShoppingListBuilder() {

	}

	public static ShoppingListBuilder builder() {
		return new ShoppingListBuilder();
	}

	public ShoppingList build() {
		return new ShoppingList(listName, userId);
	}

	public ShoppingListBuilder listName(String listName) {
		this.listName = listName;
		return this;
	}

	public ShoppingListBuilder userId(BigInteger userId) {
		this.userId = userId;
		return this;
	}

}
