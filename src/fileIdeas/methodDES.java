package fileIdeas;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey; //Above is from: https://stackoverflow.com/questions/27962116/simplest-way-to-encrypt-a-text-file-in-java

import java.security.Key;//https://danielangel22.medium.com/step-by-step-guide-to-encrypt-and-decrypt-data-with-aes-in-java-2baf4343be60
import java.util.Base64;//https://danielangel22.medium.com/step-by-step-guide-to-encrypt-and-decrypt-data-with-aes-in-java-2baf4343be60
import javax.crypto.Cipher;//https://danielangel22.medium.com/step-by-step-guide-to-encrypt-and-decrypt-data-with-aes-in-java-2baf4343be60
import javax.crypto.spec.SecretKeySpec;//https://danielangel22.medium.com/step-by-step-guide-to-encrypt-and-decrypt-data-with-aes-in-java-2baf4343be60

import javax.swing.JFileChooser;//To select a file: https://www.youtube.com/watch?v=gMVkp8108f0
import javax.swing.filechooser.FileNameExtensionFilter; //To select a file: 


import java.io.IOException; //To create a file 
import java.io.FileWriter;   // Import the FileWriter class
import java.io.File;// To read & write to files
import java.util.Scanner; // Import the Scanner class to read text files

import java.security.Key; //Generate encryption key
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher; //Generate cipher block
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import java.io.FileNotFoundException;  // Import this class to handle errors
import java.security.InvalidKeyException;// Import this class to handle errors

import java.nio.file.Files; //Import to handle reading all lines: https://medium.com/@AlexanderObregon/javas-files-readalllines-method-explained-14312314c1c4#:~:text=readAllLines()%20is%20its%20simplicity,older%20methods%20such%20as%20BufferedReader%20
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class methodDES 
{

	public static void main(String[] args) 
	{
		System.out.println("Select the file that you wish to encrypt.");
		String fileName, data= null;
		String contents = "";
		fileName = getFileName();
		Path pathTest = Paths.get(fileName);// Create a Path object representing the file
		space();
		System.out.println("Now reading the file: " + fileName);
		System.out.println("The contents of the file is as follows.");
		//Reading the file - Way 1: https://www.w3schools.com/java/showjava.asp?filename=demo_files_read 
		File myObj;
		   try {
			      myObj = new File(fileName);
			      Scanner myReader = new Scanner(myObj);  
			      while (myReader.hasNextLine()) {
			        data = myReader.nextLine();
			        System.out.println(data);
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		space();
		System.out.println("Now doing way 2 of reading a file.");
		//Way 2 of reading a file: Using a listString array: https://medium.com/@AlexanderObregon/javas-files-readalllines-method-explained-14312314c1c4#:~:text=readAllLines()%20is%20its%20simplicity,older%20methods%20such%20as%20BufferedReader%20
		 List<String> lines = null;
		 char[] charArray = null;
		try {
            // Step 2: Call Files.readAllLines() to read the file content
            lines = Files.readAllLines(pathTest);            
            for (String line :lines) {
            	System.out.println(line);//https://youtu.be/rZl3PwAtH3g?si=tZtxwp9rYusNJlVw
            	charArray = line.toCharArray();//https://www.freecodecamp.org/news/string-to-array-in-java-how-to-convert-a-string-to-an-array-in-java/
            }
        } catch (IOException e) {
            // Step 3: Handle the IOException
            e.printStackTrace();
        }
		space();
		
		
        //DES METHOD: https://stackoverflow.com/questions/27962116/simplest-way-to-encrypt-a-text-file-in-java
		try{
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");


            byte[] text = "No body can see me.".getBytes("UTF8");
            System.out.println("The text convered to bytes is: "+ text);
            space();


            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);

            String s = new String(textEncrypted);
            System.out.println("The encrypted text is: "+ s);
            space();

            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted);

            s = new String(textDecrypted);
            System.out.println("The decrypted text is: " + s);
        }catch(Exception e)
        {
            System.out.println("Exception");
        }

	}
	
//Methods
	//Way of encryption: https://danielangel22.medium.com/step-by-step-guide-to-encrypt-and-decrypt-data-with-aes-in-java-2baf4343be60
		//Method to choose a file: https://www.youtube.com/watch?v=gMVkp8108f0
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
	public static void space()
	{
		System.out.println("                                                                                       ");
	}

}
