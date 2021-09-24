package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;

public class ShoppingListProductBuilder {

	private BigInteger shoppingListId;
	private BigInteger productId;
	private Integer qty;

	public ShoppingListProductBuilder() {
		super();
	}

	public ShoppingListProductBuilder(BigInteger shoppingListId, BigInteger productId, Integer qty) {
		super();
		this.shoppingListId = shoppingListId;
		this.productId = productId;
		this.qty = qty;
	}

	public static ShoppingListProductBuilder builder() {
		return new ShoppingListProductBuilder();
	}

	public ShoppingListProductBuilder shoppingListId(BigInteger shoppingListId) {
		this.shoppingListId = shoppingListId;
		return this;
	}

	public ShoppingListProductBuilder productId(BigInteger productId) {
		this.productId = productId;
		return this;
	}

	public ShoppingListProductBuilder qty(Integer qty) {
		this.qty = qty;
		return this;
	}

	public ShoppingListProduct build() {
		return new ShoppingListProduct(shoppingListId, productId, qty);
	}

}
