
// E4
//
// Dalitso Banda
//
//
// Instructions: Finish implementing this Queue class. Bear in mind it is a circular queue!

class Queue {

	private int[] queueArray;
	private int maxSize;
	private int last;
	private int first;
	private int nElems;

	// TODO: add the four data fields here you need to implement an array-based
	// queue

	public Queue(int max) {

		// TODO: properly initialize all fields for this constructor.
		// The parameter max = maximum size of the queue.
		maxSize = max;
		queueArray = new int[maxSize];
		last = -1;
		first = 0;
		nElems = 0;

	} // end constructor

	public boolean isEmpty() {

		// TODO: implement this empty check.
		// It returns a boolean: is the queue empty?
		return (nElems == 0);

	} // end isEmpty

	public boolean isFull() {

		// TODO: implement this full check.
		// It returns a boolean: is the queue full?
		return (nElems == maxSize);

	} // end isFull

	public void push(int elem) {

		// TODO: implement the push function.
		// If the queue is full, print an error message
		// Otherwise, do what push does!
		// Remember to do a wraparound check for last...
		if (this.isFull()) {
			System.out.println("This Queue is full");
		} else {
			if (last == maxSize - 1)
				last = -1;
			queueArray[++last] = elem;
			nElems++;
		}

	} // end push

	public int pop() {

		// TODO: implement the pop function.
		// If the queue is empty, print an error message and return -1
		// Otherwise, do what pop does!
		// Remember to do a wraparound check for first...
		if (this.isEmpty()) {
			System.out.println("This Queue is empty nothing to pop");
			return -1;
		} else {
			if (first == maxSize - 1) {
				--nElems;
				return queueArray[first-=maxSize-1];
			}
			else {
				--nElems;
				return queueArray[first++];
			}
			

		}

	} // end pop

	public int peek() {

		// TODO: implement the peek function.
		// If the queue is empty, print a message indicating this.
		// Otherwise, do what peek does!
		if (this.isEmpty()) {
			System.out.println("This Queue is empty");
			return -1;
		} else {
			return queueArray[first];
		}

	} // end peek

	// This is a method used to display the entire contents of your queue
	// You don't need to change anything here.
	public void display() {

		System.out.print("\nQueue contains: ");
		int i = first;

		while (true) {
			System.out.print(queueArray[i++] + ", ");

			if (i == last + 1)
				break;
			else if (i == maxSize)
				i = 0;
		}
		System.out.println();

	} 
	public int capacity() {
		return nElems;
	}

} // end class
 