
import java.awt.*;
import javax.swing.*;
/**
 * Creates a gui that allows the user to enter bets for various horses
 * 
 */
public class BettingScreen_gui
{
    private JTextArea[] fieldsToCheck;
    private Horse[] horses;
    private double[] odds;
    private int horseCount;

    public BettingScreen_gui(CurrentRaceInfo raceInfo) 
    {
        // Ensures that there is a track to remove or add horses too
        if (raceInfo.getHorses() == null)
        {
            new ErrorBox("Create a track first");
            return;
        }

        // checks if any horses are stored
        boolean isNoHorse = true;
        for (Horse horse : raceInfo.getHorses()) {
            if (horse != null)
            {
                isNoHorse = false;
            }    
        }

        if (isNoHorse)
        {
            new ErrorBox("No horses to bet on");
            return;
        }


        
        this.horseCount = 0;
        JFrame Frame = new JFrame("Betting");
        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Frame.setSize(300, 500);

        JPanel fullPanel = new JPanel(new GridLayout(raceInfo.getHorses().length,1));
        fullPanel.add(new JLabel("You have: £" + raceInfo.getCurrency()));

        // Creates fields to store information required to create bets
        int pointer = 0;
        this.fieldsToCheck = new JTextArea[raceInfo.getHorses().length];
        this.horses = new Horse[raceInfo.getHorses().length];
        this.odds = new double[raceInfo.getHorses().length];

        // Makes a display for every horse
        for (Horse horse : raceInfo.getHorses()) {
            if (horse != null)
            {
                JPanel row = new JPanel(new FlowLayout());

                row.add(new JLabel("Name: " + horse.getName() + " "));
                row.add(new JLabel("Odds: " + String.format("%.2f", calculateOdds(horse,raceInfo.getTrack()))+ " "));
                row.add(new JLabel("Total bet amount: "));
                row.add(new JLabel("Bet amount: "));
                JTextArea textArea = new JTextArea("0");
                row.add(textArea);
                fullPanel.add(row);
                
                this.horses[pointer] = horse;
                this.odds[pointer] = calculateOdds(horse, raceInfo.getTrack());
                this.fieldsToCheck[pointer] = textArea;
                pointer++;
            }    
        }
        
        this.horseCount = pointer;

        JButton placeBet = new JButton("Place bet");
        placeBet.addActionListener(e -> enterBet(raceInfo,Frame));
        fullPanel.add(placeBet);
        Frame.add(fullPanel);
        Frame.setVisible(true);
    }

    /**
     * Handles creating the bets to read later in the program
     * and handles allowing the user to confirm bets
     * 
     */

    private void enterBet (CurrentRaceInfo raceInfo, JFrame frame)
    {
        int fullCost =0;
        int[] amounts = new int[this.horses.length];
        // Validate the fields
        for (int i = 0; i < this.horseCount;i++)
        {
            JTextArea field = this.fieldsToCheck[i];
            int value;
            // makes sure a number was entered
            try 
            {
                value = Integer.parseInt(field.getText());

                // makes sure the bet is a postive number
                if (value < 0)
                {
                    new ErrorBox("Enter a valid betting amount");
                    return;
                }
                fullCost += value;
                amounts[i] = value;
            }
            catch (NumberFormatException nfe)
            {
                new ErrorBox("Enter a number for betting amount");
                return;
            }
        }
        // Checks if the betted amount is greater than the currency the user has
        if (raceInfo.getCurrency() < fullCost)
        {
            new ErrorBox("You don't have enough money");
            return;
        }


        // Creates a stored bet for each bet the user has placed
        BetInfo[] bets = new BetInfo[this.horseCount];

        for (int i = 0; i < this.horseCount; i++)
        {   
            bets[i] = new BetInfo(this.odds[i],amounts[i],this.horses[i]);
        }

        JFrame newFrame = new JFrame("Betting confirmation");
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(300, 500);

        JPanel row = new JPanel(new FlowLayout());

        // Shows the potential winnings
        String stringToPrint = "";
        for (BetInfo bet : bets)
        {               
            stringToPrint = stringToPrint + "\n"
            + "If " + bet.getHorse().getName() + " wins you will gain " + 
            bet.getWinnings() + " pounds \n";
        }
        row.add(new JLabel(stringToPrint));
        
        // Creates the confirmation button
        JButton confirmBtn = new JButton("Confirm");
        row.add(confirmBtn);

        newFrame.add(row);
        newFrame.setVisible(true);

        final int cost = fullCost;

        confirmBtn.addActionListener((actionEvent) -> {
            raceInfo.setBets(bets);
            raceInfo.subCurrency(cost);
            frame.dispose();
            newFrame.dispose();
        });

    }

    /**
     * Creates an algorthimn to calculate the odds required 
     * 
     */

    private double calculateOdds (Horse horse,Track track)
    {
        if (horse.getWinRatioStat()  == 0 )
        {
            return 1;
        }

        return 1 + 0.1/ 
            (
                horse.getWinRatioStat() * 
                (horse.getSpeed() - track.getSpeedModifer()) * 
                (horse.getConfidence() - track.getConfidenceModifer()) * 
                (horse.getEndurance()  - track.getEnduranceModifer())
            );
    }    
}