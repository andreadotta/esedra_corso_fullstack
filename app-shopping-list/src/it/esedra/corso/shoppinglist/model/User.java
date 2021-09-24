package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esedra.corso.shoppinglist.exceptions.StoreException;
import it.esedra.corso.shoppinglist.helper.AESHelper;
import it.esedra.corso.shoppinglist.helper.SequenceManager;

/**
 * @author monica
 *
 */
public class User {
	private List<ShoppingList> shoppinglists = new ArrayList<ShoppingList>();
	private BigInteger userId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobilePhone;
	private boolean isActive = false;
	private boolean privacyConsent = false;
	private boolean newsletter = false;
	private String uniqueCode;
	private final static Logger logger = LoggerFactory.getLogger(User.class.getName());

	public User() {

	}

	public User(String firstName, String lastName, String email, String mobilePhone, boolean isActive,
			boolean privacyConsent, boolean newsletter) {
		this.userId = newUserId();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.isActive = isActive;
		this.privacyConsent = privacyConsent;
		this.newsletter = newsletter;
		setUniqueCode();
	}

	/**
	 * TODO fare un metodo per creare una shoppinglist collegata all'utente
	 * 
	 */

	public ShoppingList createShoppingList(String listName) {
		ShoppingListBuilder builder = new ShoppingListBuilder();
		builder.listName(listName).userId(this.userId);
		shoppinglists.add(builder.build());
		return builder.build();

	}

	public List<ShoppingList> getShoppinglists() {
		return shoppinglists;

	}

	public BigInteger getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public boolean isPrivacyConsent() {
		return privacyConsent;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode() {
		try {
			this.uniqueCode = AESHelper.generateUniqueKey(userId, email);
		} catch (StoreException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Get the new user ID
	 * 
	 * @return userID incrementato di 1 Sostituito getSequence() con newUserId()
	 */

	public BigInteger newUserId() {
		return userId = SequenceManager.getInstance().getCurrentIdUser();
	}

}
