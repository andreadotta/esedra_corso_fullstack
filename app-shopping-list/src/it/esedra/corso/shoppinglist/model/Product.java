package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;

/**
 * Gli elementi di queso modello sono: Descrizione Quantità Prezzo Unità
 * Identificativo univoco numerico sequenziale TODO: valutare se rinominare in
 * "Product" TODO: valutare l'utlizzo di un'enum al posto di "unit" TODO:
 * Implementare identificatore numerico BigInteger,
 */

public class Product {
	private String name;
	private Integer qty;
	private Unit unit;

	public static enum Fields {
		name, qty, unit
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
	public Product(String name, Integer qty, Unit unit) {
		this.name = name;
		this.qty = qty;
		this.unit = unit;
	}

	public Integer getQty() {
		return qty;
	}

	public Unit getUnit() {
		return unit;
	}

	public String getName() {
		return name;
	}

}
