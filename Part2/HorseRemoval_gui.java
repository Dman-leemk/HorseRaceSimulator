import javax.swing.*;


public class HorseRemoval_gui  extends  HorseSelection_gui{
private currentRaceInfo raceInfo;

public HorseRemoval_gui (currentRaceInfo raceInfo)
{
    
    super(raceInfo,"Horse Deletion");
    this.raceInfo = raceInfo;
}

@Override
 void onClickEffect (Horse horse,JFrame frame)
{
    raceInfo.removeHorse(horse);
    frame.dispose();
}

}