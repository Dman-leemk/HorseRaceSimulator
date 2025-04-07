
import java.lang.classfile.instruction.ThrowInstruction;


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
    private double horseConfidence;   
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.horseConfidence = horseConfidence;
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


