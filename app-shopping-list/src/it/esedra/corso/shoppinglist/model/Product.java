package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;

/**
 * Entity Gli elementi di queso modello sono: Descrizione Quantità Prezzo Unità
 * Identificativo univoco numerico sequenziale TODO: Implementare identificatore
 * numerico BigInteger,
 */

public class Product {
	private BigInteger id;
	private String name;
	private Unit unit;
	public static enum Fields {
		id, name, unit
	}

	
  public Product() {}
  
	public Product(String name, Integer qty, Unit unit) {
		this.name = name;
		this.qty = qty;
		this.unit = unit;
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
	
	public int hashCode() {
		return id.intValue();
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}


}
