// FOR USE IN PROJECT 3
// This is your link for the linked list. You shouldn't have to modify anything here.


public class KittenLink {

	// next is just a reference to the next link in the list
	public Kitten k;
	public int cuteLvl;
	public KittenLink next;
	
	// Constructor
	public KittenLink(Kitten k) {
		this.k = k;
		cuteLvl = this.k.getCuteness();
		next = null;
	} // end constructor
	
	
	// Displays Kitten name and Cuteness
	public void display() {
		System.out.println("\nName: " + k.getName() +  "\nCuteness: " + cuteLvl);
	}
	
}
