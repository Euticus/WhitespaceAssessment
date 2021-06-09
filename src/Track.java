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
		while(index < talks.size()-1)
		{
	
			if(duration < 180)
			{
				duration += talks.get(index).getDuration();
			}
			if(duration >= 180)
			{
				List<Talk> l1 = talks.subList(0, index);
				List<Talk> l2 = talks.subList(index, talks.size());
				this.ms = new MorningSession(new ArrayList<Talk>(l1));
				this.as = new AfternoonSession(new ArrayList<Talk>(l2));	
				return;
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
