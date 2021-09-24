package it.esedra.corso.shoppinglist.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.helper.GetFileResource;
import it.esedra.corso.shoppinglist.helper.SequenceManager;

public class ShoppingListDao implements Dao<ShoppingList> {

	private static final String fileName = "shoppinglist.csv";
	private static final String folderName = "shoppinglist";
	private static final String fieldSeparator = ",";
	private final static Logger logger = LoggerFactory.getLogger(ShoppingListDao.class.getName());

	public static enum Fields {
		id, listName, uniqueCode, userId
	}

	private final static Map<String, Integer> fieldsMap;
	static {
		HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
		tmpMap.put(Fields.id.name(), 0);
		tmpMap.put(Fields.listName.name(), 1);
		tmpMap.put(Fields.uniqueCode.name(), 2);
		tmpMap.put(Fields.userId.name(), 3);
		fieldsMap = Collections.unmodifiableMap(tmpMap);
	}

	@Override
	public void delete(BigInteger id) throws DaoException {
		File db = null;
		File dbclone = null;
		try {
			// cerco tutti i shopping list
			Collection<ShoppingList> shoppingLists = this.getAll();
			// rinominiamo il file
			// prendo il file del db
			db = new File(GetFileResource.get(fileName, folderName).toPath().toString());
			// clono il file del db
			dbclone = new File(GetFileResource.get(fileName, folderName).toPath().toString() + ".temp");
			// ma prima verifico che non esista già
			if (dbclone.exists()) {
				dbclone.delete();
			}
			// clono effettettivamente il file del db
			db.renameTo(dbclone);
			/// elimino il file del db
			db.delete();
			// lo riscrivo da zero
			for (ShoppingList line : shoppingLists) {
				// se il id corrisponde a quello in input non lo scrivo
				if (!line.getId().equals(id)) {
					this.save(line);
				}
			}
		} catch (Exception e) {
			// cancello il file nuovo del db
			db.delete();
			// ripristino il vecchio file del db
			dbclone.renameTo(db);
			throw new DaoException(e.getMessage());
		} finally {
			// eliminio il clone che avevo fatto per salvare i dati in caso di errore
			dbclone.delete();
		}
	}

	@Override
	public Collection<ShoppingList> find(ShoppingList shoppingList) throws DaoException {

		List<String[]> shoppingListRows = ShoppingListDao.fetchRows();

		return shoppingListRows.stream().map(ShoppingListDao::builderShoppingList)
				.filter(s -> s.getListName().equals(shoppingList.getListName())).collect(Collectors.toList());

	}

	/**
	 * TODO Implementare la parte Update
	 */

	@Override
	public void save(ShoppingList t) throws DaoException {
		try {

			BufferedWriter writer = new BufferedWriter(
					new FileWriter(GetFileResource.get(fileName, folderName).toPath().toString(), true));
			StringBuilder builder = new StringBuilder();

//			if (t.getUniqueCode() == null) {
//				t.getUniqueCode = ShoppingList.generateUniqueKey(this.getId(), this.getListName());
//			}

			if ((t.getId().equals(BigInteger.ONE)
					|| t.getId().compareTo(SequenceManager.getInstance().getCurrentIdShoppingList()) > 0)
					&& !find(t).contains(t)) {
				builder.append(t.getId());
				builder.append(fieldSeparator);
				builder.append(t.getListName());
				builder.append(fieldSeparator);
				builder.append(t.getUniqueCode());
				builder.append(fieldSeparator);
				builder.append(System.getProperty("line.separator"));
				writer.write(builder.toString());
				writer.flush();
				writer.close();
				logger.info("ShoppingList " + t.getListName() + " salvata");

			} else {
				logger.warn("no shoppingList stored or updated!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage());
		}
	}

	/**
	 * TODO Scegliere tra le due versioni
	 */
	@Override
	public ShoppingList get(BigInteger id) throws DaoException {
		ShoppingListDao shoppingListDao = new ShoppingListDao();

		return shoppingListDao.getAll().stream().filter(s -> s.getId().equals(id)).findFirst().get();
		
//		List<String[]> shoppingListRows = ShoppingListDao.fetchRows();
//
//		ShoppingList shoppingList = null;
//
//		if (!id.toString().equals("")) {
//
//			shoppingList = ShoppingListDao.builderShoppingList(shoppingListRows.stream()
//					.filter(s -> s[fieldsMap.get(Fields.id.name())].equals(id.toString())).findFirst().get());
//
//		}
//
//		return user;

	}

	@Override
	public Collection<ShoppingList> getAll() throws DaoException {
		return ShoppingListDao.rowConverter(ShoppingListDao.fetchRows());
	}

	private static List<String[]> fetchRows() throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());

			return lines.stream().map(s -> s.split(fieldSeparator)).collect(Collectors.toList());
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}

	public static Collection<ShoppingList> rowConverter(List<String[]> csvRows) throws DaoException {
		return csvRows.stream().map(ShoppingListDao::builderShoppingList).collect(Collectors.toList());
	}

	/**
	 * Build a ShoppingList instance from String[]
	 * 
	 * @param shoppingList
	 * @return
	 */
	public static ShoppingList builderShoppingList(String[] shoppingList) {

		ShoppingListBuilder builder = ShoppingListBuilder.builder();

		builder.listName(shoppingList[fieldsMap.get(Fields.listName.name())]);
		builder.id(new BigInteger(shoppingList[fieldsMap.get(Fields.id.name())]));
		builder.uniqueCode(shoppingList[fieldsMap.get(Fields.uniqueCode.name())]);

		UserDao userDao = new UserDao();
		ProductDao productDao = new ProductDao();
		try {
			builder.user(userDao.get(new BigInteger(shoppingList[fieldsMap.get(Fields.userId.name())])));
			builder.products(
					productDao.findByShoppingListId(new BigInteger(shoppingList[fieldsMap.get(Fields.id.name())])));
		} catch (DaoException e) {
			ShoppingListDao.logger.error(e.getMessage());
		}

		return builder.build();
	}

}
