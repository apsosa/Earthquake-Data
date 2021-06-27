import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth,double maxDepth){
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe :quakeData ) {
        	if (qe.getDepth() > minDepth && qe.getDepth()<maxDepth) {
        		answer.add(qe);
        	}
        }
        return answer;
    }
    public void quakesOfDepth(){
    	EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> answer = filterByDepth(list,-4000.0,-2000.0);
        System.out.println("Find quakes with depth between "+"-4000.0"+" and "+"-2000.0");
        for (QuakeEntry qe : answer) {
        	System.out.println(qe.toString());
        }
        System.out.println("Found "+answer.size()+" quakes that match that criteria");
        
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    			 String where,String phrase){
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        int index = 0;
        for (QuakeEntry qe :quakeData ) {
        	String currTitle = qe.getInfo();
        	if (where.equals("any")){
        		if (currTitle.contains(phrase)) {
        			answer.add(qe);
        		}
        	}else{
        		if (where.equals("end")) {
        			index = currTitle.length() - phrase.length();
        		}
        		String currPhrase = currTitle.substring(index,index+phrase.length());
        		if (currPhrase.equals(phrase)) {
        			answer.add(qe);
        		}
        	}       	
        }
        return answer;
    }
    public void quakesByPhrase(){
    	EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> answer = filterByPhrase(list,"any","Can");
        // System.out.println("Find quakes with depth between "+"-10000.0"+" and "+"-5000.0");
        for (QuakeEntry qe : answer) {
        	System.out.println(qe.toString());
        }
        System.out.println("Found "+answer.size()+" quakes that match that criteria");
        
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qa :quakeData ) {
        	if (qa.getMagnitude() > magMin) {
        		answer.add(qa);
        	}
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {

        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe :quakeData ) {        	
        	if ( qe.getLocation().distanceTo(from) < distMax) {
        		// System.out.println(qe.toString());
        		answer.add(qe);
        	}
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list,5.0);
        System.out.println("filterByMagnitude greater than 5.0 "+listBig.size()+" quakes");
        dumpCSV(listBig);


    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        String theCity = "Durham, NC";
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        float distMax = 1000*1000;
        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list,distMax,city);
        System.out.println("read data for "+answer.size()+" quakes");
        for (QuakeEntry qe : answer) {
        	float currDist = qe.getLocation().distanceTo(city);
        	System.out.println("Distance from "+theCity+" is:"+currDist+ qe.getInfo());
        	
        }

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
