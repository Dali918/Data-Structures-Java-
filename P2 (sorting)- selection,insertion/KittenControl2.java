package Control2;

//Dalitso Banda
//Implement the methods indicated in this class. Use the TestRig2 class to test
//your methods.

public class KittenControl2 {

	private Kitten[] roster;
	private int numPets;
	private boolean fullFlag;
	private Kitten temp = new Kitten();

// Default constructor
	public KittenControl2() {
		roster = new Kitten[5];
		numPets = 0;
		fullFlag = false;
	}

// Construct roster with an array of extant pets
	public KittenControl2(Kitten[] newPets) {
		roster = new Kitten[newPets.length * 2];
		numPets = newPets.length;
		fullFlag = false;

		System.arraycopy(newPets, 0, roster, 0, newPets.length);
	}

	public void swap(int a, int b) {
		temp = roster[a];
		roster[a] = roster[b];
		roster[b] = temp;

	}

// IMPLEMENT THIS METHOD
	public int selectionSort() {

		/*
		 * 
		 * 
		 * TODO: Implement a selection sort on the Kitten roster.
		 * 
		 * Return 0 if successful, -1 if not.
		 * 
		 * 
		 *
		 */
		if (numPets > 1) {
			for (int outer = 0; outer < numPets - 1; ++outer) {
				int min = outer;
				for (int inner = outer + 1; inner < numPets; ++inner) {
					if (roster[min].getName().compareTo(roster[inner].getName()) > 0)
						min = inner;

				}
				swap(outer, min);
			}
			return 0;
		} else {
			return -1;
		}
	}

// IMPLEMENT THIS METHOD
	public int insertionSort() {

		/*
		 * 
		 * 
		 * TODO: Implement an insertion sort on the Kitten roster.
		 * 
		 * Return 0 if successful, -1 if not.
		 * 
		 * 
		 *
		 */
		if (numPets > 1) {
			for (int index = 1; index < numPets; ++index) {
				Kitten temp = roster[index];
				int against = index - 1;
				while (against >= 0 && roster[against].getName().compareTo(temp.getName()) > 0) {
					roster[against + 1] = roster[against];
					--against;
				}
				roster[against + 1] = temp;
			}
			return 0;
		} else {
			return -1;
		}

	}

// IMPLEMENT THIS METHOD
	public int find(String name, String sortMethod) {

		/*
		 * 
		 * 
		 * 
		 * TODO: Implement a binary search here using Kitten names.
		 * 
		 * Use the sortMethod parameter to control whether selection sort or insertion
		 * sort is called.
		 *
		 * Return index of Kitten if found, -1 if not found.
		 *
		 *
		 */
		sortMethod = sortMethod.toLowerCase().replaceAll("\\s", "");

		int n;
		if (sortMethod.equals("insertionsort"))
			n = this.insertionSort();
		else if (sortMethod.equals("selectionsort"))
			n = this.selectionSort();
		else
			return -1;

		int lowerBound = 0;
		int upperBound = numPets - 1;
		if (n ==0) {
			while (true) {
				int current = (lowerBound + upperBound) / 2;
				if (roster[current] == null) {
					return -1;
				}
				if (roster[current].getName().compareTo(name) == 0) {
					System.out.println("found " + name);
					return current;
				} else if (lowerBound > upperBound) {
					System.out.println(name + " not in array");
					return -1;
				} else {
					if (roster[current].getName().compareTo(name) > 0) {
						upperBound = upperBound - 1;
					} else {
						lowerBound = lowerBound + 1;
					}
				}
			}
		} else {
			System.out.println("No Sort Method was Used or failed.");
			return -1;
		}
	}

// Insert new pet into roster - no duplicate names
	public int insert(Kitten pet, String sortMethod) {

		// First, check for duplicate names
		// -1 = pet name not found in roster
		if (this.find(pet.getName(), sortMethod) == -1) {

			// Check if roster full, expand if so
			if (fullFlag == true) {
				this.expand();
				fullFlag = false;
			}

			roster[numPets++] = pet;
			// Sort the porgram after inserting. The array is sorted in find using the
			// specified
			// algorithm in find, however insertion of the last new element, their is no
			// next insert
			// iteration and find is not used thus
			this.insertionSort();

			if (numPets == roster.length)
				fullFlag = true;

			System.out.println(
					"\n" + pet.getName() + " has been successfully added to the roster at position " + numPets + "!");
			return numPets;
		}

		// Duplicate name found
		else

		{
			System.out.println(
					"\nThere is already a pet with that name. Let's not confuse our pets! Try adding a pet with a different name to the roster.");

			return -1;
		}
	}

// Delete pet from roster
// Move pets higher in the roster down to fill in the gap
	public int delete(String name, String sortMethod) {

		int i = find(name, sortMethod);

		if (i == -1) {
			System.out.println("\n" + name + " isn't in the roster! Maybe you meant some other pet?");
			return -1;
		}

		else {
			roster[i] = null;
			numPets--;

			// Shift array members down
			for (int j = i + 1; j < roster.length; j++)
				roster[i++] = roster[j];
			roster[i] = null; // Remove reference at end of array
		}

		System.out.println("\n" + name + " has been deleted from the roster.");
		return numPets;
	}

// Expand roster when fullFlag
	private int expand() {
		Kitten[] tempArr = new Kitten[roster.length * 2];
		System.arraycopy(roster, 0, tempArr, 0, roster.length);

		// Verify array copied successfully
		for (int i = 0; i < numPets; i++)
			if (roster[i] != tempArr[i])
				return -1;

		roster = tempArr;
		fullFlag = false;
		return roster.length;
	}

// Check if roster full
	public boolean checkFull() {
		return fullFlag;
	}

// Get number of pets
	public int checkPets() {
		return numPets;
	}

// Get access to pet in roster
	public Kitten getPet(int index) {
		return roster[index];
	}

}
