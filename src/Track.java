import java.util.ArrayList;
import java.util.List;

public class Track 
{
	
	private MorningSession ms;
	private AfternoonSession as;
	 
	public Track(List<Talk> list) 
	{
		this.createSessions(list);
	}
	
	
	public void createSessions(List<Talk> talks)
	{
		int duration = 0;
		int index = 0;
		while( index < talks.size())
		{
			duration += talks.get(index).getDuration();
			System.out.println("duration" + duration);
			if(duration == 120 || duration + talks.get(index).getDuration() >= 120 )
			{
				this.ms = new MorningSession(new ArrayList<Talk>(talks.subList(0, index)));
				this.as = new AfternoonSession(new ArrayList<Talk>(talks.subList(index, talks.size())));
			
			}
			index++;
		}
	}
	
	public MorningSession getMorningSession() 
	{
		return this.ms; 
	}
	
	public AfternoonSession getAfternoonSession() 
	{
		return this.as;
	}
	
}
