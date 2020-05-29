// Use this class to test your Queue implementation.
import java.util.Scanner;

class QueueDemo {
   
	public static void main(String[] args) {
      
		Queue mahQueue = new Queue(10);
		Scanner input = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("\nSelect a queue operation:\n\t[1] Add an item\n\t[2] Remove top item"
					+ "\n\t[3] View top item\n\t[4] Exit");
			int ans = input.nextInt();
			
			switch (ans) {
			
				case 1:
					System.out.println("\nEnter integer to add: ");
					int in = input.nextInt();
					mahQueue.push(in);
					mahQueue.display();
					System.out.println("You have this many elements: " + mahQueue.capacity());
					break;
					
				case 2:
					int out = mahQueue.pop();
					System.out.println("\nElement removed from queue: " + out);
					System.out.println("You have this many elements: "+ mahQueue.capacity());
					mahQueue.display();
					break;
					
				case 3:
					int look = mahQueue.peek();
					System.out.println("\nTop element is: " + look);
					mahQueue.display();
					break;
					
				case 4:
					System.out.println("\nBye!");
					System.exit(0);
					break;
					
				default:
					System.out.println("\nNot a valid input. Please try again.");
					break;
			}
			
			
		}
		
     }  // end main()
}  // end class 
