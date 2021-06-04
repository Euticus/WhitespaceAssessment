import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ScheduleTest {

		private Schedule schedule; 
		
		@Test 
		@DisplayName("Scheudle is populated ")
		public void testPopulateTalk() {
			Schedule sd = new Schedule();
			assertEquals(true, sd.getListOfTalks().size() == 0 );
			// sd.setDefaultSchedule();
			assertEquals(true, sd.getListOfTalks().size() > 0);
		}
		
		@Test 
		@DisplayName("Schedule object calculates schedule ")
		public void testCalculateSchedule() {
			Schedule sd = new Schedule();
			assertEquals(true, sd.calculateSchedule().size() == 0);
		//	sd.setDefaultSchedule();
			assertEquals(true, sd.calculateSchedule().size() > 0);
		}
}
