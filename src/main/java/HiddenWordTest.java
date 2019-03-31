import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author michm
 *
 */
class HiddenWordTest {

	private StringBuilder wholeList;
	private static final int h = 12;
	private static final int w = 12;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		 wholeList = new StringBuilder(
				"VCENJUGUENTO" + 
		 		"OCORCSEERRAN" + 
		 		"SOCHASSIGNET" + 
		 		"SDRAHCUAFDND" + 
		 		"AENMALMENEES" + 
		 		"BSETINMUIDEM" + 
		 		"VOLLEYBALLTG" + 
		 		"COLATURESAUM" + 
		 		"ISEMAEGROGER" + 
		 		"SSURSITESERE" + 
		 		"TEGNITRIFIAT" + 
		 		"ENILECOUSAIS");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void transpose() {
		StringBuilder diagonalTranspose = Solution.diagonalTranspose(h, w, wholeList);
		assertTrue("VCCAABNBEOEAS".equals(diagonalTranspose.toString()));
		StringBuilder involutionDiagonal = Solution.involutionDiagonal(h, w, diagonalTranspose);
		assertTrue(wholeList.toString().equals(involutionDiagonal.toString()));
	}

	@Test
	void lowerCase() {
		String[] words = {
				"BASSOV" + 
				"CELINE" + 
				"CHASSIGNET" + 
				"CISTE" + 
				"COLATURES" + 
				"COUSAIS" + 
				"EAMES" + 
				"ENJUGUENT" + 
				"ERES" + 
				"ESCROC" + 
				"FAUCHARDS" + 
				"GRAUBUNDEN" + 
				"INSERAIENT" + 
				"MALMENEES" + 
				"MEDIUMNITES" + 
				"NARREES" + 
				"NITRIFIAT" + 
				"ODES" + 
				"REGORGEA" + 
				"SURSITES" + 
				"TUERA" + 
				"VOLLEYBALL"};
		StringBuilder lowerCaseFoundWords = Solution.lowerCaseFoundWords(Arrays.asList(words), wholeList);
		assertTrue("CODNGAME".equals(lowerCaseFoundWords.toString()));
		
		Map<String, Entry<String, String>> treeMap = new TreeMap<String, Entry<String, String>>();
		
		for (Map.Entry<String, Entry<String,String>> entry:treeMap.entrySet()) {
			String imTheKey = entry.getKey();
			Entry<String,String> imTheNestEntry = entry.getValue();
			
		}
		
		}
}
