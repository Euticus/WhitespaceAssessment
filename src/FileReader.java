import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class FileReader {
	
	private final ArrayList<String> file = new ArrayList<String>();
	
	public FileReader() {
		try 
		{
			File myObj = new File("SeedData.txt"); 
			Scanner sc = new Scanner(myObj); 	
			while (sc.hasNextLine()) {
				file.add(sc.nextLine());
			}
			sc.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public FileReader(File f) {
		try 
		{
			Scanner sc = new Scanner(f); 	
			while (sc.hasNextLine()) {
				file.add(sc.nextLine());
			}
			sc.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getFileContents() {
		return this.file;
	}
	
}
