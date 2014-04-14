package book.chapter.one;

import java.util.Arrays;

/**
 * Problem 1.3
 * Given two strings, write a method to decide if one is a permutation of the other.
 * 
 * -- Before looking at book solution --
 * Option 1: sort both and compare. O(n log n) time and O(1) space if in-place sort.
 * Option 2: hash table, count character occurrences, then compare. O(n) time and O(n) space.
 * 
 * Since neither is particularly interesting, I just implemented option 1 quickly using
 * Java collections API.
 * 
 * -- After looking at book solution --
 * The book mentions the same options. It also mentions some important edge cases around whitespace
 * and capitalization which I ignored.
 * 
 * @author rob
 *
 */
public class Problem01_03 {
	public static boolean isPermutation(char[] str1, char[] str2) {
		if (str1.length != str2.length)
			return false;
		Arrays.sort(str1);
		Arrays.sort(str2);
		return Arrays.equals(str1, str2);
	}
	
	public static void main(String[] args) {
		char[] str1 = new String("dog").toCharArray();
		char[] str2 = new String("god").toCharArray();
		char[] str3 = new String("good").toCharArray();
		char[] str4 = new String("odd").toCharArray();
		
		System.out.println("Expected = true");
		System.out.println("isPermutation('dog', 'god') = " + isPermutation(str1, str2));
		System.out.println("Expected = false");
		System.out.println("isPermutation('god', 'good') = " + isPermutation(str2, str3));
		System.out.println("Expected = false");
		System.out.println("isPermutation('dog', 'odd') = " + isPermutation(str1, str4));
	}
}
