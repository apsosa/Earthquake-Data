import java.util.*;
import edu.duke.*;
/**
 * Write a description of class MatchAllFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter
{
    // instance variables - replace the example below with your own
    private ArrayList<Filter> filters;

    /**
     * Constructor for objects of class MatchAllFilter
     */
    public MatchAllFilter()
    {
        // initialise instance variables
        filters = new ArrayList<Filter>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void addFilter(Filter f)
    {
        filters.add(f);
    }

    public boolean  satisfies(QuakeEntry qe)
    {
        for(Filter f : filters) { 
            if (!f.satisfies(qe)) { 
                return false; 
            } 
        }
        return true; 
    
    }

    public  String getName(){
        String filtersName = "";
        for (Filter f: filters ) {
            String currName = f.getName();
            filtersName =  filtersName+currName+" ";
         } 
        return filtersName;
    }



}
