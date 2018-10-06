/**
 * 
 */

/**
 * @author michm
 *
 */
public interface HiddenWordLetterListener {
	 Character getA();


	Boolean search(String findable, int i, HiddenWordVector next);


	Boolean search(String findable, int i);

	
}
