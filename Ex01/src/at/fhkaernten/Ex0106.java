package at.fhkaernten;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class Ex0106 {

	// 6.2
	public static boolean isPalindrome(char[] tokens) {
		int start = 0, end = (tokens.length) - 1;

		while (start < end) {
			if (tokens[start] != tokens[end])
				return false;
			start++;
			end--;
		}
		return true;
	}

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		// 6.1
		Scanner sc = new Scanner(System.in);
		System.out.println("Bitte ein Wort: ");
		String input = sc.nextLine();
		char[] wort = input.toCharArray();

		if (isPalindrome(wort))
			System.out.println(input + " ist ein Palindrom");
		else
			System.out.println(input + " ist kein Palindrom");

		sc.close();

		// 6.3
		Scanner sc2 = null;
		Hashtable<String, Integer> palindrome = new Hashtable<String, Integer>();
		try {
			sc2 = new Scanner(new File("text.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc2.hasNextLine()) {
			Scanner s2 = new Scanner(sc2.nextLine());
			boolean b;
			while (b = s2.hasNext()) {
				String s = s2.next();
				String resultlc = s.toLowerCase();
				String result = resultlc.replaceAll("[-+.^:,_?!\"]", "");
				if (result.length() > 0) {
					char[] c_arr = result.toCharArray();
					if (isPalindrome(c_arr) == true) {
						palindrome.put(result, 1);
					}
				}
			}
		}
		System.out.println(palindrome);
		sc2.close();
	}
}