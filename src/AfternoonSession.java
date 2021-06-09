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
		this.timeOfDay = LocalTime.of(13, 0, 0);
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
			super.getTreeMap().put(this.timeOfDay, t.getTitle());
			this.timeOfDay = this.timeOfDay.plusMinutes(t.getDuration());
			if (this.timeOfDay.compareTo(LocalTime.of(16, 0, 0)) >= 0 &&
				this.timeOfDay.compareTo(LocalTime.of(17, 0, 0)) <= 0)
			{
				super.getTreeMap().put(this.timeOfDay, "Networking Event");
				return;
			}
		}
		
	}
	
	public TreeMap<LocalTime, String> getTreeMap()
	{
		return super.getTreeMap();
	}
	
	public int getLastEvent()
	{
		return super.getTreeMap().size();
	}

	public void printTreeMap() 
	{
	    super.printTreeMap();
	}

}
