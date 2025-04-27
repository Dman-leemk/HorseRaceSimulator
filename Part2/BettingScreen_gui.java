
import java.awt.*;
import javax.swing.*;

public class BettingScreen_gui
{
    private JTextArea[] fieldsToCheck;

    public BettingScreen_gui(CurrentRaceInfo raceInfo) 
    {
        // Ensures that there is a track to remove or add horses too
        if (raceInfo.getHorses() == null)
        {
            new ErrorBox("Create a track first");
            return;
        }

        // checks if any horses are stored
        boolean isNoHorse = true;
        for (Horse horse : raceInfo.getHorses()) {
            if (horse != null)
            {
                isNoHorse = false;
            }    
        }

        if (isNoHorse)
        {
            new ErrorBox("No horses to bet on");
            return;
        }



        JFrame newFrame = new JFrame("Betting");
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(300, 500);

        JPanel fullPanel = new JPanel(new GridLayout(raceInfo.getHorses().length,1));

        int pointer = 0;
        this.fieldsToCheck = new JTextArea[raceInfo.getHorses().length];

        for (Horse horse : raceInfo.getHorses()) {
            if (horse != null)
            {
                JPanel row = new JPanel(new FlowLayout());

                row.add(new JLabel("Name: " + horse.getName() + " "));
                row.add(new JLabel("Odds: " + calculateOdds(horse,raceInfo.getTrack()) + " "));
                row.add(new JLabel("Total bet amount: "));
                row.add(new JLabel("Bet amount: "));

                JTextArea textArea = new JTextArea("Enter Amount");
                this.fieldsToCheck[pointer] = textArea;
                row.add(textArea);

                fullPanel.add(row);

            }    
        }
        newFrame.add(fullPanel);
        newFrame.setVisible(true);
    }

    

    private double calculateOdds (Horse horse,Track track)
    {
        if (horse.getWinRatioStat()  == 0 )
        {
            return 1;
        }

        return 1 + 0.1/ 
            (
                horse.getWinRatioStat() * 
                (horse.getSpeed() - track.getSpeedModifer()) * 
                (horse.getConfidence() - track.getConfidenceModifer()) * 
                (horse.getEndurance()  - track.getEnduranceModifer())
            );
    }    
}