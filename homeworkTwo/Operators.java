package homeworkTwo;
/**
 * Patrick Wamsley 
 * 110539155
 * CSE 214 (2) 
 * TA: Mingchen Zhang
 */
/**
 * Contains all Operators being implemented including
 * +, -, *, /, %, and although they aren't really operators, ( and )
 * @author Patrick Wamsley
 */
public enum Operators {

	/*
	 * Maybe this is a little verbose and overkill for this application,
	 * but I like the style and clarity.  
	 */

	PLUS('+'), MINUS('-'), TIMES('*'), DIVIDE('/'), MOD('%'),  
	LEFT_PARENTHESIS('('), RIGHT_PARENTHESIS(')'); 

	private final int precedence; 
	private final char character; 

	private Operators(char character) {

		this.character = character; 

		if (character == '+' || character == '-')
			precedence = 0; 
		else if (character == '*' || character == '/' || character == '%')
			precedence = 1; 
		else 
			precedence = 2; 
	}

	public boolean hasPrecedenceOver(Operators other) {
		return precedence > other.precedence; 
	}

	public boolean hasSamePrecedence(Operators other) {
		return precedence == other.precedence; 
	}

	public char getCharacter() {
		return character; 
	}

}