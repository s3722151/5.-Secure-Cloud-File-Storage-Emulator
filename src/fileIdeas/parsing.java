package fileIdeas;
/*
Source: https://www.youtube.com/watch?v=UYa8zSbnyjo 
*/

import java.io.*;

public class parsing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = null;
		BufferedReader br = null;
		
		try 
		{
			String fileName;
			br = new BufferedReader (new FileReader(fileName"passwords.txt"));
			String values[];
			while ( (str = br.readLine() ) != null){
				values = str.split(regex:"|");
				System.out.println(str);
			}
		}
		catch(NullPointerException npe){
			System.out.println(x:"A null pointer was encountered");
		}

	}

}
