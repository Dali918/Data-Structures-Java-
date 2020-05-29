
public class Kitten {
    
    // These fields are private, so only the object
    // itself can access them directly
    private int hunger;
    private boolean playful;
    private final String name;
    
    // Default constructor
    public Kitten(){    
        this.hunger = 0;
        this.playful = true;
        this.name = "Coco";
    }
    
    // Constructor with parameters passed in
    public Kitten(int hunger, boolean playful, String name){
        this.hunger = hunger;
        this.playful = playful;
        this.name = name;
    }
    
    // When kitten is hungry, it will eat and eventually become playful
    public void eat(int food){
        this.hunger -= food;
        if (this.hunger < 15)
            this.playful = true;
        System.out.println("\n" + this.name + " ate some food and got "
                + "a little less hungry!");
    }
    
    // Playing makes kitten hungry
    public void play(){
        
        if (!playful){
            System.out.println("\n" + this.name + " is too hungry to play!\n"
                    + this.name + " mewls piteously and scratches the floor.");
            return;
        }
          
        this.hunger += Math.round(Math.random()*10);
        if (this.hunger > 15)
            this.playful = false;
        System.out.println("\n" + this.name + " played with a toy and "
                + "got a little hungrier!");
    }
    
    // Kitten will meow to signal how it feels
    public void meow(){
        if (this.playful){
            System.out.println(this.name + " meows playfully!");
        }
        else {
            System.out.println(this.name + " mewls hungrily!");
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
