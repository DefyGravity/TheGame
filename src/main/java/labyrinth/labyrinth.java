package labyrinth;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static final String CONTROL_ROOM_INDICATOR = "C";

	public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // number of rows.
        int C = in.nextInt(); // number of columns.
        int A = in.nextInt(); // number of rounds between the time the alarm countdown is activated and the time the alarm goes off.
        int gasLeft = 1200;
        String [] map = new String[R];
        Node beam = null;
        Node controlRoom = null;
        
        // game loop
        while (true) {
            int KR = in.nextInt(); // row where Kirk is located.
            int KC = in.nextInt(); // column where Kirk is located.
            if(beam == null) {
            	beam = new Node(KC,KR);
            }
            for (int i = 0; i < R; i++) {
                String ROW = in.next(); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
                if(ROW.contains(CONTROL_ROOM_INDICATOR)) {
                	controlRoom = new Node(ROW.indexOf(CONTROL_ROOM_INDICATOR), i);
                }
                map[i] = ROW;
            }
            if(controlRoom != null) {
            	Player.calcPath(map, beam, controlRoom);
            }
            
            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println("RIGHT"); // Kirk's next move (UP DOWN LEFT or RIGHT).
            gasLeft -= 1;
        }
    }

	private static void calcPath(String[] map, Node beam, Node controlRoom) {
		HashSet<Node> openList = new HashSet<Node>();
        HashSet<Node> closedList = new HashSet<Node>();
        controlRoom.setH((double) (Math.abs(controlRoom.getX()-beam.getX())+Math.abs(controlRoom.getY()-beam.getY())));
    	openList.add(controlRoom);
	}
	
}
class Node{
	
	int x;
	int y;
	
	Double f;
	Double g;
	Double h;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Double getF() {
		return f;
	}
	public void setF(Double f) {
		this.f = f;
	}
	public Double getG() {
		return g;
	}
	public void setG(Double g) {
		this.g = g;
	}
	public Double getH() {
		return h;
	}
	public void setH(Double h) {
		this.h = h;
	}
	public Node(int destX, int destY) {
		this.x = destX;
		this.y = destY;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f == null) ? 0 : f.hashCode());
		result = prime * result + ((g == null) ? 0 : g.hashCode());
		result = prime * result + ((h == null) ? 0 : h.hashCode());
	
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (f == null) {
			if (other.f != null)
				return false;
		} else if (!f.equals(other.f))
			return false;
		if (g == null) {
			if (other.g != null)
				return false;
		} else if (!g.equals(other.g))
			return false;
		if (h == null) {
			if (other.h != null)
				return false;
		} else if (!h.equals(other.h))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}