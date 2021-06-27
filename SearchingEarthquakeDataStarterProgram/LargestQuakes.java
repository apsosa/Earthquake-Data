import java.util.*;
/**
 * Write a description of class LargestQuakes here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LargestQuakes
{
    
    public void findLargestQuakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> largest  = getLargest(list,50);

        System.out.println("read data for "+list.size());

        for(int k=0; k < largest.size(); k++){
            QuakeEntry entry = largest.get(k);
            System.out.println(entry.toString());
        }
        System.out.println("Found "+largest.size()+" quakes that match that criteria");

    }
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int indexLargest= 0;
        QuakeEntry largestEntry = data.get(indexLargest);
        for (int k = 1; k<data.size()  ;k++ ) {
            QuakeEntry currEntry = data.get(k);
            if (currEntry.getMagnitude() > largestEntry.getMagnitude()) {
                indexLargest = k;
                largestEntry = currEntry;
            }
        }
        return indexLargest;

    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){

        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> tmp = new ArrayList<QuakeEntry>(quakeData);
        /* otra opcion
        for (int k=0; k < howMany; k++) {
            
            int minIndex = 0;
            QuakeEntry maxDistQuake = quakeData.get(minIndex);
            for (int j=1; j < quakeData.size(); j++) {
                QuakeEntry currQuake = quakeData.get(j);
                
                if (maxDistQuake.getMagnitude() < currQuake.getMagnitude() &&
                             !answer.contains(quake)) {
                    maxIndex = j;
                    maxDistQuake = currQuake;
                }
            }
            answer.add(quakeData.get(maxIndex));

        }
        */
        for (int k = 0;k < howMany; k++ ) {
            int currIndex = indexOfLargest(tmp);
            QuakeEntry currQuake = tmp.get(currIndex);
            answer.add(currQuake);
            tmp.remove(tmp.get(currIndex));

        }

        return answer;
    }

    public void tester(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        int largest =  indexOfLargest(list);
        System.out.println("The Largest Index is : "+largest);
        System.out.println("With this entry : ");
        System.out.println(list.get(largest).toString());

    }


}
