package book.chapter.one;

/**
 * Problem 1.5
 * Implement a method to perform basic string compression using the counts of
 * repeated characters. For example, the string aabcccccaaa would become
 * a2blc5a3. If the "compressed" string would not become smaller than the original
 * string, your method should return the original string
 * 
 * -- Before looking at book solution --
 * This is a relatively straight-forward algorithm, to loop through the string once
 * and keep track of consecutive letters with some extra variables. My solution
 * uses O(N) time and O(N) space.
 * 
 * My solution did have several bugs. For some reason instead of outputting last+count
 * to the StringBuilder, I was just copying 'count' versions of 'last' to the newStr.
 * Essentially doing a very inefficient string copy. Also, I did not write the final combination out.
 * 
 * -- After looking at book solution --
 * The book shows how to do it without using StringBuilder, by calculating the output
 * length and then allocating an appropriate length char[], but it's essentially the same answer.
 * 
 * @author rob
 *
 */
public class Problem01_05 {
	public static String compress(String str) {
		if (str == null || str.length() <= 0) return str;
		StringBuilder newStr = new StringBuilder(str.length());
		char[] strArr = str.toCharArray();
		char last = strArr[0];
		int count = 1;
		for (int i = 1; i < strArr.length; i++) {
			if (strArr[i] == last)
				count++;
			else {
				// new letter encountered, so write 'last'+'count' to 'newStr'.
				newStr.append(last);
				newStr.append(count);
				count = 1;
				last = strArr[i];
			}
		}
		// Add last combination to newStr
		newStr.append(last);
		newStr.append(count);
		if (newStr.length() >= strArr.length)
			return str; // return old string if it is smaller or equal
		// Otherwise return new string
		return newStr.toString();
	}
	
	public static void main(String[] args) {
		String str1 = "aaabbbcccdddeee";
		String str2 = "boomerang";
		String str3 = "goodness";
		String str4 = "shhhh";
		
		System.out.println("compress('aaabbbcccdddeee') = " + compress(str1));
		System.out.println("compress('boomerang') = " + compress(str2));
		System.out.println("compress('goodness') = " + compress(str3));
		System.out.println("compress('shhh') = " + compress(str4));
	}
}
