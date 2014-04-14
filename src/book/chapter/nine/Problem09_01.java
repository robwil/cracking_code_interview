package book.chapter.nine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem 9.1, p. 118
 * A child is running up a staircase with n steps, and can hop either 1 step, 2 steps,
 * or 3 steps at a time. Implement a method to count how many possible ways the
 * child can run up the stairs.
 * 
 * This problem is more difficult than it first appears to appear. My original solution, stairhop1,
 * missed the fact that there can be duplicates. I handle this in stairhop2 with a hash table.
 * For stairhop3, I tried to memoize the hash table solution but it actually made it slower, probably
 * from all the memory allocation and string manipulation that I'm doing.
 * 
 * The book solution is to use DP, which is MUCH more elegant than my messy solutions, and MUCH faster.
 * 
 * @author rob
 *
 */
public class Problem09_01 {
	public static int stairhop1(int n) {
		Integer[] F = new Integer[n];
		return stairhop1(n, F);
	}
	private static int stairhop1(int n, Integer[] F) {
		// If already memoized, return immediately.
		if (F[n-1] != null) return F[n-1];
		// Otherwise recursively calculate result.
		int amount = 0;
		for (int hop = 1; hop <= 3; hop++) {
			// Check if we can go "hop" steps with a remainder
			// If so, do it on both sides (e.g. hop 3 steps then remainder, or remainder then 3 steps)
			// This is why we add 2 to the amount of ways we can hop the remainder.
			if (n - hop > 0)
				amount += 1 + stairhop1(n-hop, F);
			// Represents being able to hop exactly "hop" steps with no remainder.
			else if (n - hop == 0)
				amount++;
		}
		// Save result to memory, then return.
		F[n-1] = amount;
		return amount;
	}
	
	public static int stairhop2(int n) {
		Set<String> ways = new HashSet<String>();
		stairhop2(n, new StringBuilder(), ways);
		//System.out.println(ways);
		return ways.size();
	}
	private static void stairhop2(int n, StringBuilder stepsSoFar, Set<String> ways) {
		if (n == 0)
			ways.add(stepsSoFar.toString());
		else {
			for (int hop = 1; hop <= 3; hop++) {
				if (n - hop >= 0)
					stairhop2(n - hop, new StringBuilder(stepsSoFar).append(Integer.toString(hop)), ways);
			}
		}
	}
	
	public static int stairhop3(int n) {
		// Initialize memo to all nulls
		List<Set<String>> memo = new ArrayList<Set<String>>();
		for(int i = 0; i <= n; i++) {
			memo.add(null);
		}
		// Recursively find answer
		Set<String> ways = stairhop3(n, memo);
		return ways.size();
	}
	private static Set<String> stairhop3(int n, List<Set<String>> memo) {		
		if(memo.get(n) != null) return memo.get(n);
		
		Set<String> ways = new HashSet<String>();
		// Null case
		if (n == 0) {
			ways.add("");
		} else {
			for (int hop = 1; hop <= 3; hop++) {
				if (n - hop >= 0) {
					Set<String> oWays = stairhop3(n - hop, memo);
					for (String w : oWays) {
						ways.add(Integer.toString(hop) + w);
					}
				}
			}
		}
		// memoize
		memo.set(n, ways);
		return ways;
	}
	
	public static int stairhopBookSolution(int n) {
		Integer[] memo = new Integer[n];
		return stairhopBookSolution(n, memo);
	}
	private static int stairhopBookSolution(int n, Integer[] memo) {
		if (n < 0) return 0;
		if (n == 0) return 1;
		if (memo[n-1] != null) return memo[n-1];
		int ans = stairhopBookSolution(n-1, memo) + stairhopBookSolution(n-2, memo) + stairhopBookSolution(n-3, memo);
		memo[n-1] = ans;
		return ans;
	}
	
	public static void main(String[] args) {
		System.out.println("stairhop1(4)="+stairhop1(4));
		System.out.println("stairhop1(5)="+stairhop1(5));
		
		System.out.println("stairhop2(4)="+stairhop2(4));
		System.out.println("stairhop2(5)="+stairhop2(5));
		System.out.println("stairhop2(6)="+stairhop2(6));
		System.out.println("stairhop2(7)="+stairhop2(7));
		System.out.println("stairhop2(8)="+stairhop2(8));
		long startTime = System.currentTimeMillis();
		System.out.println("stairhop2(23)="+stairhop2(23));
		long endTime = System.currentTimeMillis();
		System.out.println("Elapsed = " + (endTime-startTime));
		
		System.out.println("stairhop3(4)="+stairhop3(4));
		System.out.println("stairhop3(5)="+stairhop3(5));
		System.out.println("stairhop3(6)="+stairhop3(6));
		System.out.println("stairhop3(7)="+stairhop3(7));
		System.out.println("stairhop3(8)="+stairhop3(8));
		startTime = System.currentTimeMillis();
		System.out.println("stairhop3(23)="+stairhop3(23));
		endTime = System.currentTimeMillis();
		System.out.println("Elapsed = " + (endTime-startTime));
		
		System.out.println("stairhopBookSolution(4)="+stairhopBookSolution(4));
		System.out.println("stairhopBookSolution(5)="+stairhopBookSolution(5));
		System.out.println("stairhopBookSolution(6)="+stairhopBookSolution(6));
		System.out.println("stairhopBookSolution(7)="+stairhopBookSolution(7));
		System.out.println("stairhopBookSolution(8)="+stairhopBookSolution(8));
		startTime = System.currentTimeMillis();
		System.out.println("stairhopBookSolution(23)="+stairhopBookSolution(23));
		endTime = System.currentTimeMillis();
		System.out.println("Elapsed = " + (endTime-startTime));
	}
}
