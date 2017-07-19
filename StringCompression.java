/**
 * 1.6 Compress the string by showing counts of repeated characters.
 * example aabcccccaaa = a2b1c3a3
 * if the compressed string is not shorter, return the original string
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuilder;

public class StringCompression {

	private static String s;
	
	private void input() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a string.");
		s = br.readLine();
	}
	
	/*
	 * Personal solution
	 * Time O(n)
	 * I think Space O(n) the compressed string can be at most double the length of the original string example ab = a1b1 ..and O(2n) = O(n).. of course we only return the compressed string if it's shorter
	 */
	private String compress1(String s)
	{
		if(s.length() == 1)
			return s;
		
		StringBuilder compressedStr = new StringBuilder();
		int count;
		int i = 0; 
		while(i<s.length())
		{
			count = 1;
			char ch = s.charAt(i);
			while(s.charAt(i+1) == ch)
			{
				count++;
				i++;
				if(i==s.length()-1) //don't run off the end of the string
					break;
			}
			
			compressedStr.append(ch);
			compressedStr.append(count);
			i++;
				
		}
		
		String cs = compressedStr.toString();
		if(cs.length()>=s.length()) //if the compressed string is not shorter, return the original string
			return s;
		else
			return cs;
	}
	
	/*
	 * book solution
	 * avoid creating the compressed string if you don't have to use it
	 * this is also good because you can initialize the capacity of StringBuilder up-front (otherwise it will double capacity behind the scenes everytime it hits capacity).
	 */
	private int countCompression(String s)
	{
		int compressedLength = 0;
		int countConsecutive = 0;
		for(int i=0; i<s.length(); i++)
		{
			countConsecutive++;
			
			//If next character is different from the current, increase the length
			if(i+1>=s.length() || s.charAt(i) != s.charAt(i+1))
			{
				compressedLength += 1 + String.valueOf(countConsecutive).length();
				countConsecutive = 0;
			}
		}
		
		return compressedLength;
	}
	private String compress2(String s)
	{
		//check final length and return input string if it would be longer
		int finalLength = countCompression(s);
		if(finalLength>=s.length()) 
			return s;
		
		StringBuilder compressed = new StringBuilder(finalLength); //initial capacity
		int countConsecutive = 0;
		for(int i=0; i<s.length(); i++)
		{
			countConsecutive++;
			
			//If next character is different from the current, append this char to result
			if(i+1>=s.length() || s.charAt(i) != s.charAt(i+1))
			{
				compressed.append(s.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return compressed.toString();
	}
	private void output(String s)
	{
		System.out.println(s);
	}
	
	public static void main(String args[])throws IOException
	{
		StringCompression S = new StringCompression();
		S.input();
		//S.output(S.compress1(s));
		S.output(S.compress2(s));
	}
}
