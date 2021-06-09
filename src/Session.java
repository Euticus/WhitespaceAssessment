import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class Session {

	private ArrayList<Talk> talks;
	private LocalTime timeOfDay;
	private TreeMap<LocalTime, String> map = new TreeMap<LocalTime, String>();
	
	public Session(ArrayList<Talk> talks, LocalTime lt)
	{
		this.talks = talks;
		this.timeOfDay = lt;
	}
	
	public Session(LocalTime lt)
	{
		this.talks = new ArrayList<Talk>();
		this.timeOfDay = lt;
	}
	
	abstract LocalTime getTime();
	
	abstract void populateHMap();
	
	public ArrayList<Talk> getTalks()
	{
		return talks;
	}
	
	public TreeMap<LocalTime, String> getTreeMap()
	{
		return this.map;
	}
	
	public void printTreeMap() {
	      Set set = this.map.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	      }
		
	}
}
