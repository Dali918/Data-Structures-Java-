package StacksExercise;
import java.util.Scanner;

//Dalitso Banda
class StackDemo {
	   
	public static void main(String[] args) {
      
		Stack mahStack = new Stack(20);
		Scanner input = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("\nSelect a stack operation:\n\t[1] Add an item\n\t[2] Remove top item"
					+ "\n\t[3] View top item\n\t[4] Exit");
			int ans = (int)input.nextInt();
			
			switch (ans) {
			
				case 1:
					System.out.println("\nEnter integer to add: ");
					int in = (int)input.nextInt();
					mahStack.push(in);
					mahStack.display();
					break;
					
				case 2:
					int out = mahStack.pop();
					System.out.println("\nElement removed from stack: " + out);
					mahStack.display();
					break;
					
				case 3:
					int look = mahStack.peek();
					System.out.println("\nTop element is: " + look);
					mahStack.display();
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