package it.esedra.corso.shoppinglist.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.helper.GetFileResource;
import it.esedra.corso.shoppinglist.helper.SequenceManager;
import it.esedra.corso.shoppinglist.model.ShoppingListDao.Fields;

public class ProductDao implements Dao<Product> {

	private String name;
	private Integer qty;
	private Unit unit;
	private static BigInteger id = new BigInteger("1");
	private static final String fileName = "lista.csv";
	private static final String folderName = "shoppinglist";
	private static final String fieldSeparator = ",";
	private final static Logger logger = LoggerFactory.getLogger(ShoppingListDao.class.getName());

	private final static Map<String, Integer> fieldsMap;
	static {
		HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();
		tmpMap.put(Fields.name.name(), 0);
		tmpMap.put(Fields.name.name(), 1);
		tmpMap.put(Fields.qty.name(), 2);
		tmpMap.put(Fields.unit.name(), 3);
		fieldsMap = Collections.unmodifiableMap(tmpMap);
	}

	public static enum Fields {
		id, name, qty, unit
	}

	/**
	 * TODO Implementare condizione riga 43
	 */
	@Override
	public void save(Product t) throws DaoException {
		try {

			BufferedWriter writer = new BufferedWriter(
					new FileWriter(GetFileResource.get(fileName, folderName).toPath().toString(), true));
			StringBuilder builder = new StringBuilder();

			if (true) {
				builder.append(t.getName());
				builder.append(fieldSeparator);
				builder.append(t.getQty());
				builder.append(fieldSeparator);
				builder.append(t.getUnit());
				builder.append(fieldSeparator);
				builder.append(System.getProperty("line.separator"));
				writer.write(builder.toString());
				writer.flush();
				writer.close();
				logger.info("Prodotto " + t.getName() + " aggiunto");

			} else {
				logger.warn("no product stored or updated!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public Product get(BigInteger id) throws DaoException {
		return null;
	}

	@Override
	public Collection<Product> getAll() throws DaoException {
		Collection<Product> products = ProductDao.rowConverter(this.fetchRows());
		return products;
	}

	@Override
	public void delete(BigInteger id) throws DaoException {

	}

	@Override
	public SortedSet<Product> find(Product t) throws DaoException {
		return null;
	}

	private List<String[]> fetchRows() throws DaoException {
		try {
			List<String> lines = Files.readAllLines(GetFileResource.get(fileName, folderName).toPath());

			return lines.stream().map(s -> s.split(fieldSeparator)).collect(Collectors.toList());
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * TODO rowConverter
	 */
	public static Collection<Product> rowConverter(List<String[]> csvRows) {
		return null;

	}

}
