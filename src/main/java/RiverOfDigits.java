import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		long r1 = in.nextLong();
		long r2 = in.nextLong();
		System.err.println(r1 + "," + r2);
		final UnaryOperator<Long> unaryOperator = riverOfDigitsFactory();
		// infinite stream 1
		final Stream<Long> r1Stream = Stream.iterate(r1, unaryOperator);
		// infinite stream 2
		final Stream<Long> r2Stream = Stream.iterate(r2, unaryOperator);
		Optional<Long> findFirst = null;
		final Stream<Long> both = Stream.concat(r1Stream, r2Stream);
		final HashSet<Long> filterme = new HashSet<>();
		
		findFirst = both.parallel().filter(r -> !filterme.add(r)).findFirst();
		
		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		System.out.println(findFirst.get());
	}

	/**
	 * @return
	 */
	public static UnaryOperator<Long> riverOfDigitsFactory() {
		final UnaryOperator<Long> unaryOperator = r -> {
			Integer sum = String.valueOf(r).chars().map(c -> Character.getNumericValue(c)).sum();
			return r + sum;
		};
		return unaryOperator;
	}
}