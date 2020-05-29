// binarySearch.java
// demonstrates recursive binary search
// to run this program: C>java BinarySearchApp
////////////////////////////////////////////////////////////////
class ordArray {
	private long[] a; // ref to array a
	private int nElems; // number of data items
	// -----------------------------------------------------------

	public ordArray(int max) // constructor
	{
		a = new long[max]; // create array
		nElems = 0;
	}

	// -----------------------------------------------------------
	public int size() {
		return nElems;
	}

	// -----------------------------------------------------------
	public int find(long searchKey) {

		int cur;
		int lower = 0;
		int upper = nElems - 1;

		while (true) {
			cur = (lower + upper) / 2;

			if (a[cur] == searchKey)
				return cur;
			else if (lower > upper)
				return -1;
			else {
				if (a[cur] < searchKey)
					lower = cur + 1;
				else
					upper = cur - 1;
			}
		}
	} // end find

	// -----------------------------------------------------------
	private int recFind(long search, int upper, int lower) {
		int cur = (lower + upper) / 2;
		if (a[cur] == search) 
			return 1;
		if (lower > upper) 
			return -1;
		if(a[cur]<upper)
			return recFind(search,upper-1,lower);
		else
			return recFind(search,upper,lower+1);
			
	}

	// -----------------------------------------------------------
	public void insert(long value) // put element into array
	{
		int j;
		for (j = 0; j < nElems; j++) // find where it goes
			if (a[j] > value) // (linear search)
				break;
		for (int k = nElems; k > j; k--) // move bigger ones up
			a[k] = a[k - 1];
		a[j] = value; // insert it
		nElems++; // increment size
	} // end insert()
		// -----------------------------------------------------------

	public void display() // displays array contents
	{
		for (int j = 0; j < nElems; j++) // for each element,
			System.out.print(a[j] + " "); // display it
		System.out.println("");
	}
	// -----------------------------------------------------------
}