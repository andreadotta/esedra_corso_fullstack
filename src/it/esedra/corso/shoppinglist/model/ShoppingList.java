package it.esedra.corso.shoppinglist.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.esedra.corso.shoppinglist.helper.GetFileResource;

/**
 * 
 *  Gli elementi di queso modello sono:
 *  Prodotti
 *  Nome
 *  Identificatio univoco numerico sequenziale
 *  Identificatore univoco pubblico alfanumerico
 *  (questo identificatore consente di non ricevere scanning
 *  o attacchi dall'esterno)
 *  Utente
 * 	
 * TODO: Implementare il campo "utente"
 * TODO: Implementare identificatore numerico BigInteger
 * TODO: Implementare l'identificatore alfanumenrico (String)
 *
 */

public class ShoppingList implements Persist {

	private List<Item> items = new ArrayList<Item>();
	private String listName = new String();
	
	public List<Item> getItems() {
		return items;
	}

	public String getListName() {
		return listName;
	}


	public void addItem(Item item) {
		this.items.add(item);
	}

	public void setListName(String listName) {
		this.listName = listName;
	}


	/**
	 * 
	 */
	public void store() throws IOException {

		try {

			PrintWriter writer = new PrintWriter(GetFileResource.get("lista.csv", "shoppinglist"));
			StringBuilder builder = new StringBuilder();
			for (Item listaTemp : items) {
				if (listaTemp == null) {
					continue;
				}
				builder.append(listaTemp.getName());
				builder.append(",");
				builder.append(listaTemp.getQty());
				builder.append(",");
				builder.append(listaTemp.getUnit());
				builder.append(",");
				builder.append(this.getListName());
				builder.append(System.getProperty("line.separator"));

			}

			writer.write(builder.toString());
			writer.flush();
			writer.close();

		} catch (Exception e) {
			throw new IOException();
		}

	}

}