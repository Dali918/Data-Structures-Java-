/*
STUDENT NAME GOES HERE
*/

/*
This exercise is meant to warm you up to coding in Java again.
Complete the methods indicated by the comments below.

KittenSimulator lets a user interact with a virtual kitten!
Users can feed the kitten when it gets hungry and give it toys 
to play with when it's not hungry.
*/

import java.util.Scanner;

public class KittenSimulator {

    public static void main(String[] args) {
              
         /*
         Create and use a Scanner object to prompt the user
         to enter the following values. Name is the kitten's name,
         hunger is its initial hunger level (pick an int up to 50),
         and playfulness is whether or not it's currently feeling
         playful.
         */

         String name;
         int hunger;
         boolean playfulness;

         // PROMPT USER FOR NAME, READ IN NAME

         // PROMPT USER FOR HUNGER LEVEL, READ IN HUNGER

         // PROMPT USER FOR PLAYFULNESS, READ IN PLAYFULNESS

         /*
         Once you have the kitten's initial parameters, use the Kitten
         class to create a Kitten object with them. Then pass your Scanner
         object and the Kitten object to the Simulate method defined below.
         */
         
    }
    
    public static void Simulate(Scanner input, Kitten kitten){
        
        System.out.println("Welcome to Kitten Simulator!\n"
                + "Your kitten " + kitten.getName()
                + " is ready for a new day of fun and treats with you.\n");
        
        while (true){
            
            String choice;
            kitten.meow();
            System.out.println("What will you do?");
            System.out.println("(Enter T to give toy, F to give food): ");
            choice = input.next().toUpperCase();
            
            if (choice.charAt(0) == 'T')
                kitten.play();
            else if (choice.charAt(0) == 'F')
                kitten.eat((int)Math.round(Math.random()*10));
            else
                System.out.println("\nThat's not a valid input. Try again!");
        }
    }
}
