package it.esedra.corso.lezioni.collections;

import java.util.Arrays;
import java.util.List;

public class ArraysAsList {

	public static void main(String args[]) {
		
		Arrays.asList("everton", "liverpool", "swansea", "chelsea");
		String[] k = {"ciao", "ciao"};
		List<String> h = Arrays.asList(k);
	}
}
