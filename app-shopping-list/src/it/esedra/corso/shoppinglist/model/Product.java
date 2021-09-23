package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;

/**
 * Gli elementi di queso modello sono: Descrizione Quantità Prezzo Unità
 * Identificativo univoco numerico sequenziale TODO: valutare se rinominare in
 * "Product" TODO: valutare l'utlizzo di un'enum al posto di "unit" TODO:
 * Implementare identificatore numerico BigInteger,
 */

public class Product {
	private ShoppingList shoppinglist;
	private String name;
	private Integer qty;
	private Unit unit;
	private static BigInteger id = new BigInteger("1");

	public static enum Fields {
		name, qty, unit
	}
	
	public Product () {
		
	}
	
	/**
	 * TODO gestire la generazione dell'id
	 * @param name
	 * @param qty
	 * @param unit
	 */
	public Product (String name, Integer qty, Unit unit) {
		this.name = name;
		this.qty = qty;
		this.unit = unit;
	}

	public BigInteger getSequence() {
		return id.add(new BigInteger("1"));
	}

	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty =qty;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit =unit;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name =name;
	}

	/**
	 * Restituisce un nuovo oggetto Product
	 * 
	 * 
	 * @return
	 * @throws IOException
	 */
//	public Product get() throws IOException {
//		
//		BufferedReader br = Files.newBufferedReader(GetFileResource.get("lista.csv", "shoppinglist").toPath());
//		
//		
//		String line = br.readLine();
//		
//		Product product = null;
//		
//		while (line != null) {
//			String[] fields = line.split(",");
//			BigInteger tmpId = new BigInteger(fields[4]);
//			if (tmpId.equals(this.getId())) {
//				product = new Product();
//				product.setName(fields[0]);
//				product.setQty(Integer.parseInt(fields[1]));
//				product.setUnit(Unit.valueOf(fields[2]));
//				product.setDescription(fields[3]);
//				product.setId(tmpId);
//			}	
//		}
//		return new Product();
//	}
}
