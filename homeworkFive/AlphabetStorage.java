package homeworkFive;

/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AlphabetStorage {
	public static void main(String[] args) throws IOException {
		DirectAddressTable<Alphabet> alphabetTable = new DirectAddressTable<>();
		System.out.println("Enter comma-separated lower-case letters:");
		InputStreamReader isr = new InputStreamReader(System.in);
		try (BufferedReader reader = new BufferedReader(isr)) {
			String input = reader.readLine();
			for (String s : input.trim().split(","))
				alphabetTable.insert(new Alphabet(s.charAt(0)));
		}
		System.out.println(alphabetTable.toString());
	}
}
