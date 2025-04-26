import java.awt.*;
import javax.swing.*;


public abstract class HorseSelection_gui {

public HorseSelection_gui (currentRaceInfo raceInfo, String tabName)
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

JFrame frame = new JFrame(tabName);
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setSize(300, 500);

JPanel fullPanel = new JPanel(new GridLayout(raceInfo.getHorses().length,1));

for (Horse horse : raceInfo.getHorses())
{
    if (horse != null)
    {
        JButton Btn = new JButton(horse.getName());
        Btn.addActionListener(e ->onClickEffect(horse,frame));
        fullPanel.add(Btn);
    }
}

// Add the panel to the frame
frame.add(fullPanel);
// Set the frame visible
frame.setVisible(true);

}


abstract void onClickEffect (Horse horse,JFrame frame);

}