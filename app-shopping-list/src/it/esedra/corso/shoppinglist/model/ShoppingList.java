package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esedra.corso.shoppinglist.exceptions.StoreException;
import it.esedra.corso.shoppinglist.helper.AESHelper;
import it.esedra.corso.shoppinglist.helper.SequenceManager;

public class ShoppingList {

	private Collection<Product> products = new ArrayList<Product>();
	private String listName;
	private BigInteger id;
	private String uniqueCode;
	private User user;

	private final static Logger logger = LoggerFactory.getLogger(ShoppingList.class.getName());

	public ShoppingList(BigInteger id, String listName, String uniqueCode, User user, Collection<Product> products) {
		this.id = id;
		this.listName = listName;
		this.user = user;
		this.uniqueCode = uniqueCode;
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Collection<Product> getProducts() {
		return products;
	}

	public String getListName() {
		return listName;
	}

	public BigInteger newShoppingListId() {
		return id = SequenceManager.getInstance().getCurrentIdShoppingList();
	}
}
