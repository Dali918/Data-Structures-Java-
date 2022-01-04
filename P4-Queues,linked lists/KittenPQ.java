// P3
// Dalitso Banda

/*
 * Instructions
 * ------------
 * 
 * Implement all the remaining methods in this class.
 * Feel free to use our textbook as a guide.
 * 
 * NOTE: Since this is a priority queue, its push() method will insert a new data item
 * in the correct place in the linked list. This means that push() will traverse
 * the list, comparing the cuteness level of each link to the new link's cuteness level.
 * 
 * 
 * You may implement this according to the textbook, using two KittenLink references to traverse
 * the list (prev and cur), or you may implement it using the method we've discussed in class,
 * with only one KittenLink reference (cur). The former uses a loop and one edge case (an empty
 * list), the latter uses a loop and three edge cases (an empty list, insertion before first, and
 * insertion at end).
 * 
 */


public class KittenPQ implements KittenList {
	
	private KittenLL queue;
	
	// Constructor
	public KittenPQ() {
		queue = new KittenLL();
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
		// Implement this method so that the pushed Kitten is inserted
		// in the correct place according to decreasing cuteness level.
		
	} // end push
	
	
	// IMPLEMENT THIS METHOD
	public Kitten pop() {
		
		// TODO:
		// Remove and return the fist Kitten in the queue (which should be
		// the cutest kitten). You should be able to use a call to the KittenLL class here.
		// Remember to error trap for empty lists!

		
	} // end pop
	
	
	// IMPLEMENT THIS METHOD
	public Kitten peek() {
		
		// TODO:
		// Return, but do not remove, the first Kitten in the queue (which should
		// be the cutest kitten).
		// Remember to error trap for empty lists!

		
	} // end peek
	
	
	// Helper method.
	// Walks roster to see if Kittens are in order of decreasing cuteness.
	// Also checks to see if cuteLvl for each KittenLink matches its Kitten's cuteness value
	public int walk() {
		
		KittenLink cur = queue.first;
		
		while (cur!=null) {
			if (cur.k.getCuteness() != cur.cuteLvl) {
				System.out.println("\nCuteness and cuteLvl do not match for kitten " + cur.k.getName() + ".");
				return -1;
			}
			
			if (cur.next!=null) {
				if (!(cur.k.getCuteness() >= cur.next.k.getCuteness())) {
					System.out.println("\nKitten " + cur.k.getName() + " is not in the right order.");
					return -1;
				}
			}
			
			cur = cur.next;
		} // end while
		
		return 0;
	} // end walk
	
	// Helper method.
	// Walks queue and displays it.
	public void display() {
		queue.displayList();
	} // end display
	
} // end class

