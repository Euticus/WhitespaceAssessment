import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Scheduler
{
	private List<Talk> allTalks;
	private List<Talk> tempAMTalkList = new ArrayList<Talk>(); 
	private List<Talk> tempPMTalkList = new ArrayList<Talk>();
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
		sumUp(this.allTalks, 180, 180);
	}
	
	public void sumUp(List<Talk> talks, int target, int target2)
	{
		List<Talk> talkscopy = new ArrayList<Talk>(talks);
		boolean amCreated = false;
		int duration = 0;
		int j = 0;
		int i = 0;
	 
	    while(i < talkscopy.size())
	    {
	    	duration += talkscopy.get(i).getDuration();
	    	if (duration >= target && !amCreated)
	    	{
	    		this.tempAMTalkList = talkscopy.subList(j, i);
	    		j = i;
	    		duration = 0;
	    		amCreated = !amCreated;
	    	}
	    	if (duration >= target2 || i == talkscopy.size()-1 && amCreated)
	    	{
	    		this.tempPMTalkList = talkscopy.subList(j, i);
	    		j = i;
	    		duration = 0;
	    		amCreated = !amCreated;
	    	}
	    	if(this.tempAMTalkList.size() > 0 && this.tempPMTalkList.size() > 0)
	    	{
	    		Track temp = new Track(new MorningSession(new ArrayList<Talk>(this.tempAMTalkList)), new AfternoonSession(new ArrayList<Talk>(this.tempPMTalkList)));
	    		this.allTracks.add(temp);
	    		this.tempAMTalkList = new ArrayList<Talk>();
	    		this.tempPMTalkList = new ArrayList<Talk>();
	    	}
	    	i++;
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
	
	public String handleLightning(String talk) 
	{
		Pattern re = Pattern.compile("\\blightning\\b",Pattern.CASE_INSENSITIVE);
		Matcher m = re.matcher(talk);
		talk = m.replaceAll("5min"); // replaces 'lighting' String with '5min' String
		return talk;
	}
}


