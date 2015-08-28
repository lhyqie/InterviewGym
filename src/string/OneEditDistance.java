package string;

public class OneEditDistance {
	public static void main(String[] args) {

	}

	public static boolean isOneEditDistance(String s1, String s2) {
		int n1 = s1.length();
		int n2 = s2.length();
		if (n1 > n2)
			return isOneEditDistance(s2, s1); // presume s1.length() <=
												// s2.length()

		if (n2 - n1 > 1)
			return false;

		if (n1 == n2) {
			// s1 = ABCD s2 = AECD , only one character can be different
			boolean diff = false;
			for (int i = 0; i < n1; i++) {
				if (s1.charAt(i) != s2.charAt(i)) {
					if (diff)
						return false;
					else
						diff = true;
				}
			}
			return diff == true;
		} else {// n1 + 1 == n2
			// s1 = ABCD s2 = EABCD
			// s1 = ABCD s2 = ABCDE
			// s1 = ABCD s2 = ABECD
			boolean diff = false;
			int p1 = 0, p2 = 0;
			while (p1 < n1 && p2 < n2) {
				if (s1.charAt(p1) != s2.charAt(p2)) {
					if (diff)
						return false;
					else {
						diff = true;
						p2++;
					}
				} else {
					p1++;
					p2++;
				}
			}
			if (p2 != n2 && diff)
				return false; // s1 = ABCD s2 = ABEDF
			return true;
		}
	}

	public static boolean isAtMostOneEditDistance(String s1, String s2) {
		int n1 = s1.length();
		int n2 = s2.length();
		if (n1 > n2)
			return isAtMostOneEditDistance(s2, s1); // presume s1.length() <=
												// s2.length()
		if (n2 - n1 > 1)
			return false;

		if (n1 == n2) {
			// s1 = ABCD s2 = AECD , only one character can be different or
			// s1 = ABCD s2 = ABCD , exactly the same
			boolean diff = false;
			for (int i = 0; i < n1; i++) {
				if (s1.charAt(i) != s2.charAt(i)) {
					if (diff)
						return false;
					else
						diff = true;
				}
			}
			return diff == true;
		} else {// n1 + 1 == n2
			// s1 = ABCD s2 = EABCD
			// s1 = ABCD s2 = ABCDE
			// s1 = ABCD s2 = ABECD
			boolean diff = false;
			int p1 = 0, p2 = 0;
			while (p1 < n1 && p2 < n2) {
				if (s1.charAt(p1) != s2.charAt(p2)) {
					if (diff)
						return false;
					else {
						diff = true;
						p2++;
					}
				} else {
					p1++;
					p2++;
				}
			}
			if (p2 != n2 && diff)
				return false; // s1 = ABCD s2 = ABEDF
			return true;
		}
	}
}
