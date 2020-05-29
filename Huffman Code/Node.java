public class Node {
	
// FIELDS ------------------------------------------------------
   public char ch;                // letter
   public int freq;               // instances of this letter
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

// CONSTRUCTOR -------------------------------------------------   
   Node(char c, int f) {
      ch = c;
      freq = f;
   }

// METHODS -----------------------------------------------------
   public void displayNode() 
      { System.out.print(ch + "" + freq); }
   
}  // end class Node