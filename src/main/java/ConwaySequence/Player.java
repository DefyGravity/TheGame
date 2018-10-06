import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.print.DocFlavor.STRING;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {
	// https://stackoverflow.com/q/23523597/176818
	private static final String regex = "(?<=(\\d?+))(?!\\1) ";
	java.util.regex.Pattern p = Pattern.compile(regex);

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int R = in.nextInt();
		int L = in.nextInt();

		String answer = String.valueOf(R);
		if (L > 1) {
			answer = "1 " + answer;
		}
		if (L > 2) {
			for (int i = 1; i < L; i++) {
				answer = LookAndSayUsingForLoop(answer);
			}

		}

		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		System.out.println(answer.trim());
	}

	public static String LookAndSayUsingForLoop(final String number) {
		if (null == number || number.isEmpty()) {
			return "";
		}
		String[] split = number.split(" ");
		LinkedList<String> splits = new LinkedList<String>(Arrays.asList(split));
		
		LinkedList<LinkedList<String>> numbers= new LinkedList<LinkedList<String>>();
		LinkedList<String> list = new LinkedList<String>();
		list.add(splits.removeFirst());
		numbers.add(list);
		int index = 0;
		for (String current : split) {
			if(numbers.getLast().contains(current)) {
				numbers.getLast().add(current);
			}else {
				list = new LinkedList<String>();
				list.add(current);
				numbers.offer(list);
			}
			
		}
		System.err.println(numbers);
		list = new LinkedList<String>();
		for(LinkedList<String> printMe:numbers) {
			list.add(String.valueOf(printMe.size()));
			list.add(printMe.removeFirst());
		}
		return String.join(" ", list);
	}
}
