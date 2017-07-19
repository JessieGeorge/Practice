/**
 * 1.5 check if a string is one edit away from another string. (or zero edits away).
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OneAway {

	private static String s1, s2;
	
	private void input() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a string");
		s1 = br.readLine();
		System.out.println("Enter a string");
		s2 = br.readLine();
	}
	
	/*
	 * personal solution
	 * Time O(n)
	 * Space O(1)
	 */
	private boolean checkOneAway(String s1, String s2)
	{
		boolean seenOneEdit = false;
		String longerstring;
		String shorterstring;
		int j;
		
		//if string lengths differ by more than one it's definitely not a oneaway edit
		if(Math.abs(s1.length() - s2.length()) >=2)
			return false;
		
		else if (s1.equals(s2)) //same string is zero edits
			return true;
		
		//if both strings are same length
		else if(s1.length() == s2.length())
		{
			for(int i=0; i<s1.length(); i++)
			{
				if(s1.charAt(i) != s2.charAt(i))
				{
					if(seenOneEdit)
						return false;
					
					else
						seenOneEdit = true;
				}
			}
			return true;
		}
		
		else if(s1.length()>s2.length())
		{
			longerstring = s1;
			shorterstring = s2;
		}
		
		else
		{
			longerstring = s2;
			shorterstring = s1;
		}
		
		j=0; //j is the position in the shorter string
		for(int i=0; i<longerstring.length(); i++)
		{
			if(longerstring.charAt(i) != shorterstring.charAt(j))
			{
				if(seenOneEdit)
					return false;
				
				else //j doesn't move forward but i moves forward
					seenOneEdit = true;
			}
			
			else
				j++;
		}
		return true;
		
	}
	
	/*
	 * book solution is very similar
	 */
	
	private void output(boolean b)
	{
		System.out.println(b);
	}
	
	public static void main(String args[])throws IOException
	{
		OneAway O = new OneAway();
		O.input();
		O.output(O.checkOneAway(s1, s2));
	}
}
