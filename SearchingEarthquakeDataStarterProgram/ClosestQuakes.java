
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {

        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int k=0; k < howMany; k++) {
            
            int minIndex = 0;
            QuakeEntry minDistQuake = quakeData.get(minIndex);
            Location minDistLocation = minDistQuake.getLocation();
            // ArrayList<QuakeEntry> copyList = new ArrayList<QuakeEntry>(quakeData);
            for (int j=1; j < quakeData.size(); j++) {
                QuakeEntry quake = quakeData.get(j);
                Location location = quake.getLocation();
                
                if (location.distanceTo(current) < minDistLocation.distanceTo(current) &&
                             !answer.contains(quake)) {
                    minIndex = j;
                    minDistLocation = location;
                }
            }
            minDistQuake = quakeData.get(minIndex);
            answer.add(minDistQuake);
            // copyList.remove(minDistQuake);
        }

        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
