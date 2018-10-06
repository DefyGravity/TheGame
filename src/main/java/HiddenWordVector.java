
public class HiddenWordVector {
	int x =0;
	int y =0;
	
	public HiddenWordVector(HiddenWordLetter current, HiddenWordLetter next) {
		this.x = next.x - current.x;
		this.y = next.y - current.y;
	}

	public HiddenWordVector(int i, int j) {
		this.x =i;
		this.y = j;
	}
	

}
