package stockexchange;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int stockCount = in.nextInt();
		List<Integer> values = new LinkedList<Integer>();
		HashMap<Integer, List<Integer>> subLists = new HashMap<>(stockCount);
		for (int i = 0; i < stockCount; i++) {
			values.add(in.nextInt());
		}
		values = values.stream().distinct().collect(Collectors.toList());
		int output = 0;
		while (values.isEmpty() == false) {
			Integer min = Collections.min(values);
			int indexOfMin = values.indexOf(min);
			if (indexOfMin > 0) {
				List<Integer> subList = values.subList(0, indexOfMin);

				Integer max = Collections.max(subList);
				if (min - max < output) {
					output = min - max;
				}
				subList.clear();
			} else if (indexOfMin == 0) {
				values.remove(0);
			}

		}

		System.out.println(output);
	}

}
