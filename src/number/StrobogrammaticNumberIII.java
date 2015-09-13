package number;

import java.util.*;

public class StrobogrammaticNumberIII {
	
	public static void main(String[] args) {
		StrobogrammaticNumberIII problem = new StrobogrammaticNumberIII();
		int ret = problem.strobogrammaticInRange("0", "1680");
		System.out.println(ret);
	}
	public int strobogrammaticInRange(String low, String high) {
		if (low == null || high == null || low.length() == 0 || high.length() == 0)
			return 0;
		int ll = low.length();
		int hl = high.length();
		if (ll > hl || (ll == hl && low.compareTo(high) > 0)) {
			return 0;
		}

		int result = 0;

		List<String> list = findStrobogrammatic(ll);

		if (ll == hl) {
			for (String s : list) {
				if (s.compareTo(low) >= 0 && s.compareTo(high) <= 0) {
					result++;
					System.out.println(s);
				}
			}
		} else {
			for (int i = list.size() - 1; i >= 0; i--) { // check length ll
				String s = list.get(i);
				if (s.compareTo(low) >= 0) {
					System.out.println(s);
					result++;
				}
			}
			list = findStrobogrammatic(hl); // check length hl
			for (String s : list) {
				if (s.compareTo(high) <= 0) {
					result++;
					System.out.println(s);
				}
			}

			for (int i = ll + 1; i < hl; i++) { // check length ll+1 to hl-1
				result += findStrobogrammatic(i).size();
				System.out.println(findStrobogrammatic(i));
			}
		}
		return result;
	}

	HashMap<Character, Character> mirrorNumber = new HashMap<Character, Character>() {
		{
			put('1', '1');
			put('6', '9');
			put('8', '8');
			put('9', '6');
			put('0', '0');
		}
	};

	public List<String> findStrobogrammatic(int n) {
		List<String> res = new ArrayList<String>();
		LinkedList<Character> buffer = new LinkedList<Character>();
		if (n % 2 == 0) {
			search(res, buffer, 0, n);
		} else {
			buffer.add('1');
			search(res, buffer, 1, n);
			buffer.clear();

			buffer.add('0');
			search(res, buffer, 1, n);
			buffer.clear();

			buffer.add('8');
			search(res, buffer, 1, n);
			buffer.clear();
		}
		return res;
	}

	private void search(List<String> res, LinkedList<Character> buffer, int k, int n) {
		if (k == n) {
			StringBuffer sb = new StringBuffer();
			for (char ch : buffer)
				sb.append(ch);
			// special case for "00", "0000"
			if (n > 1 && sb.charAt(0) == '0')
				return; // should not be included
			res.add(sb.toString());
		} else if (k > n) {
			return;
		} else {
			// add characters to both ends
			for (Map.Entry<Character, Character> entry : mirrorNumber.entrySet()) {
				char key = entry.getKey();
				char value = entry.getValue();
				buffer.addFirst(key);
				buffer.addLast(value);
				search(res, buffer, k + 2, n);
				buffer.removeFirst();
				buffer.removeLast();
			}

		}
	}
}
