package Control2;

//Use this main class to test your implementation of KittenControl2.

//Feel free to add methods or modify what I've provided here.
//This program draws random names from names.txt, which has a little over 24,000 names,
//creates Kitten objects with those names and random hunger levels,
//and adds those Kitten objects to a roster.

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class KittenControlTestRig2 {

	public static void main(String[] args) {

		// Get Kitten names ready for user
		File infile = new File("/Users/Dalitso Banda/Data Structures 254/P2 Kitten Control 2/names.txt");
		ArrayList<String> names = new ArrayList<String>();

		try {
			Scanner input = new Scanner(infile);
			while (input.hasNextLine())
				names.add(input.nextLine());
			input.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		// Setup of main variables
		KittenControl2 roster = new KittenControl2();
		Scanner input = new Scanner(System.in);
		Random ran = new Random();
		int adds, max = names.size();

		// Ask user how many kittens they want
		System.out.printf("\nHow many random kittens would you like to add " + "to the roster? (Enter 1 to %d): \n",
				max);
		adds = input.nextInt();

		if (adds > max)
			adds = max;
		else if (adds < 1)
			adds = 1;
		input.close();

		// Creating and adding kittens with random hunger levels
		for (int i = 0; i < adds; i++) {
			int pick = ran.nextInt(max--);
			String n = names.get(pick);
			names.remove(pick);
			Kitten k = new Kitten(n, ran.nextInt(50));
			if (roster.insert(k, "selectionsort") == -1) {
				System.out.println("\nSomething went wrong with your insert method!");
				System.exit(0);
			}
		}
//		Test algorithms =: Find(Binary search), Insertion Sort and Selection Sort

		testFind(roster);
		testInsertionSort(ran, names);
		testSelectionSort(ran, names);

		// ADD CODE HERE TO TEST THAT YOUR SORTING METHODS WORK!
		// Use public function below to print out your entire roster.
		// Feel free to modify it.

	}

	// test find by going through the roster and mathcing the element at every index
	// with the element
	// index that find returns when called.
	public static void testFind(KittenControl2 roster) {
		for (int i = 0; i < roster.checkPets(); ++i) {
			String kittenName = roster.getPet(i).getName();
			int testIndex = roster.find(kittenName, "selectionsort");
			if (i == testIndex) {
				System.out.println("\nit worked ");
				System.out.println(testIndex + "==" + i);
			} else {
				System.out.println("\nfind method not working");
			}
		}
	}

	// test selection sort by making a new unsorted roster with random kittens.
	public static void testSelectionSort(Random ran, ArrayList<String> names) {
		// The given sort algorithm is used during insert helped by another defined
		// method:populate.
		KittenControl2 testRoster = populate(ran, names, "selectionsort");
		// Go through the test roster starting from the beginning to the number of pets
		// minus one
		// and compare the the next adjacent element to see if it is bigger
		// lexicographically thus if
		// bigger, the sort algorithm worked.
		for (int i = 0; i < testRoster.checkPets() - 1; ++i) {
			if (testRoster.getPet(i + 1).getName().compareTo(testRoster.getPet(i).getName()) > 0) {
				System.out.println("Your selection sort worked");
			} else {
				System.out.println("did not work");
			}
		}
		// Print out the testRoster array.
		System.out.println("\n Your roster looks like this");
		for (int j = 0; j < testRoster.checkPets(); ++j) {
			System.out.println(testRoster.getPet(j).getName());
		}
	}

	// test insertion sort by making a new unsorted roster with random kittens.
	public static void testInsertionSort(Random ran, ArrayList<String> names) {
		// The given sort algorithm is used during insert helped by another defined
		// method:populate.
		KittenControl2 testRoster = populate(ran, names, "insertionsort");
		// Go through the test roster starting from the beginning to the number of pets
		// minus one
		// and compare the the next adjacent element to see if it is bigger
		// lexicographically thus if
		// bigger, the given sort algorithm worked.
		for (int i = 0; i < testRoster.checkPets() - 1; ++i) {
			if (testRoster.getPet(i + 1).getName().compareTo(testRoster.getPet(i).getName()) > 0) {
				System.out.println("Your insertion sort worked");
			} else {
				System.out.println("did not work");
			}
		}
		System.out.println("\n Your roster looks like this");
		// Print out the testRoster array.
		for (int j = 0; j < testRoster.checkPets(); ++j) {
			System.out.println(testRoster.getPet(j).getName());
		}
	}

	public static void printRoster(KittenControl2 roster) {
		System.out.println("\nThese are the kittens in your roster: ");
		for (int i = 0; i < roster.checkPets(); i++)
			System.out.println(roster.getPet(i).getName());
	}

	// Populate Method to randomly populate the new rosters in the the test methods.
	// Returns an unsorted roster
	public static KittenControl2 populate(Random ran, ArrayList<String> names, String method) {
		KittenControl2 testRoster = new KittenControl2();
		int max = names.size();
		for (int i = 0; i < 20; ++i) {
			String chosen = names.get(ran.nextInt(max--));
			Kitten k = new Kitten(chosen, 0);
			testRoster.insert(k, method);
			names.remove(names.indexOf(chosen));
		}

		return testRoster;
	}

}
