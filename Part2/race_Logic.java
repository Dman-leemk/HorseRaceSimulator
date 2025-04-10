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
    private int raceLength;
    private Horse[] lanes = null;
    final static double confidenceModifer = 0.02;
    
    /***
     * sets the race length
     * 
     * @param raceLength
     */
    private void setRaceLength (int raceLength)
    {
        this.raceLength = raceLength;
    }

    /***
     * sets the number of lanes
     * 
     * @param raceLength
     */
    private void setLanes (int laneCount)
    {
        this.lanes = new Horse[laneCount];
    }

    /**
     * Constructor for objects of class Race
     * 
     * @param gui the gui to print the race too
     */

    public race_Logic(Main_gui gui)
    {
        this.gui = gui;
        this.raceLength = 0;
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
    public boolean  addHorse(Horse theHorse)
    {
        for (int i = 0; i < lanes.length; i++)
        {
            if (lanes[i] == null)
            {
                lanes[i] = theHorse;
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
        

        //reset all the lanes (all horses not fallen and back to 0). 
        for (Horse horse : lanes)
        {
            if (horse != null)
            {
                horse.goBackToStart();
            }
        }
                      
        while (!finished)
        {
            //move each horse
            for (Horse horse : lanes)
            {
                if (horse != null)
                {
                    moveHorse(horse);
                }
            }
                        
            //print the race positions
            printRace();
            
           //If all horses have fallen end the race
            boolean allHorsesFallen = true;

            for (Horse horse : lanes)
            {
                if (horse != null)
                {
                    finished = allHorsesFallen && horse.hasFallen();
                }
            }
            
            //if any of the three horses has won the race is finished
            for (Horse horse : lanes)
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
                TimeUnit.MILLISECONDS.sleep(100);
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
        if (theHorse.getDistanceTravelled() >= raceLength)
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
    
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();
        

        for (Horse horse : lanes)
        {
            printLane(horse);
            System.out.println();
        }

        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        int spacesBefore;
        int spacesAfter;

        //calculate how many spaces are needed before
        //and after the horse
        if (theHorse != null)
        {
            spacesBefore = theHorse.getDistanceTravelled();
            spacesAfter = this.raceLength - theHorse.getDistanceTravelled();
        }
        else
        {
            spacesBefore =0;
            spacesAfter = this.raceLength;
        }
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if (theHorse != null)
        {
            if(theHorse.hasFallen())
            {
                System.out.print('\u2322');
            }
            else
            {
                System.out.print(theHorse.getSymbol());
            }
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');

        System.out.print("     " + theHorse.getName() + " (Current confidence " + theHorse.getConfidence() + ")");
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
