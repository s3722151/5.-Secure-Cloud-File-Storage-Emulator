import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

//https://stackoverflow.com/questions/10494764/input-length-must-be-multiple-of-16-when-decrypting-with-padded-cipher
//Does not work - not say how to link generating key


public class EncryptionDecryptionUtil {
    public static String encrypt(final String secret, final String data) {


        byte[] decodedKey = Base64.getDecoder().decode(secret);

        try {
            Cipher cipher = Cipher.getInstance("AES");
            // rebuild key using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(data.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occured while encrypting data", e);
        }

    }

    public static String decrypt(final String secret,
            final String encryptedString) {


        byte[] decodedKey = Base64.getDecoder().decode(secret);

        try {
            Cipher cipher = Cipher.getInstance("AES");
            // rebuild key using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
            return new String(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occured while decrypting data", e);
        }
    }


    public static void main(String[] args) {

        String data = "This is not easy as you think";
        String key = "---------------------------------";
        String encrypted = encrypt(key, data);
        System.out.println(encrypted);
        System.out.println(decrypt(key, encrypted));
      }

	
}

    


