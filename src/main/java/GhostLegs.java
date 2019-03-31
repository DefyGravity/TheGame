import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 
 * @author michm 
 * <code>7 7
A  B  C
|  |  |
|--|  |
|  |--|
|  |--|
|  |  |
1  2  3</code> output:<code>
A2
B1
C3
</code>
 */
public class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int W = in.nextInt();
		int H = in.nextInt();
		List<String> ghostLegs = new ArrayList<>();
		if (in.hasNextLine()) {
			in.nextLine();
		}
		for (int i = 0; i < H; i++) {
			ghostLegs.add(in.nextLine());
		}
		//System.err.println(ghostLegs.stream().collect(Collectors.joining("\n")));
		List<String> labels = Arrays.asList(ghostLegs.get(0).split("\\s+"));
		System.err.println(labels);
		Integer colWidth = ghostLegs.get(0).indexOf(labels.get(1));
		
		String answer = labels.stream().map( label -> {
			Integer x = ghostLegs.get(0).indexOf(label);
			Integer start = new Integer(x);
			Integer y = 1;
			while(y < H && x >-1) {
				if(x-1 >0 && !String.valueOf(ghostLegs.get(y).charAt(x-1)).trim().isEmpty()) {
					x -=colWidth;
				}else if(x+1 < W && !String.valueOf(ghostLegs.get(y).charAt(x+1)).trim().isEmpty()) {
					x +=colWidth;
				}
				y++;
			}
			return new String(label+ ghostLegs.get(H).charAt(start));
		}).collect(Collectors.joining("\n"));
		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		System.out.println(answer);
	}
}
