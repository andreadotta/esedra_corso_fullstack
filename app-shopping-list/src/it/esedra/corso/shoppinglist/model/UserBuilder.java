package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class UserBuilder {
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
	
	public UserBuilder(String firstName, String lastName, String email, String mobilePhone, boolean isActive,
			boolean privacyConsent, boolean newsletter, String uniqueCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.isActive = isActive;
		this.privacyConsent = privacyConsent;
		this.newsletter = newsletter;
		this.uniqueCode = uniqueCode;
	}
	
	public UserBuilder() {
		
	}
	
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	public User build() {
		return new User(firstName, lastName, email, mobilePhone, isActive,
			privacyConsent, newsletter);
	}

	public UserBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserBuilder userId(BigInteger userId) {
		this.userId = userId;
		return this;
	}
	
	public UserBuilder lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserBuilder email(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder mobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public UserBuilder active(boolean isActive) {
		this.isActive = isActive;		
		return this;
	}

	public UserBuilder privacyConsent(boolean privacyConsent) {
		this.privacyConsent = privacyConsent;
		return this;
	}

	public UserBuilder newsletter(boolean newsletter) {
		this.newsletter = newsletter;
		return this;
	}
	
	public UserBuilder uniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
		return this;
	}
	
	
	
}
