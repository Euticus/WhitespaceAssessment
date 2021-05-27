import java.util.ArrayList;

public class Track 
{
	private ArrayList<Talk> morningSession = new ArrayList<Talk>();
	private ArrayList<Talk> afternoonSession = new ArrayList<Talk>();
	private int duration = 0;
	
	public Track(ArrayList<Talk> amTalks, ArrayList<Talk> pmTalks)
	{
		this.morningSession = amTalks;
		this.afternoonSession = pmTalks;
		
	}
}
