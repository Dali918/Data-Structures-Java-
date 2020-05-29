public class Node {
	
// FIELDS ------------------------------------------------------
   private Kitten k;
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

// CONSTRUCTOR -------------------------------------------------   
   Node(Kitten k) {
      this.k = k;
      leftChild = null;
      rightChild = null;
   }

// METHODS -----------------------------------------------------
   public void display() 
      { k.display(); }
   
   public Kitten getData() {
	   return k;
   }
  
}  // end class Node
