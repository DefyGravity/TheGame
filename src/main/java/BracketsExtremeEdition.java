import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

	/**shamelessly stolen from Stackoverflow after I refused to go recursive
	 * https://stackoverflow.com/questions/28127676/find-if-the-brackets-are-properly-nested-aligned-java
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String expression = in.next();
		String openingBrackets = "([{";
		String closingBrackets = ")]}";
		Stack<Character> stack = new Stack<>();
		char[] characters = expression.toCharArray();
		Boolean ret = Boolean.TRUE;
		for (char c : characters) {
			if(ret ==Boolean.FALSE)
				break;
			if (openingBrackets.indexOf(c) != -1) {
				stack.push(c);
			} else if (closingBrackets.indexOf(c) != -1) {
				if(stack.isEmpty()|| Math.abs(stack.pop()-c)>2) {
					ret = Boolean.FALSE;

				}
			}
		}
		System.out.println( ret && stack.isEmpty());
	}

}