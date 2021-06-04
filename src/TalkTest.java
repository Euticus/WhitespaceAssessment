
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

public class TalkTest {
	private Talk talk; 
	
//	@BeforeAll
//	public  void setUp() throws Exception {
//		talk = new Talk();
//	}
	
	@Test
	@DisplayName("Talk object should instatntiate with testing as title and 45 min as duration")
	public void testClassAttributes() {
		talk = new Talk();
		assertEquals(45, talk.getDuration(), "displays duration");
		assertEquals("testing", talk.getTitle(), "displays title");
	}
	
	@Test 
	@DisplayName("No talk title has numbers in it ")
	public void testIsTalkString() {
		// each Talk title is a string
	}
}
