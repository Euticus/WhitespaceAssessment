import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AfternoonSession extends Session {

	private LocalTime timeOfDay;

	public AfternoonSession(ArrayList<Talk> talks) {
		super(talks, LocalTime.of(13, 0,0));
		this.timeOfDay = LocalTime.of(13, 0, 0);
		this.populateHMap();
	}
	
	public AfternoonSession()
	{
		super(LocalTime.of(13, 0, 0));
		this.populateHMap();
	}

	@Override
	public LocalTime getTime() {
		return this.timeOfDay;
	}

	@Override
	public void populateHMap() 
	{
		for(Talk t: super.getTalks())
		{
			super.getMap().put(this.timeOfDay, t.getTitle());
			this.timeOfDay = this.timeOfDay.plusMinutes(t.getDuration());
			if (this.timeOfDay.plusMinutes(t.getDuration()).compareTo(LocalTime.of(16, 0, 0)) >= 0 &&
				this.timeOfDay.plusMinutes(t.getDuration()).compareTo(LocalTime.of(17, 0, 0)) <= 0)
			{
				super.getMap().put(this.timeOfDay, "Network Event");
			}
		}
		
	}
	
	public TreeMap<LocalTime, String> getHashMap()
	{
		return super.getMap();
	}

	public void printHMap() 
	{
	    super.printHMap();
	}

}
