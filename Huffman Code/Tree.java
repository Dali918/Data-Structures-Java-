import java.util.Stack;

public class Tree {
	
// FIELDS ------------------------------------------------------
	
   public Node root;              // first node of tree

// CONSTRUCTOR -------------------------------------------------
   public Tree(Node nd)           // constructor
      { root = nd; }              // root of tree from argument
   
// METHODS -----------------------------------------------------
   public int getFreq()           // returns node's word freq
      { return root.freq; }

   public void display()  		  // prints out whole Tree
      {
      Stack<Node> globalStack = new Stack<Node>();
      globalStack.push(root);
      
      // IF YOUR TREE ISN'T DISPLAYING CORRECTLY,
      // ADJUST THIS VARIABLE (INCREASE IT)
      int adjustMeToFixDisplay = 4;
      
      int nBlanks = adjustMeToFixDisplay*getDepth(root);
      boolean isRowEmpty = false;
     
      while(isRowEmpty==false) {
         Stack<Node> localStack = new Stack<Node>();
         isRowEmpty = true;

         for(int j=0; j<nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty()==false) {
            Node temp = (Node)globalStack.pop();
            if(temp != null) {
               temp.displayNode();
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);

               if(temp.leftChild != null ||
                                   temp.rightChild != null)
                  isRowEmpty = false;
            }
            
            else {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
            }
            
            for(int j=0; j<nBlanks*2-2; j++)
               System.out.print(' ');
          }  // end while globalStack not empty
         
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
            globalStack.push( localStack.pop() );
         
      }  // end while isRowEmpty is false
   }  // end displayTree()
   
   // returns depth of tree to help make tree display correctly
   public int getDepth(Node node) {
	   if (node == null)
		   return 0;
	   return 1 + getDepth(node.leftChild) + getDepth(node.rightChild);
   }

}  // end class Tree