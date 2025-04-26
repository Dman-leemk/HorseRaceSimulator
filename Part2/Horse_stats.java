
public class Horse_stats
{
    private final track raceTrack;
    private final int positionFinished;
    private final double speed;
    private final int time;

    public Horse_stats(track raceTrack, int positionFinished, double speed,int time) 
    {
        this.raceTrack = raceTrack;
        this.positionFinished = positionFinished;
        this.speed = speed;
        this.time = time;
    }

    public track getRaceTrack ()
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