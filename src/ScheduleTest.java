import static org.junit.Assert.assertEquals;
import java.time.LocalTime;
import java.util.Map.Entry;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ScheduleTest { 
			
		@Test 
		@DisplayName("Each track has a morning session and afternoon session ")
		public void testAMPMtrackSessions() 
		{
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
		
			for(Track t : sd.getTracks()) 
			{
				assertEquals("Lunch", t.getMorningSession().getTreeMap().get(LocalTime.of(12,0,0))); // checks every Talk listed at 12pm is Lunch
			}
		}
		
		@Test 
		@DisplayName("Networking event starts at 5pm latest and 4pm earliest ")
		public void testEODBlock() 
		{
			FileReader fr = new FileReader();
			Scheduler sd = new Scheduler(fr.getFileContents());
			
			for(Track t : sd.getTracks()) 
			{
				Entry<LocalTime, String> lastEntry = t.getAfternoonSession().getTreeMap().lastEntry();
				assertEquals(true, "Networking Event" == lastEntry.getValue()); // checks last value is networking event
				assertEquals(true, (lastEntry.getKey().compareTo(LocalTime.of(16, 0, 0)) >= 0) || (lastEntry.getKey().compareTo(LocalTime.of(17, 0, 0)) <= 0)); // checks if networking event is in between 4 and 5pm
			}
		
		}	
}
