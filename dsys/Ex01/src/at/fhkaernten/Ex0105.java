package at.fhkaernten;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.Collections;

public class Ex0105 {

	private static final int SIZE = 32;

	private static void printVector(Vector<Integer> vector) {
		System.out.print("[");
		for (int i = 0; i < vector.size(); i++) {
			if (i > 0)
				System.out.print(",");
			System.out.print(vector.elementAt(i));
		}
		System.out.println("]");
	}

	private static void fillVector(Vector<Integer> vector) {
		for (int i = 0; i < vector.capacity(); i++)
			vector.addElement(new Integer((int) (Math.random() * 10)));
	}

	private static void countOccurrences(Vector<Integer> vector,
			HashMap<Integer, Integer> hashmap) {
		for (int i = 0; i < vector.size(); i++) {
			int key = vector.elementAt(i);
			if (hashmap.get(key) == null)
				hashmap.put(key, 1);
			else {
				int value = hashmap.get(key);
				hashmap.put(key, ++value);
			}
		}
	}

	public static void main(String[] args) {
		// 5.1
		// HashMap implements Map interface which maps key to value
		// (myHashMap.put('Zero', new Double(3434.34));)
		// HashSet extends AbstractSet and implements the Set interface. It
		// creates a collection that uses a hash table for storage. \nHashtable
		// implements Set interface which does not allow duplicate value.
		// (myHashSet.add('B')
		// The Vector class implements a growable array of objects. Like an
		// array, it contains components that can be accessed using an integer
		// index. However, the size of a Vector can grow or shrink as needed to
		// accommodate adding and removing items after the Vector has been
		// created.

		// 5.2
		Vector<Integer> vector = new Vector<Integer>(SIZE);

		printVector(vector);
		fillVector(vector);
		printVector(vector);
		Collections.sort(vector);
		printVector(vector);

		// 5.3
		HashSet<Integer> hashset = new HashSet<Integer>(vector);
		System.out.println(hashset);

		// 5.4
		HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
		countOccurrences(vector, hashmap);
		System.out.println(hashmap);

	}
}