/**
 * 1.4 Check if a given string is a permutation of a palindrome.
 * The palindrome does not need to be limited to just dictionary words.
 * 
 * example 
 * input:  Tact Coa
 * output: true (permutations: "taco cat", "atco cta", etc.)
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PalindromePermutation {

	private static String s;
	
	private void input() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a string.");
		s = br.readLine();
	}
	
	/*
	 * Personal solution
	 * Assumption: ASCII string of only English alphabets and not special characters. Can modify string to all lowercase.
	 * Time O(n) where n is the length of the string
	 * Space O(1)
	 */
	private boolean checkPalPerm1(String s)
	{
		s = s.toLowerCase();
		int[] letters = new int[26];
		boolean seenPivotOfPalindrome = false; //pivot of palindrome is the only non-repeated letter in the center. example for racecar, the pivot is e 
		for(int i=0; i<s.length(); i++)
		{
			char ch = s.charAt(i);
			
			if(Character.isLetter(ch))
				letters[ch-'a']++;
		}
		
		for(int i=0; i<26; i++)
		{
				
			if(letters[i] == 1)
			{
				if(seenPivotOfPalindrome) //you can't have more than one pivot
					return false;
				
				else
					seenPivotOfPalindrome = true;
			}
			
			else if(letters[i]%2 != 0) //can't have odd number of letters in a palindrome (excluding pivot)
				return false;
		}
		
		return true;
	}
	
	/* Book Solution
	 * using bit vector (you don't need to know the actual number of occurrences of each letter, you just need to know whether it's even or odd)
	 * Time O(n) note: it can't get better than that because you have to look at all letters
	 * Space O(1)
	 */
	boolean checkPalPerm2(String phrase)
	{
		int bitVector = createBitVector(phrase);
		return bitVector == 0 || checkExactlyOneBitSet(bitVector);
		//bitVector is zero if it's a palindrome like this abba
		//bitVector has exactly one bit if it's a palindrome like this abcba
	}
	/* Map each character to a number. a->0, b->1, c->2, etc.
	 * This is case insensitive.
	 * Non-letter characters map to -1.
	 */
	int getCharNumber(Character c)
	{
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int A = Character.getNumericValue('A');
		int Z = Character.getNumericValue('Z');
		int val = Character.getNumericValue(c);
		if(a<=val && val<=z)
			return val - a;
		else if (A<=val && val<=Z)
			return val - A;
		
		return -1;
	}
	/*Create a bit vector for the string. For each letter with value i, toggle the ith bit*/
	int createBitVector(String phrase)
	{
		int bitVector = 0;
		for(char c: phrase.toCharArray())
		{
			int x = getCharNumber(c);
			bitVector = toggle(bitVector, x);
		}
		return bitVector;
	}
	/*Toggle the ith bit in the integer*/
	int toggle(int bitVector, int index)
	{
		if(index<0) return bitVector;
		
		int mask = 1<<index; //choose the place of the alphabet
		if((bitVector & mask) == 0) //odd occurrence
			bitVector |= mask; //turn switch on
		
		else //even occurrence
			bitVector &= ~mask; //turn switch off 
		
		return bitVector;
	}
	/* Check that exactly one bit is set by subtracting one from the integer and ANDing it wiht the original integer.
	 * 
	 * Example of bitVector with exactly one bit set:
	 * 00010000 - 1 = 00001111 (there is NO overlap)
	 * 00010000 & 00001111 = 0
	 * 
	 * Example of bitVector with more than one bit set:
	 * 00101000 - 1 = 00100111 (there is overlap)
	 * 00101000 & 00100111 = 00100000
	 * */
	boolean checkExactlyOneBitSet(int bitVector)
	{
		return (bitVector & (bitVector - 1)) == 0;
	}
	
	private void output(boolean b)
	{
		if(b)
			System.out.println("Palindrome Permutation");
		else
			System.out.println("Not a Palindrome Permutation");
	}
	
	public static void main(String args[])throws IOException
	{
		PalindromePermutation P = new PalindromePermutation();
		P.input();
		//P.output(P.checkPalPerm1(s));
		P.output(P.checkPalPerm2(s));
	}
}
