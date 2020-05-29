// Dalitso Banda
// Add two more stack references that users can select from in the method switchStacks.
// You'll have to add to or modify the static fields for the overall class to do this.

//
// EXERCISE 5 INSTRUCTIONS
// -----------------------
// 
import java.util.Scanner;

class ReferencePractice {

	static Stack stack1 = new Stack(10);
	static Stack stack2 = stack1;
	static Stack curStack = stack1;
	static Stack anotherStack = stack1;
	static Stack lastStack = stack1;
	static String s1 = "stack1";
	static String s2 = "stack2";
	static String s3 = "Another Stack";
	static String s4 = "The last Stack";
	static String cur = s1;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		while (true) {

			System.out.println("\nCurrent reference being used to access stack: " + cur);
			System.out.println(
					"\nSelect a stack operation:\n\t[1] Switch references\n\t[2] Add an item\n\t[3] Remove top item"
							+ "\n\t[4] View top item\n\t[5] Exit");
			int ans = input.nextInt();

			switch (ans) {

			case 1:

				System.out.println("\nWhich stack would you like to access?");
				switchStacks(input.nextInt());
				break;

			case 2:
				System.out.println("\nEnter integer to add: ");
				int in = input.nextInt();
				curStack.push(in);
				curStack.display();
				break;

			case 3:
				int out = curStack.pop();
				System.out.println("\nElement removed from stack: " + out);
				curStack.display();
				break;

			case 4:
				int look = curStack.peek();
				System.out.println("\nTop element is: " + look);
				curStack.display();
				break;

			case 5:
				System.out.println("\nBye!");
				System.exit(0);
				break;

			default:
				System.out.println("\nNot a valid input. Please try again.");
				break;
			}

		}

	} // end main()

	public static void switchStacks(int choice) {

		System.out.print("\nNow accessing stack via reference: ");

		switch (choice) {

		case 1:
			cur = s1;
			curStack = stack1;
			break;

		case 2:
			cur = s2;
			curStack = stack2;
			break;
		case 3:
			cur = s3;
			curStack = anotherStack;
			break;
		case 4:
			cur = s4;
			curStack = lastStack;
			break;

		default:
			cur = s1;
			curStack = stack1;
		}

		System.out.println(cur);
		return;

	} // end switchStacks
} // end class
