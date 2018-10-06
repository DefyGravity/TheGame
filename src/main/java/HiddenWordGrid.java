import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class HiddenWordGrid {

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
		
		Stream<HiddenWordLetterListener> filter = this.listeners.parallelStream().filter(hwll-> hwll.search(findable,0));
		

	}

}
