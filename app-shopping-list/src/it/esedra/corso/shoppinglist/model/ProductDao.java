package it.esedra.corso.shoppinglist.model;

import java.math.BigInteger;
import java.util.Collection;
import java.util.SortedSet;

import it.esedra.corso.shoppinglist.exceptions.DaoException;

public class ProductDao implements Dao<Product>{
	
//	private String name;
//	private Integer qty;
//	private Unit unit;
//	private static BigInteger id = new BigInteger("1");

	public static enum Fields {
		name, qty, unit
	}

	@Override
	public void save(Product t) throws DaoException {
		
	}

	@Override
	public Product get(BigInteger id) throws DaoException {
		return null;
	}

	@Override
	public Collection<Product> getAll() throws DaoException {
		return null;
	}

	@Override
	public void delete(BigInteger id) throws DaoException {
		
	}

	@Override
	public SortedSet<Product> find(Product t) throws DaoException {
		return null;
	}
	

}
