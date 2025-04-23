import java.awt.event.*;
import javax.swing.*;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @version 2
 */
public class race_GUI
{
    private int delay = 300;

    private JTextArea gui;
    private boolean isRaceFinished;

    private ActionListener racePrint = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        
        String endString = "";

         //print the race positions
        gui.setText(raceInfo.getTrack().printRace(raceInfo.getHorses()));

        //move each horse
        for (Horse horse : raceInfo.getHorses())
        {
            if (horse != null)
            {
                moveHorse(horse);
            }
        }
        
        //If all horses have fallen end the race
        boolean allHorsesFallen = true;

        for (Horse horse : raceInfo.getHorses())
        {
            if (horse != null)
            {
                if (horse.hasFallen())
                {
                    endString = endString + "\n" + horse.getName() + " fell!";
                }
                
                isRaceFinished = allHorsesFallen & horse.hasFallen();
            }
        }
        
        //if any of the three horses has won the race is finished
        for (Horse horse : raceInfo.getHorses())
        {
            if (horse != null)
            {
                if (raceWonBy(horse))
                {
                    endString = endString + "\n" + horse.getName() + " has won!"; 
                    isRaceFinished = true;
                }
            }
        }

        if (isRaceFinished)
        {
            gui.setText(endString);
            
            ((Timer)evt.getSource()).stop();
        }
        
    }
    };



    final static double confidenceModifer = 0.02;
    private final currentRaceInfo raceInfo;
    
    public race_GUI (currentRaceInfo raceInfo)
    {
        this.raceInfo = raceInfo;
        startRace();
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
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        if (raceInfo.getTrack() == null)
        {
            new errorBox("Create a race first");
            return;
        }

        //declare a local variable to tell us when the race is finished
        this.isRaceFinished = false;

        //reset all the lanes (all horses not fallen and back to 0). 
        for (Horse horse : raceInfo.getHorses())
        {
            if (horse != null)
            {
                horse.goBackToStart();
            }
        }
        JFrame frame = new JFrame("Race");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 350);
        this.gui= new JTextArea();
        // Add the panel to the frame
        frame.add(this.gui);
        // Set the frame visible
        frame.setVisible(true);

        new Timer(delay, racePrint).start();
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
        if (theHorse.getDistanceTravelled() >= raceInfo.getTrack().getlength())
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
