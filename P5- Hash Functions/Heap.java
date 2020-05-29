/*
 * 
 * DESCRIPTION
 * ------------
 * 
 * Heap is a... um... a heap. Original, amirite? It is used in this program to create a priority
 * list for adoption. The MOST WORTHY KITTEN is always at the top of the heap, and is the only kitten
 * that can be adopted. The definition of "most worthy" can be freely changed by the user, using
 * different keys in the Kitten class (name, hunger, cuteness). Each time a new key is selected, however,
 * a new Heap must be created.
 * 
 * Note that since heaps are weakly ordered, when there are ties between items on a given key,
 * the first item to enter the heap will be above the later item.
 * 
 * You don't need to change anything in this class, but it relies on an implementation of DB to function.
 * 
 */


public class Heap {

	ShelterManager.PriorityKey curKey;
	DB table;
	
	private Node[] heapArray;
	public int maxSize;
	public int currentSize;
	
	public Heap(DB table, ShelterManager.PriorityKey key) {
		
		this.table = table;
		curKey = key;
		maxSize = this.table.size;
		currentSize = 0;
		heapArray = new Node[maxSize];
	} // end constructor
	
	
	public void heapify() {
		
		for (int i = 0; i < maxSize; i++) {
			if (table.table[i] != null) {
				insert(table.table[i]);
			}
		}
	} // end heapify
	
   public boolean insert(Kitten k)
      {
      if(currentSize==maxSize)
         return false;
      Node newNode = new Node(k);
      heapArray[currentSize] = newNode;
      trickleUp(currentSize++);
      return true;
	  }  // end insert()
   
   
   public void trickleUp(int index)
      {
      int parent = (index-1) / 2;
      Node bottom = heapArray[index];

      while(index > 0 &&
             isBigger(bottom.getData(), heapArray[parent].getData()))
         {   	  
         heapArray[index] = heapArray[parent];  // move it down
         index = parent;
         parent = (parent-1) / 2;
         }  // end while
      heapArray[index] = bottom;
      }  // end trickleUp()

   
   public Node remove()           
      {                           
      Node root = heapArray[0];
      heapArray[0] = heapArray[--currentSize];
      trickleDown(0);
      return root;
      }  
	
   public void trickleDown(int index)
      {
      int largerChild;
      Node top = heapArray[index];       // save root
      while(index < currentSize/2)       // while node has at
         {                               //    least one child,
         int leftChild = 2*index+1;
         int rightChild = leftChild+1;
                                         // find larger child
         if(rightChild < currentSize &&  // (rightChild exists?)
                             isBigger(heapArray[rightChild].getData(),
                             heapArray[leftChild].getData()))
            largerChild = rightChild;
         else
            largerChild = leftChild;
                                         // top >= largerChild?
         if(isBigger(top.getData(), heapArray[largerChild].getData()) ||
        		 isEqual(top.getData(), heapArray[largerChild].getData()))
            break;
                                         // shift child up
         heapArray[index] = heapArray[largerChild];
         index = largerChild;            // go down
         }  // end while
      heapArray[index] = top;            // root to index
      }  // end trickleDown()
   
	// Helper method
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	// Peeks at top of heap
	public Node peek() {
		return heapArray[0];
	} // end peek
	
	// Returns true if k1 is "bigger" than k2 based on curKey
	public boolean isBigger(Kitten k1, Kitten k2) {
	
		boolean ret;
		switch (curKey) {
		  
		case NAME:
			if (k1.getName().compareTo(k2.getName()) > 0) ret = false;
			else ret = true;
			break;
		
		case CUTENESS:
			if (k1.getCuteness() > k2.getCuteness()) ret = true;
			else ret = false;
			break;
		
		case HUNGER:
			if (k1.getHunger() > k2.getHunger()) ret = true;
			else ret = false;
			break;
		
		default:
			if (k1.getCuteness() > k2.getCuteness()) ret = true;
			else ret = false;
		}
		return ret;
	 } // end isBigger
	
	// Checks to see if key value is equal depending on curKey
	public boolean isEqual(Kitten k1, Kitten k2) {
		
		boolean ret;
		switch (curKey) {
		  
		case NAME:
			if (k1.getName().compareTo(k2.getName()) == 0) ret = true;
			else ret = false;
			break;
		
		case CUTENESS:
			if (k1.getCuteness() == k2.getCuteness()) ret = true;
			else ret = false;
			break;
		
		case HUNGER:
			if (k1.getHunger() == k2.getHunger()) ret = true;
			else ret = false;
			break;
		
		default:
			if (k1.getCuteness() == k2.getCuteness()) ret = true;
			else ret = false;
		}
		return ret;
	 } // end isEqual
} // end Heap

