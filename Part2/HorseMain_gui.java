import java.awt.*;
import javax.swing.*;

/**
 * This handles the user selecting between the remove horse
 * and add new horse functions
 * 
 */


public class HorseMain_gui {

public HorseMain_gui (CurrentRaceInfo raceInfo)
{
    // Ensures that there is a track to remove or add horses too
    if (raceInfo.getHorses() == null)
    {
        new ErrorBox("Create a track first");
        return;
    }

    JFrame frame = new JFrame("Horse panel");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(500, 300);

    JPanel fullPanel = new JPanel(new GridLayout(1,2));

    // Creates the Track button
    JButton addBtn = new JButton("ADD horse");
    addBtn.addActionListener(e -> new HorseCreation_gui(raceInfo));
    fullPanel.add(addBtn);

    // Creates the Horse button
    JButton removeBtn = new JButton("REMOVE horse");
    removeBtn.addActionListener(e -> new HorseRemoval_gui(raceInfo));
    fullPanel.add(removeBtn);

    frame.add(fullPanel);
    frame.setVisible(true);

}
}