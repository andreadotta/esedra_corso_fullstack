package it.esedra.corso.shoppinglist.exceptions;

public class DaoException extends Exception{
	
	public DaoException(String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return getMessage() + ": Dao exception";
	}

}
