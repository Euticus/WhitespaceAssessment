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
		sumUp(this.allTalks, 180, 420);
	
	}
	
	public void sumUp(List<Talk> talks, int target, int target2)
	{
		List<Talk> talkscopy = talks;
		boolean AMcreated = false;
		int duration = 0;
		int j = 0;
		int i = 0;
	 
	    while(i < talkscopy.size())
	    {
	    	System.out.println("this is i " + i);
	    	System.out.println("this is j " + j);
	    	if(duration < target2)
	    	{
	    		duration += talkscopy.get(i).getDuration();
	    	}
	    	if (duration >= target && !AMcreated)
	    	{
	    		this.tempAMTalkList = talkscopy.subList(j, i);
	    		j = i;
	    		AMcreated = !AMcreated;
	    	}
	    	if (duration >= target2 && AMcreated)
	    	{
	    		this.tempPMTalkList = talkscopy.subList(j, i);
	    	}
	    	if(this.tempAMTalkList.size() > 0 && this.tempPMTalkList.size() > 0)
	    	{
	    		Track temp = new Track(new MorningSession(new ArrayList<Talk>(this.tempAMTalkList)), new AfternoonSession(new ArrayList<Talk>(this.tempPMTalkList)));
				this.allTracks.add(temp);
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


