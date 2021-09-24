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

public class ProductDao implements Dao<Product> {

	private static final String fileName = "products.csv";
	private static final String folderName = "shoppinglist";
	private static final String fieldSeparator = ",";
	private final static Logger logger = LoggerFactory.getLogger(ProductDao.class.getName());

	private final static Map<String, Integer> fieldsMap;
	static {
		HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
		tmpMap.put(Fields.id.name(), 0);
		tmpMap.put(Fields.name.name(), 1);
		tmpMap.put(Fields.unit.name(), 2);
		fieldsMap = Collections.unmodifiableMap(tmpMap);
	}

	public static enum Fields {
		id, name, unit
	}

	/**
	 * TODO Implementare condizione riga 43
	 */
	@Override
	public void save(Product t) throws DaoException {
		throw new DaoException("Not implamented yet");
	}
	
	/**
	 * TODO Scegliere tra le due versioni
	 */
	@Override
	public Product get(BigInteger id) throws DaoException {
		ProductDao productDao = new ProductDao();

		return productDao.getAll().stream().filter(s -> s.getid().equals(id)).findFirst().get();
		
//		List<String[]> productsRows = ProductDao.fetchRows();
//
//		Product product = null;
//
//		if (!id.toString().equals("")) {
//
//			product = ProductDao.builderShoppingList(productsRows.stream()
//					.filter(s -> s[fieldsMap.get(Fields.id.name())].equals(id.toString())).findFirst().get());
//
//		}
//
//		return product;
	}

	@Override
	public Collection<Product> getAll() throws DaoException {
		return ProductDao.rowConverter(ProductDao.fetchRows());
	}

	@Override
	public void delete(BigInteger id) throws DaoException {

	}

	@Override
	public SortedSet<Product> find(Product t) throws DaoException {
		return null;
	}

	/**
	 * Ottengo l'elenco dei prodotti per una specifica shoppingList
	 * 
	 * @param shoppingListId
	 * @return
	 * @throws DaoException
	 */
	public Collection<Product> findByShoppingListId(BigInteger shoppingListId) {
		Collection<Product> p = null;
		try {
			ShoppingListProductDao slpDao = new ShoppingListProductDao();
			List<Product> products = ProductDao.rowConverter(ProductDao.fetchRows()).stream().collect(Collectors.toList());

			List<ShoppingListProduct> slproducts = slpDao.getAll().stream()
					.filter(slproduct -> slproduct.getShoppingListId().equals(shoppingListId))
					.collect(Collectors.toList());

			p = products.stream().distinct()
					.filter(product -> slproducts.stream()
							.anyMatch(slproduct -> product.getid().equals(slproduct.getProductId())))
					.collect(Collectors.toList());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return p;
	}

	public static Collection<Product> rowConverter(List<String[]> csvRows) throws DaoException {
		return csvRows.stream().map(ProductDao::builderShoppingList).collect(Collectors.toList());
	}

	private static List<String[]> fetchRows() throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());

			return lines.stream().map(s -> s.split(fieldSeparator)).collect(Collectors.toList());
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * Build a Product instance from String[]
	 * 
	 * @param shoppingList
	 * @return
	 */
	public static Product builderShoppingList(String[] product) {

		ProductBuilder builder = ProductBuilder.builder();

		builder.id(new BigInteger(product[fieldsMap.get(Fields.id.name())]));
		builder.name(product[fieldsMap.get(Fields.name.name())]);
		builder.unit(Unit.valueOf(product[fieldsMap.get(Fields.unit.name())]));

		return builder.build();
	}

}
