package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListBuilder {

	private List<Product> products = new ArrayList<Product>();
	private String listName = new String();
	private User user;
	private BigInteger id;
	private String uniqueCode;

	public ShoppingListBuilder(List<Product> products, String listName, User user) {
		this.products = products;
		this.listName = listName;
		this.user = user;
	}

	public ShoppingListBuilder() {

	}

	public static ShoppingListBuilder builder() {
		return new ShoppingListBuilder();
	}

	public ShoppingList build() {
		return new ShoppingList(products, listName, user);
	}

	public User getUser() {
		return user;
	}

	public ShoppingListBuilder user(User user) {
		this.user = user;
		return this;
	}

	public ShoppingListBuilder id(BigInteger id) {
		this.id = id;
		return this;
	}

	public ShoppingListBuilder uniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
		return this;
	}

	public ShoppingListBuilder addProduct(Product product) {
		this.products.add(product);
		return this;
	}

	public ShoppingListBuilder listName(String listName) {
		this.listName = listName;
		return this;
	}

	public ShoppingListBuilder products(List<Product> products) {
		this.products = products;
		return this;
	}

}
