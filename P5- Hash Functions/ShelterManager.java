import java.util.Random;
import java.util.Scanner;

public class ShelterManager {
	
	public enum PriorityKey {NAME, HUNGER, CUTENESS}

	public static void main(String[] args) {

		int DBsize = 1001;
		int initial = 1000;
		PriorityKey curKey = PriorityKey.CUTENESS;
		
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to ShelterManager!");
		System.out.println("Preparing database...");
		
		// generate and store a bunch of kittens in table and heap
		DB roster = new DB(DBsize, DB.Probes.QUADRATIC);
	for (int i = 0; i < initial; i++)
		roster.add(generate());
	
//		Kitten nuevo= new Kitten("Dalitso",30,40);
//		Kitten nuevo2= new Kitten("Dalitso", 30,40);
//		Kitten nuevo3= new Kitten("Dalitso", 30, 40);	
//		roster.add(nuevo);
//		roster.add(nuevo2);
//		roster.add(nuevo3);
	
		
		Heap priorityList = new Heap(roster, curKey);
		
		while (true) {
			
			System.out.println("\nChoose an option from the following: ");
			System.out.println("\t[1] Add new kitten to DB");
			System.out.println("\t[2] Set priority key");
			System.out.println("\t[3] Generate priority list");
			System.out.println("\t[4] Report adoption of first-priority kitten");
			System.out.println("\t[5] Display DB");
			System.out.println(">>  ");
			
			int choice = input.nextInt();
			
			switch(choice) {
			
			case 1:
				System.out.println("\nAdding a new kitten to DB...");
				Kitten newKit = generate();
				int res = roster.add(newKit);
				
				if (res != -1) {
					System.out.println("Kitten " + newKit.getName() + " added to index " + res);
					priorityList.insert(newKit);
				}
				else
					System.out.println("Could not find an open index for " + newKit.getName());
				break;
				
			case 2:
				System.out.println("\nCurrent priority key is " + curKey);
				System.out.println("To change this key, enter one of the following:"
						+ "\n\t1 for Name\n\t2 for Hunger\n\t3 for Cuteness");
				System.out.println(">>  ");
				int key = input.nextInt();
				
				if (key == 1)
					curKey = PriorityKey.NAME;
				else if (key == 2)
					curKey = PriorityKey.HUNGER;
				else if (key == 3)
					curKey = PriorityKey.CUTENESS;
				else
					System.out.println("\nThat was not a valid choice!");
				break;
				
			case 3:
				System.out.println("\nCurrent priority key is " + curKey);
				System.out.println("Generating a new priority list with this key...");
				priorityList = new Heap(roster, curKey);
				priorityList.heapify();
				Kitten topKit = priorityList.peek().getData();
				
				System.out.println("New priority list ready!\nHighest-priority kitten is "
						+ topKit.getName() + " with cuteness " + topKit.getCuteness()
						+ " and hunger " + topKit.getHunger());
				break;
				
			case 4:
				if(priorityList.currentSize == 0) {
					System.out.println("\nThe priority list is empty! Add more kittens first.");
					break;
				}
				System.out.println("\nRemoving highest-priority kitten from database...");
				Kitten delKit = priorityList.remove().getData();
				Kitten del = roster.delete(delKit.getName());
				
				if (del != null)
					System.out.println("Kitten " + del.getName() + " removed from the database!");
				else
					System.out.println("Could not find kitten " + delKit.getName());
				break;
				
			case 5:
				System.out.println("\nDisplaying entire database...");
				roster.display();
				break;
				
			default:
				System.out.println("\nThat was not a valid choice!");
				break;
			} // end switch
		} // end while
		
	} // end main
	
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

}

