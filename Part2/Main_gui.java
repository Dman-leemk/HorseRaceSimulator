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


// Lays out the button section
JPanel buttonList = new JPanel(new GridLayout(4, 1)); 

buttonList.add(new JButton("Customise Track"));
buttonList.add(new JButton("Customise Horses"));
buttonList.add(new JButton("View Stats"));
buttonList.add(new JButton("Betting"));

// creates the start race button
JButton startBtn = new JButton("Start race");
startBtn.addActionListener(e -> logic.startRace());
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