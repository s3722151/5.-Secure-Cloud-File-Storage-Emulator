import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.util.Base64;

public class DeepseekSolutionEncryptionFile {

    // Method to choose a file
    public static String getFileName() {
        String filepath = "None"; // Default value
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text & Image Files", "txt", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(chooser);

        try {
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                filepath = selectedFile.getAbsolutePath();
                System.out.println("Selected file: " + filepath);
            } else {
                System.out.println("No file selected.");
            }
        } catch (Exception e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
        return filepath;
    }

    // Method to read the entire file content
    public static String readFileContent(String filePath) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()); // Append each line without adding extra newlines
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return content.toString();
    }

    // Method to encrypt data using AES
    public static String encrypt(String data, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Encode to Base64 for safe storage
    }

    // Method to decrypt data using AES
    public static String decrypt(String encryptedData, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        // KEY CHANGE: Trim the Base64 string to remove extra spaces/newlines before decoding
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData.trim())); 
        return new String(decryptedBytes);
    }

    // Method to save data to a file
    public static void saveToFile(String filePath, String data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
            System.out.println("File saved: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Method to add spacing in the console for better readability
    public static void space() {
        System.out.println("\n--------------------------------------------------\n");
    }

    // Main method
    public static void main(String[] args) {
        try {
            // Step 1: Select a file to encrypt
            System.out.println("Select the file you wish to encrypt.");
            String filePath = getFileName();
            if (filePath.equals("None")) {
                System.out.println("No file selected. Exiting...");
                return;
            }

            // Step 2: Read the file content
            String fileContent = readFileContent(filePath);
            System.out.println("File content:\n" + fileContent);
            space();

            // Step 3: Generate AES key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            Key key = keyGen.generateKey();

            // Step 4: Encrypt the file content
            String encryptedData = encrypt(fileContent, key);
            System.out.println("Encrypted data:\n" + encryptedData);
            space();

            // Step 5: Save the encrypted data to a file
            String encryptedFilePath = Paths.get("cloud", "encrypted_" + new File(filePath).getName()).toString();
            saveToFile(encryptedFilePath, encryptedData);
            space();

            // Step 6: Select the encrypted file to decrypt
            System.out.println("Select the encrypted file you wish to decrypt.");
            String encryptedFileToDecrypt = getFileName();
            if (encryptedFileToDecrypt.equals("None")) {
                System.out.println("No file selected. Exiting...");
                return;
            }

            // Step 7: Read the encrypted file content
            String encryptedFileContent = readFileContent(encryptedFileToDecrypt);
            System.out.println("Encrypted file content:\n" + encryptedFileContent);
            space();

            // Step 8: Decrypt the file content
            String decryptedData = decrypt(encryptedFileContent, key);
            System.out.println("Decrypted data:\n" + decryptedData);
            space();

            // Step 9: Save the decrypted data to a file
            String decryptedFilePath = Paths.get("cloud", "decrypted_" + new File(encryptedFileToDecrypt).getName()).toString();
            saveToFile(decryptedFilePath, decryptedData);
            space();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

// ============================================================
// Explanation of the Issue, Changes, and Solution:
// ============================================================
/*
 * Issue in the Original File:
 * 1. The Base64 string in the encrypted file was corrupted due to extra spaces or newline characters.
 * 2. This caused the Base64 decoding to fail during decryption, throwing the error:
 *    "Input byte array has incorrect ending byte at X."
 *
 * Key Changes Made:
 * 1. Trimmed the Base64 string before decoding in the `decrypt` method to remove extra spaces/newlines.
 * 2. Modified the `readFileContent` method to read the entire file content without adding extra newlines.
 * 3. Added debug statements to verify the encrypted and decrypted data.
 *
 * Solution:
 * The issue was resolved by ensuring the Base64 string remains intact during file handling. Trimming the string
 * before decoding ensures that no extra characters interfere with the Base64 decoding process.
 */

//============================================================
//Explanation of StringBuilder:
//============================================================
/*
* What is StringBuilder?
* - StringBuilder is a class in Java used to create and manipulate strings efficiently.
* - Unlike regular strings (String class), which are immutable (cannot be changed after creation),
*   StringBuilder allows you to modify the content of a string without creating new objects.
*
* Why use StringBuilder?
* - When you need to concatenate (combine) many strings or modify a string frequently, using StringBuilder
*   is more efficient than using the `+` operator or `String.concat()`. This is because:
*   1. Regular strings create a new object every time you modify them, which can be slow and memory-intensive.
*   2. StringBuilder modifies the same object, making it faster and more memory-efficient.
*
* Use Cases:
* 1. Reading a file line by line and combining the lines into a single string (as done in this project).
* 2. Building long strings dynamically, such as generating HTML or SQL queries.
* 3. Modifying strings in loops or other performance-critical scenarios.
*
* Example:
* StringBuilder sb = new StringBuilder();
* sb.append("Hello"); // Append a string
* sb.append(" ");     // Append a space
* sb.append("World"); // Append another string
* String result = sb.toString(); // Convert to a regular string: "Hello World"
*
* In this project, StringBuilder is used in the `readFileContent` method to efficiently combine all lines
* of a file into a single string.
* 
*/
//============================================================
//Explanation of proper usage of Try and Catch statements:
//============================================================
/* 
Group related operations in a single try-catch.
If multiple operations are related and can throw similar exceptions, group them in a single try-catch block. This reduces redundancy and improves readability.E.g.
			try {
		    // Open a file
		    File file = new File("example.txt");
		    Scanner scanner = new Scanner(file);
		
		    // Read the file
		    while (scanner.hasNextLine()) {
		        System.out.println(scanner.nextLine());
		    }
		
		    // Close the scanner
		    scanner.close();
		} catch (FileNotFoundException e) {
		    System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
		    System.out.println("Error reading file: " + e.getMessage());
		}
			
Use separate try-catch blocks for unrelated operations.
If operations are unrelated or throw different types of exceptions, use separate try-catch blocks. This makes the code more modular and easier to debug.

Use try-with-resources for resource management.
For larger applications, consider using a centralized exception handler (e.g., in a utility class or a global exception handler). This reduces the need for repetitive try-catch blocks.

Avoid catching generic exceptions.

Log exceptions for better debugging.

Re-throw exceptions when necessary.

Aim for meaningful exception handling without cluttering the code.
 */
