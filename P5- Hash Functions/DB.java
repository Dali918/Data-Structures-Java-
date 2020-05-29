import java.util.zip.CRC32;

/*
 * ***Dalitso Banda***
 * 
 * DESCRIPTION
 * ------------
 * 
 * DB is an open-addressed hash table that contains Kitten objects. The probing method is set at the time
 * of creation in the Probes enum.
 * 
 * 
 * INSTRUCTIONS
 * ------------
 * Implement the following methods: add, delete, lProbe, qProbe, dhProbe, hash, doubleHash
 * 
 * You are free to research and use well-known hash functions or to invent your own. The primary hash function
 * should only use a Kitten's name. Due to our use of randomly generated Kitten names, we won't be implementing
 * an explicit find method, although the delete method will do the same thing a find method would do (plus free
 * up an index in the table). 
 */

public class DB {

	static enum Probes {
		LINEAR, QUADRATIC, DOUBLEHASH
	}

	int size;
	Kitten[] table;
	Probes probeMethod = Probes.LINEAR;

	public DB(int maxNum, Probes probe) {

		size = maxNum;
		table = new Kitten[size];
		probeMethod = probe;
	} // end constructor

	/*
	 * TODO: Implement this method!
	 * 
	 * Takes a Kitten name, runs it though a hash function, returns the hash as an
	 * int.
	 */

	private int hash(String name) {
		// String hash function called CRC32
		int result = 0;
		CRC32 myCRC = new CRC32();
		myCRC.update(name.getBytes());
		// used the Math.abs method to get rid of negatives and typecast the value from
		// long to int.Then modulo it by 1001 to ensure value is within array size length.
		result = Math.abs(((int) myCRC.getValue())) % 1001;
		return result;
	} // end hash

	/*
	 * TODO: Implement this method!
	 * 
	 * Takes a Kitten object, runs it through a second hash function, returns the
	 * double hash as an int to be used as a step size in probing.
	 */

	private int doubleHash(int index) {

		int result = 0;

		// Insert double hash function here. You can start with the hash method
		// and modify the result, or just use the Kitten name to generate a double
		// hash directly.

		// double hash using the conventional double hash function. method takes
		// in the first hash as an argument and not the string directly.
		result = 7 - (index % 7);
		return result;
	} // end doubleHash

	/*
	 * TODO: Implement this method!
	 * 
	 * Uses linear probing to search for an open index. Returns that index or, in
	 * the case of a failed probe (no open indices), -1.
	 * 
	 * For simplicity's sake, the probe method fails when it returns to the index it
	 * started with. Make sure your code reflects this condition.
	 */

	private int lProbe(int start, boolean deleting, String name) {
		int result = -1;
		int current = start + 1;
		// while loop that terminates when the original collision spot is encountered
		// again.
		while (current != start) {
			// Adjust appropriately to avoid going out of bounds.
			if (current >= size)
				current = 0;
			if (!deleting) {
				// continue probing or return empty spot if adding.
				if (table[current] == null)
					return current;
				current++;
			} else {
				// continue probing or return found kitten when deleting.
				if (table[current] != null && (table[current].getName().equals(name)))
					return current;
				else
					++current;
			}
		}
		// return -1 (result initialized to -1 above) if probing fails
		return result;
	} // end lProbe

	/*
	 * TODO: Implement this method!
	 * 
	 * Uses quadratic probing to search for an open index. Returns that index or, in
	 * the case of a failed probe (no open indices), -1.
	 * 
	 * For simplicity's sake, the probe method fails when it returns to the index it
	 * started with. Make sure your code reflects this condition.
	 */

	private int qProbe(int start, boolean deleting, String name) {
		int result = -1;
		int crash = 1;
		int step = 1;
		int current = start + step;
		// while loop that terminates when the original collision spot is encountered
		// again.
		while (current != start) {
			// loop back around array if current is out of bounds
			if (current >= size) {
				// adjust current appropriately to avoid going out of bounds.
				current = current % size;
//				if (current >= size)
//					System.out.print("WTF");
			}
			// increase step size or return empty spot if adding.
			if (!deleting) {
				if (table[current] == null)
					return current;
				else {
					++crash;
					step = crash * crash;
					current = (start + step) % size;
				}
				// Increase step size or return found kitten if deleting.
			} else {
				if (table[current] != null && table[current].getName().equals(name)) {
					return current;
				} else {
					crash++;
					step = crash * crash;
					current = (start + step) % size;
				}

			}

		}
		// if probing fails, return -1
		return result;
	} // end qProbe

	/*
	 * TODO: Implement this method!
	 * 
	 * Uses double hashing to search for an open index. Returns that index or, in
	 * the case of a failed probe (no open indices), -1.
	 * 
	 * For simplicity's sake, the probe method fails when it returns to the index it
	 * started with. Make sure your code reflects this condition.
	 */

	private int dhProbe(int start, boolean deleting, String name) {

		int result = -1;
		// Acquire unique step size from double hash
		int step = doubleHash(start);
		int current = start + step;
		// while loop that terminates when the orginal collision spot is encountered
		// again.
		while (current != start) {
			// Adjust current index appropriately to avoid going out of bounds.
			if (current >= size) {
				current = current % size;
			}
			if (!deleting) {
				// return index of empty spot or continue probing if adding.
				if (table[current] == null)
					return current;
				else
					current = current + step;
			} else {
				// return index of found kitten or continue probing if deleting.
				if (table[current] != null && table[current].getName().equals(name))
					return current;
				else
					current = current + step;
			}
		}
		// return -1 (result initialized to -1 above) if probing fails
		return result;
	} // end dhProbe

	/*
	 * TODO: Implement this method!
	 * 
	 * Hashes a Kitten object and tries to insert it, probing if necessary. Returns
	 * the index of a successful insertion, -1 for failure.
	 */

	public int add(Kitten newKitten) {

		int index = -1;
		// get hash index
		index = hash(newKitten.getName());
		// insert if empty at index and return index.
		if (table[index] == null) {
			table[index] = newKitten;
			return index;
		} else {
			// probe if collision occurs, return -1 if probing fails/ index if probing
			// succeeds.
			index = probe(index, false, "");
			if (index == -1)
				return -1;
			else
				// insert at index
				table[index] = newKitten;
			return index;
		}
	} // end add

	/*
	 * TODO: Implement this method!
	 * 
	 * Deletes a Kitten object by removing its reference in the table. Returns a
	 * reference to the deleted Kitten or null in the case of a failed search.
	 */

	public Kitten delete(String name) {

		Kitten delKit = null;
		// get hash index
		int index = hash(name);
		if (table[index] == null || table[index].getName() != name) {
			// probe if null is encountered(previous deletion of identical hash) or
			// kitten with duplicate hash is there.
			index = probe(index, true, name);
			// Return found kitten if probe successful or null if not and delete from
			// roster.
			if (index != -1) {
				delKit = table[index];
				table[index] = null;
			}
			return delKit;
			// Return found kitten if no probe necessary and delete from roster.
		} else {
			delKit = table[index];
			table[index] = null;
			return delKit;
		}
	} // end delete

	/*
	 * Wrapper method that calls the correct probing method based on the probeMethod
	 * field. You should use this method in your add and delete.
	 */
	private int probe(int start, boolean deleting, String name) {
		// added extra 'name' parameter to help with kitten comparison when
		// deleting.
		switch (this.probeMethod) {

		case LINEAR:
			return lProbe(start, deleting, name);

		case QUADRATIC:
			return qProbe(start, deleting, name);

		case DOUBLEHASH:
			return dhProbe(start, deleting, name);

		default:
			return lProbe(start, deleting, name);
		}
	} // end probe

	// Helper method that prints a list of indices that quadratic probing
	// will check, starting with the start index. Produces n probe values
	public void quadCheck(int start, int n) {

		System.out.println("\nQuadratic probe locations:");
		for (int i = 1; i <= n; i++)
			System.out.println((start + i * i) % size);
	} // end quadCheck

	// Helper method that prints a list of indices that double hashing
	// will check, starting with the start index. Produces n probe values
	public void dhCheck(int start, int step, int n) {

		System.out.println("\nDouble hash probe locations:");
		for (int i = 1; i <= n; i++)
			System.out.println((start + step * i) % size);
	} // end dhCheck

	// Display method for DB
	public void display() {

		int index = -1;

		for (Kitten kit : table) {
			index++;
			if (kit != null)
				System.out.println(index + ": " + kit.getName() + " has hunger " + kit.getHunger() + " and cuteness "
						+ kit.getCuteness());
		}
	} // end display

} // end DB class
