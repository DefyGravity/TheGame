import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int L = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}
		List<String> square = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String LINE = in.nextLine();
			// rip out the whitespace
			square.add(LINE.replaceAll("\\s", ""));
		}
		
		// I only use the 2D things because it makes the code easy to read, hate their
		// internal methods though
		Point2D.Double origin = new Point2D.Double(0, 0);
		final char c = 'C';
		// coords of the candles
		final List<Double> candles = map(N, origin).filter(coord -> {
			return c== square.get((int) coord.getY()).charAt((int) coord.getX());
		}).collect(Collectors.toList());

		final long count = map(N, origin).filter(coord -> {

			return candles.parallelStream().allMatch(candle -> {
				int distance = (int) (Point2D.distance(coord.getX(), coord.getY(), candle.getX(), candle.getY()));
				return Integer.compare(distance, L) >= 0;
			});
		}).count();

		System.out.println(count);
	}

	/**
	 * @param N
	 *            size of the square
	 * @param origin
	 *            coords you want to start from
	 * @return THEIR sacrificial hall
	 */
	private static Stream<Double> map(int N, Point2D.Double origin) {
		return Stream.iterate(origin, point -> {

			if (((int) point.getX() + 1) % N == 0)
				return new Point2D.Double(0, point.getY() + 1);
			return new Point2D.Double(point.getX() + 1, point.getY());

		}).limit(N * N);
	}
}