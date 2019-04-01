import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RiverOfDigitsIITest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		int r1 = 20;
		Stream<Integer> potentials = Stream.iterate(1, r -> r++).limit(r1);
		assertTrue(potentials.count()==r1);	
		potentials = Stream.iterate(1, r -> r++).limit(r1);
		assertTrue(potentials.parallel()
				.map(r -> Stream.iterate(1, RiverOfDigitsII.riverOfDigitsFactory())).count() ==r1);
		potentials = Stream.iterate(1, r -> r++).limit(r1);
		assertTrue(potentials.parallel()
				.map(r -> Stream.iterate(1, RiverOfDigitsII.riverOfDigitsFactory()).max));
	}

}
