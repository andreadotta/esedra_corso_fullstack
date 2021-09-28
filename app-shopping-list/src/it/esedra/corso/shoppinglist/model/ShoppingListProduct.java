package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;

public class ShoppingListProduct {
	private BigInteger shoppingListId;
	private BigInteger productId;
	private Integer qty;

	public ShoppingListProduct() {

	}

	public ShoppingListProduct(BigInteger shoppingListId, BigInteger productId, Integer qty) {
		this.shoppingListId = shoppingListId;
		this.productId = productId;
		this.qty = qty;
	}

	public BigInteger getShoppingListId() {
		return shoppingListId;
	}

	public BigInteger getProductId() {
		return productId;
	}

	public Integer getQty() {
		return qty;
	}

	public boolean equals(Object obj) {
		if (obj instanceof ShoppingListProduct) {
			var slp = (ShoppingListProduct) obj;
			return shoppingListId.equals(slp.productId);
		} else {
			return false;
		}
		
	}
	


}
