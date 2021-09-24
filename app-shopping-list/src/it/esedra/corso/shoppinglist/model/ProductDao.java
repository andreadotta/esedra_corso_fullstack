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

	@Override
	public Product get(BigInteger id) throws DaoException {
		return null;
	}

	@Override
	public Collection<Product> getAll() throws DaoException {
		throw new DaoException("Not implamented yet");
	}

	@Override
	public void delete(BigInteger id) throws DaoException {

	}

	@Override
	public SortedSet<Product> find(Product t) throws DaoException {
		return null;
	}

	private List<String[]> fetchRows() throws DaoException {
		throw new DaoException("Not implamented yet");
	}

	/**
	 * TODO rowConverter
	 * @throws DaoException 
	 */
	public static Collection<Product> rowConverter(List<String[]> csvRows) throws DaoException {
		throw new DaoException("Not implamented yet");
	}

}
