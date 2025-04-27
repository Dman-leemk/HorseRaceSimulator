
public class BetInfo
{
    private double betOdds;
    private int betAmount;
    private Horse betHorse;

    public BetInfo (double betOdds,int betAmount,Horse betHorse)
    {
        this.betAmount = betAmount;
        this.betHorse = betHorse;
        this.betOdds = betOdds;
    }

    public Horse getHorse ()
    {
        return this.betHorse;
    }

    public double getWinnings ()
    {
        return this.betAmount * this.betOdds;
    }
}