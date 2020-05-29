
/*
 * Instructions
 * ----------------------
 * 
 * Use this class to test your implementations of the KittenLL, KittenPQ, and KittenStack classes.
 * I've provided testing methods that you can run by uncommenting each appropriate command in the testing
 * section.
 * 
 *
 */

import java.util.Random;
import java.util.Scanner;

public class KittenAdopter {
	
	static KittenPQ roster;
	
	public static void main(String[] args) {
		
	
		/*
		 * -------------------------------------------
		 * INITIAL TESTING METHODS
		 * -------------------------------------------
		 * 
		 * Implement your classes in this order: KittenLL, KittenStack, KittenPQ.
		 * Be sure to make use of the methods of classes you've already implented rather
		 * than reinventing the wheel!
		 * 
		 * You can test the basic functionality of your classes by using the initial testing
		 * methods provided below.
		 * 
		 */
		
		// Uncomment the following to test your KittenLL class
		// Add or modify the testing method if you want!
//		 testKittenLL();
		
		// Uncomment the following to test your KittenStack class
		// Add or modify the testing method if you want!
//		 testKittenStackOrPQ(new KittenStack());
		
		// Uncomment the following to test your KittenLL class
		// Add or modify the testing method if you want!
//		 testKittenStackOrPQ(new KittenPQ());
		
		/*
		 * -----------------------------------
		 * INTERACTIVE TESTING MODE
		 * -----------------------------------
		 * 
		 * Uncomment the following line to start an interactive mode that allows 
		 * you to dynamically add, remove, and feed Kittens.
		 * 
		 */
		
	//	interactiveTest();
		
	} // end main
	
// ---------------------------------------------------------------------------------------------	
// HELPER AND TESTING METHODS
// ---------------------------------------------------------------------------------------------	
	
	// Tests your KittenLL class, throws appropriate error messages
	public static int testKittenLL() {
		
		KittenLL test = new KittenLL();
	
		boolean empty = test.first == null ? true : false;
		
		if (test.isEmpty() != empty) {
			System.out.println("\nYour isEmpty method isn't working correctly.");
			return -1;
		}
		
		String[] arr = new String[10];
		
		for (int i=9; i>=0; i--) {
			Kitten k = generate();
			test.insertFirst(k);
			arr[i] = k.getName();
		}
		
		KittenLink cur = test.first;
		for (String name : arr) {
			if (name != cur.k.getName()) {
				System.out.println("\nYour insertFirst method isn't working correctly.");
				return -1;
			}
			cur = cur.next;
		}
		
		for (String name: arr) {
			Kitten k = test.deleteFirst();
			if (name != k.getName()) {
				System.out.println("\nYour deleteFirst method isn't working correctly.");
				return -1;
			}
		}
		
		System.out.println("\nThis method tests clean! But there might be errors this method does not test...");
		return 0;
	} // end testKittenLL
	
	
	// Tests your KittenStack class, throws appropriate error messages
	public static int testKittenStackOrPQ(KittenList test) {
		
		Kitten a = generate();
		
		test.push(a);
		Kitten b = test.peek();
		if (a != b) {
			System.out.println("\nYour push or peek method isn't working correctly.");
			return -1;
		}
		
		test.pop();
		int numKits = 10;
		
		
		// This only executes if a KittenStack is passed in
		if (test.getClass().toString() == "KittenStack") {
			
			String[] arr = new String[numKits];
			
			for (int i=numKits-1; i>=0; i--) {
				Kitten k = generate();
				test.push(k);
				arr[i] = k.getName();
			}
			
			for (String name: arr) {
				Kitten k = test.pop();
				if (name != k.getName()) {
					System.out.println("\nYour pop method isn't working correctly.");
					return -1;
				}
			}
		}
		
		// This only executes if a KittenPQ is passed in.
		else {
			for (int i=0; i<numKits; i++)
				test.push(generate());
		}
	
		test.walk(); // Tests match between cuteness and cuteLvl, also order for KittenPQ
		
		System.out.println("\nThis method tests clean! But there might be errors this method does not test...");
		return 0;
	} // end testKittenStackOrPQ
	
	
	// Pops each Kitten off the PQ, feeds it, pushes it into a stack, then pushes
	// the whole stack back into the PQ, which should automatically reorder the Kittens
	// by decreasing cuteness
	public static void feed() {
		
		KittenStack fedKitties = new KittenStack();
		
		Random ran = new Random();
		
		// Get each Kitten, feed it, put it in stack
		while (!roster.isEmpty()) {
			Kitten k = roster.pop();
			k.eat(ran.nextInt(5));
			fedKitties.push(k);
		}
		
		while (!fedKitties.isEmpty())
			roster.push(fedKitties.pop());
		
	} // end feed
	
	// Call this class to generate a Kitten with a random string of characters for a name
	public static Kitten generate() {
		
		int left = 97;
		int right = 122;
		int length = 10;  // change this to change length of random Kitten name
		
		Random ran = new Random();
		StringBuilder buffer = new StringBuilder(length);
		for (int i=0; i<length; i++) {
			int ranInt = left + (int)(ran.nextFloat() * (right-left+1));
			buffer.append((char)ranInt);
		}
		
		String name = buffer.toString();
		return new Kitten(name, ran.nextInt(20), ran.nextInt(20));
	} // end generate
	
// ---------------------------------------------------------------------------------------------	
// INTERACTIVE TESTING MODE
// ---------------------------------------------------------------------------------------------	
	
	public static int interactiveTest() {
		
		roster = new KittenPQ();
		
		// Adding 10 random Kittens to set up
		for (int i=0; i<10; i++)
			roster.push(generate());
		
		Scanner input = new Scanner(System.in);

		while (true) {
			
			System.out.println("\nSelect a PQ operation:\n\t[1] Add a kitten\n\t[2] Remove the highest priority kitten"
					+ "\n\t[3] View top kitten\n\t[4] Feed all kittens\n\t[5] Exit");
			int ans = input.nextInt();
			
			Kitten k;
			
			switch (ans) {
			
				case 1:
					k = generate();
					roster.push(k);
					System.out.println("\nKitten " + k.getName() + " with cuteness " + k.getCuteness() + " has been added.");
					roster.display();
					break;
					
				case 2:
					k = roster.pop();
					if (k != null)
						System.out.println("\nKitten " + k.getName() + " with cuteness " + k.getCuteness() + " has been removed.");
					roster.display();
					break;
					
				case 3:
					k = roster.peek();
					if (k != null)
						System.out.println("\nTop kitten is: " + k.getName() + " with cuteness " + k.getCuteness() + ".");
					break;
					
				case 4:
					System.out.println("\nThe roster before kitties are fed: ");
					roster.display();
					feed();
					System.out.println("\nThe roster after kitties have been fed: ");
					roster.display();
					break;	
					
				case 5:
					System.out.println("\nBye!");
					System.exit(0);
					break;
					
				default:
					System.out.println("\nNot a valid input. Please try again.");
					break;
			} // end switch
		} // end while
	} // end interactiveTest

} // end class

