package homeworkSix;

/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JOptionPane;

/**
 * Uses implementations of Maps to display
 * data about contacts input by user. 
 * @author Patrick Wamsley
 */
public class FileToSimpleMapLoader {

	public static void main(String[] args) {
		
		TreeMap<String, String> contactsMap = new TreeMap<>(); //store map sorted by keys
		LinkedHashMap<String, String> contactsInputOrder = new LinkedHashMap<>();  //store map in input order
		
		/*
		 * User input
		 */
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter in contact info in the following format: name, (123)456-1234 or type \"end\".");
		
		while (true) {
			String currLine = scanner.nextLine().trim(); 
			if (currLine.equalsIgnoreCase("end")) {
				scanner.close(); 
				break; 
			}
			String[] info = currLine.split(","); 
			contactsMap.put(info[0].trim(), info[1].trim()); 
			contactsInputOrder.put(info[0].trim(), info[1].trim());  
		}
		
		/*
		 * User picks sort type w/ a drop down menu
		 */
		String[] options = "name,phone number,same as input".split(","); 
		String sortType = (String)JOptionPane.showInputDialog(null, "Select sort type", "Sort selection",
				0, null, options, options[2]);
		
		/*
		 * Display contacts
		 */
		if (sortType.equals("name")) {
			for (String name : contactsMap.keySet())
				System.out.println(name + ",  " + contactsMap.get(name));
		} else if (sortType.equals("phone number")) {
			
			TreeMap<String, String> contactsByPhoneNumber = new TreeMap<>(
					new MapValueComparator<String>(contactsMap)); 
			contactsByPhoneNumber.putAll(contactsMap); 
			
			for (String name : contactsByPhoneNumber.keySet())
				System.out.println(name + ",  " + contactsMap.get(name));
		} else {
			for (String name : contactsInputOrder.keySet())
				System.out.println(name + ",  " + contactsInputOrder.get(name));
		}
	}
}
