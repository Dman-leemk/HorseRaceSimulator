import java.awt.*;
import javax.swing.*;


public class HorseCreation_gui {
    
final static String[] LISTOFBREEDS= {"Thoroughbred","Arabian"};
final static String[] LISTOFCOLOURS= {"black","brown","grey"};
final static String[] LISTOFSADDLES= {"jumping","speed","normal"};
final static String[] LISTOFHORSESHOES= {"light","heavy","normal"};
final static String[] LISTOFBRIDLES= {"Snaffle ","Double","Bitless"};


private JList<String> breedList;
private JList<String> colourList;
private JList<String> saddleList;
private JList<String> horseShoeList;
private JList<String> bridleList;
private JTextArea symbolBox;
private JTextArea nameBox;
private JFrame frame;
private currentRaceInfo raceInfo;

public HorseCreation_gui(currentRaceInfo raceInfo) {
this.raceInfo = raceInfo;
// Create a JFrame
this.frame = new JFrame("Horse creator");
this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
this.frame.setSize(1000, 500);


// Horse name row

// Symbol row
JPanel nameRow = new JPanel(new FlowLayout());
JLabel namelabel = new JLabel("Horse name: ");

nameRow.add(namelabel);

this.nameBox = new JTextArea("Name");
nameRow.add(this.nameBox);

// Horse Breed row
JPanel breedRow = new JPanel(new FlowLayout());

JLabel breedlabel = new JLabel("Horse Breed: ");
breedRow.add(breedlabel);

this.breedList = new JList<String>();
this.breedList.setListData((String[])LISTOFBREEDS);
breedRow.add(this.breedList);


// Colour row
JPanel colourRow = new JPanel(new FlowLayout());

JLabel colourlabel = new JLabel("Coat Colour: ");
colourRow.add(colourlabel);

this.colourList = new JList<String>();
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

this.saddleList = new JList<String>();
this.saddleList.setListData((String[])LISTOFSADDLES);
saddleRow.add(this.saddleList);

// Horseshoe row
JPanel horseshoeRow = new JPanel(new FlowLayout());

JLabel horseshoelabel = new JLabel("HorseShoe Type: ");
horseshoeRow.add(horseshoelabel);

this.horseShoeList= new JList<String>();
this.horseShoeList.setListData((String[])LISTOFHORSESHOES);
horseshoeRow.add(this.horseShoeList);

// Bridle row
JPanel bridleRow = new JPanel(new FlowLayout());

JLabel bridlelabel = new JLabel("HorseShoe Type: ");
bridleRow.add(bridlelabel);

this.bridleList= new JList<String>();
this.bridleList.setListData((String[])LISTOFBRIDLES);
bridleRow.add(this.bridleList);


// Add new Horse
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


// Add the panel to the frame
this.frame.add(optionList);
// Set the frame visible
this.frame.setVisible(true);
}

private void addNewHorse ()
{
    char symbol = symbolBox.getText().charAt(0);
    String name = nameBox.getText(); 

    if (this.breedList.getSelectedIndex() == -1)
    {
        new errorBox("Select a breed");
        return;
    }

    if (this.colourList.getSelectedIndex() == -1)
    {
        new errorBox("Select a colour");
        return;
    }

    if (this.saddleList.getSelectedIndex() == -1)
    {
        new errorBox("Select a saddle");
        return;
    }

    if (this.horseShoeList.getSelectedIndex() == -1)
    {
        new errorBox("Select a horse shoe");
        return;

    }

    if (this.breedList.getSelectedIndex() == -1)
    {
        new errorBox("Select a breed");
        return;
    }

    if (this.bridleList.getSelectedIndex() == -1)
    {
        new errorBox("Select a bridle");
        return;

    }


    this.raceInfo.addHorse(
    new Horse(symbol,
    name, 
    0.5,
    LISTOFCOLOURS[this.colourList.getSelectedIndex()],
    this.breedList.getSelectedIndex(),
    this.saddleList.getSelectedIndex(),
    this.horseShoeList.getSelectedIndex(),
    this.bridleList.getSelectedIndex() ));

    this.frame.dispose();
}

}