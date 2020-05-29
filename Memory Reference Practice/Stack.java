
// E3
//
// STUDENT NAME HERE
//
//
// Instructions: Finish implementing this Stack class.


class Stack {
   
	private int[] stackArray;

	// TODO: add the two data fields here you need to implement an array-based stack
	int top;
	int maxSize;
	
	
	public Stack(int max) {
		
		// TODO: properly initialize all fields for this constructor.
		// The parameter max = maximum size of the stack.  
		maxSize = max;
		stackArray = new int[maxSize];
		top = -1;
		
    } // end constructor
	

   public boolean isEmpty() {

		// TODO: implement this empty check.
		// It returns a boolean: is the stack full?
	   	return (top == -1);
	   
    } // end isEmpty

   public boolean isFull() {
	   	
	   	// TODO: implement this full check.
		// It returns a boolean: is the stack full?
	   	return (top == maxSize-1);
	   
    } // end isFull

	public void push(int elem) {
		
		// TODO: implement the push function.
		// If the stack is full, print an error message
		// Otherwise, do what push does!
		
		if (isFull())
			System.out.println("\nStack is full, could not add new item.");
		else
			stackArray[++top] = elem;
		
    } // end push

   public int pop() {
    
		// TODO: implement the pop function.
		// If the stack is empty, print an error message and return -1
		// Otherwise, do what pop does!
	   
		if (isEmpty()) {
			System.out.println("\nStack is empty, could not return any item.");
			return -1;
		}
		else
			return stackArray[top--];
	   
    } // end pop

   public int peek() {
	   
		// TODO: implement the peek function.
		// If the stack is empty, print a message indicating this.
		// Otherwise, do what peek does!
	   
		if (isEmpty()) {
			System.out.println("\nStack is empty, could not look at top item.");
			return -1;
		}
		else
			return stackArray[top];
	   
    } // end peek
   
   
   // This is a method used to display the entire contents of your stack
   // You don't need to change anything here.
   public void display() {
	   
 
	   System.out.print("\nStack contains: ");
	   for (int i=0; i<=top; i++)
		   System.out.print(stackArray[i] + ", ");
	   System.out.println();
	   
   } // end display

}  // end class

