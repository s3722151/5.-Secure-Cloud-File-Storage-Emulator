//Source: https://danielangel22.medium.com/step-by-step-guide-to-encrypt-and-decrypt-data-with-aes-in-java-2baf4343be60
//DOES NOT WORK
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class aesMediumEncryption 
{
	//Define Algorithm encryption algorithm and key
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = 
	new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
	
	//method to encrypt data:
	public static String encrypt(String data) throws Exception {
	    Key key = generateKey();//generation dinamic 
	    Cipher c = Cipher.getInstance(ALGORITHM);
	    c.init(Cipher.ENCRYPT_MODE, key);
	    byte[] encVal = c.doFinal(data.getBytes());
	    return Base64.getEncoder().encodeToString(encVal);
	}
	
	//method to generate the encryption key:
	private static Key generateKey() throws Exception {
	    return new SecretKeySpec(keyValue, ALGORITHM);
	}
	
	//method to decrypt data:
	public static String decrypt(String encryptedData) throws Exception {
	    Key key = generateKey();
	    Cipher c = Cipher.getInstance(ALGORITHM);
	    c.init(Cipher.DECRYPT_MODE, key);
	    byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
	    byte[] decVal = c.doFinal(decodedValue);
	    return new String(decVal);
	}
	
	public static void main(String[] args) throws Exception
	{
		String data = "secret data";
		generateKey();
		String encryptedData = encrypt(data);
		String decryptedData = decrypt(encryptedData);
		if (data.equals(decryptedData)) {
		    System.out.println("Data is successfully encrypted and decrypted");
		} else {
		    System.out.println("Encryption and decryption failed");
		}
	}
	

}
