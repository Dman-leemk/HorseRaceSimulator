public class main 
{
    public static void main (String[] args)
    {
        Main_gui main_gui = new Main_gui();
        race_Logic Race_logic = new race_Logic (main_gui);
        main_gui.setLogic(Race_logic);
        Race_logic.setLanes(13);
        Race_logic.setRaceLength(10);
        Race_logic.addHorse(new Horse('#',"Horsey",0.8));
        Race_logic.addHorse(new Horse('%',"Pony",0.4));
    }
}