
/**
 * Write a description of class DepthFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter
{
    // instance variables - replace the example below with your own
    private double minimum;
    private double maximum;

    /**
     * Constructor for objects of class DepthFilter
     */
    public DepthFilter(double min, double max)
    {
        // initialise instance variables
        minimum = min;
        maximum = max;    
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean  satisfies(QuakeEntry qe)
    {
        return qe.getDepth() >= minimum && qe.getDepth() <= maximum;
    
    }
    public  String getName(){
        return "Depth";
    }
}
