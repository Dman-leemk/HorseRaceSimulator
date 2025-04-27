/**
 * File used to start the program
 * 
 */


public class Main 
{
    public static void main (String[] args)
    {
        CurrentRaceInfo raceInfo = new CurrentRaceInfo ();
        new Main_gui(raceInfo);
    }
}