import java.awt.Desktop;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FlowLayout;

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
				System.out.println("The file that you are trying to retrieve is:  " + filepath);
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

	
	public static void main(String[] args) 
	{
	getFileName();
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
1. Get File 
https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
https://www.youtube.com/watch?v=1mVldWMT7Vc
	https://www.youtube.com/watch?v=gMVkp8108f0
	
Other
How to make a method: https://www.w3schools.com/java/java_methods.asp

 */
