package it.esedra.corso.shoppinglist.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.helper.AESHelper;
import it.esedra.corso.shoppinglist.helper.GetFileResource;
import it.esedra.corso.shoppinglist.model.ShoppingList.Fields;

public class ShoppingListDao implements Dao<ShoppingList> {

	private String uniqueCode;
	private static final String fileName = "lista.csv";
	private static final String folderName = "shoppinglist";
	private static final String fieldSeparator = ",";

	private final static Map<String, Integer> fieldsMap;
	static {
		HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
		tmpMap.put(Fields.id.name(), 0);
		tmpMap.put(Fields.listName.name(), 1);
		tmpMap.put(Fields.uniqueCode.name(), 2);
		tmpMap.put(Product.Fields.name.name(), 3);
		tmpMap.put(Product.Fields.qty.name(), 4);
		tmpMap.put(Product.Fields.unit.name(), 5);
		fieldsMap = Collections.unmodifiableMap(tmpMap);
	}
	
	/**
	 * TODO Implemetare Delete
	 */
	
	@Override
	public void delete(BigInteger id) throws DaoException {
		File db = null;
		File dbclone = null;
		try {
			//cerco tutti i shopping list
			SortedSet<ShoppingList> shoppingLists = this.find(new ShoppingListBuilder().build());
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
			for (ShoppingList line : shoppingLists) {
				//se il id corrisponde a quello in input non lo scrivo
				if (!line.getId().equals(id)) {
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
	public SortedSet<ShoppingList> find(ShoppingList shoppingList) throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());
			SortedSet<ShoppingList> shoppingLists = new TreeSet<ShoppingList>();
			ShoppingListBuilder builder = null;
			for (String line : lines) {
				String[] fields = line.split(fieldSeparator);
				if (!fields[0].equals("")) {
					if (builder == null) {
						builder = ShoppingListBuilder.builder();
						builder.uniqueCode(fields[fieldsMap.get(Fields.uniqueCode.name())])
								.listName(fields[fieldsMap.get(Fields.listName.name())]);
					}
					Product tmpProduct = new Product();
					tmpProduct.setName(fields[fieldsMap.get("name")]);
					tmpProduct.setQty(Integer.parseInt(fields[fieldsMap.get("qty")]));
					tmpProduct.setUnit(Unit.valueOf(fields[fieldsMap.get("unit")]));
					builder.addProduct(tmpProduct);
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
	 * TODO Da correggere implementazione!
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws DaoException
	 * 
	 */
	private static String generateUniqueKey(BigInteger id, String name) throws DaoException {
		try {
			return URLEncoder.encode(AESHelper.encrypt(id + name, "EsedraShoppingList"), StandardCharsets.UTF_8.toString());
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	/**
	 * TODO Implementare la parte Update
	 */
	
	@Override
	public void save(ShoppingList t) throws DaoException {
		try {

			PrintWriter writer = new PrintWriter(GetFileResource.get(fileName, folderName));
			StringBuilder builder = new StringBuilder();

//			if (t.getUniqueCode() == null) {
//				t.getUniqueCode = ShoppingList.generateUniqueKey(this.getId(), this.getListName());
//			}

			for (Product listaTemp : t.getProducts()) {
				if (listaTemp == null) {
					continue;
				}
				builder.append(t.getId());
				builder.append(fieldSeparator);
				builder.append(t.getListName());
				builder.append(fieldSeparator);
				builder.append(t.getUniqueCode());
				builder.append(fieldSeparator);
				builder.append(listaTemp.getName());
				builder.append(fieldSeparator);
				builder.append(listaTemp.getQty());
				builder.append(fieldSeparator);
				builder.append(listaTemp.getUnit());
				builder.append(fieldSeparator);
				builder.append(System.getProperty("line.separator"));

			}
			writer.write(builder.toString());
			writer.flush();
			writer.close();

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public ShoppingList get(BigInteger id) throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());
			ShoppingListBuilder builder = null;
			for (String line : lines) {
				String[] fields = line.split(fieldSeparator);

				if (id.equals(new BigInteger(fields[fieldsMap.get(Fields.id.name())]))) {
					if (builder == null) {
						builder = ShoppingListBuilder.builder();
						builder.uniqueCode(fields[fieldsMap.get(Fields.uniqueCode.name())])
								.listName(fields[fieldsMap.get(Fields.listName.name())])
								.id(new BigInteger(fields[fieldsMap.get(Fields.id.name())]));
					}
					Product tmpProduct = new Product();
					tmpProduct.setName(fields[fieldsMap.get("name")]);
					tmpProduct.setQty(Integer.parseInt(fields[fieldsMap.get("qty")]));
					tmpProduct.setUnit(Unit.valueOf(fields[fieldsMap.get("unit")]));
					builder.addProduct(tmpProduct);
				}
			}
			return builder.build();
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}

	}

}
