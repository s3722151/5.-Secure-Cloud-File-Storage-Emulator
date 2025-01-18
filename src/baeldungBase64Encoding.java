//https://www.baeldung.com/java-base64-encode-and-decode
import java.util.Base64;

//BELOW DOES NOT WORK
public class baeldungBase64Encoding 
{
	public static void space()
	{
		System.out.println("                                                                                       ");
	}

	public static void main(String[] args) 
	{
		//BELOW DOES NOT WORK
//		String originalInput = "test input";
//		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//		System.out.println(encodedString);
//		space();
//		
//		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//		String decodedString = new String(decodedBytes);
//		System.out.println(encodedString);
		
		String originalInput = "dGVzdCBpbnB1dA==";
		byte[] result = Base64.getDecoder().decode(originalInput);

		//assertEquals("test input", new String(result));


	}

}
