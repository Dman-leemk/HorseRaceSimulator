import java.awt.*;
import javax.swing.*;


public class LaneSelection_gui {
    
final static int MAXLANES = 6;
final static int MINLANES = 2;
final static String[] LISTOFSHAPES= {"straight","circle"};
final static String[] LISTOFCONDITIONS= {"none","muddy","icy"};


public static void main(String[] args) {
// Create a JFrame
JFrame frame = new JFrame("Track settings");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(300, 500);




// lane count row
JPanel laneCountRow = new JPanel(new FlowLayout());

JLabel laneCountLabel = new JLabel("Lane count: ");
laneCountRow.add(laneCountLabel);

JSlider laneCount = new JSlider();
laneCount.setMaximum(MAXLANES);
laneCount.setMinimum(MINLANES);
laneCount.setMajorTickSpacing(1);
laneCount.setPaintLabels(true);
laneCountRow.add(laneCount);

// Track length row
JPanel raceLengthRow = new JPanel(new FlowLayout());

JLabel raceLengthlabel = new JLabel("Track length: ");

raceLengthRow.add(raceLengthlabel);

JTextArea  raceLengthBox = new JTextArea("10");
raceLengthRow.add(raceLengthBox);

// Track shape row
JPanel raceShapeRow = new JPanel(new FlowLayout());

JLabel raceShapelabel = new JLabel("Track Shape: ");
raceShapeRow.add(raceShapelabel);

JList shapeList = new JList();
shapeList.setListData((String[])LISTOFSHAPES);
JScrollPane shapeListScrollable = new JScrollPane(shapeList);
shapeListScrollable.setPreferredSize(new Dimension(100, 20));
raceShapeRow.add(shapeListScrollable);

// Track condition
JPanel raceConditionRow = new JPanel(new FlowLayout());

JLabel raceConditionlabel = new JLabel("Track condition: ");
raceConditionRow.add(raceConditionlabel);


JList conditionList = new JList();
conditionList.setListData((String[])LISTOFCONDITIONS);
JScrollPane conditionListScrollable = new JScrollPane(conditionList);
conditionListScrollable.setPreferredSize(new Dimension(100, 20));
raceConditionRow.add(conditionListScrollable);


// Lays out the button section
JPanel optionList = new JPanel(new GridLayout(5, 1)); 
optionList.add(laneCountRow);
optionList.add(raceLengthRow);
optionList.add(raceShapeRow);
optionList.add(raceConditionRow);
optionList.add(new JButton("Create"));



// Add the panel to the frame
frame.add(optionList);
// Set the frame visible
frame.setVisible(true);
}
}