import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        
        Filter f = new MagnitudeFilter(3.5,4.5); 
        ArrayList<QuakeEntry> m7 = filter(list, f); 
        Filter f1 = new DepthFilter(-55000.0,-20000.0); 
        ArrayList<QuakeEntry> m8 = filter(m7, f1); 
        
        /*
        // This location is denver,Colorado
        Location city =  new Location(39.7392, -104.9903);
        Filter f = new DistanceFilter(city,1000*1000); 
        ArrayList<QuakeEntry> m7 = filter(list, f); 
        Filter f1 = new PhraseFilter("end","a"); 
        ArrayList<QuakeEntry> m8 = filter(m7, f1); 
        */
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        }
        System.out.println("Found "+m8.size()+" quakes that match that criteria"); 
    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Filter f1 = new MagnitudeFilter(1.0,4.0);
        Filter f2 = new DepthFilter(-180000.0,-30000.0);
        Filter f3 = new PhraseFilter("any","o");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(f1);  
        maf.addFilter(f2);  
        maf.addFilter(f3);
        ArrayList<QuakeEntry> m = filter(list, maf);   
        for (QuakeEntry qe: m) { 
            System.out.println(qe);
        }
        System.out.println("Found "+m.size()+" quakes that match that criteria"); 
        System.out.println("Filter used are :"+maf.getName());

    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        // This location is Billud, Denmark
        Location city =  new Location(55.7308, 9.1153);
        Filter f1 = new DistanceFilter(city,3000000); 
        Filter f2 = new MagnitudeFilter(0.0,5.0);
        Filter f3 = new PhraseFilter("any","e");
        // Filter f4 = new DepthFilter(-100000.0,-10000.0);
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(f1);  
        maf.addFilter(f2);  
        maf.addFilter(f3);
        ArrayList<QuakeEntry> m = filter(list, maf);   
        for (QuakeEntry qe: m) { 
            System.out.println(qe);
        }
        System.out.println("Found "+m.size()+" quakes that match that criteria");  
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
