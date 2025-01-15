import java.awt.Desktop;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FlowLayout;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.UnsupportedEncodingException;
import java.util.Scanner; // Import the Scanner class to read text files
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

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
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException 
	{
	System.out.println("Select the file that you wish to encrypt.");
	String fileName;
	fileName = getFileName();
	System.out.println("Now reading the file" + fileName);
	System.out.println("The contents of the file is as follows.");
	//Reading the file: https://www.w3schools.com/java/showjava.asp?filename=demo_files_read 
	File myObj;
	   try {
		      myObj = new File(fileName);
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
	   
	System.out.println("Now proceeding to encrypt with AES.");
	// Step 1: Convert the text to bytes.
	byte[] message = fileName.getBytes();
    // Step 2: Create a KeyGenerator object - Generate a secret (symmetric) key
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    // Step 3: Initialize KeyGenerator - tell how many bytes we want our key to be.
    keyGen.init(256);
    // Step 4: Generate the key
    Key key = keyGen.generateKey();
    // Step 5: Create a Cipher object -  handle our encryption and decryption
    Cipher cipher = null;
	try {
		cipher = Cipher.getInstance("AES");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		System.out.println("Couldn't complete step 5 and create Cipher object.");	
		e.printStackTrace();	
	} catch (NoSuchPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    // Step 6: Initialize the Cipher object - Tell to encrypt
    try {
		cipher.init(Cipher.ENCRYPT_MODE, key);
	} catch (InvalidKeyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    // Step 7: Give the Cipher our message
    cipher.update(message);
    // Step 8: Encrypt the message
    byte[] ciphertext = cipher.doFinal();
    // Step 9: Print the ciphertext
    System.out.println("Plain message, unencrypted input is: " + new String(fileName));
    System.out.println("Ciphertext, encrypted input is now: " + new String(ciphertext, "UTF8"));

    //How to create and write a file: https://www.w3schools.com/java/java_files_create.asp
    
//	String fileName = getFileName();
//	System.out.println(fileName);
//	if (fileName.equals("None"))
//		{
//			System.out.println("No file could be found.");
//		}

	}//End of main statement

}//End of class


/*
What I still don't understand
- Why I had to throw an exception in main method 

Referernces
STAGE 1: 
1. Get File 
https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
https://www.youtube.com/watch?v=1mVldWMT7Vc
	https://www.youtube.com/watch?v=gMVkp8108f0

2. Read the file
Reading the file: https://www.w3schools.com/java/showjava.asp?filename=demo_files_read 

3. Encrypt the file (AES)
What is AES: https://www.techtarget.com/searchsecurity/definition/Advanced-Encryption-Standard
How to do Symmetric Encryption: https://gregorycernera.medium.com/encrypting-and-decrypting-a-message-using-symmetric-keys-with-java-explained-step-by-step-with-a523b67877d8
	https://www.baeldung.com/java-aes-encryption-decryption

4. Write a new file 
https://www.w3schools.com/java/java_files_create.asp

STAGE 2: Retrieving the file

Other
How to make a method: https://www.w3schools.com/java/java_methods.asp

 */
