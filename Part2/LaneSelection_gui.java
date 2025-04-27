import java.awt.*;
import javax.swing.*;

/**
 * This handles creating a gui that collects 
 * the data required to add a new lane to the race
 * 
*/

public class LaneSelection_gui 
{    
    private final static int MAXLANES = 6;
    private final static int MINLANES = 2;
    private final static String[] LISTOFSHAPES= {"straight","circle"};
    private final static String[] LISTOFCONDITIONS= {"none","muddy","icy"};
    private final CurrentRaceInfo raceInfo;

    private final JTextArea raceLengthBox;
    private final JList<String> shapeList;
    private final JList<String> conditionList;
    private final JSlider laneCount;
    private final JFrame frame;
    
    /**
     * This handles creating the gui that collects information for every field
     *
     * @param raceInfo the class to write the new lane object to 
     */

    public LaneSelection_gui(CurrentRaceInfo raceInfo) 
    {
        this.raceInfo = raceInfo;

        this.frame = new JFrame("Track settings");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        this.frame.setSize(300, 500);

        // Lane count row
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

        this.shapeList = new JList<>();
        this.shapeList.setListData((String[])LISTOFSHAPES);
        raceShapeRow.add(this.shapeList);

        // Track condition
        JPanel raceConditionRow = new JPanel(new FlowLayout());

        JLabel raceConditionlabel = new JLabel("Track condition: ");
        raceConditionRow.add(raceConditionlabel);

        this.conditionList = new JList<>();
        this.conditionList.setListData((String[])LISTOFCONDITIONS);
        raceConditionRow.add(this.conditionList);


        // Submit btn
        JButton submitBtn = new JButton("Create new Track");
        submitBtn.addActionListener(e -> createNewRace());


        // Lays out the button section
        JPanel optionList = new JPanel(new GridLayout(5, 1)); 
        optionList.add(laneCountRow);
        optionList.add(raceLengthRow);
        optionList.add(raceShapeRow);
        optionList.add(raceConditionRow);
        optionList.add(submitBtn);


        this.frame.add(optionList);
        this.frame.setVisible(true);
    }


    /**
     * This handles collecting the information from the gui elements
     * and creating a new track class to store the information
     */

    private void createNewRace ()
    {
        int length;
        // makes sure a number was entered
        try 
        {
            length = Integer.parseInt(raceLengthBox.getText());

            // makes sure the length is raceable
            if (length < 1)
            {
                new ErrorBox("Enter a valid length");
                return;
            }
        }
        catch (NumberFormatException nfe)
        {
            new ErrorBox("Enter a number for length");
            return;
        }

        // Makes sure all fields are filled
        if (this.shapeList.getSelectedIndex() == -1)
        {
            new ErrorBox("Select a shape");
            return;
        }

        if (this.conditionList.getSelectedIndex() == -1)
        {
            new ErrorBox("Select a condition");
            return;

        }

        this.frame.dispose();
        
        // creates the new track with the collected data and stores it
        raceInfo.setRaceTrack(
            new Track(
                this.laneCount.getValue(),
                length, 
                LISTOFSHAPES[this.shapeList.getSelectedIndex()],
                this.conditionList.getSelectedIndex()));
    }

}