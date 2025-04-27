
/**
 * Stores the infomation about completed races
 * 
 */

public class HorseStats
{
    private final Track raceTrack;
    private final int positionFinished;
    private final double speed;
    private final int time;
    
    public HorseStats(Track raceTrack, int positionFinished, double speed,int time) 
    {
        this.raceTrack = raceTrack;
        this.positionFinished = positionFinished;
        this.speed = speed;
        this.time = time;
    }

    public Track getRaceTrack ()
    {
        return this.raceTrack;
    }

    public int getPositionFinished ()
    {
        return this.positionFinished;
    }

    public double getRaceSpeed ()
    {
        return this.speed;
    }

    public int getRaceTime ()
    {
        return this.time;
    }
}