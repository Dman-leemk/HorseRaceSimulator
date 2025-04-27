/**
 * File used to start the program
 * 
 */


public class Main 
{
    public static void startRaceGUI ()
    {
        final int bettingCurrency = 1000;
        CurrentRaceInfo raceInfo = new CurrentRaceInfo (bettingCurrency);
        new Main_gui(raceInfo);
    }
}