class horseStatChangers
{
    final double confidenceModifer;
    final double speedModifer;
    final double enduranceModifer;

    public horseStatChangers(double confidenceModifer, double speedModifer,double enduranceModifer) {
        this.confidenceModifer = confidenceModifer;
        this.speedModifer = speedModifer;
        this.enduranceModifer = enduranceModifer;
    }
}



/**
 * Stores the necessary information about the horse and provides the abity to edit the features of the horses
 * 
 * @author (Damian Lemke) 
 * @version (1.0)
 */
public class Horse
{
    //Fields of class Horse
    private String horseName;
    private char horseSymbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private String colour;
    private int breed;
    private int horseShoe;
    private int saddle;
    private int bridle;   

    static final double baseConfidence = 0.5;
    static final double baseSpeed = 0.5;
    static final double baseEndurance = 0.5;

    private double horseConfidence;
    private final double horseSpeed;
    private final double horseEndurance;


    static horseStatChangers[] breedEffects = {new horseStatChangers(0.1,0.2,0.1),new horseStatChangers(-0.1,0.4,-0.1)};
    static horseStatChangers[] horseShoeEffects = {new horseStatChangers(0,0.3,-0.3),new horseStatChangers(0,-0.3,0.3),new horseStatChangers(0,0,0)};
    static horseStatChangers[] saddleEffects = {new horseStatChangers(0.2,0,0),new horseStatChangers(0,0.2,0),new horseStatChangers (0,0,0)};
    static horseStatChangers[] bridleEffects = {new horseStatChangers(0.1,0.2,0.1),new horseStatChangers(0.2,0.1,0.1),new horseStatChangers(0.1,0.1,0.2)};

    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName,
     String colour, int breed, int horseShoe, 
     int saddle, int bridle)
    {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.colour = colour;
        this.breed = breed;
        this.horseShoe = horseShoe;
        this.saddle = saddle;
        this.bridle = bridle;

        this.horseConfidence = 
        baseConfidence + 
        breedEffects[breed].confidenceModifer + 
        horseShoeEffects[horseShoe].confidenceModifer + 
        saddleEffects[saddle].confidenceModifer + 
        bridleEffects[bridle].confidenceModifer;

        this.horseSpeed = 
        baseSpeed + 
        breedEffects[breed].speedModifer + 
        horseShoeEffects[horseShoe].speedModifer + 
        saddleEffects[saddle].speedModifer +
        bridleEffects[bridle].speedModifer;

        this.horseEndurance = 
        baseEndurance + 
        breedEffects[breed].enduranceModifer + 
        horseShoeEffects[horseShoe].enduranceModifer + 
        saddleEffects[saddle].enduranceModifer +
        bridleEffects[bridle].enduranceModifer;


        this.hasFallen = false;
        this.distanceTravelled = 0;
    }
    
    
    
    //Other methods of class Horse
    public void fall()
    {
        this.hasFallen = true;
    }
    
    public double getConfidence()
    {
       return this.horseConfidence; 
    }

    public double getSpeed()
    {
       return this.horseSpeed; 
    }

    public double getEndurance()
    {
       return this.horseEndurance; 
    }
    
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }
    
    public String getName()
    {
        return this.horseName;
    }
    
    public char getSymbol()
    {
        return this.horseSymbol;
    }
    
    public void goBackToStart()
    {
        this.hasFallen = false;
        this.distanceTravelled = 0;
    }
    
    public boolean hasFallen()
    {
        return this.hasFallen;
    }

    public void moveForward()
    {
        this.distanceTravelled ++;
    }

    public void setConfidence(double newConfidence)
    {
        if ((0 < newConfidence) && (newConfidence < 1))
        {
            this.horseConfidence = newConfidence;
        }
        else
        {
            System.out.println("Confidence out of bounds");
        }
    }
    
    public void setSymbol(char newSymbol)
    {
        this.horseSymbol = newSymbol;
    }
    
}


