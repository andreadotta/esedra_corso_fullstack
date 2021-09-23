package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;

public class ProductBuilder {

	private String name;
	private Integer qty;
	private Unit unit;
	private static BigInteger id = new BigInteger("1");

	public ProductBuilder() {
		super();
	}

	public ProductBuilder(String name, Integer qty, Unit unit) {
		super();
		this.name = name;
		this.qty = qty;
		this.unit = unit;
	}

	public static ProductBuilder builder() {
		return new ProductBuilder();
	}

	public ProductBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public ProductBuilder setQty(Integer qty) {
		this.qty = qty;
		return this;
	}

	public ProductBuilder setUnit(Unit unit) {
		this.unit = unit;
		return this;
	}

	public ProductBuilder id(BigInteger id) {
		this.id = id;
		return this;
	}

	public Product build() {
		return new Product(name, qty, unit);
	}

}
