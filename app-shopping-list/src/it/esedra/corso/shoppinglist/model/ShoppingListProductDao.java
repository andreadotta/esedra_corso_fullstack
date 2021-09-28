package it.esedra.corso.shoppinglist.model;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.helper.GetFileResource;

public class ShoppingListProductDao implements Dao<ShoppingListProduct> {

	private static final String fileName = "shoppinglist-products.csv";
	private static final String folderName = "shoppinglist";
	private static final String fieldSeparator = ",";
	private final static Logger logger = LoggerFactory.getLogger(ShoppingListProductDao.class.getName());

	private final static Map<String, Integer> fieldsMap;
	static {
		HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
		tmpMap.put(Fields.shoppingListId.name(), 0);
		tmpMap.put(Fields.productId.name(), 1);
		tmpMap.put(Fields.qty.name(), 2);
		fieldsMap = Collections.unmodifiableMap(tmpMap);
	}

	public static enum Fields {
		shoppingListId, qty, productId
	}

	@Override
	public Collection<ShoppingListProduct> getAll() throws DaoException {
		return ShoppingListProductDao.rowConverter(ShoppingListProductDao.fetchRows());
	}

	/**
	 * TODO Scegliere tra le due versioni
	 */
	@Override
	public ShoppingListProduct get(BigInteger id) throws DaoException {
		ShoppingListProductDao shoppingListProductDao = new ShoppingListProductDao();
	
		return shoppingListProductDao.getAll().stream().filter(s -> s.getProductId().equals(id)).findFirst().get();
	
	}

	@Override
	public SortedSet<ShoppingListProduct> find(ShoppingListProduct t) throws DaoException {
		return null;
	}

	/**
	 * TODO Implementare condizione riga 43
	 */
	@Override
	public void save(ShoppingListProduct t) throws DaoException {
		throw new DaoException("Not implamented yet");
	}

	@Override
	public void delete(BigInteger id) throws DaoException {

	}

	private static List<String[]> fetchRows() throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());
	
			return lines.stream().map(s -> s.split(fieldSeparator)).collect(Collectors.toList());
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}

	public static Collection<ShoppingListProduct> rowConverter(List<String[]> csvRows) throws DaoException {
		return csvRows.stream().map(ShoppingListProductDao::builderShoppingListProduct).collect(Collectors.toList());
	}

	/**
	 * Build a Product instance from String[]
	 * 
	 * @param shoppingList
	 * @return
	 */
	public static ShoppingListProduct builderShoppingListProduct(String[] product) {

		ShoppingListProductBuilder builder = ShoppingListProductBuilder.builder();

		builder.shoppingListId(new BigInteger(product[fieldsMap.get(Fields.shoppingListId.name())]));
		builder.productId(new BigInteger(product[fieldsMap.get(Fields.productId.name())]));
		builder.qty(Integer.parseInt(product[fieldsMap.get(Fields.qty.name())]));

		return builder.build();
	}

}
