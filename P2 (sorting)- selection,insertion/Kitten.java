package Control2;

public class Kitten {
	
	public enum States {
		  PLAYFUL;
		}

    // These fields are private, so only the object
    // itself can access them directly
    private int hunger;
    private boolean playful;
    private String name;
    
    // Default constructor
    public Kitten(){    
        this.hunger = 0;
        this.playful = true;
        this.name = "Coco";
    }
    
    // Constructor with parameters passed in
    // Automatic playfulness detection!
    // Automatic name normalization!
    public Kitten(String name, int hunger){
        this.hunger = hunger;
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
          if (this.hunger < 20)
            this.playful = true;
          else
            this.playful = false;
          break;

        default:
          break;
      }
    }
   
    
    // When kitten is hungry, it will eat and eventually become playful
    public void eat(int food){
        this.hunger -= food;
        changeState(States.PLAYFUL);
        System.out.println(this.name + " ate some food and got "
                + "a little less hungry!");
    }
    
    // Playing makes kitten hungry
    public void play(){
        
        if (!playful){
            System.out.println(this.name + " is too hungry to play!\n"
                    + this.name + " mewls piteously and scratches the floor.");
            return;
        }
        this.hunger += Math.round(Math.random()*10);
        changeState(States.PLAYFUL);
        System.out.println(this.name + " played with some toys and "
                + "got a little hungrier!");
    } 
    
    // Kitten will meow to signal how it feels
    public void meow(){
        if (this.playful){
            System.out.println(this.name + " meows playfully!");
        }
        else {
            System.out.println(this.name + " meows hungrily!");
        }
    }
    
    // Public access method to check kitten's internal state
    public boolean checkplay(){
        return this.playful;
    }
    
    // Public access method to check kitten's name
    public String getName(){
        return this.name;
    }
}
