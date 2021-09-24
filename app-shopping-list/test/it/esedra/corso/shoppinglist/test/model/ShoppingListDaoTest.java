package it.esedra.corso.shoppinglist.test.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ShoppingListDaoTest {

	@Test
	void rowConverterTest() {
		
//		tmpMap.put(Fields.id.name(), 0);
//		tmpMap.put(Fields.listName.name(), 1);
//		tmpMap.put(Fields.uniqueCode.name(), 2);
//		tmpMap.put(Product.Fields.name.name(), 3);
//		tmpMap.put(Product.Fields.qty.name(), 4);
//		tmpMap.put(Product.Fields.unit.name(), 5);
		
		String[] row0 = {"1", "lista1", "abcdef", "latte", "1", "L"};
		String[] row1 = {"2", "lista2", "abcde", "uova", "1", "NUMBER"};
		String[] row2 = {"3", "lista2", "abcde", "latte", "1", "L"};
		
		List<String[]> list = new ArrayList<String[]>();
		
		list.add(row0);
		list.add(row1);
		list.add(row2);
		
//		Collection<ShoppingList> shoppingLists = ShoppingListDao.rowConverter(list);
//		assertTrue(shoppingLists.size() == 2);
//		
//		Integer productQty = shoppingLists.stream().map((shoppingList) -> shoppingList.getProducts()
//				.stream().map((product) -> product.getQty()).reduce(0, Integer::sum)).reduce(0, Integer::sum);
//		
//		assertTrue(productQty == 3);
	}

}
