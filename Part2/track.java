
public class track 
{
    private final int laneCount;
    private final int length;
    private final String trackShape;
    private final String trackCondition;

    public track (int laneCount,int length, String trackShape, String trackCondition)
    {
        this.laneCount = laneCount;
        this.length = length;
        this.trackShape = trackShape;
        this.trackCondition = trackCondition;
    }

    public int getlaneCount () 
    {
        return this.laneCount;
    }

    public int getlength () 
    {
        return this.length;
    }

    /***
     * Print the race on the terminal
     */
    public String printRace(Horse[] horses)
    {
        if (this.trackShape.equals("straight"))
        {
            return printStraightRace(horses);
        }
        return "error not implemented";
    }
    
    public String printStraightRace(Horse[] horses)
    {
        String race = "";
        
        race = race + multipleChars('=',this.length); //top edge of track
        race = race + '\n';
        
        for (Horse horse : horses) {
            race = race + printLane(horse);
            race = race + '\n';
        }

        race = race + multipleChars('=',this.length); //bottom edge of track
        race = race + '\n';
        return race; 
    }


    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private String printLane(Horse theHorse)
    {
        int spacesBefore;
        int spacesAfter;
        String lane;

        //calculate how many spaces are needed before
        //and after the horse
        if (theHorse != null)
        {
            spacesBefore = theHorse.getDistanceTravelled();
            spacesAfter = this.length - theHorse.getDistanceTravelled();
        }
        else
        {
            spacesBefore =0;
            spacesAfter = this.length;
        }
        //print a | for the beginning of the lane
        lane = "|";
        
        //print the spaces before the horse
        lane = lane + multipleChars(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if (theHorse != null)
        {
            if(theHorse.hasFallen())
            {
                lane = lane + '\u2322';
            }
            else
            {
                lane = lane + theHorse.getSymbol();
            }
        }
        else
        {
            lane = lane + " ";
        }
        
         //print the spaces after the horse
        lane = lane + multipleChars(' ',spacesAfter);
        
        //print the | for the end of the track
        lane = lane +  '|';
        if (theHorse != null)
        {
            lane = lane + "     " + theHorse.getName() + " (Current confidence " + theHorse.getConfidence() + ")";
        }
        return lane;
    }
        
    
    /***
     * returns a string of a character repeated a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     * @param times the number of times
     */
    private String multipleChars(char aChar, int times)
    {
        String word = "";
        for (int i = 0; i<times;i++)
        {
            word = word + aChar;
        }
        return word;
    }


}