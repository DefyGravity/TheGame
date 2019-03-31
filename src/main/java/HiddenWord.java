import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		List<String> words = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			String aword = in.next();
			System.err.println(aword);
			words.add(aword.toUpperCase());
		}
		int h = in.nextInt();
		int w = in.nextInt();
		System.err.println("==================");
		ArrayList<String> grid = new ArrayList<>(h);
		StringBuilder wholeList = new StringBuilder();
		for (int i = 0; i < h; i++) {
			String line = in.next();
			System.err.println(line);
			grid.add(line.toLowerCase());
			wholeList.append(line.toUpperCase());
		}
		System.err.println("==================");
		wholeList = lowerCaseFoundWords(words, wholeList);
		wholeList = wholeList.reverse();
		wholeList = lowerCaseFoundWords(words, wholeList);
		wholeList = wholeList.reverse();
		StringBuilder diagonal = diagonalTranspose(h, w, wholeList);
		diagonal = lowerCaseFoundWords(words, diagonal);
		diagonal = diagonal.reverse();
		diagonal = lowerCaseFoundWords(words, diagonal);
		diagonal = diagonal.reverse();
		wholeList = involutionDiagonal(h, w, diagonal);
		StringBuilder upDown = transpose(h, w, wholeList);
		upDown = lowerCaseFoundWords(words, upDown);
		upDown = upDown.reverse();
		upDown = lowerCaseFoundWords(words, upDown);
		upDown = upDown.reverse();
		diagonal = diagonalTranspose(h, w, upDown);
		diagonal = lowerCaseFoundWords(words, diagonal);
		diagonal = diagonal.reverse();
		diagonal = lowerCaseFoundWords(words, diagonal);
		diagonal = diagonal.reverse();
		upDown = involutionDiagonal(h, w, diagonal);
		wholeList = transpose(h, w, upDown);
		
		System.out.println(wholeList.codePoints().filter(x -> Character.isUpperCase(x))
				.mapToObj(cp -> Character.toChars(cp))
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString().toUpperCase());
	}

	/**
	 * @param h
	 * @param w
	 * @param diagonal
	 * @return
	 */
	public static StringBuilder involutionDiagonal(int h, int w, StringBuilder diagonal) {
		StringBuilder wholeList = new StringBuilder(diagonal.length());
		wholeList.setLength(diagonal.length());
		int count=0;
		while(count < w*h ) {
			int location = (count*(w+1))% diagonal.length();
			wholeList.replace(location, location+1, diagonal.substring(count, count+1));
			count++;
		}
		return wholeList;
	}
	/**
	 * @param h
	 * @param w
	 * @param wholeList
	 * @return
	 */
	public static StringBuilder diagonalTranspose(int h, int w, StringBuilder wholeList) {
		StringBuilder diagonal = new StringBuilder();
		int count=0;
		while(count < w*h ) {
			diagonal.append(wholeList.charAt((count*(w+1))% wholeList.length()));
			count++;
		}
		return diagonal;
	}

	/**
	 * @param h
	 * @param w
	 * @param source
	 * @return TODO
	 */
	public static StringBuilder transpose(int h, int w, StringBuilder source) {
		StringBuilder destination = new StringBuilder();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				destination.append(source.charAt(i + j * w));
			}
		}
		return destination;
	}

	/**
	 * @param words
	 * @param linearGrid
	 * @return
	 */
	public static StringBuilder lowerCaseFoundWords(List<String> words, StringBuilder linearGrid) {
		for(String word:words) {
			linearGrid = new StringBuilder(linearGrid.toString().replaceAll("(?i)"+word, word.toLowerCase()));
		}
		return linearGrid;
	}
}