// P3
// STUDENT NAME HERE

/*
 * Instructions
 * ------------
 * 
 * Implement all the remaining methods in this class.
 * Feel free to use our textbook as a guide.
 * 
 * NOTE: To operate most efficiently as a stack, the linked list uses
 * the first element in the list as top. All you need to do is make calls
 * to the KittenLL class, provided that it's properly implemented.
 * 
 */


public class KittenStack implements KittenList {
	
	private KittenLL stack;
	
	// Constructor
	public KittenStack() {
		stack = new KittenLL();
	} // end constructor
	
	
	// IMPLEMENT THIS METHOD
	public boolean isEmpty() {
		
		// TODO:
		// Return boolean indicating whether list is empty.
		// You should be able to just call a method in KittenLL.
		
	} // end isEmpty
	
	
	// IMPLEMENT THIS METHOD
	public void push(Kitten k) {
		
		// TODO:
		// Add this Kitten to the top of the stack
		
	} // end push
	
	
	// IMPLEMENT THIS METHOD
	public Kitten pop() {
		
		// TODO:
		// Remove and return the Kitten at the top of the stack
		// Remember to error trap for empty lists!
		
	} // end pop
	
	
	// IMPLEMENT THIS METHOD
	public Kitten peek() {
		
		// TODO:
		// Return, but do not remove, the Kitten at the top of the stack
		// Remember to error trap for empty lists!

		
	} // end peek
	
	
	// Helper method.
	// Walks roster to see if cuteLvl for each KittenLink matches its Kitten's cuteness value
	public int walk() {
		
		KittenLink cur = stack.first;
		
		while (cur!=null) {
			if (cur.k.getCuteness() != cur.cuteLvl) {
				System.out.println("\nCuteness and cuteLvl do not match for kitten " + cur.k.getName() + ".");
				return -1;
			}
			
			cur = cur.next;
		} // end while
		
		return 0;
	} // end walk
	
	
	// Helper method.
	// Walks stack and displays it.
	public void display() {
		stack.displayList();
	} // end display
} // end class
