import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface, Damian  
 * @version 2
 */
public class Race_gui
{
    private int delay = 300;

    private JTextArea gui;

    final static double CONFIDENCEMODIFER = 0.02;
    private final CurrentRaceInfo raceInfo;
    private int currentPostion;
    private int numberOfTimerTicks;

    /**
     *  Handles running the race, called via a timer  
     *  Moves the horse and calculates if the race is finished
     * 
    */
    private final ActionListener racePrint = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        numberOfTimerTicks ++;

        // Print the race positions
        gui.setText(raceInfo.getTrack().printRace(raceInfo.getHorses()));

        // Move each horse that hasn't finished
        for (Horse horse : raceInfo.getHorses())
        {
            if (horse != null && !horse.isFinished())
            {
                moveHorse(horse,raceInfo.getTrack(),numberOfTimerTicks * delay);
            }
        }

        //Checks if any horses have completed the race, if they have assigns the the correct finish postion
        int numberOfCompletedHorses = 0;
        for (Horse horse : raceInfo.getHorses())
        {
            if (horse != null && !horse.isFinished())
            {
                if (horse.getDistanceTravelled() >= raceInfo.getTrack().getlength())
                {
                    horse.raceFinished(currentPostion,numberOfTimerTicks * delay, raceInfo.getTrack());
                    numberOfCompletedHorses ++;
                }
            }
        }
        currentPostion += numberOfCompletedHorses;         

        // Checks all horses have finished and ends the race if needed
        boolean isFinished = true;
        for (Horse horse : raceInfo.getHorses())
        {
            if (horse != null)
            {   
                if (!horse.isFinished())
                {
                    isFinished = false;
                }
            } 
        }

        // Ends the race
        if (isFinished)
        {
            printEndStats();
            ((Timer)evt.getSource()).stop();
        }
        
    }
    };

    // Constuctor
    public Race_gui (CurrentRaceInfo raceInfo)
    {
        currentPostion = 1;
        this.numberOfTimerTicks = 0;
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
            theHorse.setConfidence(currentConfidence + CONFIDENCEMODIFER);    
        }
        else
        {
            theHorse.setConfidence(currentConfidence - CONFIDENCEMODIFER);   
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
            new ErrorBox("Create a race first");
            return;
        }

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
    private void moveHorse(Horse theHorse,Track currentTracks,int timePassed)
    {
        //the probability that the horse will move forward depends on the confidence;
        if (Math.random() < (theHorse.getConfidence() + currentTracks.getConfidenceModifer()) )
        {
            if (Math.random() < (theHorse.getSpeed()-currentTracks.getSpeedModifer()) * theHorse.getSpeed())
            {
                theHorse.moveForward();
            }
            theHorse.moveForward();
        }
        
        //the probability that the horse will fall is very small (max is 0.1)
        //but will also will depends exponentially on confidence 
        //so if you double the confidence, the probability that it will fall is *2
        if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
        {
            if (Math.random() < theHorse.getEndurance()- currentTracks.getEnduranceModifer())
            {
                theHorse.fall(timePassed, raceInfo.getTrack());
                changeConfidence(theHorse,false);
            }
        }
    }
        
    /** 
     * Prints the final match stats
     *
     */

    private void printEndStats ()
    {
        String printedMessage = "";
 
        for (Horse horse : raceInfo.getHorses())
        {
            if (horse != null)
            {
                String racePostion;
                String confidenceChange;

                if (horse.getCurrentFinishPostion() == -1)
                {
                    racePostion = "Fell";
                    confidenceChange = "-" + CONFIDENCEMODIFER; 
                }
                else if (horse.getCurrentFinishPostion() == 1)
                {
                    racePostion = "Won";
                    confidenceChange = "+" + CONFIDENCEMODIFER;
                }
                else
                {
                    racePostion = "" + horse.getCurrentFinishPostion() ;
                    confidenceChange = "No change";
                }

                printedMessage = 
                     printedMessage  +
                    "   Name: " + horse.getName() + 
                    "   Postion: " + racePostion +
                    "   Win loss ratio: " + horse.getWinRatio() + 
                    "   Average speed: " + String.format("%.2f",horse.getAverageSpeed()) + 
                    "   Time: " + horse.getTimeTaken() +
                    "   Confidence: " + confidenceChange + '\n';
            }

        }

        gui.setText(printedMessage);
    }

}
