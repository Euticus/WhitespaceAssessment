import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MorningSession extends Session{

	private LocalTime timeOfDay;
	
	public MorningSession(ArrayList<Talk> talks)
	{
		super(talks, LocalTime.of(9, 0,0));
		this.timeOfDay = LocalTime.of(9, 0, 0);
		this.populateHMap();
	}
	
	public MorningSession()
	{
		super(LocalTime.of(9, 0, 0));
		this.timeOfDay = LocalTime.of(9, 0, 0);
		this.populateHMap();
	}
	
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
		}
		super.getTreeMap().put(LocalTime.of(12, 0, 0), "Lunch");
		return;
	}
	
	public TreeMap<LocalTime, String> getTreeMap()
	{
		return super.getTreeMap();
	}
	
	public void printTreeMap() 
	{
	    super.printTreeMap();
	}
	

}
