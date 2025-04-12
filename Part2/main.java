public class main 
{
    public static void main (String[] args)
    {
        Main_gui main_gui = new Main_gui();
        race_Logic Race_logic = new race_Logic (main_gui);
        main_gui.setLogic(Race_logic);
    }
}