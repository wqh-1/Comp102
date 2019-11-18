// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 9
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/**
 * EarthquakeAnalyser
 * Analyses data about a collection of 4335 NZ earthquakes from May 2016 to May 2017
 * Each line of the file "earthquake-data.txt" has a description of one earthquake:
 *   ID time longitude  latitude magnitude depth region
 * Data is from http://quakesearch.geonet.org.nz/
 *  Note bigearthquake-data.txt has just the 421 earthquakes of magnitude 4.0 and above
 *   which may be useful for testing, since it is not as big as the full file.
 * 
 * Core:  two methods:
 *   loadData
 *      Loads the data from a file into a field containing an
 *      ArrayList of Earthquake objects.
 *      Hint : to make an Earthquake object, read all the lines from the file
 *              and then take each line apart into the values to pass to the
 *              Earthquake constructor
 *   findBigOnes
 *      Prints out all the earthquakes in the ArrayList that have a magnitude 5.5 and over.
 *      Hint: see the methods in the Earthquake class, especially getMagnitude and toString
 *   
 * Completion: one method:
 *   findPairs
 *  Compares each Earthquake in the list with every other Earthquake
 *      and finds every pair of "close" earthquakes - earthquakes that
 *        - are within 1km of each other, and
 *        - have depths within 1km of each other, and
 *        - are separated by at least 24 hours days 
 *      For each pair, prints
 *        - their ID's,
 *        - the distance between them
 *        - the depth difference
 *        - the number of days between them.

 * Challenge: two methods
 *  findFollowOns;
 *      Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6.0
 *      For each such earthquake on this list
 *       - finds a list of all the "follow-on" earthquakes:
 *         later earthquakes within a distance of 10km and depth difference <= 10km.
 *       - If there are at least two follow-on earthquakes, then it prints out
 *          the full details of the big earthquake followed by
 *          ID, magnitude and days since the big one for each follow-on earthquake
 *  plotEarthquakes
 *      Given a time range, plots earthquake as circles by position
 *      You may choose to represent earthquake with different colours or sizes
 *      depending on their magnitude or depth.

 * The file "example-output.txt" has sample output for the "bigearthquake-data.txt"
 *   file, which only contains earthquakes with magnitude 4 and above.
 */

public class EarthquakeAnalyser{

    private ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

    /*
     * Load data from the data file into the earthquakes field:
     * clear the earthquakes field.
     * Read lines from file
     * For each line, use Scanner to break up each line and make an Earthquake
     *  adding it to the earthquakes field.
     */
    public void loadData(){
        String filename = UIFileChooser.open("Data File");
        /*# YOUR CODE HERE */
       this.earthquakes.clear();
       try {
            Scanner scan = new Scanner(new File(filename));
            while(scan.hasNext()){
                String ID = scan.next();
                String Date = scan.next();
                double region = scan.nextDouble();
                double longitude = scan.nextDouble();
                double latitude = scan.nextDouble();
                double depth = scan.nextDouble();
                String magnitude = scan.next();
                Earthquake earth = new Earthquake(ID,Date,region,longitude,latitude,depth,magnitude);
                earthquakes.add(earth);
            }
            scan.close();
            //UI.println(filename);
       }catch(IOException e){UI.println("File reading failed"+e);}
       UI.printf("Loaded %d earthquakes into list\n", this.earthquakes.size());
       UI.println("----------------------------");
    }

    /**
     * Utility method: 
     * Read each line in a file into a list of Strings
     * Returns the list
     */
    public ArrayList<String> readAllLines(String fname){
        ArrayList<String> ans = new ArrayList<String>();
        try {
            Scanner scan = new Scanner(new File(fname));
            while (scan.hasNext()){
                ans.add(scan.nextLine());
            }
            scan.close();
            return ans;
        }
        catch(IOException e){UI.println("File reading failed");}
        return null;
    }

    /**
     * Print details of all earthquakes with a magnitude of 5.5 or more
     */
    public void findBigOnes(){
        UI.println("Earthquakes 5.5 and above");
        /*# YOUR CODE HERE */
        // if earthquake magnitude is bigger than 5.5 then print them out.
        for(Earthquake earth:earthquakes){
            if(earth.getMagnitude()>=5.5){
                UI.println(earth.toString());
            }
        }
        UI.println("------------------------");
    }

    /**
     * Print all pairs of earthquakes within 1km of each other and within 1km depth from each other
     * and separated by at least 1 day;
     */
    public void findPairs(){
        UI.println("Close earthquakes");
        /*# YOUR CODE HERE */
         for(int i = 0;i<earthquakes.size();i++){
            Earthquake depth1 = earthquakes.get(i);
            for(int k=i+1; k<earthquakes.size()-1;k++){
                Earthquake depth2 = earthquakes.get(k);
                double dist = depth1.distanceTo(depth2);
                double depthDif = Math.abs(depth1.getDepth()-depth2.getDepth());
                double days = depth1.timeBetween(depth2)/24/60;
                if(dist <= 1 && depthDif <= 1 && days >= 1){
                    UI.printf("%s and %s: dist=%.2f depth diff=%.2f, days apart=%.0f\n", depth1.getID(), depth2.getID(), dist, depthDif, days);
                }
                
            }
        }
        UI.println("----------------------------");
    }

    /**
     * CHALLENGE 1
     * Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6 
     * For each earthquake on this list
     * - finds a list of all the "follow-on" earthquakes:
     *   later earthquakes within a distance of 10km and depth difference <= 10km.
     * - If there are at least two follow-on earthquakes, then it prints out
     *     the full details of the big earthquake followed by
     *    ID, magnitude and days since the big one for each follow-on earthquake
     */

    public void findFollowOns(){
        UI.println("Big earthquakes and their follow-on earthquakes");
        /*# YOUR CODE HERE */

        UI.println("-------------------------------------");
    }

    /**
     * CHALLENGE 2
     * Given a time range, plots earthquake as circles.
     * You may choose to represent earthquake 
     * with different colours or sizes depending
     * on their magnitude or depth.
     */
    public void plotEarthquakes(){
        UI.clearPanes();
        /*# YOUR CODE HERE */

    }

    public static void main(String[] arguments){
        EarthquakeAnalyser obj = new EarthquakeAnalyser();
        UI.initialise();
        UI.addButton("Load", obj::loadData);
        UI.addButton("Big ones",  obj::findBigOnes);
        UI.addButton("Pairs", obj::findPairs);
        UI.addButton("Follow-ons", obj::findFollowOns);
        UI.addButton("Plot", obj::plotEarthquakes);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);  //text pane only 
    }   

}
