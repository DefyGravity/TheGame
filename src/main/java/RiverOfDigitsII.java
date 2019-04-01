import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class RiverOfDigitsII {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int r1 = in.nextInt();

		final Stream<Integer> potentials = Stream.iterate(1, r -> r++).limit(r1);
		final Stream<Integer> iterate = Stream.iterate(1, riverOfDigitsFactory());
		
		Integer count = 0;
		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");
		
		System.out.println(count > 1 ? "YES" : "NO");
	}

	/**
	 * @return
	 */
	public static UnaryOperator<Integer> riverOfDigitsFactory() {
		final UnaryOperator<Integer> unaryOperator = r -> {
			Integer sum = String.valueOf(r).chars().map(c -> Character.getNumericValue(c)).sum();
			return r + sum;
		};
		return unaryOperator;
	}
}