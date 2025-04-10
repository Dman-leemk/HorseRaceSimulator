public class main 
{
    public static void main (String[] args)
    {
        Main_gui main_gui = new Main_gui();
        race_Logic Race_logic = new race_Logic (main_gui);
        Race_logic.setLanes(6);
        Race_logic.setRaceLength(10);
        Race_logic.addHorse(new Horse('#',"Horsey",0.8));
        
        Race_logic.startRace();
    }
}