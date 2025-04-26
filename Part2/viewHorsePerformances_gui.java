
import javax.swing.*;


public class viewHorsePerformances_gui  extends  HorseSelection_gui{
private currentRaceInfo raceInfo;

public viewHorsePerformances_gui (currentRaceInfo raceInfo)
{
    
    super(raceInfo,"Horse Deletion");
    this.raceInfo = raceInfo;
}

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

    int counter = 0;
    double totalSpeeds = 0;

    for (Horse_stats stats : horse.getStats())
    {
        counter ++;
        totalSpeeds += stats.getRaceSpeed();
    }

    stringToDisplay = stringToDisplay + "Average speed: " + String.format("%.2f",(totalSpeeds/counter)) + "\n\n" ; 


    for (Horse_stats stats : horse.getStats())
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
    // Set the frame visible
    newFrame.setVisible(true);
}

}