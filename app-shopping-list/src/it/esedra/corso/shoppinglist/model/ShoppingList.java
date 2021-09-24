package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esedra.corso.shoppinglist.exceptions.StoreException;
import it.esedra.corso.shoppinglist.helper.AESHelper;
import it.esedra.corso.shoppinglist.helper.SequenceManager;

public class ShoppingList {

	private List<Product> products = new ArrayList<Product>();
	private String listName;
	private BigInteger userId;
	private BigInteger id;
	private String uniqueCode;
	private final static Logger logger = LoggerFactory.getLogger(ShoppingList.class.getName());

	public ShoppingList(String listName, BigInteger userId) {
		this.id = newShoppingListId();
		this.listName = listName;
		this.userId = userId;
		setUniqueCode();

	}

	private ShoppingList() {

	}

	public BigInteger getUserId() {
		return userId;
	}

	public BigInteger getId() {
		return id;
	}

	public void setUniqueCode() {
		try {
			this.uniqueCode = AESHelper.generateUniqueKey(id, listName);
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

	public BigInteger newShoppingListId() {
		return id = SequenceManager.getInstance().getCurrentIdShoppingList();
	}
}
