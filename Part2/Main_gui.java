import java.awt.*;
import javax.swing.*;


public class Main_gui {

private  currentRaceInfo raceInfo;

public Main_gui (currentRaceInfo raceInfo) {
// Create a JFrame
JFrame frame = new JFrame("Horse Simulator");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(500, 350);

// creates the start Race button
JButton startBtn = new JButton("Start race");
startBtn.addActionListener(e -> new race_GUI(raceInfo));

// creates the Track button
JButton trackBtn = new JButton("Customise Track");
trackBtn.addActionListener(e -> new LaneSelection_gui(raceInfo));

// creates the Horse button
JButton horseBtn = new JButton("Customise Horses");
horseBtn.addActionListener(e -> new HorseMain_gui(raceInfo));

// creates the Stats button
JButton statsBtn = new JButton("View Stats");
statsBtn.addActionListener(e -> new StatSelection_gui(raceInfo));

// creates the Betting button
JButton betBtn = new JButton("Betting");

// Lays out the button section
JPanel buttonList = new JPanel(new GridLayout(2, 2)); 

buttonList.add(trackBtn);
buttonList.add(horseBtn);
buttonList.add(statsBtn);
buttonList.add(betBtn);


// Layouts out the various sections
// Create a JPanel with GridLayout
JPanel fullPanel = new JPanel(new GridLayout(2,1));
fullPanel.add(buttonList);
fullPanel.add(startBtn);

// Add the panel to the frame
frame.add(fullPanel);
// Set the frame visible
frame.setVisible(true);

}
}