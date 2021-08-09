package it.esedra.corso.shoppinglist.test.unit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.helper.GetFileResource;
import it.esedra.corso.shoppinglist.model.User;
import it.esedra.corso.shoppinglist.model.UserBuilder;
import it.esedra.corso.shoppinglist.model.UserDao;

public class TestUser {

	public static void main(String[] args) throws IOException {
		TestUser.save();
		TestUser.delete();
	}

	public static void save() throws IOException {
		try {
			StringBuilder sb = new StringBuilder();
			InputStream inputUser = new FileInputStream(GetFileResource.get("user.json", "test"));
			User user = null;
			int i;
			while ((i = inputUser.read()) != -1) {
				sb.append((char) i);
			}
			String jsonStr = sb.toString();

			JsonReader reader = Json.createReader(new StringReader(jsonStr));
			JsonObject userJson = reader.readObject();
			JsonArray userArr = userJson.get("users").asJsonArray();
			UserDao userDao = new UserDao();
			for (Object el : userArr) {
				JsonObject tmpUser = (JsonObject) (el);

				user = UserBuilder.builder().firstName(tmpUser.getString("firstName"))
						.lastName(tmpUser.getString("lastName")).email(tmpUser.getString("email"))
						.mobilePhone(tmpUser.getString("mobilePhone"))
						.active(Boolean.parseBoolean(tmpUser.getString("isActive")))
						.privacyConsent(Boolean.parseBoolean(tmpUser.getString("isPrivacyConsent")))
						.newsletter(Boolean.parseBoolean(tmpUser.getString("isNewsletter"))).build();
				user.newUserId();

				try {
					userDao.save(user);
				} catch (DaoException e) {
					e.printStackTrace();
				}
				Map<BigInteger, User> users = user.getStoredUsers();
				for (Map.Entry<BigInteger, User> entry : users.entrySet()) {
					System.out.println("User id: " + entry.getKey() + " - Nome: " + entry.getValue().getFirstName());
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void delete() throws IOException {
		try {

			UserDao userDao = new UserDao();

			userDao.delete(new BigInteger("3"));
//				Map<BigInteger, User> users = user.getStoredUsers();
//				for(Map.Entry<BigInteger, User> entry: users.entrySet()) {
//					System.out.println("User id: " + entry.getKey() + " - Nome: " + entry.getValue().getFirstName());
//				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
