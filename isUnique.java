/**
 * 1.1 Check if a string has all unique characters.
 * Questions: Is it ASCII or Unicode string? Because unicode needs more storage space.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class isUnique {
private static String s;

private void input()throws IOException
{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Enter a string.");
	s = br.readLine();
}

/* 
 * Personal solution
 * Time O(n^2) where n is the length of the string
 * Space O(1) 
 */
private boolean checkIsUnique(String s) 
{
	int l = s.length();
	if(l<2)
		return true;
	
	for(int i=0; i<l-1; i++)
	{
		for(int j=i+1; j<l; j++)
		{
			if(s.charAt(i)==s.charAt(j))
				return false;

		}
	}
	
	return true;
}

/*
 * Using boolean array
 * Assumptions: using ASCII string (ascii represents each character with 7 bits so there are 2^7 = 128 unique characters. Extended ascii has 256 unique characters.)
 * Time O(n) where n is the length of the string (you could say O(1) here because length <128) 
 * Space O(1) because the boolean array is of constant size 128.
 */
private boolean checkIsUnique2(String s)
{
	if(s.length()>128) //only 128 unique characters so a unique string cannot be longer
		return false;
	
	boolean[] char_set = new boolean[128];
	
	for(int i=0; i<s.length(); i++)
	{
		int val = s.charAt(i);
		
		if(char_set[val]) //character is already seen
			return false;
		
		char_set[val] = true; //mark character as seen
	}
	
	return true;
}

/*
 * Using bit vector
 * Assumptions: The string is only lowercase letters so we can use a single int.
 * Time O(n) where n is the length of the string
 * Space O(1) but we reduced the space usage by a factor of eight by using a bit vector
 */
private boolean checkIsUnique3(String s)
{
	int checker = 0;
	for(int i=0; i<s.length(); i++)
	{
		int val = s.charAt(i) - 'a';
		if((checker & (1<<val)) > 0)
			return false;
		
		checker |= (1<<val);
	}
	
	return true;
}

/*
 * sorting the string
 * Assumptions: you can modify the string
 * Time O(n log (n))
 * I think Space O(log n) because quick sort uses log n extra space in worst case
 * 
 * Note: here i didn't modify the string, instead i just put it into a char array because i wanted to use Arrays.sort()
 * so i guess you have to say Space is O(n) because the size of the char array is the length of the string
 */
private boolean checkIsUnique4(String s)
{
	char[] c = s.toCharArray();
	Arrays.sort(c);
	
	for(int i=0; i<s.length()-1; i++)
	{
		if(c[i] == c[i+1])
			return false;
	}
	
	return true;
}

private void output(boolean b)
{
	if(b)
		System.out.println("It is unique.");
	else
		System.out.println("It is not unique.");
}

public static void main(String args[])throws IOException
{
	isUnique U = new isUnique();
	U.input();
	//U.output(U.checkIsUnique(s));
	//U.output(U.checkIsUnique2(s));
	//U.output(U.checkIsUnique3(s));
	U.output(U.checkIsUnique4(s));
}

}
