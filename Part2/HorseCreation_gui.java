import java.awt.*;
import javax.swing.*;
/**
 * This handles creating a gui that collects 
 * the data required to add a new horse to the race
 * 
*/

public class HorseCreation_gui 
{
    
    final static String[] LISTOFBREEDS= {"Thoroughbred","Arabian"};
    final static String[] LISTOFCOLOURS= {"black","brown","grey"};
    final static String[] LISTOFSADDLES= {"jumping","speed","normal"};
    final static String[] LISTOFHORSESHOES= {"light","heavy","normal"};
    final static String[] LISTOFBRIDLES= {"Snaffle ","Double","Bitless"};


    private final JList<String> breedList;
    private final JList<String> colourList;
    private final JList<String> saddleList;
    private final JList<String> horseShoeList;
    private final JList<String> bridleList;
    private final JTextArea symbolBox;
    private final JTextArea nameBox;
    private final JFrame frame;
    private final CurrentRaceInfo raceInfo;

    /**
     * This handles creating the gui that collects information for every field
     *
     * @param raceInfo the class to write the new horse object to 
     */
    public HorseCreation_gui(CurrentRaceInfo raceInfo) 
    {
        this.raceInfo = raceInfo;

        this.frame = new JFrame("Horse creator");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(1000, 500);


        // Horse name row
        JPanel nameRow = new JPanel(new FlowLayout());

        JLabel namelabel = new JLabel("Horse name: ");
        nameRow.add(namelabel);

        this.nameBox = new JTextArea("Name");
        nameRow.add(this.nameBox);


        // Horse Breed row
        JPanel breedRow = new JPanel(new FlowLayout());

        JLabel breedlabel = new JLabel("Horse Breed: ");
        breedRow.add(breedlabel);

        this.breedList = new JList<>();
        this.breedList.setListData((String[])LISTOFBREEDS);
        breedRow.add(this.breedList);


        // Colour row
        JPanel colourRow = new JPanel(new FlowLayout());

        JLabel colourlabel = new JLabel("Coat Colour: ");
        colourRow.add(colourlabel);

        this.colourList = new JList<>();
        this.colourList.setListData((String[])LISTOFCOLOURS);
        colourRow.add(this.colourList);


        // Symbol row
        JPanel symbolRow = new JPanel(new FlowLayout());
        JLabel symbollabel = new JLabel("Horse symbol: ");

        symbolRow.add(symbollabel);

        this.symbolBox = new JTextArea("#");
        symbolRow.add(this.symbolBox);


        // Saddle row
        JPanel saddleRow = new JPanel(new FlowLayout());

        JLabel saddlelabel = new JLabel("Saddle Type: ");
        saddleRow.add(saddlelabel);

        this.saddleList = new JList<>();
        this.saddleList.setListData((String[])LISTOFSADDLES);
        saddleRow.add(this.saddleList);


        // Horseshoe row
        JPanel horseshoeRow = new JPanel(new FlowLayout());

        JLabel horseshoelabel = new JLabel("HorseShoe Type: ");
        horseshoeRow.add(horseshoelabel);

        this.horseShoeList= new JList<>();
        this.horseShoeList.setListData((String[])LISTOFHORSESHOES);
        horseshoeRow.add(this.horseShoeList);


        // Bridle row
        JPanel bridleRow = new JPanel(new FlowLayout());

        JLabel bridlelabel = new JLabel("Bridle Type: ");
        bridleRow.add(bridlelabel);

        this.bridleList= new JList<>();
        this.bridleList.setListData((String[])LISTOFBRIDLES);
        bridleRow.add(this.bridleList);


        // Add new Horse btn
        JButton horseBtn=  new JButton("Create");
        horseBtn.addActionListener(e -> addNewHorse());


        // Lays out the button section
        JPanel optionList = new JPanel(new GridLayout(3, 2)); 
        optionList.add(nameRow);
        optionList.add(breedRow);
        optionList.add(colourRow);
        optionList.add(symbolRow);
        optionList.add(saddleRow);
        optionList.add(horseshoeRow);
        optionList.add(bridleRow);
        optionList.add(horseBtn);

        this.frame.add(optionList);

        this.frame.setVisible(true);
    }


    /**
     * This handles collecting the information from the gui elements
     * and creating a new horse class to store the information
     */

    private void addNewHorse ()
    {
        char symbol = symbolBox.getText().charAt(0);
        String name = nameBox.getText(); 


        // Makes sure all fields are filled
        if (this.breedList.getSelectedIndex() == -1)
        {
            
            new ErrorBox("Select a breed");
            return;
        }

        if (this.colourList.getSelectedIndex() == -1)
        {
            new ErrorBox("Select a colour");
            return;
        }

        if (this.saddleList.getSelectedIndex() == -1)
        {
            new ErrorBox("Select a saddle");
            return;
        }

        if (this.horseShoeList.getSelectedIndex() == -1)
        {
            new ErrorBox("Select a horse shoe");
            return;

        }

        if (this.breedList.getSelectedIndex() == -1)
        {
            new ErrorBox("Select a breed");
            return;
        }

        if (this.bridleList.getSelectedIndex() == -1)
        {
            new ErrorBox("Select a bridle");
            return;

        }

        // Creates and adds the new horse 
        this.raceInfo.addHorse(
            new Horse(
                symbol,
                name, 
                LISTOFCOLOURS[this.colourList.getSelectedIndex()],
                this.breedList.getSelectedIndex(),
                this.saddleList.getSelectedIndex(),
                this.horseShoeList.getSelectedIndex(),
                this.bridleList.getSelectedIndex() ));

        this.frame.dispose();
    }

}