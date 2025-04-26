import java.util.ArrayList;

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
    private int winCount;
    private int lossCount;

    private int distanceTravelled;
    private int currentFinishPostion;
    private int timeToFinish;

    private ArrayList <Horse_stats> stats = new ArrayList<>();

    static horseStatChangers[] breedEffects = {
        new horseStatChangers(0.1,0.2,0.1),
        new horseStatChangers(-0.1,0.4,-0.1)};

    static horseStatChangers[] horseShoeEffects = {
        new horseStatChangers(0,0.3,-0.3),
        new horseStatChangers(0,-0.3,0.3),
        new horseStatChangers(0,0,0)};

    static horseStatChangers[] saddleEffects = {
        new horseStatChangers(0.2,0,0),
        new horseStatChangers(0,0.2,0),
        new horseStatChangers (0,0,0)};
    static horseStatChangers[] bridleEffects = {
        new horseStatChangers(0.1,0.2,0.1),
        new horseStatChangers(0.2,0.1,0.1),
        new horseStatChangers(0.1,0.1,0.2)};

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


        this.distanceTravelled = 0;
        this.currentFinishPostion = 0;
    }
    
    public void raceFinished (int currentFinishPostion,int timeToFinish, track track)
    {
        this.timeToFinish = timeToFinish;
        if (currentFinishPostion == 1)
        {
            this.winCount ++;
        }
        else
        {
            this.lossCount ++;
        }
        track.setBestTime(timeToFinish);
        this.currentFinishPostion = currentFinishPostion;
        this.stats.add(new Horse_stats(track,currentFinishPostion,getAverageSpeed(),this.timeToFinish));
    }

    public int getCurrentFinishPostion ()
    {
        return this.currentFinishPostion;
    }

    public boolean isFinished ()
    {
        return this.currentFinishPostion != 0;
    }

    //Other methods of class Horse
    public void fall(int timeToFinish,track track)
    {
        this.timeToFinish = timeToFinish;
        this.lossCount ++;
        this.currentFinishPostion = -1;
        this.stats.add(new Horse_stats(track,currentFinishPostion,getAverageSpeed(),this.timeToFinish));
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
        this.currentFinishPostion = 0;
        this.distanceTravelled = 0;
    }

    public void moveForward()
    {
        this.distanceTravelled ++;
    }

    public double getAverageSpeed ()
    {
        return ((double) this.distanceTravelled) / ((double) this.timeToFinish / 1000);
    }

    public String getWinRatio ()
    {   if (this.lossCount == 0)
        {
            return "Undefeated";
        }
        else
        {
            return String.format("%.2f",(double) this.winCount / (double) this.lossCount);
        }
    }

    public int getTimeTaken ()
    {
        return this.timeToFinish;
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

    public ArrayList <Horse_stats> getStats ()
    {
        return this.stats;
    }
    
}


