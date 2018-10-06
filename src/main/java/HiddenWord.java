import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Solution {

	private static final LinkedList<HiddenWordVector> vectors = init();
	private static LinkedList<HiddenWordVector> init() {

		LinkedList<HiddenWordVector> linkedList = new LinkedList<>();
		linkedList.add(HiddenWordVector.upLeft);
		linkedList.add(HiddenWordVector.left);
		linkedList.add(HiddenWordVector.downLeft);
		linkedList.add(HiddenWordVector.up);
		linkedList.add(HiddenWordVector.down);
		linkedList.add(HiddenWordVector.upRight);
		linkedList.add(HiddenWordVector.right);
		linkedList.add(HiddenWordVector.downRight);
		return linkedList;

	}

	static final public class HiddenWordVector {
		static final HiddenWordVector upLeft = new HiddenWordVector(-1, -1);
		static final HiddenWordVector left = new HiddenWordVector(-1, 0);
		static final HiddenWordVector downLeft = new HiddenWordVector(-1, 1);
		static final HiddenWordVector up = new HiddenWordVector(0, -1);
		static final HiddenWordVector down = new HiddenWordVector(0, 1);
		static final HiddenWordVector upRight = new HiddenWordVector(1, -1);
		static final HiddenWordVector right = new HiddenWordVector(1, 0);
		static final HiddenWordVector downRight = new HiddenWordVector(1, 1);
		
		int x = 0;
		int y = 0;
		
		public HiddenWordVector(HiddenWordLetter current, HiddenWordLetter next) {
			this.x = next.x - current.x;
			this.y = next.y - current.y;
		}

		public HiddenWordVector(int i, int j) {
			this.x = i;
			this.y = j;
		}

	}

	/**
	 * @author michm
	 *
	 */
	public interface HiddenWordLetterListener {
		Character getA();

		Boolean search(String findable, int i, HiddenWordVector next);

		Boolean search(String findable, int i);

	}

	public static class HiddenWordLetter implements HiddenWordLetterListener {
		Integer x;
		Integer y;
		Boolean active;
		Character a;
		private HiddenWordGrid grid;
		

		/**
		 * @return the a
		 */
		public Character getA() {
			return a;
		}

		/**
		 * @param a
		 *            the a to set
		 */
		public void setA(Character a) {
			this.a = a;
		}

		/**
		 * @return the x
		 */
		public Integer getX() {
			return x;
		}

		/**
		 * @param x
		 *            the x to set
		 */
		public void setX(Integer x) {
			this.x = x;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((active == null) ? 0 : active.hashCode());
			result = prime * result + ((x == null) ? 0 : x.hashCode());
			result = prime * result + ((y == null) ? 0 : y.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			HiddenWordLetter other = (HiddenWordLetter) obj;
			if (active == null) {
				if (other.active != null)
					return false;
			} else if (!active.equals(other.active))
				return false;
			if (x == null) {
				if (other.x != null)
					return false;
			} else if (!x.equals(other.x))
				return false;
			if (y == null) {
				if (other.y != null)
					return false;
			} else if (!y.equals(other.y))
				return false;
			return true;
		}

		/**
		 * @return the y
		 */
		public Integer getY() {
			return y;
		}

		/**
		 * @param y
		 *            the y to set
		 */
		public void setY(Integer y) {
			this.y = y;
		}

		/**
		 * @return the active
		 */
		public Boolean getActive() {
			return active;
		}

		/**
		 * @param active
		 *            the active to set
		 */
		public void setActive(Boolean active) {
			this.active = active;
		}

		public HiddenWordLetter(HiddenWordGrid grid, Integer x, Integer h, Character a) {
			this.grid = grid;
			this.grid.addListener(this);
			this.x = x;
			this.y = h;
			this.a = a;
		}

		@Override
		public Boolean search(String findable, int i, HiddenWordVector v) {
			Boolean returnable = false;
			if (a.equals(findable.charAt(i))) {
				i++;
				if (i < findable.length()) {
					HiddenWordLetter next = this.add(v);
					returnable = next.search(findable, i, v);
				} else {
					returnable = true;
				}

			}
			if (returnable) {
				this.active = false;
				System.err.println(this.a);
			}
			return returnable;
		}

		private HiddenWordLetter add(HiddenWordVector v) {
			int xI = this.x + v.x;
			int yI = this.y + v.y;
			HiddenWordLetter hiddenWordLetter = null;
			if (xI >= 0 && xI < this.grid.getWidth() && yI >= 0 && yI < this.grid.getHeight()) {
				hiddenWordLetter = this.grid.getGrid()[xI][yI];
			}
			return hiddenWordLetter;
		}

		@Override
		public Boolean search(String findable, int i) {
			Boolean returnable = this.active.equals(findable.charAt(i));
			int index = i + 1;
			if (returnable && i < findable.length()) {
				Solution.vectors.parallelStream().anyMatch(v -> {
					boolean matched = false;
					HiddenWordLetter add = this.add(v);
					if (add != null) {
						return add.search(findable, index, v);
					}
					return matched;
				});
			}

			return returnable;
		}

		public static HiddenWordLetter instance(HiddenWordGrid hwg, int j, int i, char charAt) {
			
			return new HiddenWordLetter(hwg, j, i, charAt);
		}

	}

	public static class HiddenWordGrid {

		List<HiddenWordLetterListener> listeners = new LinkedList<>();
		Integer width = 0;
		Integer height = 0;
		HiddenWordLetter[][] grid = null;
		

		/**
		 * @return the listeners
		 */
		public List<HiddenWordLetterListener> getListeners() {
			return listeners;
		}

		/**
		 * @param listeners
		 *            the listeners to set
		 */
		public void setListeners(List<HiddenWordLetterListener> listeners) {
			this.listeners = listeners;
		}

		/**
		 * @return the width
		 */
		public Integer getWidth() {
			return width;
		}

		/**
		 * @param width
		 *            the width to set
		 */
		public void setWidth(Integer width) {
			this.width = width;
		}

		/**
		 * @return the height
		 */
		public Integer getHeight() {
			return height;
		}

		/**
		 * @return the grid
		 */
		public HiddenWordLetter[][] getGrid() {
			return grid;
		}

		/**
		 * @param grid
		 *            the grid to set
		 */
		public void setGrid(HiddenWordLetter[][] grid) {
			this.grid = grid;
		}

		/**
		 * @param height
		 *            the height to set
		 */
		public void setHeight(Integer height) {
			this.height = height;
		}

		public HiddenWordGrid(Integer width, Integer height) {
			this.width = width;
			this.height = height;
			this.grid = new HiddenWordLetter[width][height];
		}

		public void addListener(HiddenWordLetter hiddenWordLetter) {
			this.listeners.add(hiddenWordLetter);
		}

		public void startSearch(String findable) {

			this.listeners.parallelStream()
					.filter(hwll -> hwll.search(findable, 0));

		}

	}

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
			for (int j = 0; j < line.length(); j++) {
				HiddenWordLetter hwl = HiddenWordLetter.instance(hwg, j, i, line.charAt(j));
				grid[j][i] = hwl;
			}
		}
		find.parallelStream().forEach(findable -> {
			hwg.startSearch(findable);
		});
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i< w;i++) {
			for(int j =0;j<h;j++) {
				if(grid[j][i].active) {
					sb.append(grid[j][i].a);
				}
			}
		}
		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		System.out.println(sb.toString());
	}
}
