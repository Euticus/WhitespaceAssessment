import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Scheduler
{
	private List<Talk> allTalks;
	private ArrayList<Track> allTracks = new ArrayList<Track>();
	
	public Scheduler(List<String> talks)
	{
		this.populateTalk(talks);
		this.createTracks();
	}
		
	public void populateTalk(List<String> fileContents) 
	{
		this.allTalks = fileContents.stream().map(content -> {
			String data = handleLightning(content);
			Integer duration = Integer.parseInt(data.replaceAll("[^0-9]+", ""));
			return new Talk(data, duration);
		}).collect(Collectors.toList());;
	}
	
	public void createTracks()
	{
		int duration = 0;
		int index = 0;
		int j = 0;
		while(index <= this.allTalks.size()-1)
		{
			duration += this.allTalks.get(index).getDuration();
			System.out.println("WHYYY " + duration);
			if(duration >= 480 || index == this.allTalks.size()-1)
			{
				Track temp = new Track(this.allTalks.subList(j, index));
				this.allTracks.add(temp);
				j = index;
				duration = 0;
			}
				index++;
		}
	}
	
	public void getSchedule()
	{
		System.out.println("Scheduler getSchedule() " + this.allTracks.size());
		int i = 0;
		for (Track t : this.allTracks)
		{	
			System.out.println("Track " + ++i);
			t.getMorningSession().printTreeMap();
		 	t.getAfternoonSession().printTreeMap();
		}
	}
	
	public ArrayList<Track> getTracks()
	{
		return this.allTracks;
	}
	
	public void getTotalNumTalks()
	{
		System.out.println("Total Num talks passed in " + this.allTalks.size());
	}
	
	public String handleLightning(String talk) 
	{
		Pattern re = Pattern.compile("\\blightning\\b",Pattern.CASE_INSENSITIVE);
		Matcher m = re.matcher(talk);
		talk = m.replaceAll("5min"); // replaces 'lighting' String with '5min' String
		return talk;
	}
}


