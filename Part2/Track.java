/**
 * A class used to organise the various effects that could be provided to the horse
 * due to the track and a way to describe the effects in words
 * 
 */
class trackEffect
{
    final String name;
    final double speedModifer;
    final double enduranceModifer;
    final double confidenceModifer;
    final String effectInWords;

    public trackEffect(String name, double speedModifer, double enduranceModifer,double confidenceModifer,String effectInWords) 
    {
        this.name = name;
        this.speedModifer = speedModifer;
        this.enduranceModifer = enduranceModifer;
        this.confidenceModifer = confidenceModifer;
        this.effectInWords = effectInWords;
    }
}

/**
 * Handles storing the information tied to the track
 * handles the printing of the track
 */


public class Track 
{
    private final int laneCount;
    private final int length;
    private final String trackShape;
    private final int trackCondition;
    private int bestTime;

    private final double trackSpeedEffect;
    private final double trackEnduranceEffect;
    private final double trackConfidenceEffect;

    static final trackEffect[] TRACK_EFFECTS = {
        new trackEffect("none",0,0,0," no effects"), 
        new trackEffect("muddy", 0, -0.2, -0.1, " reduces endurance and confidence"), 
        new trackEffect("icy", -0.2, 0, -0.1, "reduces speed and confidence"), 
        new trackEffect("fresh", 0.1,0.1,0.1, "increases speed, endurance and confidence")};

    public Track (int laneCount,int length, String trackShape, int trackCondition)
    {
        this.laneCount = laneCount;
        this.length = length;
        this.trackShape = trackShape;
        this.bestTime = -1;
        this.trackCondition       = trackCondition;
        this.trackSpeedEffect     = TRACK_EFFECTS[trackCondition].speedModifer;
        this.trackEnduranceEffect = TRACK_EFFECTS[trackCondition].enduranceModifer;
        this.trackConfidenceEffect = TRACK_EFFECTS[trackCondition].confidenceModifer;
    }

    // Accessor methods
    public void setBestTime (int bestTime)
    {
        if (this.bestTime > bestTime || this.bestTime == -1) 
        {
            this.bestTime = bestTime;            
        }
    }

    private String returnBestTime ()
    {
        if (this.bestTime == -1) 
        {
            return "No best time";
        }
        else
        {
            return "Best time = " + this.bestTime + " ms";
        }
    } 

    private String returnConditonEffect ()
    {
        return TRACK_EFFECTS[trackCondition].name + " " + TRACK_EFFECTS[trackCondition].effectInWords;
    }
    
    public int getlaneCount () 
    {
        return this.laneCount;
    }

    public int getlength () 
    {
        return this.length;
    }

    public double getSpeedModifer ()
    {
        return this.trackSpeedEffect;
    }

    public double getEnduranceModifer ()
    {
        return this.trackEnduranceEffect;
    }

    public double getConfidenceModifer ()
    {
        return this.trackConfidenceEffect;
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
    

    // Prints the information required for a straight track 
    public String printStraightRace(Horse[] horses)
    {
        String race = "Best time is: " + returnBestTime() + " the condition is " + returnConditonEffect() + "\n";
        
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
            if(theHorse.getCurrentFinishPostion() == -1)
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
            lane = lane + "     " + theHorse.getName() + " (Current confidence " + String.format("%.2f", theHorse.getConfidence()) + ")";
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