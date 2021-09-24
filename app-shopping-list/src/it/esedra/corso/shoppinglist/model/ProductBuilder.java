package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;

public class ProductBuilder {

	private String name;
	private Unit unit;
	private BigInteger id = null;

	public ProductBuilder() {
		super();
	}

	public ProductBuilder(BigInteger id, String name, Unit unit) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
	}

	public static ProductBuilder builder() {
		return new ProductBuilder();
	}

	public ProductBuilder name(String name) {
		this.name = name;
		return this;
	}

	public ProductBuilder unit(Unit unit) {
		this.unit = unit;
		return this;
	}

	public ProductBuilder id(BigInteger id) {
		this.id = id;
		return this;
	}

	public Product build() {
		return new Product(id, name, unit);
	}

}
