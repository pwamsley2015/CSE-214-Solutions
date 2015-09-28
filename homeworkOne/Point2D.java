package homeworkOne;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

import java.util.Scanner;

/**
 * Uses LinkedList Implementation and OrderedDoublePair 
 * to represent numerical operation in 2D space. 
 * @author Patrick Wamsley
 */
public class Point2D {

	/**
	 * @return The distance between point p and point q. 
	 */
	public static double distance(OrderedDoublePair p, OrderedDoublePair q) {

		double deltaX = p.getX() - q.getX();
		double deltaY = p.getY() - q.getY(); 

		return Math.sqrt(deltaX * deltaX + deltaY * deltaY); 
	}

	/**
	 * @param points
	 * @return A {@code UniLinkedList} containing every point from the array.
	 */
	public static UniLinkedList<OrderedDoublePair> fromArray(OrderedDoublePair[] points) {

		UniLinkedList<OrderedDoublePair> returnList = new UniLinkedList<OrderedDoublePair>(); 

		for (OrderedDoublePair x : points)
			returnList.add(x); 

		return returnList; 
	}

	/**
	 * @param points
	 * @return A {@code UniLinkedList} containing every point from the 2D array.
	 */
	public static UniLinkedList<OrderedDoublePair> from2DArray(double[][] points) {

		UniLinkedList<OrderedDoublePair> returnList = new UniLinkedList<OrderedDoublePair>(); 

		for (double[] pair : points) 
			returnList.add(new OrderedDoublePair(pair[0], pair[1])); 

		return returnList; 
	}

	/**
	 * @param points
	 * @return Geometric Centroid Point given by (average(x), average(y))
	 */
	public static OrderedDoublePair centroid(UniLinkedList<OrderedDoublePair> points) {

		points.resetPositionCursor();

		double sigmaX = 0, sigmaY = 0; 

		while (points.hasNext()) {
			OrderedDoublePair pair = points.get(); 
			sigmaX += pair.getX(); 
			sigmaY += pair.getY(); 
		}

		int size = points.size(); 
		return new OrderedDoublePair(sigmaX / size, sigmaY / size); 	
	}

	/**
	 * @return the OrderedDoublePair closest to the origin. 
	 */
	public static OrderedDoublePair smallest(UniLinkedList<OrderedDoublePair> points) {

		points.resetPositionCursor();

		OrderedDoublePair smallest = points.head(); 
		double minDistance = distance(smallest, OrderedDoublePair.ORIGIN); 

		while (points.hasNext()) {
			OrderedDoublePair curr = points.get();
			double currDistance = distance(curr, OrderedDoublePair.ORIGIN); 
			if (minDistance > currDistance) {
				minDistance = currDistance; 
				smallest = curr; 
			}
		}
		return smallest; 		
	}

	/**
	 * @return the OrderedDoublePair farthest away from the origin. 
	 */
	public static OrderedDoublePair largest(UniLinkedList<OrderedDoublePair> points) {

		points.resetPositionCursor();

		OrderedDoublePair largest = points.head(); 
		double maxDistance = distance(largest, OrderedDoublePair.ORIGIN); 

		while (points.hasNext()) {
			OrderedDoublePair curr = points.get(); 
			double currDistance = distance(curr, OrderedDoublePair.ORIGIN); 
			if (maxDistance < currDistance) {
				maxDistance = currDistance; 
				largest = curr; 
			}
		}
		return largest; 		
	}

	/**
	 * Something something homework 
	 */
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in); 
		UniLinkedList<OrderedDoublePair> points = new UniLinkedList<OrderedDoublePair>(); 

		System.out.println("Add points to the list in the format \"x y\" or type done");

		while (true) {
			String currLine = scanner.nextLine(); 

			if (currLine.contains("done"))
				break; 
			else {	
				try {
					double x = Double.parseDouble(currLine.substring(0, currLine.indexOf(" "))); 
					double y = Double.parseDouble(currLine.substring(currLine.indexOf(" ") + 1)); 
					points.add(new OrderedDoublePair(x, y)); 
				} catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
					System.err.println("Invalid Number format. Must provide input in the form \"x y\"."); 
					continue; 
				}
			}
		}

		//io done

		UniLinkedList<OrderedDoublePair> copy = points.clone(); 

		System.out.println("Centroid = " + centroid(points));
		points.deduplicate();
		System.out.println("Centroid of unique points = " + centroid(points));
		System.out.println("Point closest to the orgin is " + smallest(points));
		System.out.println("Point fartest from the origin is " + largest(points));

		copy.resetPositionCursor(); 

		while (copy.hasNext()) {
			OrderedDoublePair curr = copy.get(); 
			if ((curr.getX() + curr.getY()) % 2 == 0) 
				copy.remove(curr); 
		}
		System.out.println("After filtering out even-summed points, the list is: " + copy);

		OrderedDoublePair centroid = centroid(points), best = points.head();

		double bestDistance = distance(centroid, best); 

		points.resetPositionCursor(); 
		while (points.hasNext()) {
			OrderedDoublePair currPair = points.get(); 
			double currDistance = distance(centroid, currPair); 
			if (currDistance < bestDistance) {
				best = currPair; 
				bestDistance = currDistance; 
			}
		}
		System.out.println("Point closest to the centroid (after deduplication of list data) is " + best);
	}
}
