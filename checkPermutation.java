/**
 * 1.2 Given two strings, check if one is a permutation of the other
 * Questions: 
 * Case sensitive? Example "God" and "dog".
 * Does whitespace matter? Example "God   " and "dog".
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class checkPermutation {

	private static String s1, s2;
	
	private void input() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a string.");
		s1 = br.readLine();
		System.out.println("Enter a string.");
		s2 = br.readLine();
	}
	
	/*
	 * Personal solution
	 * Time O(n log n)
	 * Space O(n)
	 * where n is the length of s1 = the length of s2 
	 */
	private boolean isPerm1(String s1, String s2)
	{
		if(s1.length()!=s2.length())
			return false;
		
		char c1[] = s1.toCharArray();
		char c2[] = s2.toCharArray();
		Arrays.sort(c1);
		Arrays.sort(c2);
		
		for(int i=0; i<s1.length(); i++)
		{
			if(c1[i] != c2[i])
				return false;
		}
		
		return true;
	}
	
	/*
	 * similar logic but more compartmentalized
	 */
	private String sort(String s)
	{
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
	private boolean isPerm2(String s1, String s2)
	{
		if(s1.length() != s2.length())
			return false;
		
		return sort(s1).equals(sort(s2));
	}
	
	/*
	 * Using character count
	 * Assumption: ASCII string
	 * I think Time O(n) where n is the length of s1 = length of s2
	 * I think Space O(n)
	 */
	private boolean isPerm3(String s1, String s2)
	{
		if(s1.length() != s2.length())
			return false;
		
		int[] letters = new int[128];
		char[] s_array = s1.toCharArray();
		
		for(char c: s_array) //count number of each character in s
		{
			letters[c]++;
		}
		
		for(int i=0; i<s1.length(); i++)
		{
			int c = (int)s2.charAt(i);
			letters[c]--;
			
			if(letters[c] < 0) //s2 has a letter that s1 doesn't have OR s2 has more occurrences of a letter than s1 has of that letter.
				return false;
		}
		
			return true;
	}
	
	private void output(boolean b)
	{
		if(b)
			System.out.println("Permutation");
		else
			System.out.println("Not a permutation");
	}
	
	public static void main(String args[])throws IOException
	{
		checkPermutation P = new checkPermutation();
		P.input();
		//P.output(P.isPerm1(s1, s2));
		//P.output(P.isPerm2(s1, s2));
		P.output(P.isPerm3(s1, s2));
	}
}
