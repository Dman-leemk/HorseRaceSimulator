import java.awt.*;
import javax.swing.*;

/**
 * Creates a gui to allow the user to chose between comparing and viewing a single horses stats
 * 
 */

public class StatSelection_gui 
{

    public StatSelection_gui (CurrentRaceInfo raceInfo)
    {
        // Ensures that there is a track
        if (raceInfo.getHorses() == null)
        {
            new ErrorBox("Create a track first");
            return;
        }

        JFrame frame = new JFrame("Horse panel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel fullPanel = new JPanel(new GridLayout(1,2));

        // Track records btn
        JButton trackRecordBtn = new JButton("View horse stats");
        trackRecordBtn.addActionListener(e -> new ViewHorsePerformances_gui(raceInfo));
        fullPanel.add(trackRecordBtn);

        // Compare btn
        JButton compareBtn = new JButton("Compare horses");
        //compareBtn.addActionListener(e -> new HorseRemoval_gui(raceInfo));
        fullPanel.add(compareBtn);


        frame.add(fullPanel);
        frame.setVisible(true);

    }
}