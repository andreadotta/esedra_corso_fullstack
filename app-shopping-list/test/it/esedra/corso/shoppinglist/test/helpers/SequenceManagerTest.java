package it.esedra.corso.shoppinglist.test.helpers;



import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esedra.corso.shoppinglist.helper.SequenceManager;
import it.esedra.corso.shoppinglist.model.User;
import it.esedra.corso.shoppinglist.model.UserBuilder;
import it.esedra.corso.shoppinglist.model.UserDao;

public class SequenceManagerTest {
	private final static Logger logger = LoggerFactory.getLogger(UserDao.class.getName());
	
	@Test
	public void testNewUserId() throws Exception {
		
		User u1 = UserBuilder.builder().userId(new BigInteger("1")).build();
		User u2 = UserBuilder.builder().userId(new BigInteger("2")).build();
		User u3 = UserBuilder.builder().userId(new BigInteger("3")).build();
		
		Collection<User> users = new ArrayList<User>();
		users.add(u1);
		users.add(u2);
		users.add(u3);		
		
		
		BigInteger userId = SequenceManager.findLastUserId(users);
		logger.debug(userId.toString());
		assertTrue(userId.compareTo(new BigInteger("3")) == 0);
		
		userId = SequenceManager.findLastUserId(null);
		logger.debug(userId.toString());
		assertTrue(userId.compareTo(new BigInteger("1")) == 0);
		
		userId = SequenceManager.findLastUserId(null);
		logger.debug(userId.toString());
		assertTrue(userId.compareTo(new BigInteger("2")) == 0);
		
		
	}
}
