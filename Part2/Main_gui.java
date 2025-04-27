import java.awt.*;
import javax.swing.*;

/**
 * Creates the starting gui
 * It allows the user to select various functions like
 * adding a track, adding a horse, viewing the horse stats 
 * and starting the race
 */

public class Main_gui {
    
    public Main_gui (CurrentRaceInfo raceInfo) 
    {
        JFrame frame = new JFrame("Horse Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);

        // Creates the start Race button
        JButton startBtn = new JButton("Start race");
        startBtn.addActionListener(e -> new Race_gui(raceInfo));

        // Creates the Track button
        JButton trackBtn = new JButton("Customise Track");
        trackBtn.addActionListener(e -> new LaneSelection_gui(raceInfo));

        // Creates the Horse button
        JButton horseBtn = new JButton("Customise Horses");
        horseBtn.addActionListener(e -> new HorseMain_gui(raceInfo));

        // Creates the Stats button
        JButton statsBtn = new JButton("View Stats");
        statsBtn.addActionListener(e -> new StatSelection_gui(raceInfo));

        // Creates the Betting button
        JButton betBtn = new JButton("Betting");
        betBtn.addActionListener(e -> new BettingScreen_gui(raceInfo));

        // Lays out the button section
        JPanel buttonList = new JPanel(new GridLayout(2, 2)); 

        buttonList.add(trackBtn);
        buttonList.add(horseBtn);
        buttonList.add(statsBtn);
        buttonList.add(betBtn);


        // Layouts out the various sections
        JPanel fullPanel = new JPanel(new GridLayout(2,1));
        fullPanel.add(buttonList);
        fullPanel.add(startBtn);

        frame.add(fullPanel);
        frame.setVisible(true);

    }
}