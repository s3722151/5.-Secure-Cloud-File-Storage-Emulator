import java.io.File;// To read & write to files
import javax.swing.JFileChooser; //To select files
import javax.swing.filechooser.FileNameExtensionFilter;//To select files
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.UnsupportedEncodingException;// Import this class to handle errors
import java.security.InvalidKeyException;// Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.security.Key; //Generate encryption key
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher; //Generate cipher block
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException; //To create a file 
import java.io.FileWriter;   // Import the FileWriter class


public class encryptFile 
{
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
	
//Main Method
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException 
	{
	System.out.println("Select the file that you wish to encrypt.");
	String fileName, data = null;
	fileName = getFileName();
	space();
	System.out.println("Now reading the file: " + fileName);
	System.out.println("The contents of the file is as follows.");
	//Reading the file: https://www.w3schools.com/java/showjava.asp?filename=demo_files_read 
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
	//Encrypt stage: https://gregorycernera.medium.com/encrypting-and-decrypting-a-message-using-symmetric-keys-with-java-explained-step-by-step-with-a523b67877d8
	System.out.println("Now proceeding to encrypt with AES.");
	// Step 1: Convert the text to bytes.
	byte[] message = data.getBytes();
    // Step 2: Create a KeyGenerator object - Generate a secret (symmetric) key
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    // Step 3: Initialize KeyGenerator - tell how many bytes we want our key to be.
    keyGen.init(128);
    // Step 4: Generate the key
    Key key = keyGen.generateKey();
    // Step 5: Create a Cipher object -  handle our encryption and decryption
    Cipher cipher = null;
	try {
		cipher = Cipher.getInstance("AES");
	} catch (NoSuchAlgorithmException e) {
		System.out.println("The AES cryptographic algorithm requested is not available in the environment.");	
		e.printStackTrace();	
	} catch (NoSuchPaddingException e) {
		e.printStackTrace();
	}
    // Step 6: Initialize the Cipher object - Tell to encrypt
    try {
		cipher.init(Cipher.ENCRYPT_MODE, key);
	} catch (InvalidKeyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    // Step 7: Give the Cipher our file
    cipher.update(message);
    // Step 8: Encrypt the file
    byte[] ciphertext = cipher.doFinal();
    // Step 9: Print the ciphertext
    System.out.println("Plain message, unencrypted input is: " + new String(data));
    System.out.println("Ciphertext, encrypted input is now: " + new String(ciphertext, "UTF8"));
    space();

    //How to create and write to a file: https://www.w3schools.com/java/java_files_create.asp
    try {  
        File createObj = new File("cloud\\encrypted"+ fileName); //CHANGE this path based on where you saved it.  
        if (createObj.createNewFile()) {  
          System.out.println("File created: " + createObj.getName());  
        } else {  
          System.out.println("Could not create the file:encrypted"+ fileName + " to store encrypted results as file already exists.");  
        }  
      } catch (IOException e) {
        System.out.println("An error occurred while creating the file that would be encrypted.");
        e.printStackTrace();  
      }
    space();
    try {
        FileWriter myWriter = new FileWriter("cloud\\encrypted"+ fileName);//CHANGE this path based on where you saved it.
        myWriter.write(new String(ciphertext, "UTF8"));
        myWriter.close();//Ensure that this writes after operation
        System.out.println("Successfully wrote to the file the encrypted contents.");
      } catch (IOException e) {
        System.out.println("An error occurred while writing to the file - encrypted contents.");
        e.printStackTrace();
      }
    space();    
    System.out.println("File: " +  fileName + " encrypted and uploaded successfully to the cloud.");
    space();
    
//Decrypting Stage
    System.out.println("Now proceed to decrypt a file of your choice.");
    String retrieveEncryptedFileName, decryptedData = null;
    retrieveEncryptedFileName = getFileName();
	System.out.println("Now reading the file: " + retrieveEncryptedFileName);
	System.out.println("The contents of the file is as follows.");
	space();
	//Reading the file: https://www.w3schools.com/java/showjava.asp?filename=demo_files_read 
	File readEncryptedObj;
	   try {
		   readEncryptedObj = new File(retrieveEncryptedFileName);
		      Scanner myReader = new Scanner(readEncryptedObj);  
		      while (myReader.hasNextLine()) {
		    	decryptedData = myReader.nextLine();
		        System.out.println(decryptedData);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	space();
	System.out.println("Now proceeding to decrypt with AES.");
	// Step 1: Convert the text to bytes.
	byte[] encryptedMessage = retrieveEncryptedFileName.getBytes();
    //Step 1: Initialize the Cipher object - Tell to decrypt
    try {
		cipher.init(Cipher.DECRYPT_MODE, key);
	} catch (InvalidKeyException e) {
		e.printStackTrace();
	}
    // Step 2: Give the Cipher our file
    cipher.update(encryptedMessage);
    // Step 3: Decrypt the file
    byte[] decryptText = cipher.doFinal();
    // Step 4: Print the decryptText
    System.out.println("Cipghertext, encrypted input is: " + new String(retrieveEncryptedFileName));
    System.out.println("Plain text, decrypted input is now: " + new String(decryptText, "UTF8"));
    space();
    //Step 5: Create the file where decrypted message will be saved
    try {  
        File createObj = new File("cloud\\encrypted"+ retrieveEncryptedFileName); //CHANGE this path based on where you saved it.  
        if (createObj.createNewFile()) {  
          System.out.println("File created: " + createObj.getName());  
        } else {  
          System.out.println("Could not create the file: "+ fileName + " to store decrypted results as file already exists.");  
        }  
      } catch (IOException e) {
        System.out.println("An error occurred while creating the file that would be decrypted.");
        e.printStackTrace();  
      }
    space();
    //Step 6: Save the file
    try {
        FileWriter myWriter = new FileWriter("cloud\\decrypted"+ retrieveEncryptedFileName);//CHANGE this path based on where you saved it.
        myWriter.write(new String(encryptedMessage, "UTF8"));
        myWriter.close();
        System.out.println("Successfully wrote to the file the decrypted contents.");
      } catch (IOException e) {
        System.out.println("An error occurred while writing to the file - decrypted contents.");
        e.printStackTrace();
      }
    space();
    //Step 7: Confirmation message
    System.out.println("File: " +  retrieveEncryptedFileName + " encrypted and uploaded successfully to the cloud.");
    space();
    
    //Main Issue: You cannot save an encrypted file as a txt file:https://stackoverflow.com/questions/69632602/best-way-to-store-aes-encrypted-data
    //Solution: Try to encrypt using aesMediumEncryption or https://www.youtube.com/watch?v=J1RmZZEkN0k
    
    //USED : https://www.baeldung.com/java-base64-encode-and-decode
    	//Documentation on why we need padding: https://stackoverflow.com/questions/4080988/why-does-base64-encoding-require-padding-if-the-input-length-is-not-divisible-by/18518605#18518605
    //READ already - This is also similar: https://danielangel22.medium.com/step-by-step-guide-to-encrypt-and-decrypt-data-with-aes-in-java-2baf4343be60
	//FOLLOW UP https://www.youtube.com/watch?v=Se9EbUTSHO8
    
	}//End of main statement

}
/*
What I still don't understand
- Why I had to throw an exception in main method
- Create file method: Should I have selected where file is saved? 

Referernces
STAGE 1: 
1. Get File 
https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
https://www.youtube.com/watch?v=1mVldWMT7Vc
	https://www.youtube.com/watch?v=gMVkp8108f0

2. Read the file
Reading the file: https://www.w3schools.com/java/showjava.asp?filename=demo_files_read 
	Alternate Way: https://www.youtube.com/watch?v=ScUJx4aWRi0&t=210s
					https://www.youtube.com/watch?v=2ZVH1KeDSHo
	Parsing a file: https://www.youtube.com/watch?v=UYa8zSbnyjo

3. Encrypt the file (AES)
What is AES: https://www.techtarget.com/searchsecurity/definition/Advanced-Encryption-Standard
How to do Symmetric Encryption: https://gregorycernera.medium.com/encrypting-and-decrypting-a-message-using-symmetric-keys-with-java-explained-step-by-step-with-a523b67877d8
	https://www.baeldung.com/java-aes-encryption-decryption

4. Write a new file 
https://www.w3schools.com/java/java_files_create.asp
Absolute vs relative paths: https://www.youtube.com/watch?v=ephId3mYu9o

STAGE 2: Retrieving the file
ERROR - OInput length must be a multilpe of 16: https://stackoverflow.com/questions/10494764/input-length-must-be-multiple-of-16-when-decrypting-with-padded-cipher

Other
How to make a method: https://www.w3schools.com/java/java_methods.asp
Declaring and initalising variables: https://www.youtube.com/watch?v=FIbvd64b6ZA

 */
