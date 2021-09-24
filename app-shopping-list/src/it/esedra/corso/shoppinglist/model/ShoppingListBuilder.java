package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;
import java.util.Collection;

public class ShoppingListBuilder {

	private Collection<Product> products;
	private BigInteger id;
	private String listName;
	private String uniqueCode;
	private User user;

	public ShoppingListBuilder(String listName) {
		this.listName = listName;
	}

	public ShoppingListBuilder() {

	}

	public static ShoppingListBuilder builder() {
		return new ShoppingListBuilder();
	}

	public ShoppingList build() {
		return new ShoppingList(id, listName, uniqueCode, user, products);
	}

	public ShoppingListBuilder id(BigInteger id) {
		this.id = id;
		return this;
	}

	public ShoppingListBuilder uniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
		return this;
	}

	public ShoppingListBuilder listName(String listName) {
		this.listName = listName;
		return this;
	}

	public ShoppingListBuilder user(User user) {
		this.user = user;
		return this;
	}

	public ShoppingListBuilder products(Collection<Product> products) {
		this.products = products;
		return this;
	}

}
