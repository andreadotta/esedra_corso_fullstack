package it.esedra.corso.shoppinglist.helper;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.commons.lang3.EnumUtils;

import it.esedra.corso.shoppinglist.exceptions.ValidateException;
import it.esedra.corso.shoppinglist.model.ShoppingListDao;
import it.esedra.corso.shoppinglist.model.Unit;

public class ValidateHelper {

	public static void validateShoppingList(String params) throws ValidateException {
		JsonReader reader = Json.createReader(new StringReader(params));
		JsonObject listaSpesaObject = reader.readObject();
		try {
			Integer.parseInt(listaSpesaObject.get(ShoppingListDao.Fields.id.name()).toString());
		} catch (NumberFormatException e) {
			throw new ValidateException("L'id inserito non � un intero");
		}

		JsonArray items = listaSpesaObject.get("products").asJsonArray();

		for (Object o : items) {
			JsonObject tmpObj = (JsonObject) o;

			try {
				Integer.parseInt(tmpObj.get("qty").toString().replaceAll("^.|.$", ""));
			} catch (NumberFormatException e) {
				throw new ValidateException("La quantit� inserita non � un intero");
			}

			if (!EnumUtils.isValidEnum(Unit.class, tmpObj.get("unit").toString().replaceAll("^.|.$", ""))) {
				throw new ValidateException("L'unit� di misura non � corretta");
			}

			if (ValidateHelper.validateProductName(tmpObj.get("name").toString().replaceAll("^.|.$", ""))) {
				throw new ValidateException("Non � un nome valido per un prodotto");
			}
		}

		if (ValidateHelper.validateListName(listaSpesaObject
				.get(ShoppingListDao.Fields.listName.name()).toString().replaceAll("^.|.$", "").replace(" ", ""))) {
			throw new ValidateException("Non � un nome valido per una lista");
		}

	}

	public static void validateUser(String params) throws ValidateException {
		JsonReader reader = Json.createReader(new StringReader(params));
		JsonObject userJson = reader.readObject();
		JsonArray userArr = userJson.get("users").asJsonArray();
		for (Object el : userArr) {
			JsonObject tmpUser = (JsonObject) (el);
			if (ValidateHelper.validateUserName(tmpUser.get("firstName").toString().replaceAll("^.|.$", ""))) {
				throw new ValidateException("Non � un nome valido per un utente");
			}
			if (ValidateHelper.validateUserName(tmpUser.get("lastName").toString().replaceAll("^.|.$", ""))) {
				throw new ValidateException("Non � un cognome valido per un utente");
			}

			if (ValidateHelper.validateEmail(tmpUser.get("email").toString().replaceAll("^.|.$", ""))) {
				throw new ValidateException("Non � un'email valida");
			}

			try {
				Integer.parseInt(tmpUser.get("mobilePhone").toString().replaceAll("^.|.$", ""));
			} catch (NumberFormatException e) {
				throw new ValidateException("La quantit� inserita non � un intero");
			}

			if (Boolean.parseBoolean(tmpUser.get("isActive").toString().replaceAll("^.|.$", "")) != false && Boolean.parseBoolean(tmpUser.get("isActive").toString().replaceAll("^.|.$", "")) != true) {
				throw new ValidateException("isActive non � boolean");
			}
			if (Boolean.parseBoolean(tmpUser.get("isPrivacyConsent").toString().replaceAll("^.|.$", "")) != false && Boolean.parseBoolean(tmpUser.get("isPrivacyConsent").toString().replaceAll("^.|.$", "")) != true) {
				throw new ValidateException("isPrivacyConsent non � boolean");
			}
			if (Boolean.parseBoolean(tmpUser.get("isNewsletter").toString().replaceAll("^.|.$", "")) != false && Boolean.parseBoolean(tmpUser.get("isNewsletter").toString().replaceAll("^.|.$", "")) != true) {
				throw new ValidateException("isNewsletter non � boolean");
			}

		}

	}

	public static boolean validateProductName(String inputString) {

		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(inputString);
		boolean isStringContainsSpecialCharacter = matcher.find();
		if (isStringContainsSpecialCharacter)
			System.out.println(inputString + " contains special character");
		else
			System.out.println(inputString + " does NOT contain special character");

		return isStringContainsSpecialCharacter;
	}

	public static boolean validateListName(String inputString) {

		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(inputString);
		boolean isStringContainsSpecialCharacter = matcher.find();
		if (isStringContainsSpecialCharacter)
			System.out.println(inputString + " contains special character");
		else
			System.out.println(inputString + " does NOT contain special character");

		return isStringContainsSpecialCharacter;
	}

	public static boolean validateUserName(String inputString) {

		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(inputString);
		boolean isStringContainsSpecialCharacter = matcher.find();
		if (isStringContainsSpecialCharacter)
			System.out.println(inputString + " contains special character");
		else
			System.out.println(inputString + " does NOT contain special character");

		return isStringContainsSpecialCharacter;
	}

	public static boolean validateEmail(String inputString) {

		Pattern pattern = Pattern.compile("[^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$]");
		Matcher matcher = pattern.matcher(inputString);
		boolean isStringContainsSpecialCharacter = matcher.find();
		if (isStringContainsSpecialCharacter)
			System.out.println(inputString + " contains special character");
		else
			System.out.println(inputString + " does NOT contain special character");

		return isStringContainsSpecialCharacter;
	}

	public static boolean validateId(String inputString) {
		// TODO validate uniqueCode
		return true;
	}

	public static boolean validateUniqueCode(String inputString) {
		// TODO validate uniqueCode
		return true;
	}

}
