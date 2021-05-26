import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleDriver 
{
	public static String pattern = "hh:mm a";
	static ArrayList<Talk> listOfTalks = new ArrayList<>();
	
	public static void populateTalk(Scanner sc) 
	{
		Pattern re = Pattern.compile("\\blightning\\b",Pattern.CASE_INSENSITIVE);
		Matcher m ;
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
		LocalTime clock = LocalTime.of(9, 0, 0);
		clock.format(DateTimeFormatter.ofPattern(pattern));
		System.out.println("Track 1: ");
		for(Talk tlk: talks) 
		{
			handleLightning(tlk);
			if (clock == LocalTime.of(12, 0, 0) || 
				clock.plusMinutes(tlk.getDuration()).compareTo(LocalTime.of(12, 0, 0)) > 0 &&
				clock.plusMinutes(tlk.getDuration()).compareTo(LocalTime.of(13, 0, 0)) < 0 )
			{
				clock =  LocalTime.of(12, 0, 0);
				System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
				System.out.println("Lunch");
				clock = LocalTime.of(13, 0, 0);
			}
			else if (clock.plusMinutes(tlk.getDuration()).compareTo(LocalTime.of(17, 0, 0)) > 0) 
			{
				System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
				System.out.println("Networking Event");
				System.out.println("Track 2: ");
				clock = LocalTime.of(9, 0, 0);
				System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
				System.out.println(tlk.getTitle());
				clock = clock.plusMinutes(tlk.getDuration());
			}
			else
			{
				System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
				System.out.println(tlk.getTitle());
				clock = clock.plusMinutes(tlk.getDuration());
			}
		}
		clock = LocalTime.of(17, 0, 0);
		System.out.print(clock.format(DateTimeFormatter.ofPattern(pattern)) + " ");
		System.out.println("Networking Event");
	}
	
	public static void handleLightning(Talk talk) 
	{
		String temp = talk.getTitle();
		Pattern re = Pattern.compile("\\b5min\\b",Pattern.CASE_INSENSITIVE);
		Matcher m = re.matcher(temp);
		temp = m.replaceAll("lightning");
		talk.setTitle(temp);
	}
	
	public static String handleLightning(String talk)
	{
		Pattern re = Pattern.compile("\\blightning\\b",Pattern.CASE_INSENSITIVE);
		Matcher m = re.matcher(talk);
		talk = m.replaceAll("5min");
		return talk;
	}

	public static void main(String[] args) 
	{
		try 
		{
			File myObj = new File("SeedData.txt"); 	// retrieves data from BookPrices.tct
			Scanner sc = new Scanner(myObj); 	// creates Scanner to read File
			populateTalk(sc);
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
