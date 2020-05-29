// KITTEN 3.0 FOR USE IN PROJECT 3
// NO CODING REQUIRED, but you can uncomment the System output lines
// in the eat() and play() methods for error checking if you like

public class Kitten {
	
	public enum States {
		  PLAYFUL
		}

    // These fields are private, so only the object
    // itself can access them directly
    private int hunger;
    private int cuteness;
    private boolean playful;
    private String name;
    
    // Default constructor
    public Kitten(){    
        this.cuteness = 20;
        this.playful = true;
        this.name = "Coco";
    }
    
    // Constructor with parameters passed in
    public Kitten(String name, int hunger, int cuteness){
        this.hunger = hunger;
        this.cuteness = cuteness;
        changeState(States.PLAYFUL);
        if(name.isEmpty())
          this.name = "Nobody";
        else
          this.name = name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
    }

    // Checks internal thresholds to see if states need adjustment
    public void changeState(States state){
      switch(state){
        case PLAYFUL:
          if (this.hunger < 20) {
            this.playful = true;
            this.cuteness += Math.random()*10;
          }
          else {
            this.playful = false;
            this.cuteness -= Math.random()*10;
          }
          break;

        default:
          break;
      }
    }
    
    // When kitten is hungry, it will eat and eventually become playful
    public void eat(int food){
        this.hunger -= food;
        changeState(States.PLAYFUL);
        // System.out.println("\n" + this.name + " ate some food and got a little less hungry!");
    }
    
    // Playing makes kitten hungry
    public void play(){
        if (!this.playful){
//            System.out.println("\n" + this.name + " is too hungry to play!\n"
//                    + this.name + " mewls piteously and scratches the floor.");
            return;
        }
          
        this.hunger += Math.round(Math.random()*10);
        changeState(States.PLAYFUL);
//        System.out.println("\n" + this.name + " played with some toys and "
//                + "got a little hungrier!");
    } 
    
    // Kitten will meow to signal how it feels
    public void meow(){
        if (this.playful){
            System.out.println("\n" + this.name + " meows playfully!");
        }
        else {
            System.out.println("\n" + this.name + " mewls hungrily!");
        }
    }
    
    // Public access method to check kitten's internal state
    public boolean checkPlay(){
        return this.playful;
    }
    
    // Public access method to check kitten's name
    public String getName(){
        return this.name;
    }
    
    // Public access method to check kitten's hunger
    public int getHunger(){
    	return this.hunger;
    }
    
    // Public access method to check kitten's cuteness
    public int getCuteness() {
    	return this.cuteness;
    }
}
