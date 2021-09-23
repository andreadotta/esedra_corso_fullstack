package it.esedra.corso.shoppinglist.model;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esedra.corso.shoppinglist.exceptions.StoreException;
import it.esedra.corso.shoppinglist.helper.AESHelper;

/**
 * 
 * Gli elementi di queso modello sono: Prodotti Nome Identificatio univoco
 * numerico sequenziale Identificatore univoco pubblico alfanumerico (questo
 * identificatore consente di non ricevere scanning o attacchi dall'esterno)
 * Utente
 * 
 */

public class ShoppingList {

	private List<Product> products = new ArrayList<Product>();
	private String listName = new String();
	private User user;
	private BigInteger id;
	private String uniqueCode;
	private static final String fileName = "lista.csv";
	private static final String folderName = "shoppinglist";
	private static final String fieldSeparator = ",";
	private final static Logger logger = LoggerFactory.getLogger(ShoppingList.class.getName());
	
	public static enum Fields {
		listName, id, uniqueCode
	}
	/**
	 * TODO creare la relazione tra shoppinglist e user per gestire la creazione dell'id della shoppinglist
	 * 
	 * @param products
	 * @param listName
	 * @param user
	 * @param id
	 */

	public ShoppingList(List<Product> products, String listName, User user, BigInteger id) {
		this.products = products;
		this.listName = listName;
		this.user = user;
		this.id = id;
		setUniqueCode();

	}

	private ShoppingList() {

	}

	public User getUser() {
		return user;
	}

	public BigInteger getId() {
		return id;
	}

	public void setUniqueCode() {
		try {
			this.uniqueCode = AESHelper.generateUniqueKey(id,listName);
		} catch (StoreException e) {
			logger.error(e.getMessage());
		}
	}
	
	public String getUniqueCode() {
		return uniqueCode;
	}

	public List<Product> getProducts() {
		return products;
	}

	public String getListName() {
		return listName;
	}

	public static synchronized BigInteger getLastId() throws IOException {
		BigInteger lastId = null;
		return lastId;
	}	
}
