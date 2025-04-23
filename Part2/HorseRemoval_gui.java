import java.awt.*;
import javax.swing.*;


public class HorseRemoval_gui {

public HorseRemoval_gui (currentRaceInfo raceInfo)
{
boolean isNoHorse = true;

for (Horse horse : raceInfo.getHorses()) {
    if (horse != null)
    {
        isNoHorse = false;
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
    if (horse != null)
    {
        JButton Btn = new JButton(horse.getName());
        Btn.addActionListener(e ->removeHorse(raceInfo,horse,frame) );
        fullPanel.add(Btn);
    }
}

// Add the panel to the frame
frame.add(fullPanel);
// Set the frame visible
frame.setVisible(true);

}

private void removeHorse (currentRaceInfo raceInfo, Horse horse,JFrame frame)
{
    raceInfo.removeHorse(horse);
    frame.dispose();
}

}