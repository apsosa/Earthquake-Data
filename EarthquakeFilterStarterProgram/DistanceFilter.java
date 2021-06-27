
/**
 * Write a description of class DistanceFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter
{
    // instance variables - replace the example below with your own
    private Location myLocation;
    private double distMax;

    /**
     * Constructor for objects of class DistanceFilter
     */
    public DistanceFilter(Location loc, double max)
    {
        // initialise instance variables
        myLocation  = loc;
        distMax = max;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean  satisfies(QuakeEntry qe)
    {
        Location tmp = qe.getLocation();
        return qe.getLocation().distanceTo(myLocation) < distMax;
    
    }
    public  String getName(){
        return "Distance";
    }

}
