package it.esedra.corso.shoppinglist.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.helper.AESHelper;
import it.esedra.corso.shoppinglist.helper.GetFileResource;
import it.esedra.corso.shoppinglist.helper.SequenceManager;

public class ShoppingListDao implements Dao<ShoppingList> {

	private static final String fileName = "shoppinglist.csv";
	private static final String folderName = "shoppinglist";
	private static final String fieldSeparator = ",";
	private final static Logger logger = LoggerFactory.getLogger(ShoppingListDao.class.getName());

	public static enum Fields {
		userId, listName, id, uniqueCode
	}

	private final static Map<String, Integer> fieldsMap;
	static {
		HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
		tmpMap.put(Fields.userId.name(), 0);
		tmpMap.put(Fields.id.name(), 1);
		tmpMap.put(Fields.listName.name(), 2);
		tmpMap.put(Fields.uniqueCode.name(), 3);
		fieldsMap = Collections.unmodifiableMap(tmpMap);
	}

	@Override
	public void delete(BigInteger id) throws DaoException {
		File db = null;
		File dbclone = null;
		try {
			// cerco tutti i shopping list
			SortedSet<ShoppingList> shoppingLists = this.find(new ShoppingListBuilder().build());
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
	public SortedSet<ShoppingList> find(ShoppingList shoppingList) throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());
			SortedSet<ShoppingList> shoppingLists = new TreeSet<ShoppingList>();
			ShoppingListBuilder builder = null;
			for (String line : lines) {
				String[] fields = line.split(fieldSeparator);
				if (fields[fieldsMap.get(Fields.id.name())].equals(shoppingList.getId().toString())
						|| fields[fieldsMap.get(Fields.listName.name())].equals(shoppingList.getListName())) {
					if (builder == null) {
						builder = ShoppingListBuilder.builder();
						builder.listName(fields[fieldsMap.get(Fields.listName.name())]);
					}
					Product tmpProduct = new Product();
//					tmpProduct.setName(fields[fieldsMap.get("name")]);
//					tmpProduct.setQty(Integer.parseInt(fields[fieldsMap.get("qty")]));
//					tmpProduct.setUnit(Unit.valueOf(fields[fieldsMap.get("unit")]));
					// builder.addProduct(tmpProduct);
				}
				shoppingLists.add((ShoppingList) builder.build());
			}
			return shoppingLists;
		} catch (IOException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		}
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
	 * TODO: Rivedere il ritorno di un'istanza di ShoppingList al post di una
	 * Collection
	 */

	@Override
	public ShoppingList get(BigInteger id) throws DaoException {
		Collection<ShoppingList> shoppingLists = ShoppingListDao.rowConverter(this.fetchRows());

		return shoppingLists.stream().filter(s -> s.getId().equals(id)).findFirst().get();

	}

	@Override
	public Collection<ShoppingList> getAll() throws DaoException {
		return ShoppingListDao.rowConverter(this.fetchRows());

	}

	private List<String[]> fetchRows() throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());

			return lines.stream().map(s -> s.split(fieldSeparator)).collect(Collectors.toList());
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}

	public static Collection<ShoppingList> rowConverter(List<String[]> csvRows) {
		Collection<List<String[]>> st = csvRows.stream().collect(Collectors
				.groupingBy(s -> s[fieldsMap.get(Fields.uniqueCode.name())], TreeMap::new, Collectors.toList()))
				.values();

		return st.stream().map(ShoppingListDao::builderShoppingList).collect(Collectors.toList());

	}

	public static ShoppingList builderShoppingList(List<String[]> shoppingList) {

		ShoppingListBuilder builder = ShoppingListBuilder.builder();

		builder.listName(shoppingList.get(0)[fieldsMap.get(Fields.listName.name())]);

//		builder.products(shoppingList.stream()
//				.map(s -> new Product(s[fieldsMap.get(Product.Fields.name.name())],
//						Integer.parseInt(s[fieldsMap.get(Product.Fields.qty.name())]),
//						Unit.valueOf(s[fieldsMap.get(Product.Fields.unit.name())])))
//				.collect(Collectors.toList()));

		return builder.build();
	}

}
