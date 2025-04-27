
/**
 * A class used to store all the required information about the current race
 * Allows for adding and removing horses from the race
 * 
 */


public class CurrentRaceInfo
{
    private Horse[] horses = null;
    private Track currentTrack;

    // accessor methods
    public Horse[] getHorses ()
    {
        return horses;
    }

    public Track getTrack ()
    {
        return currentTrack;
    }

    /***
     * sets the track, the number of lanes is used to determine how many horses can be stored 
     * 
     * @param newTrack the lane object to store
     */
    public void setRaceTrack (Track newTrack)
    {
        this.currentTrack = newTrack;

        // Stores horses from the previous track
        Horse[] newLanes = new Horse[newTrack.getlaneCount()];
        int startValue = 0;

        if (this.horses != null)
        {
            for (Horse horse : this.horses)
            {
                if (horse != null && startValue < newLanes.length)
                {
                    newLanes[startValue] = horse;
                }
            }
        }
        
        this.horses = newLanes;
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
        new ErrorBox("Add more lanes or remove a horse");

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