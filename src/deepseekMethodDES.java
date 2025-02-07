import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Base64;

public class deepseekMethodDES {

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

            // Step 3: Generate DES key
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keyGen.generateKey();

            // Step 4: Encrypt the file content
            String encryptedData = encrypt(fileContent, secretKey);
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
            String decryptedData = decrypt(encryptedFileContent, secretKey);
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
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return content.toString();
    }

    // Method to encrypt data using DES
    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Encode to Base64 for safe storage
    }

    // Method to decrypt data using DES
    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData.trim())); // Trim to remove extra spaces/newlines
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
}