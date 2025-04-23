import java.awt.*;
import javax.swing.*;


public class LaneSelection_gui {
    
private final static int MAXLANES = 6;
private final static int MINLANES = 2;
private final static String[] LISTOFSHAPES= {"straight","circle"};
private final static String[] LISTOFCONDITIONS= {"none","muddy","icy"};
private currentRaceInfo raceInfo;

private JTextArea raceLengthBox;
private JList<String> shapeList;
private JList<String> conditionList;
private JSlider laneCount;
private JFrame frame;



public LaneSelection_gui(currentRaceInfo raceInfo) 
{
    this.raceInfo = raceInfo;

    // Create a JFrame
    this.frame = new JFrame("Track settings");
    this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
    this.frame.setSize(300, 500);

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

    this.shapeList = new JList<String>();
    this.shapeList.setListData((String[])LISTOFSHAPES);
    raceShapeRow.add(this.shapeList);

    // Track condition
    JPanel raceConditionRow = new JPanel(new FlowLayout());

    JLabel raceConditionlabel = new JLabel("Track condition: ");
    raceConditionRow.add(raceConditionlabel);


    this.conditionList = new JList<String>();
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
    this.frame.add(optionList);
    // Set the frame visible
    this.frame.setVisible(true);
}

private void createNewRace ()
{
    int length = 0;

    try 
    {
        length = Integer.parseInt(raceLengthBox.getText());

        if (length < 1)
        {
            new errorBox("Enter a valid length");
            return;
        }
    }
    catch (NumberFormatException nfe)
    {
        new errorBox("Enter a number for length");
        return;
    }

    if (this.shapeList.getSelectedIndex() == -1)
    {
        new errorBox("Select a shape");
        return;
    }

    if (this.conditionList.getSelectedIndex() == -1)
    {
        new errorBox("Select a condition");
        return;

    }

    this.frame.dispose();
    raceInfo.setRaceTrack(new track(this.laneCount.getValue(),length,  LISTOFSHAPES[this.shapeList.getSelectedIndex()], LISTOFCONDITIONS[this.conditionList.getSelectedIndex()]));
}

}