// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 8
 * Name:
 * Username: 
 * ID: 
 */

import ecs100.*;
import java.io.*;
import java.util.*;
import java.awt.Color;

/**
 * This program reads waveform data from a file and displays it
 * The program will also do some analysis on the data
 * The user can also edit the data - deleting, duplicating, and adding 
 *
 * The methods you are to complete all focus on the ArrayList of data.
 * It is related to assignment 3 which analysed pollution levels
 *
 * CORE
 *  read:               reads numbers into an ArrayList.
 *  display:            displays the waveform.
 *  showSpread:         displays the maximum and minimum values of the waveform.
 *  increaseRegion:     increases all the values in the selected region by 10%.
 *  decreaseRegion:     decreases all the values in the selected region by 10%.
 *
 * COMPLETION
 *  highlightPeaks:     puts small green circles around all the peaks in the waveform.
 *  displayDistortion:  shows in red the distorted part of the signal.
 *  deleteRegion:       deletes the selected region of the waveform

 * CHALLENGE
 *  duplicateRegion:    duplicates the selected region of the waveform
 *  displayEnvelope:    displays the envelope.
 *  save:               saves the current waveform values into a file.
 *  ....                allows more editing
 *                       
 */

public class WaveformAnalyser{

    // Constants: 
    public static final int ZERO_LINE = 300;    // dimensions of the graph for the display method
    public static final int GRAPH_LEFT = 10;
    public static final int GRAPH_WIDTH = 800;
    public static final int GRAPH_RIGHT = GRAPH_LEFT + GRAPH_WIDTH;

    public static final double THRESHOLD = 200;  // threshold for the distortion level
    public static final int CIRCLE_SIZE = 10;    // size of the circles for the highlightPeaks method

    // Fields 
    private ArrayList<Double> waveform;   // the field to hold the ArrayList of values

    private int regionStart = 0; // The index of the first value in the selected region
    private int regionEnd;       // The index one past the last value in the selected region

    /**
     * Set up the user interface
     */
    public void setupGUI(){
        UI.setMouseListener(this::doMouse);   
        //core
        UI.addButton("Read Data", this::read);
        UI.addButton("Show Spread", this::showSpread);
        UI.addButton("Increase region", this::increaseRegion);
        UI.addButton("Decrease region", this::decreaseRegion);
        //completion
        UI.addButton("Peaks", this::highlightPeaks);
        UI.addButton("Distortion", this::displayDistortion);
        UI.addButton("Delete", this::deleteRegion);
        //challenge
        UI.addButton("Duplicate", this::duplicateRegion);
        UI.addButton("Envelope", this::displayEnvelope);
        UI.addButton("Save", this::save);

        UI.addButton("Display", this::display);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(900, 650);
    }

    /**
     * [CORE]
     * Clears the panes, 
     * Asks user for a waveform file (eg waveform1.txt) 
     * Creates an ArrayList stored in the waveform field, then
     * Reads data from the file into the ArrayList
     * calls display.
     */
    public void read(){
        UI.clearPanes();
        String fname = UIFileChooser.open();
        /*# YOUR CODE HERE */
        try{
            Scanner scan = new Scanner(new File(fname)); //scanning the waveform file
            this.waveform = new ArrayList<Double>(); //creating a new arraylist using the waveform field
            while(scan.hasNext()){
                this.waveform.add(scan.nextDouble());
                //UI.println(this.waveform);
            }
            scan.close();
        }catch(IOException e){UI.println("File failure" + e);}
        UI.printMessage("Read " + this.waveform.size() + " data points from " + fname);

        this.regionStart = 0;
        this.regionEnd = this.waveform.size();
        this.display();
    }

    /**
     * [CORE]
     * Displays the waveform as a line graph,
     * Draw the axes
     * Plots a line graph of all the points with a blue line between
     *  each pair of adjacent points
     * The n'th value in waveform is at
     *    x-position is GRAPH_LEFT + n
     *    y-position is ZERO_LINE - the value
     * Don't worry if the data goes past the end the window
     */
    public void display(){
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        UI.clearGraphics();

        // draw x axis (showing where the value 0 will be)
        UI.setColor(Color.black);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_RIGHT, ZERO_LINE); 

        // plot points: blue line between each pair of values
        /*# YOUR CODE HERE */
        for(int i = 0; i < this.waveform.size() -1; i++){//out of bound error, without the -1 it try to read the file after the last digit.
            //double n = (GRAPH_RIGHT - GRAPH_LEFT)/this.waveform.size();
            UI.setColor(Color.blue);
            UI.drawLine(GRAPH_LEFT+i,ZERO_LINE-this.waveform.get(i),GRAPH_LEFT+(i+1),ZERO_LINE-this.waveform.get(i+1));
            UI.print(waveform.get(i));
        }
    }

    /**
     * Displays the selected region by a red line on the axis
     */
    public void displayRegion(){
        UI.setColor(Color.red);
        UI.setLineWidth(3);
        UI.drawLine(GRAPH_LEFT+this.regionStart, ZERO_LINE, GRAPH_LEFT+this.regionEnd-1, ZERO_LINE);
        UI.setLineWidth(1);
    }

    /**
     * [CORE]
     * The spread is the difference between the maximum and minimum values of the waveform.
     * Finds the maximum and minimum values of the waveform, then
     * Displays the spread by drawing two horizontal lines on top of the waveform: 
     *   one green line for the maximum value, and
     *   one red line for the minimum value.
     */
    public void showSpread() {
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        this.display();
        /*# YOUR CODE HERE */
        double min = Double.MAX_VALUE; //or 0
        double max = Double.MIN_VALUE; // or 0
        for(Double num:waveform){
            if(num > max){
                max = num;//finding the max value and make that equal to num
            }
        }
        for(Double num:waveform){
            if(num < min){
                min = num;//finding the min value;
            }
        }
        UI.setColor(Color.green);
        UI.drawLine(GRAPH_LEFT,ZERO_LINE-max,GRAPH_RIGHT,ZERO_LINE - max);//max green line

        UI.setColor(Color.red);
        UI.drawLine(GRAPH_LEFT,ZERO_LINE-min,GRAPH_RIGHT,ZERO_LINE - min);//min red line

    }

    /**
     * [CORE]
     * Increases the values in the selected region of the waveform by 10%.
     * (The selected region is initially the whole waveform, but the user can drag the
     *  mouse over part of the graph to select a smaller region).
     * The selected region goes from the index in the regionStart field to the index
     *  in the regionEnd field.
     */
    public void increaseRegion() {
        if (this.waveform == null){ //there is no waveform to process
            UI.println("No waveform");
            return;
        }
        /*# YOUR CODE HERE */
        for(int i = this.regionStart; i < this.regionEnd; i++){
            double num = this.waveform.get(i)*1.1;//increase by 10%
            waveform.set(i,num);
            //int and double,
        }
        this.display();
    }

    /**
     * [CORE]
     * Decreases the values in the selected region of the waveform by 10%.
     * (The selected region is initially the whole waveform, but the user can drag the
     *  mouse over part of the graph to select a smaller region).
     * The selected region goes from the index in the regionStart field to the index
     *  in the regionEnd field.
     */
    public void decreaseRegion() {
        if (this.waveform == null){ //there is no waveform to process
            UI.println("No waveform");
            return;
        }
        /*# YOUR CODE HERE */
        for(int i = regionStart; i < this.regionEnd;i++){
            double num = this.waveform.get(i)*0.9;//decrease in 10%
            waveform.set(i,num);

        }
        this.display();
    }

    /**
     * [COMPLETION]  [Fancy version of display]
     * Display the waveform as a line graph. 
     * Draw a line between each pair of adjacent points
     *   * If neither of the points is distorted, the line is BLUE
     *   * If either of the two end points is distorted, the line is RED
     * Draw the horizontal lines representing the value zero and thresholds values.
     * Uses THRESHOLD to determine distorted values.
     * Uses GRAPH_LEFT and ZERO_LINE for the dimensions and positions of the graph.
     * [Hint] You may find Math.abs(int a) useful for this method.
     * You may assume that all the values are between -250 and +250.
     */
    public void displayDistortion() {
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        UI.clearGraphics();

        // draw zero axis
        UI.setColor(Color.black);
        UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.waveform.size() , ZERO_LINE); 

        // draw thresholds
        /*# YOUR CODE HERE */
        this.display();
        for(Double num: this.waveform){
            for(int i = 0; i < this.waveform.size()-1;i++){
                if((Math.abs(this.waveform.get(i)) > THRESHOLD)){
                    UI.setColor(Color.red);
                    UI.drawLine(GRAPH_LEFT+i,ZERO_LINE-this.waveform.get(i),GRAPH_LEFT+(i+1),ZERO_LINE-this.waveform.get(i+1));
                }
                else {
                    //UI.setColor(Color.blue);    
                }
            }
        }
        //draw Thresholds
        UI.setColor(Color.green);
        UI.drawLine(GRAPH_LEFT,ZERO_LINE+THRESHOLD,GRAPH_RIGHT,ZERO_LINE+THRESHOLD);
        UI.drawLine(GRAPH_LEFT,ZERO_LINE-THRESHOLD,GRAPH_RIGHT,ZERO_LINE - THRESHOLD);

        this.displayRegion();
    }

    /**
     * [COMPLETION]
     * Plots the peaks with small green circles. 
     *    A peak is defined as a value that is greater than or equal to both its
     *    neighbouring values.
     * Note the size of the circle is in the constant CIRCLE_SIZE
     */
    public void highlightPeaks() {
        this.display();     //use display if displayDistortion isn't complete
        /*# YOUR CODE HERE */
        for(int i = 1; i < this.waveform.size()-1;i++){
            double peak = ZERO_LINE-waveform.get(i);
            if (peak < ZERO_LINE-waveform.get(i-1) && peak < ZERO_LINE-waveform.get(i+1)){
                UI.setColor(Color.green);
                UI.drawOval(GRAPH_LEFT + i-CIRCLE_SIZE/2,peak-CIRCLE_SIZE/2,CIRCLE_SIZE,CIRCLE_SIZE);
            }
        }
    }

    /**
     * [COMPLETION]
     * Removes the selected region from the waveform
     * selection should be reset to be the whole waveform
     * redisplays the waveform
     */
    public void deleteRegion(){
        /*# YOUR CODE HERE */
        for(int i = this.regionEnd-1; i > regionStart; i--){
            this.waveform.remove(i);
        }
        this.regionStart = 0;
        this.regionEnd = this.waveform.size();
        this.display();
    }

    /**
     * [CHALLENGE]
     * If there is a selected region, then add a copy of that section to the waveform,
     * immediately following the selected region
     * selection should be reset to be the whole waveform
     * redisplay the waveform
     */
    public void duplicateRegion(){
        /*# YOUR CODE HERE */
        for(double i=regionStart;i<=regionEnd;i++){
            this.waveform.add(i);

        }
        //not fnished
        this.display();
    }

    /**
     * [CHALLENGE]
     * Displays the envelope (upper and lower) with GREEN lines connecting all the peaks.
     *    A peak is defined as a point that is greater than or equal to *both* neighbouring points.
     */
    public void displayEnvelope(){
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        this.display();  // display the waveform,

        /*# YOUR CODE HERE */
        UI.setColor(Color.green);
        int k = this.waveform.size()-1;
        double firstX = 0;
        double firstY = 0;

        //starting with the first point 
        for (int i=1; i<this.waveform.size()-1; i++){
            if (this.waveform.get(i)>=this.waveform.get(i-1) && this.waveform.get(i)>=this.waveform.get(i+1)) {
                firstX = GRAPH_LEFT + i;
                firstY = ZERO_LINE - this.waveform.get(i);
                k = i;
                break;
            }
        }

        // Find the other peaks
        for (int i = k; i<this.waveform.size()-1; i++){
            if (this.waveform.get(i)>=this.waveform.get(i-1) && this.waveform.get(i)>=this.waveform.get(i+1)) {

                double endX = GRAPH_LEFT + i; // Found a second peak, add it to the first peak to make the green lines
                double endY = ZERO_LINE - this.waveform.get(i);
                UI.drawLine(firstX,firstY,endX,endY);   

                firstX = endX;// Make this second point the first point for each line in the graph
                firstY = endY;
            }
        }

    }

    /**
     * [CHALLENGE]
     * Saves the current waveform values into a file
     */
    public void save(){
        /*# YOUR CODE HERE */
        try{
            PrintStream outputFile = new PrintStream(new File(UIFileChooser.save()));
            for(double num:this.waveform){
                outputFile.println(num);
            }
            outputFile.close();
            this.display();
        }catch(IOException e){UI.println("File error"+e);}

    }

    /**
     * Lets user select a region of the waveform with the mouse
     * and deletes that section of the waveform.
     */
    public void doMouse(String action, double x, double y){
        int index = (int)x-GRAPH_LEFT;
        if (action.equals("pressed")){
            this.regionStart = Math.max(index, 0);
        }
        else if (action.equals("released")){
            if (index < this.regionStart){
                this.regionEnd = this.regionStart;
                this.regionStart = Math.max(index,this.waveform.size());
            }
            else {
                this.regionEnd = Math.min(index,this.waveform.size());
            }
            this.display();
        }

    }

    public static void main(String[] args){
        WaveformAnalyser wav = new WaveformAnalyser();
        wav.setupGUI();   
    }

}
