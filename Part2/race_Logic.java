import java.util.concurrent.TimeUnit;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @version 1.0
 */
public class race_Logic
{
    private Main_gui gui;
    private Horse[] horses = null;
    private track currentTrack;
    final static double confidenceModifer = 0.02;
    
    /***
     * sets the race length
     * 
     * @param raceLength
     */
    public void setRaceTrack (track newTrack)
    {
        this.currentTrack = newTrack;
        this.horses = new Horse[newTrack.getlaneCount()];
    }

    /**
     * Constructor for objects of class Race
     * 
     * @param gui the gui to print the race too
     */

    public race_Logic(Main_gui gui)
    {
        this.gui = gui;
    } 
    
    /**
     * Changes the confidence of the horseClass
     * 
     * @param theHorse the horse that should have its confidence changed 
     * @param isIncrease tells if the confidence is to be increased or decreased
     * 
     * 
     */

    private void changeConfidence (Horse theHorse, boolean isIncrease)
    {
        double currentConfidence = theHorse.getConfidence();
        
        if (isIncrease) 
        {
            theHorse.setConfidence(currentConfidence + confidenceModifer);    
        }
        else
        {
            theHorse.setConfidence(currentConfidence - confidenceModifer);   
        }
         
    }


    /**
     * Adds a horse to the next empty lane returns false is no lanes are empty
     * 
     * @param theHorse the horse to be added to the race
     */
    public boolean addHorse(Horse theHorse)
    {
        for (int i = 0; i < this.horses.length; i++)
        {
            if (this.horses[i] == null)
            {
                this.horses[i] = theHorse;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        //declare a local variable to tell us when the race is finished
        boolean finished = false;
        
        // temporay testing
        addHorse(new Horse('#',"Horsey",0.8));
        addHorse(new Horse('%',"Pony",0.4));

        //reset all the lanes (all horses not fallen and back to 0). 
        for (Horse horse : this.horses)
        {
            if (horse != null)
            {
                System.out.println(currentTrack.getlength() + "");
                horse.goBackToStart();
            }
        }
                      
        while (!finished)
        {
            //move each horse
            for (Horse horse : this.horses)
            {
                if (horse != null)
                {
                    moveHorse(horse);
                }
            }
                        
            //print the race positions
            gui.setScreen(currentTrack.printRace(this.horses));
            
           //If all horses have fallen end the race
            boolean allHorsesFallen = true;

            for (Horse horse : this.horses)
            {
                if (horse != null)
                {
                    finished = allHorsesFallen && horse.hasFallen();
                }
            }
            
            //if any of the three horses has won the race is finished
            for (Horse horse : this.horses)
            {
                if (horse != null)
                {
                    if (raceWonBy(horse))
                    {
                        finished = true;
                    }
                }
            }
           
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(10);
            }catch(Exception e){}
        }
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
                changeConfidence(theHorse,false);
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() >= currentTrack.getlength())
        {
            changeConfidence(theHorse,true);
            System.out.println("The winner is " + theHorse.getName());
            return true;
        }
        else
        {
            return false;
        }
    }
}
