package it.esedra.corso.shoppinglist.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import it.esedra.corso.shoppinglist.helper.GetFileResource;
import it.esedra.corso.shoppinglist.helper.SequenceManager;

/**
 * @author monica
 *
 */
public class User implements Persist, Comparable<User> {
	private static BigInteger id = new BigInteger("0");
	private BigInteger userId = id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobilePhone;
	private boolean isActive = false;
	private boolean privacyConsent = false;
	private boolean newsletter = false;
	private Map<BigInteger, User> storedUsers = new HashMap<>();

  private final static String[] campi = {		
			"userId",
			"firstName",
			"lastName",
			"email",
			"mobilePhone",
			"isActive",
			"isPrivacyConsent",
			"isNewsletter",
	};
	private final static HashMap<String, String> fieldsMap = new HashMap<>() ;
	static {		
		for(int i = 0 ; i < campi.length; i++) {
			fieldsMap.put(campi[i],null ); // mi aggiunge alla mappa tutti i campi con le chiavi null così dopo posso richiamare i campi e rimpiazzare il null con la chiave desiderata
		}
	}
	
	

	public User() {
		
	}
	
	public User(String firstName, String lastName, String email, String mobilePhone, boolean isActive, boolean privacyConsent, boolean newsletter) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.isActive = isActive;
		this.privacyConsent = privacyConsent;
		this.newsletter = newsletter;
	}
	
	/**
	 * 
	 * @return new User()
	 * restituisce un'istanza di User con i parametri nell'ordine
	 */
	public User build() {
		return new User(firstName, lastName, email, mobilePhone, isActive, privacyConsent, newsletter);
	}

	public BigInteger getUserId() {
		return userId;
	}

	public User setUserId(BigInteger userId) {
		this.userId = userId;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public boolean isPrivacyConsent() {
		return privacyConsent;
	}
	
	public User setPrivacyConsent(boolean privacyConsent) {
		this.privacyConsent = privacyConsent;
		return this;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public User setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public boolean isActive() {
		return isActive;
	}

	public User setActive(boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public User setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
		return this;
	}
	
	/**
	 * 
	 * @return userID incrementato di 1
	 * 
	 * Testato l'incremento: il secondo user ha userId = 2;
	 */
	
	public BigInteger getSequence() {
		id = id.add(BigInteger.ONE);
		return userId = id ;

	public Map<BigInteger, User> getStoredUsers() {
		return storedUsers;
	}

	/**
	 * 
	 * @return userID incrementato di 1
	 *  Sostituito getSequence() con newUserId()
	 */
	
	public BigInteger newUserId() {
		return userId = SequenceManager.newIdUser();
	}
	
	public Map<BigInteger, User> addToStoredUsers (User user) {
		storedUsers.put(user.getUserId(), user);
		return storedUsers;
	}
	

	/**
	 * 
	 * @return l'id piÃ¹ in alto assegnato
	 * @throws IOException
	 */
	
	public synchronized static BigInteger getLastId() throws IOException{
		try {					
			BigInteger lastId = (getAll().isEmpty())? SequenceManager.newIdUser() :  getAll().last().getUserId();
			return lastId;			
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
	}
    

	
	
	public void aggiornaMappa(String[] fields) {
		
		for(int i = 0 ; i < campi.length; i++) {
			fieldsMap.replace(campi[i],fields[i] );
		}

	}
	
	/**
	 * Restituisce un nuovo oggetto User
	 * 
	 * 
	 * @return
	 * @throws IOException
	 */
	
	/**
	 * 
	 * Modifica: aggiunto blocco try/catch per gestire l'errore ed evitare l'uso del blocco
	 * nella classe TestUser.
	 * 
	 * Modificato return (era "new User()" vuoto)
	 * 
	 * Modificato Il BufferedReader con il metodo statico Files.readAlLines(path) che restituisce
	 * una List di String ciascuna con una linea. Utilizzato un ciclo for migiorato per la ricerca
	 * dello User nel file user.csv.
	 * 
	 * Modifica: aggiunta di un argomento BigInteger findId per la ricerca: se il file Ã¨ vuoto, 
	 * il campo tmpUserId assume un valore BigInteger.zeroLenght e da luogo ad un flusso non gestito;
	 * 
	 */
	
	public User get(BigInteger findId) throws IOException {		
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get("user.csv", "shoppinglist").toPath());
			User user = null;
			for(String line:lines) {
				String[] fields = line.split(",") ;				
				aggiornaMappa(fields);
				BigInteger tmpUserId = new BigInteger(fieldsMap.get("userId"));
				if (tmpUserId.equals(findId)) {
					user = new User();					
					user.setFirstName(fieldsMap.get("firstName"));
					user.setLastName(fieldsMap.get("lastName"));
					user.setEmail(fieldsMap.get("email"));					
					user.setMobilePhone(fieldsMap.get("mobilePhone"));
					user.setActive(Boolean.parseBoolean(fieldsMap.get("isActive")));
					user.setPrivacyConsent(Boolean.parseBoolean(fieldsMap.get("isPrivacyConsent")));
					user.setNewsletter(Boolean.parseBoolean(fieldsMap.get("isNewsletter")));					
					user.setUserId(tmpUserId);
				}
			}
			return user;
		} catch (Exception e) {
			throw new IOException();
		}
				
	}
	
	/**
	 * Salva un oggetto user se non  registrato il suo userId
	 */
  
	public void store() throws IOException {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(GetFileResource.get("user.csv", "shoppinglist").toPath().toString(), true));
			StringBuilder builder = new StringBuilder();
			if ((this.getUserId().equals(BigInteger.ONE) || this.getUserId().compareTo(getLastId()) > 0) && !getAll().contains(this)) {
				builder.append(this.getUserId());
				builder.append(",");
				builder.append(this.getFirstName());
				builder.append(",");
				builder.append(this.getLastName());
				builder.append(",");
				builder.append(this.getEmail());
				builder.append(",");
				builder.append(this.getMobilePhone());
				builder.append(",");
				builder.append(this.isActive());
				builder.append(",");
				builder.append(this.isPrivacyConsent());
				builder.append(",");
				builder.append(this.isNewsletter());
				builder.append(",");
				builder.append(System.getProperty("line.separator"));
				writer.write(builder.toString());
				writer.flush();
				writer.close();
				System.out.println("User " + this.getFirstName() + " salvato");
				addToStoredUsers(this);
			} else {
//				Implementazione del blocco per l'update o chiamata ad un metodo apposito
				System.out.println("no user stored or updated!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException();
		}
	}

	@Override
	public int compareTo(User o) {
		return o.getUserId().compareTo(userId);
	}

	public void updateUser() {
		
	}
	
	public void deleteUser() {
		
	}
	
	@Override
	public int compareTo(User o) {
		return o.getUserId().compareTo(userId);
	}

}
