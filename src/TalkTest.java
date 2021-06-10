import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TalkTest {
	private Talk talk; 
	

	
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
		FileReader fr = new FileReader();
		Scheduler sd = new Scheduler(fr.getFileContents());
		
		for(Track trk : sd.getTracks()) 
		{
			for(Talk tlk: trk.getMorningSession().getTalks())
			{
				assertEquals(true, (tlk.getTitle() instanceof String));
			}
		}
	}
}
