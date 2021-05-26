
public class Talk {
	
	private String title;
	private int duration;
	
	public Talk(String title, int duration) {
		this.title = title;
		this.duration = duration; 
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public int getDuration() {
		return this.duration;
	}
}
