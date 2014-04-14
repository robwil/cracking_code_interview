package book.chapter.one;

import java.util.Arrays;

/**
 * Problem 1.4
 * Write a method to replace all spaces in a string with '%20'. You may assume that the
 * string has sufficient space at the end of the string to hold the additional characters,
 * and that you are given the "true" length of the string. (Note: if implementing in Java,
 * please use a character array so that you can perform this operation in place.)
 * 
 * -- Before looking at book solution --
 * Two situations:
 * 	1) if space at the end of string is perfect amount, then we can just work backward,
 *     copying as we go. O(N) time and O(1) space with only one pass through array.
 *  2) if space at the end may contain extra space, then we must do an initial pass
 *     thru array to count spaces, then work backward starting at length + numSpaces*2.
 *  
 *  I implemented #2 since it's slightly more interesting.
 *  
 *  -- After looking at book solution --
 *  The book solution is identical to what I implemented, almost verbatim.
 *  
 * @author rob
 *
 */
public class Problem01_04 {
	public static void replaceSpaces(char[] str, int length) {
		int numSpaces = 0;
		for (int i = 0; i < length; i++) {
			if (str[i] == ' ') {
				numSpaces++;
			}
		}
		for (int i = length + numSpaces*2, j = length; j >= 0; j--) {
			if (str[j] == ' ') {
				// If we have space, manually copy %20 into three previous spaces
				str[i] = '0';
				str[i-1] = '2';
				str[i-2] = '%';
				i -= 3;
			} else {
				// Otherwise perform straight copy
				str[i] = str[j];
				i--;
			}
		}
	}
	
	public static void main(String[] args) {
		char[] str = {'a', ' ', 'b', ' ', 'c', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
		// "a b c", length = 5
		replaceSpaces(str, 5);
		System.out.println("replaceSpaces(\"a b c\", 5) = " + Arrays.toString(str));
		System.out.println("replaceSpaces(\"a b c\", 5) converted to String = " + new String(str));
	}
}
