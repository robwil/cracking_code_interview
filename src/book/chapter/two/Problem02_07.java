package book.chapter.two;

import java.nio.file.NotLinkException;
import java.util.ArrayList;

import book.common.List;
import book.common.Node;

/**
 * Problem 2.7, p. 87
 * 
 * Given a (singly) linked list, determine if it is a palindrome.
 * 
 * ---- Before looking at book solution ----
 * 
 * This problem allows the classic demonstration of time/space trade-off.
 * I implemented isPalindrome1, which is O(N^2) time and O(1) space.
 * Also implemented isPalindrome2, which is O(N) time and O(N) space.
 * 
 * ---- After looking at book solution ----
 * 
 * The book presents 3 solutions, none of which are equivalent to what I did.
 * Their first two solutions involve reversing portions of the list and then comparing.
 * The final solution involves using recursion.
 * 
 * Since neither of the book approaches have better than O(N) time + O(N) space,
 * I will keep my simpler solution. The book solution #2 may be faster in practice
 * because it does a single pass through the list, rather than the two I do in isPalindrome2.
 * 
 * @author rob
 *
 */
public class Problem02_07 {
	private static int findLength(Node startNode) {
		int length = 0;
		while (startNode != null) {
			length++;
			startNode = startNode.getNext();
		}
		return length;
	}
	private static Node nodeAtIndex(Node startNode, int i) {
		int currentIndex = 0;
		if (i > 0) {
			while (currentIndex != i) {
				currentIndex++;
				startNode = startNode.getNext();
			}
		}
		return startNode;
	}
	public static boolean isPalindrome1(Node startNode) {
		Node currentNode = startNode;
		int length = findLength(startNode);
		for (int i = 0; i < length/2; i++) {
			Node otherNode = nodeAtIndex(startNode, length-1-i);
			if (currentNode.getData() != otherNode.getData()) {
				return false;
			}
			currentNode = currentNode.getNext();
		}
		return true;
	}
	
	/**
	 * O(N) execution time through first converting Linked List to an array,
	 * which we can more efficiently access positional elements of. This takes
	 * an additional O(N) space.
	 * 
	 * @param startNode
	 * @return
	 */
	public static boolean isPalindrome2(Node startNode) {
		ArrayList<Integer> copyOfList = new ArrayList<Integer>();
		while (startNode != null) {
			copyOfList.add(startNode.getData());
			startNode = startNode.getNext();
		}
		for (int i = 0; i < copyOfList.size()/2; i++) {
			if (copyOfList.get(i) != copyOfList.get(copyOfList.size() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		int[] notPalindrome = {1, 2, 3, 4};
		int[] yesPalindrome = {1, 2, 3, 3, 2, 1};
		int[] oddPalindrome = {1, 2, 3, 42, 3, 2, 1};
		List l1 = List.fromArray(notPalindrome);
		List l2 = List.fromArray(yesPalindrome);
		List l3 = List.fromArray(oddPalindrome);
		
		List[] lists = {l1, l2, l3};
		
		for (int i = 1; i <= 3; i++) {
			System.out.println("isPalindrome1(L" + i + ") = " + isPalindrome1(lists[i-1].getHead()));
			System.out.println("isPalindrome2(L" + i + ") = " + isPalindrome2(lists[i-1].getHead()));
		}
	}
}
