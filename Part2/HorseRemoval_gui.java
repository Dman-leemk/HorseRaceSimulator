import java.awt.*;
import javax.swing.*;


public class HorseRemoval_gui {

public HorseRemoval_gui (currentRaceInfo raceInfo)
{
boolean isNoHorse = true;

for (Horse horse : raceInfo.getHorses()) {
    if (horse != null)
    {
        isNoHorse = true;
    }    
}

if (isNoHorse)
{
    new errorBox("No horses to remove");
    return;
}

JFrame frame = new JFrame("Horse removal");
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setSize(300, 500);

JPanel fullPanel = new JPanel(new GridLayout(raceInfo.getHorses().length,1));

for (Horse horse : raceInfo.getHorses())
{
    JButton Btn = new JButton(horse.getName());
    Btn.addActionListener(e -> raceInfo.removeHorse(horse));
    fullPanel.add(Btn);
}

// Add the panel to the frame
fullPanel.add(fullPanel);
// Set the frame visible
fullPanel.setVisible(true);

}
}