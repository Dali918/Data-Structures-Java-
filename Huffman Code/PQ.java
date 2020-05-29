
public class PQ {

// FIELDS ------------------------------------------------------
   private int maxSize;
   private Tree[] queArray;
   private int nItems;

// CONSTRUCTOR -------------------------------------------------  
   public PQ(int s){
      maxSize = s;
      queArray = new Tree[maxSize];
      nItems = 0;
    }

// METHODS -----------------------------------------------------
   public void insert(Tree item){
     
	  int j;

	  // insertion of first item
      if(nItems==0)                         
         queArray[nItems++] = item;         
      
      else {
         for(j=nItems-1; j>=0; j--) {
            if( item.root.freq > queArray[j].root.freq )
               queArray[j+1] = queArray[j]; 
            else                           
               break;                     
         }  // end for
         queArray[j+1] = item;              // insert it
         nItems++;
       }  // end else (nItems > 0)
    }  // end insert()
//-------------------------------------------------------------
   public Tree remove()             // remove minimum item
      { return queArray[--nItems]; }
//-------------------------------------------------------------
   public Tree peekMin()            // peek at minimum item
      { return queArray[nItems-1]; }
//-------------------------------------------------------------
   public boolean isEmpty()         // true if queue is empty
      { return (nItems==0); }
//-------------------------------------------------------------
   public boolean isFull()          // true if queue is full
      { return (nItems == maxSize); }
//-------------------------------------------------------------
   public int getSize()
      { return nItems; }

}  // end class PQ