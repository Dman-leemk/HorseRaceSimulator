
import javax.swing.*;

/**
 * Handles printing all the information tied to a horse to gui
 * 
 */

public class ViewHorsePerformances_gui  extends  HorseSelection_gui
{
    private final CurrentRaceInfo raceInfo;

    // creates a selection gui to get the horse to print the information of
    public ViewHorsePerformances_gui (CurrentRaceInfo raceInfo)
    {
        
        super(raceInfo,"Horse Details");
        this.raceInfo = raceInfo;
    }

    /**
     * Handles printing all the information relating to the horse
     * 
     */
    @Override
    void onClickEffect (Horse horse,JFrame frame)
    {
        frame.dispose();

        JFrame newFrame = new JFrame(horse.getName());
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(300, 500);
        JTextArea gui = new JTextArea();

        newFrame.add(gui);
        String stringToDisplay = "";

        stringToDisplay = stringToDisplay + "Win ratio: " + horse.getWinRatio() + "\n";

        // Find the average speed
        int counter = 0;
        double totalSpeeds = 0;
        for (HorseStats stats : horse.getStats())
        {
            counter ++;
            totalSpeeds += stats.getRaceSpeed();
        }
        stringToDisplay = stringToDisplay + "Average speed: " + String.format("%.2f",(totalSpeeds/counter)) + "\n\n" ; 

        // Prints all the races
        for (HorseStats stats : horse.getStats())
        {
            String racePostion;

            if (horse.getCurrentFinishPostion() == -1)
            {
                racePostion = "Fell";
            }
            else if (horse.getCurrentFinishPostion() == 1)
            {
                racePostion = "Won";
            }
            else
            {
                racePostion = "" + horse.getCurrentFinishPostion() ;
            }

            stringToDisplay = 
            stringToDisplay + "\n"+ 
            "Race Postion: " + racePostion + "\n"+ 
            "Race Time: " + stats.getRaceTime() + "\n"+ 
            "Race Speed: " + String.format("%.2f",stats.getRaceSpeed()) + "\n";
        }
        gui.setText(stringToDisplay);

        newFrame.setVisible(true);
    }

}