package it.esedra.corso.shoppinglist.model;

public class UserBuilder {
	private String firstName;
	private String lastName;
	private String email;
	private String mobilePhone;
	private boolean isActive = false;
	private boolean privacyConsent = false;
	private boolean newsletter = false;

	public UserBuilder(String firstName, String lastName, String email, String mobilePhone, boolean isActive,
			boolean privacyConsent, boolean newsletter) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.isActive = isActive;
		this.privacyConsent = privacyConsent;
		this.newsletter = newsletter;
	}

	public UserBuilder() {

	}

	public static UserBuilder builder() {
		return new UserBuilder();
	}

	public User build() {
		return new User(firstName, lastName, email, mobilePhone, isActive, privacyConsent, newsletter);
	}

	public UserBuilder firstName(String firstName) {
		this.firstName = firstName;
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

}
