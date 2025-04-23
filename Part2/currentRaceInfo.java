public class currentRaceInfo
{
    private Horse[] horses = null;
    private track currentTrack;

    public Horse[] getHorses ()
    {
        return horses;
    }

    public track getTrack ()
    {
        return currentTrack;
    }

    /***
     * sets the race length
     * 
     * @param raceLength
     */
    public void setRaceTrack (track newTrack)
    {
        this.currentTrack = newTrack;
        this.horses = new Horse[newTrack.getlaneCount()];
    }

    /**
     * Adds a horse to the next empty lane returns false is no lanes are empty
     * 
     * @param theHorse the horse to be added to the race
     */
    public boolean addHorse(Horse theHorse)
    {
        for (int i = 0; i < this.horses.length; i++)
        {
            if (this.horses[i] == null)
            {
                this.horses[i] = theHorse;
                return true;
            }
        }
        new errorBox("Add more lanes or remove a horse");

        return false;
    }

    /**
     * Removes a horse setting the lane empty
     * 
     * @param theHorse the horse to be removed from the race
     */
    public void removeHorse(Horse theHorse)
    {
        for (int i = 0; i < this.horses.length; i++)
        {
            if (this.horses[i] == theHorse)
            {
                this.horses[i] = null;
            }
        }
    }
}