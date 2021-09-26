package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;

public class Product {
	private BigInteger id;
	private String name;
	private Unit unit;

	public static enum Fields {
		id, name, unit
	}

	public Product() {

	}

	/**
	 * TODO gestire la generazione dell'id
	 * 
	 * @param name
	 * @param qty
	 * @param unit
	 */

	public Product(BigInteger id, String name, Unit unit) {
		this.name = name;
		this.id = id;
		this.unit = unit;
	}

	public BigInteger getid() {
		return id;
	}

	public Unit getUnit() {
		return unit;
	}

	public String getName() {
		return name;
	}

}
