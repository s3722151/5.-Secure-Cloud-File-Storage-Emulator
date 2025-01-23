//https://www.baeldung.com/java-base64-encode-and-decode
import java.util.Base64;

/*
Works
Issue: You do not define the key
 */

public class baeldungBase64Encoding 
{
	public static void space()
	{
		System.out.println("                                                                                       ");
	}

	public static void main(String[] args) 
	{
//Way to encode a simple string
		System.out.println("Way to encode a simple string");
		String originalInput = "test input";
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		System.out.println("The encoded string is: "+ encodedString);	
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		System.out.println("The decoded string is "+ decodedString);
		space();

//Way to encode without padding
		System.out.println("Way to encode without padding");
		String encodedStringNoPadding = Base64.getEncoder().withoutPadding().encodeToString(originalInput.getBytes());
		System.out.println("The encoded string is: "+ encodedStringNoPadding);	
		byte[] decodedBytesNoPadding = Base64.getDecoder().decode(encodedStringNoPadding);
		String decodedStringNoPadding = new String(decodedBytesNoPadding);
		System.out.println("The decoded string is "+ decodedStringNoPadding);
		space();


	}

}
