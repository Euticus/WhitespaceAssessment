public class Track 
{
	
	private MorningSession ms;
	private AfternoonSession as;
	 
	public Track(MorningSession ms, AfternoonSession as) 
	{
		this.ms = ms;
		this.as = as;
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
