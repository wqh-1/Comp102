// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 9
 * Name:
 * Username: 
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;
import java.io.*;

/**
 * AirPollutionAnalyser analyses hourly data about PM2.5 concentration of five
 * cities in China in October, November and December in 2015.
 * Each line of the file "pollution.txt" has information about the pollution
 * level and weather in a city at a particular hour and date.
 * Data is from https://archive.ics.uci.edu/ml/datasets.php.
 *
 *
 *
 * Core: two methods:
 *   loadData
 *      Loads the data from a file into a field containing an ArrayList of
 *      AirPollution objects.
 *      Hint: read all the lines from the file, and then take each line
 *            apart into the values to pass to the AirPollution constructor.
 *   findHazardousLevels
 *      Prints out all the records in the ArrayList that have a
 *      PM2.5 concentration 300 and over.
 *      Hint: see the methods in the AirPollution class, especially getPM and toString
 *
 * Completion: one method:
 *   findContrastingCities
 *      Compares each record in the list with every other record and finds
 *      every pair of cities that having a difference of PM2.5 concentration
 *      larger than 400, at the same hour on the same day.
 *      For each pair, it should print cityA, cityB, hour, date, difference
 *
 * Challenge: two methods
 *   findDailyAverage(String city)
 *      -Prints the average daily PM2.5 value for each day for the given city.
 *      -Finds the longest sequence of days there the average PM2.5 is always
 *       above 200 ("Very unhealthy").
 *      Hint: Use an array where the index corresponds to the day of the year.
 *   plotPollutionLevels
 *      Makes a line plot for the daily average PM2.5 concentration data of
 *      the five cities in the same figure. You may choose different colours
 *      to represent different cities.
 *      Hint: Make the findDailyAverage(String city) method return the array
 *      for a given city.
 */

public class AirPollutionAnalyser {

    private ArrayList<AirPollution> pollutions = new ArrayList<AirPollution>();

    /**
     * CORE
     *
     * Load data from the data file into the pollutions field:
     * clear the pollutions field.
     * Read lines from file
     * For each line, use Scanner to break up each line and make an AirPollution
     *  adding it to the pollutions field.
     */
    public void loadData() {
        String filename = "pollution.txt";
        /* # YOUR CODE HERE */
        //ArrayList<String>lines = readAllLines(filename);
        this.pollutions.clear();
        try{
            Scanner scan = new Scanner(new File(filename));
            //for(String p: lines){
            //Scanner scan = new Scanner(p);
            while(scan.hasNext()){

                String city = scan.next();
                String date = scan.next();
                int hour = scan.nextInt();
                double concentration = scan.nextDouble();
                double dew =  scan.nextDouble();
                double humidity = scan.nextDouble();
                double pressure = scan.nextDouble();
                double temp =  scan.nextDouble();
                String windDirection = scan.next();
                double windSpeed = scan.nextDouble();
                double hourPrecipitation = scan.nextDouble();
                double CumulativePrecipitation = scan.nextDouble();

                //This also works
                /*
                AirPollution AP = new AirPollution(city,date,hour,concentration,dew,humidity,pressure,temp,windDirection,windSpeed,hourPrecipitation,CumulativePrecipitation);
                this.pollutions.add(AP);
                 */
                this.pollutions.add(new AirPollution(city,date,hour,concentration,dew,humidity,pressure,temp,windDirection,windSpeed,hourPrecipitation,CumulativePrecipitation));
            }
            scan.close();
            //}
        }catch(IOException e){UI.println("File failure" + e);}
        UI.printf("Loaded %d pollution information into list\n", this.pollutions.size());
        UI.println("----------------------------");
    }

    /**
     * Utility method: Read each line in a file into a list of Strings Returns the
     * list
     */
    public ArrayList<String> readAllLines(String fname) {
        ArrayList<String> ans = new ArrayList<String>();
        try {
            Scanner scan = new Scanner(new File(fname));
            while (scan.hasNext()) {
                ans.add(scan.nextLine());
            }
            scan.close();
            return ans;
        } catch (IOException e) {
            UI.println("File reading failed");
        }
        return null;
    }

    /**
     * CORE
     *
     * Prints out all the records in the ArrayList that have a PM2.5 concentration
     * 300 and over
     */
    public void findHazardousLevels() {
        UI.println("PM2.5 Concentration 300 and above");
        /* # YOUR CODE HERE */
        for(AirPollution AP:pollutions){
            if(AP.getPM()>=300){
                UI.println(AP.toString());
            }
        }
        UI.println("------------------------");
    }

    /**
     * COMPLETION
     * 
     * Finds every pair of cities that have at the same hour on the same day 
     * a difference of PM2.5 concentration larger than 400.
     * You need to compare each record in the list with every other record
     * For each pair, it should print
     * cityA, cityB, hour, date, difference
     */
    public void findContrastingCities() {
        UI.println("Pair of cities having a difference of PM2.5 larger than 400");
        /* # YOUR CODE HERE */
        for(int i = 0;i < pollutions.size();i++){
            AirPollution ap = pollutions.get(i);
            for(int k = i + 1; k < pollutions.size() - 1; k++){
                AirPollution ap2 = pollutions.get(k);
                
                double diff = ap.diffPM(ap2);
                if(diff > 400 && ap2.getDate().equals(ap.getDate()) && ap2.getTime() == ap.getTime()){
                    UI.printf("%s and %s at %s on %s, diff=%.2f\n", ap.getCity(), ap2.getCity(), ap.getTime(), ap.getDate(), diff);
                }
            }
        }
        UI.println("----------------------------");
    }

    // ------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI() {
        UI.initialise();
        UI.addButton("Load", this::loadData);
        UI.addButton("Hazardous Levels", this::findHazardousLevels);
        UI.addButton("Contrasting Cities", this::findContrastingCities);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0); // text pane only
    }

    public static void main(String[] arguments) {
        AirPollutionAnalyser obj = new AirPollutionAnalyser();
        obj.setupGUI();

    }

}
