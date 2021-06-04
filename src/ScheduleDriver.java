import java.util.ArrayList;
import java.util.List;

public class ScheduleDriver 
{

	public static void main(String[] args) 
	{
		FileReader fr = new FileReader();
		Scheduler conference = new Scheduler(fr.getFileContents());  
		conference.populateTalk(fr.getFileContents());
		conference.getSchedule();
	}

}
