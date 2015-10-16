package homeworkTwo;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */

/**
 * Util class which handles converstion from 
 * infix Expressions to postFix Expressions
 * @author Patrick Wamsley
 */
public class InfixToPostfixConverter {

	/* 
	 * Do not allow InfixToPostfixConverters to be instantiated 
	 * because this class should only be used statically. 
	 */
	private InfixToPostfixConverter() {}

	/**
	 * Converts a infix expression to a postfix expression
	 * @param infix expression
	 * @return postfix expression as a String
	 */
	public static String convert(char[] infix) {

		StringBuilder builder = new StringBuilder(); 

		Stack<Operators> operators = new Stack<>(); 

		/*takes deep breath*/
		for (int i = 0; i < infix.length; i++) {
			char currChar = infix[i]; 
			if (Character.isDigit(currChar)) 
				builder.append(currChar + " ");  
			else {
				Operators nextOp = getOperator(currChar); 
				if (nextOp == null) { 
					continue; //skip spaces
				} else if (operators.isEmpty() || operators.peek() == Operators.LEFT_PARENTHESIS ||
						nextOp == Operators.LEFT_PARENTHESIS) {
					operators.push(nextOp); 
				} else if (nextOp == Operators.RIGHT_PARENTHESIS) {
					Operators currOp = operators.pop(); 
					do {
						builder.append(currOp.getCharacter() + " "); 
						currOp = operators.pop();
					} while(currOp != Operators.LEFT_PARENTHESIS); 
				} else if (nextOp.hasPrecedenceOver(operators.peek())) {
					operators.push(nextOp); 
				} else if (nextOp.hasSamePrecedence(operators.peek())) {
					builder.append(operators.pop().getCharacter() + " ");
					operators.push(nextOp);
				} else { //has lower precedence
					builder.append(operators.pop().getCharacter() + " "); 
					i--; //need to compare to the top op on the stack so redo this operator. 
				}
			}
		}
		//finish
		while (!(operators.isEmpty())) 
			builder.append(operators.pop().getCharacter() + " "); 

		return builder.toString(); 
	}

	/**
	 * Returns the correct Operator object 
	 * corresponding to the {@code c} 
	 * @param operator character
	 * @return Operator if valid character, null if space
	 * @throws IllegalArgumentException if the infix expression is invalid. 
	 */
	private static Operators getOperator(char c) {
		switch (c) {
			case '+':
				return Operators.PLUS; 
			case '-':
				return Operators.MINUS; 
			case '*': 
				return Operators.TIMES; 
			case '/':
				return Operators.DIVIDE; 
			case '%':
				return Operators.MOD; 
			case '(':
				return Operators.LEFT_PARENTHESIS; 
			case ')':
				return Operators.RIGHT_PARENTHESIS; 
			case ' ':
				return null; 
			default:
				throw new IllegalArgumentException("Infix expression invalid"); 
		}
	}


}