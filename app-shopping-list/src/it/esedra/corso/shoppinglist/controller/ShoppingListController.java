package it.esedra.corso.shoppinglist.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.esedra.corso.shoppinglist.exceptions.DaoException;
import it.esedra.corso.shoppinglist.model.ShoppingList;
import it.esedra.corso.shoppinglist.model.ShoppingListDao;

@RestController
public class ShoppingListController {

	ShoppingListDao dao = new ShoppingListDao();
	@GetMapping("/shoppinglists")
	Collection<ShoppingList> all() {
		Collection<ShoppingList> shoppingLists = null;
		try {
			shoppingLists = dao.getAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shoppingLists;
	}
}