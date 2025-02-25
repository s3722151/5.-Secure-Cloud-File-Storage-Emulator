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
import java.util.ArrayList;
import java.util.List;

import java.io.*; //Used to convery an Array to String
import java.util.Arrays;

public class wayOneEncryption 
{
//Way of encryption: https://danielangel22.medium.com/step-by-step-guide-to-encrypt-and-decrypt-data-with-aes-in-java-2baf4343be60
//METHODS
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
		
	//Define Algorithm encryption algorithm and key
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
	
	//Method to encrypt data - As string:
	public static String encrypt(String fileName) throws Exception {
	    Key key = generateKey();//generation dinamic 
	    Cipher c = Cipher.getInstance(ALGORITHM);
	    c.init(Cipher.ENCRYPT_MODE, key);
	    byte[] encVal = c.doFinal(fileName.getBytes());
	    return Base64.getEncoder().encodeToString(encVal);
	}
	
	
	//Method to generate the encryption key:
	private static Key generateKey() throws Exception {
	    return new SecretKeySpec(keyValue, ALGORITHM);
	}
	
	//Method to decrypt data:
	public static String decrypt(String encryptedData) throws Exception {
	    Key key = generateKey();
	    Cipher c = Cipher.getInstance(ALGORITHM);
	    c.init(Cipher.DECRYPT_MODE, key);
	    byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
	    byte[] decVal = c.doFinal(decodedValue);
	    return new String(decVal);
	}
//Main method
	@SuppressWarnings("null")
	public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		System.out.println("Select the file that you wish to encrypt.");
		String fileName, data= null;
		String contents = "";
		fileName = getFileName();
		Path pathTest = Paths.get(fileName);// Create a Path object representing the file
		space();
		System.out.println("Now reading the file: " + fileName);
		System.out.println("The contents of the file is as follows.");
		space();
		
		//Readlines to read file by an array: https://www.geeksforgeeks.org/read-file-into-an-array-in-java/
		String[] array = null;
		try 
		{
			System.out.println("Now doing way 3 of reading a file.");
	        List<String> listOfStrings= new ArrayList<String>();
       
        // load the data from file
        listOfStrings = Files.readAllLines(pathTest);
       
        // convert arraylist to array
        array = listOfStrings.toArray(new String[0]);
       
        // print each line of string in array
        	for (String eachString : array) 
	        {
        		Arrays.toString(array);//Turn the array into a string
	            System.out.println(eachString);
	        }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		space();

//Generate key
		try {
			generateKey();
		} catch (Exception e) {
			System.out.println("Could not generate the key");
			e.printStackTrace();
		}
		//Encrypt stage
		String encryptedData = null;
		String[] encryptedArray = null;
		try {
			encryptedData = encrypt(Arrays.toString(array));
			System.out.println("Here is the contents");
			
        	for (String eachString : array) 
	        {
	            System.out.println(eachString);
	        }
			
		} catch (Exception e) {
			System.out.println("Could not encrypt the data");
			e.printStackTrace();
		}
		System.out.println("The encrypted data is: "+ encryptedData);
		space();
		//Decrypt stage
		String decryptedData = null;
		try {
			decryptedData = decrypt(encryptedData);
		} catch (Exception e) {
			System.out.println("Could not decrypt the data");
			e.printStackTrace();
		}
		System.out.println("The decrypted data is: " + decryptedData);

	}

}
