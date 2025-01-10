import java.awt.Desktop;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class retrieveFile {
	
	//Methods
	static void selectedFile() {
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	            "JPG & GIF Images", "jpg", "gif", "png", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    
	    try {
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	            System.out.println("You chose to open this file: " +
	                    chooser.getSelectedFile().getName());
	        }	
	    }
	    catch (Exception e)
	    {
	    	System.out.println(e);
	    }
		
	}

	
	public static void main(String[] args) {
		selectedFile();

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
