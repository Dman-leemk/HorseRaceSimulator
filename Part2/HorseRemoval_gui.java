import javax.swing.*;

/**
 * Provides a GUI to select the horse to remove
 * uses the HorseSelection_gui for the selection
 */


public class HorseRemoval_gui  extends  HorseSelection_gui
{
    
    private CurrentRaceInfo raceInfo;

    // constuctor
    public HorseRemoval_gui (CurrentRaceInfo raceInfo)
    {    
        super(raceInfo,"Horse Deletion");
        this.raceInfo = raceInfo;
    }


    /**
     * Handles the removal of the horse
     * 
     * @param horse the reference to the horse to remove
     * @param frame the panel to close after the horse is selected
     */
    @Override
    void onClickEffect (Horse horse,JFrame frame)
    {
        raceInfo.removeHorse(horse);
        frame.dispose();
    }

}