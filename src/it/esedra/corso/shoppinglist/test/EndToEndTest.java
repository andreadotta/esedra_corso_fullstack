package it.esedra.corso.shoppinglist.test;

public class EndToEndTest {
	public static void main(String args[]) {
		
		System.out.println("Eseguo i test attualmente realizzati");		
		
		AddUserTest.execute();
		AddShoppingListTest.execute();
		GetUserTest.execute();
		GetShoppingListTest.execute();
		//UpdateUserTest.execute();
		//UpdateShoppingListTest.execute();
		//DeleteUserTest.execute();
		//DeleteShoppingListTest.execute();
	}
}

