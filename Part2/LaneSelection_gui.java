import java.awt.*;
import javax.swing.*;


public class LaneSelection_gui {
    
private final static int MAXLANES = 6;
private final static int MINLANES = 2;
private final static String[] LISTOFSHAPES= {"straight","circle"};
private final static String[] LISTOFCONDITIONS= {"none","muddy","icy"};
private race_Logic raceLogic;

private JTextArea raceLengthBox;
private JList shapeList;
private JList conditionList;
private JSlider laneCount;

public LaneSelection_gui(race_Logic raceLogic) {


this.raceLogic = raceLogic;

// Create a JFrame
JFrame frame = new JFrame("Track settings");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(300, 500);

// lane count row
JPanel laneCountRow = new JPanel(new FlowLayout());

JLabel laneCountLabel = new JLabel("Lane count: ");
laneCountRow.add(laneCountLabel);

this.laneCount = new JSlider();
this.laneCount.setMaximum(MAXLANES);
this.laneCount.setMinimum(MINLANES);
this.laneCount.setMajorTickSpacing(1);
this.laneCount.setPaintLabels(true);
laneCountRow.add(this.laneCount);

// Track length row

JPanel raceLengthRow = new JPanel(new FlowLayout());
JLabel raceLengthlabel = new JLabel("Track length: ");

raceLengthRow.add(raceLengthlabel);

this.raceLengthBox = new JTextArea("10");
raceLengthRow.add(this.raceLengthBox);

// Track shape row
JPanel raceShapeRow = new JPanel(new FlowLayout());

JLabel raceShapelabel = new JLabel("Track Shape: ");
raceShapeRow.add(raceShapelabel);

this.shapeList = new JList();
this.shapeList.setListData((String[])LISTOFSHAPES);
raceShapeRow.add(this.shapeList);

// Track condition
JPanel raceConditionRow = new JPanel(new FlowLayout());

JLabel raceConditionlabel = new JLabel("Track condition: ");
raceConditionRow.add(raceConditionlabel);


this.conditionList = new JList();
this.conditionList.setListData((String[])LISTOFCONDITIONS);
raceConditionRow.add(this.conditionList);

// submit btn
JButton submitBtn = new JButton("Create new Track");
submitBtn.addActionListener(e -> createNewRace());

// Lays out the button section
JPanel optionList = new JPanel(new GridLayout(5, 1)); 
optionList.add(laneCountRow);
optionList.add(raceLengthRow);
optionList.add(raceShapeRow);
optionList.add(raceConditionRow);
optionList.add(submitBtn);



// Add the panel to the frame
frame.add(optionList);
// Set the frame visible
frame.setVisible(true);
}

private boolean createNewRace ()
{
    int length;

    try 
    {
        length = Integer.parseInt(raceLengthBox.getText());
    }
    catch (NumberFormatException nfe)
    {
        // error
        return false;
    }

    if (this.shapeList.getSelectedIndex() == -1)
    {
        // error
        return false;
    }

     if (this.conditionList.getSelectedIndex() == -1)
    {
        // error
        return false;
    }

    
    raceLogic.setRaceTrack(new track(this.laneCount.getValue(),length,  LISTOFSHAPES[this.shapeList.getSelectedIndex()], LISTOFCONDITIONS[this.conditionList.getSelectedIndex()]));
    System.out.println(this.laneCount.getValue());

    return true;
}

}