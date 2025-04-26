import java.awt.*;
import javax.swing.*;


public class StatSelection_gui {

public StatSelection_gui (currentRaceInfo raceInfo)
{

JFrame frame = new JFrame("Horse panel");
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setSize(500, 300);

JPanel fullPanel = new JPanel(new GridLayout(1,3));

// Track records btn
JButton trackRecordBtn = new JButton("View horse stats");
trackRecordBtn.addActionListener(e -> new viewHorsePerformances_gui(raceInfo));
fullPanel.add(trackRecordBtn);

// Compare btn
JButton compareBtn = new JButton("Compare horses");
//compareBtn.addActionListener(e -> new HorseRemoval_gui(raceInfo));
fullPanel.add(compareBtn);

// Add the panel to the frame
frame.add(fullPanel);
// Set the frame visible
frame.setVisible(true);

}
}