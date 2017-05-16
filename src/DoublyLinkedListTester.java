
import java.util.Random;

/**
 * Test of CS 143 Assignment 4 by Martin Hock (Version of 2:40 PM 5/8/2017)
 * 
 * You may only use this code as a student of Martin Hock, CS 143 Spring 2017.
 */

public class DoublyLinkedListTester {

	public static void main(String[] args) {
		int sizeScore = 0, getScore = 0, revScore = 0, addScore = 0;
		try {
			DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
			list.reverse();
			int n = 1000000;
			for (int i = 0; i < n; i++) {
				list.add(i);
				if (i == n / 2) {
					list.reverse();
					int j = i;
					for (int k : list) {
						if (k != j) {
							System.out.println("Unexpected value in reversed array! Expected " + j + " got " + k);
							break;
						}
						j--;
					}
					if (j == -1) {
						revScore += 10;
					} else {
						System.out.println("Reversed array contained error at index " + (i - j));
					}
					j = 0;
					list.reverse();
					for (int k : list) {
						if (k != j) {
							System.out.println("Unexpected value in re-reversed array! Expected " + j + " got " + k);
							break;
						}
						j++;
					}
					if (j == i + 1) {
						revScore += 10;
					} else {
						System.out.println("Re-reversed array contained error at index " + j);
					}
				}
			}
			int s;
			{
				long start = System.currentTimeMillis();
				s = list.size();
				long end = System.currentTimeMillis();
				if (end - start > 10) {
					System.out.println("Size of list was slow! Took " + (end - start) + " ms.");
				} else {
					sizeScore += 10;
				}
			}
			if (s != n) {
				System.out.println("Size incorrect after adding " + n + " elements! You report " + list.size());
			} else {
				sizeScore += 10;
			}
			Random r = new Random();
			int n2 = 100;
			int[] vals = new int[n2];
			for (int i = 0; i < n2; i++) {
				int ri = r.nextInt();
				vals[i] = ri;
				list.add(ri);
			}
			int count = 0;
			{
				long start = System.currentTimeMillis();
				for (int i = 0; i < n2; i++) {
					if (list.get(n) != vals[i]) {
						System.out.println("Get didn't work for element " + n + ": got " + list.get(n)
								+ " expected " + vals[i]);
					} else
						count++;
					list.remove(1);
				}
				if (count == n2) {
					getScore += 10;
				}
				long end = System.currentTimeMillis();
				if (end - start > 20) {
					System.out.println("Get was slow! Took " + (end - start) + " ms.");
				} else {
					getScore += 10;
				}
			}
			list.remove(-1);
			list.remove(n + n2);
			if (list.size() != n) {
				System.out.println("Wrong size after more adds and removes! Expected " + n + " got " + list.size());
			} else {
				sizeScore += 5;
			}
			for (int i = n2; i >= 0; i--) {
				if (!list.add(i, -i - 1)) {
					System.out.println("Complained when adding " + (-i - 1) + " to index " + i);
					break;
				}
				int saw = list.get(i);
				if (saw != -i - 1) {
					System.out.println(
							"Insert at index " + i + " did not place " + (-i - 1) + " there - instead, saw " + saw);
					break;
				}
				if (i == 0) {
					addScore += 10;
				}
			}
			if (list.size() != n + n2 + 1) {
				System.out.println("Wrong size after indexed adds! Expected " + (n + 101) + " got " + list.size());
			} else {
				sizeScore += 5;
			}

			if (list.remove(1) != 0) {
				System.out.println("First element should be 0 because list.remove(1) ignored element 0");
			} else {
				addScore += 2;
			}

			for (int i = 0; i < n2; i++) {
				int saw = list.remove(i + 2);
				if (saw != i + n2 + 1) {
					System.out.println("Alternating removals expected to see " + (i + n2 + 1) + " but saw " + saw);
					break;
				}
				if (i == n2 - 1) {
					addScore += 10;
				}
			}
			list.reverse();
			{
				int j = -n2 - 1;
				int k = -1;
				for (int i : list) {
					k++;
					if (k < n - n2 - 1)
						continue; // Skip first (was last) part of list
					if (i != j) {
						System.out.println("Element retrieved " + i
								+ " not the expected value inserted from list.add(i, -i - 1) " + j);
						System.out.println("This error affects both your reverse and add method scores.");
						break;
					}
					j++;
				}
				if (k == list.size() - 1) {
					revScore += 5;
					addScore += 3;
				}
			}
		} finally {
			System.out.println("Size method: " + sizeScore + " / 30");
			System.out.println("Get method: " + getScore + " / 20");
			System.out.println("Reverse method: " + revScore + " / 25");
			System.out.println("Add with index method: " + addScore + " / 25");
			System.out.println("Tentative score: " + (sizeScore + getScore + revScore + addScore) + " / 100");
		}
	}

}
