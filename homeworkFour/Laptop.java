package homeworkFour;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Class which represents a Laptop and contains Comparators for different ways to sort them.
 * 
 * @author Patrick Wamsley
 */
public class Laptop {

	/**
	 * Comparator for int values
	 */
	public static final Comparator<Integer> intComparator = new Comparator<Integer>() { 
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2); 
		}
	};
	/**
	 * Compares laptops based off the brand name.
	 */
	public static final Comparator<Laptop> brandComparator = new Comparator<Laptop>() {

		@Override
		public int compare(Laptop o1, Laptop o2) {
			return o1.getBrand().compareTo(o2.getBrand()); 
		}
	};
	/**
	 * Compares laptops based off the amount of memory they have.
	 */
	public static final Comparator<Laptop> memoryComparator = new Comparator<Laptop>() { 

		@Override
		public int compare(Laptop o1, Laptop o2) {
			return Integer.compare(o1.getMemory(), o2.getMemory()); 
		}
	};
	/**
	 * Compares laptops based off the amount of storage
	 */
	public static final Comparator<Laptop> hddComparator = new Comparator<Laptop>() { 

		@Override
		public int compare(Laptop o1, Laptop o2) {
			return Integer.compare(o1.getHdd(), o2.getHdd()); 
		}
	};
	/**
	 * Compares laptops based off the speed of the processors 
	 */
	public static final Comparator<Laptop> processorComparator = new Comparator<Laptop>() {

		@Override
		public int compare(Laptop o1, Laptop o2) {
			return Double.compare(o1.getProcSpeed(), o2.getProcSpeed()); 
		}
	};

	private final String brand; 
	private final double procSpeed; 
	private final int memory, hdd;

	public Laptop(String brand, double procSpeed, int memory, int hdd) {
		this.brand = brand;
		this.procSpeed = procSpeed;
		this.memory = memory;
		this.hdd = hdd;
	}

	public String getBrand() {
		return brand;
	}

	public double getProcSpeed() {
		return procSpeed;
	}

	public int getMemory() {
		return memory;
	}

	public int getHdd() {
		return hdd;
	} 

	@Override
	public String toString() {
		return brand + ": " + procSpeed + " gHz," + " " + memory + " GB Memory," + " " + hdd + " GB Storage";
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input data type (must be ’int’ or ’laptop’):");
		String inputDataType = scanner.nextLine().trim();
		if (!inputDataType.equals("int") && !inputDataType.equals("laptop"))
			throw new IllegalArgumentException("Invalid data type specified.");
		switch (inputDataType) {
			case "int":
				ArrayList<Integer> integers = readIntegerInputs(scanner);
				Sorter<Integer> intSorter = new Quicksorter<>(intComparator,
						integers);
				intSorter.sort();
				System.out.println(getStringJoinedBy(integers, ", "));
				break;
			case "laptop":
				ArrayList<Laptop> laptops = readLaptopInputs(scanner);
				Sorter<Laptop> laptopSorter = new Quicksorter<>(brandComparator, laptops); 
				laptopSorter.sort(); 
				System.out.print("Sorted by brand name:\n\t");

				System.out.println(getStringJoinedBy(laptops, "\n\t"));
				System.out.println();
				laptopSorter.setComparator(processorComparator);
				laptopSorter.sort();
				System.out.print("Sorted by processor speed:\n\t");
				System.out.println(getStringJoinedBy(laptops, "\n\t"));
				System.out.println();
				laptopSorter.setComparator(memoryComparator);
				laptopSorter.sort();
				System.out.print("Sorted by RAM:\n\t");
				System.out.println(getStringJoinedBy(laptops, "\n\t"));
				System.out.println();
				laptopSorter.setComparator(hddComparator);
				laptopSorter.sort();
				System.out.print("Sorted by hard disk capacity:\n\t");
				System.out.println(getStringJoinedBy(laptops, "\n\t"));
				break;
			default:
				throw new IllegalArgumentException("Invalid data type specified.");
		}
	}

	private static ArrayList<Laptop> readLaptopInputs(Scanner scanner) {
		ArrayList<Laptop> returnList = new ArrayList<>(); 
		System.out.println("Provide Laptops (one per line) as \"brand,prosSpeed,memory,hddCap\" or type \"end\" to finish");
		while (true) {
			String line = scanner.nextLine().trim(); 
			if (line.contains("end"))
				break;
			String[] items = line.split(","); 
			Laptop l = new Laptop(items[0], Double.parseDouble(items[1]), Integer.parseInt(items[2]), Integer.parseInt(items[3])); 
			returnList.add(l); 
		}
		
		return returnList; 
	}

	private static String getStringJoinedBy(ArrayList list, String preFixSeq) {
		StringBuilder returnString = new StringBuilder(); 
		
		for (int i = 0; i < list.size(); i++) { 
			if (i != 0)
				returnString.append(preFixSeq); 
			returnString.append(list.get(i).toString()); 
		}
		return returnString.toString(); 
	}

	private static ArrayList<Integer> readIntegerInputs(Scanner scanner) {
		
		ArrayList<Integer> returnList = new ArrayList<>(); 
		System.out.println("Provide Integers (one per line) or type \"end\" to finish");
		while (true) {
			String line = scanner.nextLine(); 
			if (line.equals("end"))
				break; 
			returnList.add(Integer.parseInt(line)); 
		}
		
		return returnList; 
	}
}
