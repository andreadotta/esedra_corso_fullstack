package it.esedra.corso.shoppinglist.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.helper.GetFileResource;
import it.esedra.corso.shoppinglist.model.User.Fields;

public class UserDao implements Dao<User>{

	private static final String fileName = "user.csv";
	private static final String folderName = "shoppinglist";
	private static final String fieldSeparator = ",";
	private final static Map<String, Integer> fieldsMap;
	static {
		HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
		tmpMap.put(Fields.userId.name(), 0);
		tmpMap.put(Fields.firstName.name(), 1);
		tmpMap.put(Fields.lastName.name(), 2);
		tmpMap.put(Fields.email.name(), 3);
		tmpMap.put(Fields.mobilePhone.name(), 4);
		tmpMap.put(Fields.isActive.name(), 5);
		tmpMap.put(Fields.isPrivacyConsent.name(), 6);
		tmpMap.put(Fields.isNewsletter.name(), 7);
		fieldsMap = Collections.unmodifiableMap(tmpMap);

	}
	/**
	 * TODO Implementare Parte per Update
	 */
	@Override
	public void save(User t) throws DaoException {
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(GetFileResource.get(fileName, folderName).toPath().toString(), true));
			StringBuilder builder = new StringBuilder();
			if ((t.getUserId().equals(BigInteger.ONE) || t.getUserId().compareTo(User.getLastId()) > 0)
					&& !find(t).contains(t)) {
				builder.append(t.getUserId());
				builder.append(fieldSeparator);
				builder.append(t.getFirstName());
				builder.append(fieldSeparator);
				builder.append(t.getLastName());
				builder.append(fieldSeparator);
				builder.append(t.getEmail());
				builder.append(fieldSeparator);
				builder.append(t.getMobilePhone());
				builder.append(fieldSeparator);
				builder.append(t.isActive());
				builder.append(fieldSeparator);
				builder.append(t.isPrivacyConsent());
				builder.append(fieldSeparator);
				builder.append(t.isNewsletter());
				builder.append(fieldSeparator);
				builder.append(System.getProperty("line.separator"));
				writer.write(builder.toString());
				writer.flush();
				writer.close();
				System.out.println("User " + t.getFirstName() + " salvato");
				t.addToStoredUsers(t);
			} else {
//				Implementazione del blocco per l'update o chiamata ad un metodo apposito
				System.out.println("no user stored or updated!");
			}
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public User get(BigInteger id) throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());
			for (String line : lines) {
				String[] fields = line.split(fieldSeparator);
				BigInteger tmpUserId = new BigInteger(fields[fieldsMap.get(Fields.userId.name())]);
				if (tmpUserId.equals(id)) {
					return UserBuilder.builder().firstName(fields[fieldsMap.get(Fields.firstName.name())])
							.lastName(fields[fieldsMap.get(Fields.lastName.name())])
							.email(fields[fieldsMap.get(Fields.email.name())])
							.mobilePhone(fields[fieldsMap.get(Fields.mobilePhone.name())])
							.active(Boolean.parseBoolean(fields[fieldsMap.get(Fields.isActive.name())]))
							.privacyConsent(Boolean.parseBoolean(fields[fieldsMap.get(Fields.isPrivacyConsent.name())]))
							.userId(tmpUserId)
							.newsletter(Boolean.parseBoolean(fields[fieldsMap.get(Fields.isNewsletter.name())]))
							.build();

				}
			}

			return null;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}
	/**
	 * TODO Implementare Delete
	 */
	@Override
	public void delete(BigInteger id) throws DaoException {
		File db = null;
		File dbclone = null;
		try {
			//cerco tutti i shopping list
			SortedSet<User> users = this.find(new UserBuilder().build());
			//rinominiamo il file
			//prendo il file del db
			db = new File(GetFileResource.get(fileName, folderName).toPath().toString());
			//clono il file del db
			dbclone = new File(GetFileResource.get(fileName, folderName).toPath().toString() + ".temp");
			// ma prima verifico che non esista già
			if (dbclone.exists()) {
				dbclone.delete();
			}
			// clono effettettivamente il file del db
			db.renameTo(dbclone);
			///elimino il file del db
			db.delete();
			//lo riscrivo da zero
			for (User line : users) {
				//se il id corrisponde a quello in input non lo scrivo
				if (!line.getUserId().equals(id)) {
					this.save(line);
				}				
			}
		} catch (Exception e) {
			//cancello il file nuovo del db 
			db.delete();
			//ripristino il vecchio file del db
			dbclone.renameTo(db);
			throw new DaoException(e.getMessage());
		} finally {
			//eliminio il clone che avevo fatto per salvare i dati in caso di errore
			dbclone.delete();
		}
	}

	@Override
	public SortedSet<User> find(User t) throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());
			SortedSet<User> users = new TreeSet<User>();
			for (String line : lines) {
				String[] fields = line.split(fieldSeparator);
				if (!fields[fieldsMap.get(Fields.userId.name())].equals("")) {

					users.add(UserBuilder.builder().firstName(fields[fieldsMap.get(Fields.firstName.name())])
							.lastName(fields[fieldsMap.get(Fields.lastName.name())])
							.email(fields[fieldsMap.get(Fields.email.name())])
							.mobilePhone(fields[fieldsMap.get(Fields.mobilePhone.name())])
							.active(Boolean.parseBoolean(fields[fieldsMap.get(Fields.isActive.name())]))
							.privacyConsent(Boolean.parseBoolean(fields[fieldsMap.get(Fields.isPrivacyConsent.name())]))
							.newsletter(Boolean.parseBoolean(fields[fieldsMap.get(Fields.isNewsletter.name())]))
							.userId(new BigInteger(fields[fieldsMap.get(Fields.userId.name())])).build());
				}
			}
			return users;
		} catch (IOException e) {
			throw new DaoException(e.getMessage());
		}
	}

}
