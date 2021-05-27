import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleDriver 
{
	public static String pattern = "hh:mm a";
	static ArrayList<Talk> listOfTalks = new ArrayList<>();
	public static int nonce = 0;
	
	public static void populateTalk(Scanner sc) 
	{
		while (sc.hasNextLine()) 
		{
			String data = sc.nextLine();
			data = handleLightning(data);
			Integer duration = Integer.parseInt(data.replaceAll("[^0-9]+", ""));
			Talk talk = new Talk(data, duration);
			listOfTalks.add(talk);
		}
	}
	
	public static void conferenceScheduler(ArrayList<Talk> talks) 
	{
		int countMin = 0; // count minutes of each talk
		int j = 0; // records most recent index of i
		for(int i = 0; i < talks.size(); i++)
		{
			countMin += talks.get(i).getDuration(); 
			if (countMin == 540 || (countMin + talks.get(i).getDuration()) >= 540) // if minutes equal 540 or the next value makes it above 540 
			{
				ArrayList<Talk> temp = new ArrayList<Talk>(talks.subList(j, i)); 
				printTrack(temp);
				j = i; // sets j to position i for next iteration
				countMin = 0; //resets for next ArrayList<Talk>
			}
			if(i >= talks.size()-1) // if last loop of talks
			{
				ArrayList<Talk> temp = new ArrayList<Talk>(talks.subList(j-1, talks.size()));
				printTrack(temp);
		
			}
		}
	}
	
	public static void printTrack(ArrayList<Talk> talks) 
	{
		LocalTime clock = LocalTime.of(9, 0, 0);
		clock.format(DateTimeFormatter.ofPattern(pattern));
		System.out.println("Track " + ++nonce); 
		for(int i = 0; i < talks.size(); i++) 
		{
			handleLightning(talks.get(i)); // ensures 'lightning' is stored and read properly upon printing
			if (clock == LocalTime.of(12, 0, 0) || 
				clock.plusMinutes(talks.get(i).getDuration()).compareTo(LocalTime.of(12, 0, 0)) > 0 &&
				clock.plusMinutes(talks.get(i).getDuration()).compareTo(LocalTime.of(13, 0, 0)) < 0 ) // if clock is 12pm or the Talk exceeds 12 but is less than 1pm 
			{
				clock =  LocalTime.of(12, 0, 0);
				System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
				System.out.println("Lunch");
				clock = LocalTime.of(13, 0, 0);
			}
			if (clock.plusMinutes(talks.get(i).getDuration()).compareTo(LocalTime.of(17, 0, 0)) >= 0 ) // if adding another talk exceeds  or equals 5pm
			{
				System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
				System.out.println("Networking Event");
				return;
			}
			if (i == (talks.size()-1)) // if last iteration of ArrayList<Talk>
			{
				System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
				System.out.println(talks.get(i).getTitle());
				clock = clock.plusMinutes(talks.get(i).getDuration());
				System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
				System.out.println("Networking Event");
				return;
			}
			else
			{
				System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
				System.out.println(talks.get(i).getTitle());
				clock = clock.plusMinutes(talks.get(i).getDuration());
			}
		}
	}
	
	public static void handleLightning(Talk talk) // Talk object as argument 
	{
		String temp = talk.getTitle();
		Pattern re = Pattern.compile("\\b5min\\b",Pattern.CASE_INSENSITIVE);
		Matcher m = re.matcher(temp);
		temp = m.replaceAll("lightning"); // replaces '5min' String for 'lightning' String
		talk.setTitle(temp);
	}
	
	public static String handleLightning(String talk) // String as argument
	{
		Pattern re = Pattern.compile("\\blightning\\b",Pattern.CASE_INSENSITIVE);
		Matcher m = re.matcher(talk);
		talk = m.replaceAll("5min"); // replaces 'lighting' String with '5min' String
		return talk;
	}

	public static void main(String[] args) 
	{
		try 
		{
			File myObj = new File("SeedData.txt"); 
			Scanner sc = new Scanner(myObj); 	
			populateTalk(sc); // populates instantiated ArrayList<Talk> listOfTalks
			sc.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		conferenceScheduler(listOfTalks); 
	}

}
