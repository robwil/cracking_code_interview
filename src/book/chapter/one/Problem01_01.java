package book.chapter.one;

/**
 * Implement an algorithm to determine if a string has all unique characters. What
 * if you cannot use additional data structures?
 * 
 * --- Before looking at book solution ---
 * 
 * The obvious solution here is to use a hash table, storing each character as seen.
 * If a character is seen twice, return false. Otherwise return true. But this uses
 * an additional data structure.
 * 
 * One approach that counts as O(1) space (although technically doesn't meet the no-data-structure stipulation)
 * is is to use an boolean array of 256 elements. To explicitly make this
 * a bit array, I used a long[4] array and handled the modulo arithmetic manually.
 * 
 * My implementation below had one bug where I set the bit, I was using (1 << y) but this is an integer literal.
 * I needed to change to (1L << y) otherwise y > 32 would overflow.
 * 
 * --- After looking at book solution ---
 * 
 * The book solution, like me, assumes an ASCII string and uses bit vectors.
 * However, the book has an additional optimization, which is returning false immediately
 * if the string's length is greater than number of characters (256). I added this check
 * to uniqueCharsBookSolution below.
 * 
 * @author rob
 *
 */
public class Problem01_01 {
	public static boolean uniqueChars(char[] str) {
		// long is 64 bits. long[4] gives us 256 bits, one for each ASCII char
		long[] seen = new long[4];
		for (int i = 0; i < str.length; i++) {
			int x = str[i] / 64;
			int y = str[i] % 64;
			if ((seen[x] >> y & 1) == 1) { // check if seen[x] at bit #y is set to 1
				return false; // if so, return false as it indicates seeing this char before
			}
			// otherwise, mark current char as seen by setting seen[x] bit #y to 1
			seen[x] |= (1L << y);
		}
		return true;
	}
	
	public static boolean uniqueCharsBookSolution(char[] str) {
		// string can't be all unique if it has more characters than unique chars in ASCII!
		if (str.length > 256) return false;
		// long is 64 bits. long[4] gives us 256 bits, one for each ASCII char
		long[] seen = new long[4];
		for (int i = 0; i < str.length; i++) {
			int x = str[i] / 64;
			int y = str[i] % 64;
			if ((seen[x] >> y & 1) == 1) { // check if seen[x] at bit #y is set to 1
				return false; // if so, return false as it indicates seeing this char before
			}
			// otherwise, mark current char as seen by setting seen[x] bit #y to 1
			seen[x] |= (1L << y);
		}
		return true;
	}
	
	public static void main(String[] args) {
		char[] str1 = new String("abcdefghijklmnopqrstuvwxyz1234567890").toCharArray();
		char[] str2 = new String("blahblahblah").toCharArray();
		
		System.out.println("Expected = true");
		System.out.println("uniqueChars(str1) = " + uniqueChars(str1));
		System.out.println("Expected = false");
		System.out.println("uniqueChars(str2) = " + uniqueChars(str2));
	}
}
