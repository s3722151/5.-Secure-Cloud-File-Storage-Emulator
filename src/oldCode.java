import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
//
public class oldCode {
	
	//Method 1: https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
	static void selectedFile() 
	{
	    JFileChooser chooser = new JFileChooser();
	    
	    chooser.setCurrentDirectory(new File("."));
	    //How to open at select place: chooser.setCurrentDirectory(new File("C:\\Users\\JC\\Documents"));
	    
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	            "JPG & GIF Images", "jpg", "gif", "png", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    
	    try 
	    {
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	            System.out.println("You chose to open this file: " +
	                    chooser.getSelectedFile().getName());
	        }	
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Opening the file has failed.");
	    	System.out.println(e);
	    }		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

