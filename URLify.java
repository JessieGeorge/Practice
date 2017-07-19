/**
 * 1.3 replace spaces in a string with '%20'
 * the string has sufficient space to store extra characters
 * you are given the true length of the string
 * In Java, use a char array so that you can perform this operation in place.
 * 
 * example 
 * input:  "Mr John Smith    ", 13
 * output: "Mr%20John%20Smith"
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class URLify {

	private static String s;
	private static int truelen;
	
	/*
	 * Note: I'm not doing error checks on the input because I'm assuming it's entered correctly
	 */
	private void input()throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter string.");
		s = br.readLine();
		truelen = Integer.parseInt(s.substring(s.lastIndexOf(',')+2));//number at the end
		s = s.substring(1, s.lastIndexOf(',')-1); //string without the quotes and end number
	}
	
	/*
	 * Personal Solution ignoring the char array instruction.
	 * Not sure about time and space complexity. I think replaceall copies over the string to a new string and replaces characters. So I guess the complexity depends on the number of spaces in the input.
	 */
	private String makeURL1(String s, int trueLen)
	{
		s = s.substring(0, truelen);
		s = s.replaceAll(" ", "%20");
		
		return s;
	}
	
	/*
	 * Personal Solution with char array
	 * Time O(n) where n is the length of the string
	 * Space O(n) because you have a char array of size n
	 * Note: It's supposed to be in place, so actually you have to accept the input as a char array instead of accepting as string and converting into char array. (I just did this for convenience). In that case it is in place so space is O(1). 
	 */
	private char[] makeURL2(String s, int trueLen)
	{
		char[] c = s.toCharArray();
		for(int i=s.length()-1; i>=0; i--)
		{
			if(trueLen-1 == i) //there aren't any spaces before this point so you don't need to move over letters
				break;
			
			char ch = c[trueLen-1];
			
			if(ch==' ') //change space to "%20"
			{
				c[i] = '0';
				c[i-1] = '2';
				c[i-2] = '%';
				i = i-2; //don't rewrite over "%20"
			}
			
			else
				c[i] = ch; //moving over letters to make room for "%20"
			
			trueLen--;
		}
		
		return c;
	}
	/* Note: The book solution is similar but it counts the number of spaces first.
	 * I like my solutions better so I haven't included the book solution here. */
	
	private void output(String s)
	{
		System.out.println("\""+s+"\"");
	}
	
	private void output(char[] c)
	{
		System.out.print("\"");
		for(char ch: c)
			System.out.print(ch);
		System.out.print("\"");
		
		System.out.println();
	}
	
	public static void main(String args[])throws IOException
	{
		URLify U = new URLify();
		U.input();
		//U.output(U.makeURL1(s, truelen));
		U.output(U.makeURL2(s, truelen));
	}
}
