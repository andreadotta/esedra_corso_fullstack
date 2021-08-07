package it.esedra.corso.shoppinglist.model;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.helper.GetFileResource;
import it.esedra.corso.shoppinglist.model.ShoppingList.Fields;

public class ShoppingListDao implements Dao<ShoppingList>{

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
	
	@Override
	public void delete(BigInteger id) throws DaoException {
		// TODO Auto-generated method stub
		
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
					if(builder == null) {
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
			UUID uuid = UUID.randomUUID();
			long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
			return Long.toString(l);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void save(ShoppingList t) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ShoppingList get(BigInteger id) throws DaoException {
//		List<String> lines = Files
//				.readAllLines(GetFileResource.get(fileName, folderName).toPath());
//		ShoppingListBuilder builder = null;
//		for (String line : lines) {
//			String[] fields = line.split(fieldSeparator);
//
//			if (inShoppingList.getUniqueCode().equals(fields[fieldsMap.get(Fields.uniqueCode.name())])) {
//				if (builder == null) {
//					builder = ShoppingListBuilder.builder();
//					builder.uniqueCode(fields[fieldsMap.get(Fields.uniqueCode.name())])
//							.listName(fields[fieldsMap.get(Fields.listName.name())])
//							.id(new BigInteger(fields[fieldsMap.get(Fields.id.name())]));
//				}
//				Product tmpProduct = new Product();
//				tmpProduct.setName(fields[fieldsMap.get("name")]);
//				tmpProduct.setQty(Integer.parseInt(fields[fieldsMap.get("qty")]));
//				tmpProduct.setUnit(Unit.valueOf(fields[fieldsMap.get("unit")]));
//				builder.addProduct(tmpProduct);
//			}
//		}
//		return builder.build();
		return null;
	}

}
