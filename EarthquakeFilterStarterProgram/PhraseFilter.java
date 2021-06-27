
/**
 * Write a description of class PhraseFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter
{
    // instance variables - replace the example below with your own
    private String where_;
    private String phrase_;

    /**
     * Constructor for objects of class PhraseFilter
     */
    public PhraseFilter(String where, String phrase)
    {
        // initialise instance variables
        where_  = where;
        phrase_ = phrase;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean  satisfies(QuakeEntry qe)
    {
        int index = 0;
        String title = qe.getInfo();
        if (where_.equals("any")){
                if (title.contains(phrase_)) {
                    return true;
                }
        }else{
            if (where_.equals("end")) {
                index = title.length() - phrase_.length();
            }
            String tmp = title.substring(index,index+phrase_.length());
            if (tmp.equals(phrase_)) {
                return true;
            }
    
        }
        return false;
    }
    public  String getName(){
        return "Phrase";
    }
}
