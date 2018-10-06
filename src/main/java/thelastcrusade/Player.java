package thelastcrusade;

import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        int[][] map = new int[W][H];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            String[] split = LINE.split("\\s");
            final int y = i;
            IntStream.range(0, W).parallel().forEach(x-> {
            	map[y][x] = Integer.valueOf(split[x], 10);
            	System.err.println(x);
            });
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
        
        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();
            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            if("LEFT".equals(POS)) {
            	switch(map[YI][XI]) {
            	case 1:
            	case 5:
            	case 8:
            	case 9:
            	case 13:
            		
            		YI++;
            		break;
            	case 2:
            	case 6:
            		XI++;
            		break;
            	}
            }else if("TOP".equals(POS)) {
            	switch(map[XI][YI]) {
            	case 1:
            	case 3:
            	case 7:
            	case 9:
            		YI++;
            		break;
            	case 4:
            	case 10:
            		XI--;
            		break;
            	case 5:
            	case 11:
            		XI++;
            		break;
            	}
            }else if("RIGHT".equals(POS)) {
            	switch(map[XI][YI]) {
            	case 1:
            	case 4:
            	case 7:
            	case 8:
            	case 12:
            		YI++;
            		break;
            	case 2:
            	case 6:
            		XI--;
            		break;
            	}
            }

            // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
            System.out.println(XI + " "+YI);
        }
    }
}
