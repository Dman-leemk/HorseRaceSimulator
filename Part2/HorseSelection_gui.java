import java.awt.*;
import javax.swing.*;

/**
 * Provides a gui that selected a horse that calls a effect 
 * that is overridden
 * 
 */

public abstract class HorseSelection_gui {

    public HorseSelection_gui (CurrentRaceInfo raceInfo, String tabName)
    {
        // checks is any horses are stores
        boolean isNoHorse = true;
        for (Horse horse : raceInfo.getHorses()) {
            if (horse != null)
            {
                isNoHorse = false;
            }    
        }

        if (isNoHorse)
        {
            new ErrorBox("No horses");
            return;
        }

        // Sreates the gui
        JFrame frame = new JFrame(tabName);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 500);

        JPanel fullPanel = new JPanel(new GridLayout(raceInfo.getHorses().length,1));

        // Adds a button for every horse
        for (Horse horse : raceInfo.getHorses())
        {
            if (horse != null)
            {
                JButton Btn = new JButton(horse.getName());
                Btn.addActionListener(e ->onClickEffect(horse,frame));
                fullPanel.add(Btn);
            }
        }

        frame.add(fullPanel);
        frame.setVisible(true);

    }

    // Method to call after a click
    abstract void onClickEffect (Horse horse,JFrame frame);

}