import java.util.LinkedList;

public class HiddenWordLetter implements HiddenWordLetterListener {
	Integer x;
	Integer y;
	Boolean active;
	Character a;
	private HiddenWordGrid grid;
	private static LinkedList<HiddenWordVector> vectors;
	{
		HiddenWordVector upLeft = new HiddenWordVector(-1, -1);
		HiddenWordVector left = new HiddenWordVector(-1, 0);
		HiddenWordVector downLeft = new HiddenWordVector(-1, 1);
		HiddenWordVector up = new HiddenWordVector(0, -1);
		HiddenWordVector down = new HiddenWordVector(0, 1);
		HiddenWordVector upRight = new HiddenWordVector(1, -1);
		HiddenWordVector right = new HiddenWordVector(1, 0);
		HiddenWordVector downRight = new HiddenWordVector(1, 1);
		this.vectors = new LinkedList<>();
		vectors.add(upLeft);
		vectors.add(left);
		vectors.add(downLeft);
		vectors.add(up);
		vectors.add(down);
		vectors.add(upRight);
		vectors.add(right);
		vectors.add(downRight);
	}

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
			HiddenWordLetter.vectors.parallelStream().anyMatch( v ->{
				boolean matched = false;
				HiddenWordLetter add = this.add(v);
				if(add != null) {
					return add.search(findable, index, v);
				}
				return matched;
			});
		}

		return returnable;
	}

}
