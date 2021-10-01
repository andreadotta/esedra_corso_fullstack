
package it.esedra.corso.shoppinglist.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.model.ShoppingList;
import it.esedra.corso.shoppinglist.model.ShoppingListDao;

@RestController
public class ShoppingListController {

	ShoppingListDao dao = new ShoppingListDao();
	@CrossOrigin
	@GetMapping("/shoppinglists")
	Collection<ShoppingList> all() {
		Collection<ShoppingList> shoppingLists = null;
		try {
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			shoppingLists = dao.getAll();

		} catch (DaoException e) {
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
			
		}

		return shoppingLists;
	}


}