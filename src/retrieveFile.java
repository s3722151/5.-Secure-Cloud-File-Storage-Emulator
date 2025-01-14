import java.awt.Desktop;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FlowLayout;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class retrieveFile 
{
	//Method 2: https://www.youtube.com/watch?v=gMVkp8108f0
	public static String getFileName() 
	{
		String filepath ="None"; //Default value of string
		JFileChooser chooser = new JFileChooser();//Allows you to browse files
		chooser.setCurrentDirectory(new File("."));//Where JFileChooser opens by default
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	            "JPG & GIF Images", "jpg", "gif", "png", "txt");
	    chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(chooser); //We store result as int to interact with
		
		try 
		{
			if ((result ==JFileChooser.APPROVE_OPTION)) 
			{
				File selectedFile = chooser.getSelectedFile();
				filepath = selectedFile.getName();
				System.out.println("The file that you are trying to retrieve and encrypt is:  " + filepath);
			}
			else if (filepath.equals("None"))
			{
				System.out.println("No file could be found. The first line");
			}
		}
	    catch (Exception e)
	    {
	    	System.out.println("Opening the file has failed.");
	    	System.out.println(e);
	    }
		return filepath;	
	}
	//Method 3 - Reading the file: https://www.w3schools.com/java/showjava.asp?filename=demo_files_read 
	public static String readFile()
	{
	File myObj;
	   try {
		      myObj = new File("test.txt");
		      Scanner myReader = new Scanner(myObj);  
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      //myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } 	
	   return myObj;
	}
	
	public static void main(String[] args) 
	{
	System.out.println("Select the file that you wish to encrypt.");
	String fileName;
	fileName = getFileName();
	System.out.println("Now reading the file and encrypting the file with AES");
	readFile(fileName);
	System.out.println("Now generating a key to encrypt the file.");
	
	
	
//	String fileName = getFileName();
//	System.out.println(fileName);
//	if (fileName.equals("None"))
//		{
//			System.out.println("No file could be found.");
//		}

	}//End of main statement

}//End of class


/*
Referernces
STAGE 1: 
1. Get File 
https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
https://www.youtube.com/watch?v=1mVldWMT7Vc
	https://www.youtube.com/watch?v=gMVkp8108f0

2. Read the file 

3. Encrypt the file 

4. Write a new file 

STAGE 2: Retrieving the file

Other
How to make a method: https://www.w3schools.com/java/java_methods.asp

 */
