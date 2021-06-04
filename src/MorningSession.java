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
			super.getMap().put(this.timeOfDay, t.getTitle());
			this.timeOfDay = this.timeOfDay.plusMinutes(t.getDuration());	
			if (this.timeOfDay.plusMinutes(t.getDuration()).compareTo(LocalTime.of(12, 0, 0)) >= 0) // if clock is 12pm or the Talk exceeds 12 but is less than 1pm 
			{
				super.getMap().put(LocalTime.of(12, 0, 0), "Lunch");
				//	this.timeOfDay = this.timeOfDay.plusMinutes(60);
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