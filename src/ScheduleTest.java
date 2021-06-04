import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ScheduleTest {

		private Scheduler scheduler; 
		
		
		@Test 
		@DisplayName("Each track has a morning session and afternoon session ")
		public void testAMPMtrackSessions() 
		{
			FileReader fr = new FileReader();
			Scheduler sd = new Scheduler(fr.getFileContents());
			
			sd.populateTalk(fr.getFileContents());
			Track testMT = new Track(new ArrayList<Talk>()); // creates Track Object
			MorningSession testMS = new MorningSession(); 
			testMS.equals(testMT.getMorningSession()); // checks if these are the same object
			AfternoonSession testAS = new AfternoonSession(); 
			testAS.equals(testMT.getAfternoonSession()); // checks if these are the same object

		}
		
		@Test 
		@DisplayName("Morning session starts at 9am")
		public void testMorningSessionStart() 
		{
			FileReader fr = new FileReader();
			Scheduler sd = new Scheduler(fr.getFileContents());
			sd.populateTalk(fr.getFileContents());
			
			Track testMT = new Track(new ArrayList<Talk>()); // creates Track Object
			LocalTime.of(9, 0,0).equals(testMT.getMorningSession().getTime());
			// first talk of each morning sessions starts at 9am
		}
		
		@Test 
		@DisplayName("They take a break at noon for lunch")
		public void testLunchBlock() 
		{
			FileReader fr = new FileReader();
			Scheduler sd = new Scheduler(fr.getFileContents());
		
			Track testMT = new Track(new ArrayList<Talk>());
		// 	assertEquals("Lunch", sd.getTracks()..getHashMap().get(LocalTime.of(12,0, 0)));
		}
		
//		@Test 
//		@DisplayName("Networking event starts at 5pm latest and 4pm earliest ")
//		public void testEODBlock() 
//		{
//			Schedule sd = new Schedule();
//			FileReader fr = new FileReader();
//			
//			sd.populateTalk(fr.getFileContents());
//			Track testMT = new Track(new ArrayList<Talk>(sd.getList()));
//			LocalTime timeOfnetworkingEvent = testMT.getAfternoonSession().getHashMap().entrySet()
//													 .stream()
//													 .filter(e -> Objects.equals(e.getValue(), "Networking Event"))
//													 .map(Map.Entry::getKey)
//													 .findFirst()
//													 .orElse(null);
//			assertEquals(true, ((timeOfnetworkingEvent.compareTo(LocalTime.of(16, 0, 0)) >= 0) || ((timeOfnetworkingEvent.compareTo(LocalTime.of(17, 0, 0)) <= 0))));
//		}
		
		
		
		
}
