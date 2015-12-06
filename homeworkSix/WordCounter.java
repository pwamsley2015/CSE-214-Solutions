package homeworkSix;

/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Prints a word histogram from a user input file
 * @author Patrick Wamsley
 */
public class WordCounter {

	public static void main(String[] args) {

		HashMap<String, Integer> wordHistogram = new HashMap<>(); 
		Scanner scanner = new Scanner(System.in);
		String filePath = scanner.nextLine(); 
		scanner.close(); 

		/*
		 * O(n) solution to creating a full histogram from the word base. 
		 */
		for (String word : loadWordsFromFile(filePath)) {
			if (wordHistogram.get(word) == null)
				wordHistogram.put(word, 1); 
			else 
				wordHistogram.put(word, wordHistogram.get(word) + 1); 
		}
		
		Comparator comp = new MapValueComparator<Integer>(wordHistogram, true); 
		
		TreeMap<String, Integer> sortedHistogram = new TreeMap<String, Integer>(comp); 
		sortedHistogram.putAll(wordHistogram);
		
		for (String word : sortedHistogram.keySet())
			System.out.println(word + "\t\t" + wordHistogram.get(word));

		
	}

	private static ArrayList<String> loadWordsFromFile(String filePath) {

		File file = new File(filePath); 
		ArrayList<String> returnList = new ArrayList<>();  

		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		while (scanner.hasNextLine()) {
			String currLine = removeNonletters(scanner.nextLine()); 
			for (String word : currLine.split(" ")) 
				if (!(word.equals(" ")))
					returnList.add(word); 
		}
		scanner.close(); 
		return returnList; 
	}

	private static String removeNonletters(String orginal) {
		StringBuilder returnString = new StringBuilder(); 
		for (char c : orginal.toCharArray())
			if (Character.getType(c) == Character.LOWERCASE_LETTER || 
			Character.getType(c) == Character.UPPERCASE_LETTER || c == ' ')
				returnString.append(c); 
		return returnString.toString(); 
	}
}
