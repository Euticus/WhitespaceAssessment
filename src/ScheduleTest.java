import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
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
		
			Track testMT = new Track(new MorningSession(), new AfternoonSession()); // creates Track Object
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
			
			for(Track t : sd.getTracks()) {
				LocalTime.of(9, 0,0).equals(t.getMorningSession().getTime());
			}
		}
		
		@Test 
		@DisplayName("They take a break at noon for lunch")
		public void testLunchBlock() 
		{
			FileReader fr = new FileReader();
			Scheduler sd = new Scheduler(fr.getFileContents());
		
			for(Track t : sd.getTracks()) {
				assertEquals("Lunch", t.getMorningSession().getTreeMap().get(LocalTime.of(12,0,0)));
			}
		}
		
		@Test 
		@DisplayName("Networking event starts at 5pm latest and 4pm earliest ")
		public void testEODBlock() 
		{
			FileReader fr = new FileReader();
			Scheduler sd = new Scheduler(fr.getFileContents());
			
			for(Track t : sd.getTracks()) {
				Entry<LocalTime, String> lastEntry = t.getAfternoonSession().getTreeMap().lastEntry();
				assertEquals(true, "Networking Event" == lastEntry.getValue());
				assertEquals(true, (lastEntry.getKey().compareTo(LocalTime.of(16, 0, 0)) >= 0) || (lastEntry.getKey().compareTo(LocalTime.of(17, 0, 0)) <= 0));
		}
		
		
		
		}	
}
