package StacksExercise;

//Dalitso Banda
class Stack {

	private int[] stackArray;
	// TODO: add the two data fields here you need to implement an array-based stack
	int top;
	int maxSize;

	public Stack(int max) {

		// TODO: properly initialize all fields for this constructor.
		// The parameter max = maximum size of the stack.
		maxSize = max;
		top = -1;
		stackArray = new int[maxSize];
	} // end constructor

	public boolean isEmpty() {

		// TODO: implement this empty check.
		// It returns a boolean: is the stack full?
		if (top == -1)
			return true;
		else
			return false;

	} // end isEmpty

	public boolean isFull() {

		// TODO: implement this full check.
		// It returns a boolean: is the stack full?
		if (top == maxSize - 1)
			return true;
		else
			return false;

	} // end isFull

	public void push(int elem) {

		// TODO: implement the push function.
		// If the stack is full, print an error message
		// Otherwise, do what push does!
		if (this.isFull()) {
			System.out.println("\nThis Stack is full");
		} else {
			stackArray[++top] = elem;

		}

	} // end push

	public int pop() {

		// TODO: implement the pop function.
		// If the stack is empty, print an error message and return -1
		// Otherwise, do what pop does!
		if (this.isEmpty()) {
			System.out.println("This Stack is Empty");
			return -1;
		}
		return stackArray[top--];

	} // end pop

	public int peek() {

		// TODO: implement the peek function.
		// If the stack is empty, print a message indicating this and return -1
		// Otherwise, do what peek does!
		if (this.isEmpty()) {
			System.out.println("This stack is empty");
			return -1;
		}
		return stackArray[top];

	} // end peek

	// This is a method used to display the entire contents of your stack
	// You don't need to change anything here.
	public void display() {

		System.out.print("\nStack contains: ");
		for (int i = 0; i <= top; i++)
			System.out.print(stackArray[i] + ", ");
		System.out.println();

	} // end display

} // end class
