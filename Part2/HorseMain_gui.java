import java.awt.*;
import javax.swing.*;


public class HorseMain_gui {

public HorseMain_gui (currentRaceInfo raceInfo)
{

if (raceInfo.getHorses() == null)
{
    new errorBox("Create a track first");
    return;
}


JFrame frame = new JFrame("Horse panel");
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setSize(500, 300);

JPanel fullPanel = new JPanel(new GridLayout(1,2));

// creates the Track button
JButton addBtn = new JButton("ADD horse");
addBtn.addActionListener(e -> new HorseCreation_gui(raceInfo));
fullPanel.add(addBtn);

// creates the Horse button
JButton removeBtn = new JButton("REMOVE horse");
removeBtn.addActionListener(e -> new HorseRemoval_gui(raceInfo));
fullPanel.add(removeBtn);

// Add the panel to the frame
frame.add(fullPanel);
// Set the frame visible
frame.setVisible(true);

}
}