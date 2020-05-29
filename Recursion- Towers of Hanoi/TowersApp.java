/*
 * Dalitso Banda
 * 
 * Instructions
 * ------------
 * 
 * Implement the recursive calls for this Towers of Hanoi solver!
 * 
 * By "solve," I mean "print out a list of the moves required to solve."
 * There isn't any object keeping track of where disks are being kept, etc.
 * We're doing that abstractly by just swapping around which poles
 * are identified as the "from," "to," and "third" poles.
 * 
 * So "moving" a disk just means printing out a line declaring the disk moves.
 * See the base case for an example of this.
 * 
 */

public class TowersApp {
	static int nMoves = 0;

	public static void main(String[] args) {
		doTowers(3, 'A', 'B', 'C');
		System.out.println("Done!");
		System.out.println("Number of Moves: " +nMoves);
	}

	// -----------------------------------------------------------
	public static void doTowers(int nDisks, char from, char third, char to) {
		// Base Case
		if (nDisks == 1) {
			System.out.println("Move 1 disk on " + from + " to " + to);
		}

		else {

			// TODO: move subtree, flipping "third" and "to" poles.
			// This means that FROM THE PERSPECTIVE OF THIS CALL'S BASE DISK,
			// the subtree will be moved from the "from" pole to the "third" pole.
			doTowers(nDisks - 1, from, to, third);
			System.out.println("Move 1 disk on " + from + " to " + to);
			// TODO: move base disk for this call "to" the appropriate pole
			doTowers(nDisks - 1, third, from, to);
			// TODO: move subtree, rotating all poles one position to the right
			// This means that FROM THE PERSPECTIVE OF THIS CALL'S BASE DISK,
			// the subtree will be moved from the "third" pole to the "to" pole
			
		}
		nMoves++;
	}
//-------------------------------------------------------------
} // end class TowersApp
////////////////////////////////////////////////////////////////
