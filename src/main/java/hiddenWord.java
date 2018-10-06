import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class hiddenWord {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<String> find = new LinkedList<String>();
        for (int i = 0; i < n; i++) {
            String aword = in.next();
            find.add(aword);
        }
        int h = in.nextInt();
        int w = in.nextInt();
        HiddenWordGrid hwg = new HiddenWordGrid(w, h);
        HiddenWordLetter[][] grid = hwg.getGrid();
        for (int i = 0; i < h; i++) {
            String line = in.next();
            for(int j = 0;j<line.length();j++) {
            	HiddenWordLetter hwl = new HiddenWordLetter(hwg, j, i, line.charAt(j));
            	grid[j][i] = hwl;
            }
        }
        find.parallelStream().forEach(findable->{
        	hwg.startSearch(findable);
        });

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("word");
    }
}