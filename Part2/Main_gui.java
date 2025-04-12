import java.awt.*;
import javax.swing.*;


public class Main_gui {

private static final JTextArea  raceScreen = new JTextArea("");
private  race_Logic logic;

public Main_gui () {
// Create a JFrame
JFrame frame = new JFrame("Horse Simulator");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(1000, 700);

// creates the start Race button
JButton startBtn = new JButton("Start race");
startBtn.addActionListener(e -> logic.startRace());

// creates the Track button
JButton trackBtn = new JButton("Customise Track");
trackBtn.addActionListener(e -> new LaneSelection_gui(logic));

// creates the Horse button
JButton horseBtn = new JButton("Customise Horses");

// creates the Stats button
JButton statsBtn = new JButton("View Stats");

// creates the Betting button
JButton betBtn = new JButton("Betting");

// Lays out the button section
JPanel buttonList = new JPanel(new GridLayout(4, 1)); 

buttonList.add(trackBtn);
buttonList.add(horseBtn);
buttonList.add(statsBtn);
buttonList.add(betBtn);


// Layouts out the various sections
// Create a JPanel with GridLayout
JPanel fullPanel = new JPanel(new BorderLayout());
fullPanel.add(buttonList, BorderLayout.WEST);
fullPanel.add(raceScreen, BorderLayout.CENTER);
fullPanel.add(startBtn, BorderLayout.SOUTH);

// Add the panel to the frame
frame.add(fullPanel);
// Set the frame visible
frame.setVisible(true);

}

public void setScreen (String text)
{
    raceScreen.setText(text);
}

public void setLogic (race_Logic newLogic)
{
    this.logic = newLogic;
}

}