package homeworkTwo;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
import java.util.Scanner;

/**
 * Util class which evaluates post fix expressions. 
 * 
 * @author Patrick Wamsley
 */
public class PostFixEvaluator {

	/* 
	 * Do not allow PostFixEvaluator to be instantiated 
	 * because this class should only be used statically. 
	 */
	private PostFixEvaluator() {}
	
	/**
	 * <b>Assumes PostFix expression is correctly formatted
	 * and that numbers are all one digit. </b>
	 * @param postFix expression. 
	 * @return value of postfix expression
	 */
	public static int evaluate(String postFix) {
		
		Stack<Integer> stack = new Stack<>(); 
		
		for (char currChar : postFix.toCharArray()) {
			if (currChar == ' ')
				continue; 
			if (Character.isDigit(currChar)) 
				stack.push(Integer.parseInt("" + currChar)); //don't cast because char value might not map to its integer value
			else {
				
				int onTop = stack.pop();
		        int secondOnTop = stack.pop();  
	
		        if (currChar == '+') 
		        	stack.push(secondOnTop + onTop);
		        else if (currChar == '-') 
		        	stack.push(secondOnTop - onTop);
		        else if (currChar == '*') 
		        	stack.push(secondOnTop * onTop);
		        else if (currChar == '/') 
		        	stack.push(secondOnTop / onTop);
		        else if (currChar == '%') 
		        	stack.push(secondOnTop % onTop);
			}
		}
		return stack.pop(); 
	}
	
	/**
	 * Something something something homework
	 */
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); 
		
		while (true) {
			System.out.println("Enter in Infix Expression or type q to quit.");
			
			String currLine = scanner.nextLine();
			
			if (currLine.equalsIgnoreCase("q"))
				break; 
			
			String postFix = InfixToPostfixConverter.convert(currLine.toCharArray()); 
			System.out.println("PostFix: " + postFix + " value: " + evaluate(postFix));
			
		}
	}
}
