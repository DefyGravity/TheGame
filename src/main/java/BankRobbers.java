import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int V = in.nextInt();
        Double time = 0.0;
        System.err.println(R+","+V);
        List<Double> combos = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            int C = in.nextInt();
            int N = in.nextInt();
            combos.add(Math.pow(10, N)*Math.pow(5, C-N));
        }
        
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        
        System.out.println(time.intValue());
    }
}