// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 4
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/**
 * SalesVisualiser
 * Reads an order from a file and produces a bar graph of the data
 */

public class SalesVisualiser{

    // Constants for plotting the graph
    public static final double GRAPH_LEFT = 50;
    public static final double GRAPH_RIGHT = 650;
    public static final double GRAPH_BASE = 400;
    public static final double MONTH_WIDTH = (GRAPH_RIGHT-GRAPH_LEFT)/12;  // the width for each set of three bars
    public static final double BAR_WIDTH = 12;    // the width of each bar

    /**
     * Asks the user for the name of a file containing the details of sales
     *  reported by dealers over the last three years, and then produces a
     *  bar graph of the data, showing the sales for each month, with different
     *  color bars for each year.
     * Each line of a sales data file contains
     *    a year, a month, and a series of sales from the dealers
     *    There may be a different number of sales on each line.
     * For example:
     *   2016 01 21 15 32 12 2 7
     *   2016 02 5 18 12
     *   :
     *   2017 01 16 3 2 4 1 8 4 13
     *   2017 Feb 41 3
     *   :
     * There is no guarantee that the lines are in order of date
     * The total sales in any month will never be over 200.
     *
     * The method should draw a bar graph with 12 sets of bars, one set for each month
     * Each set should have
     *  a red bar for the 2016 data,
     *  a green bar for the 2017 data, and
     *  a blue bar for the 2018 data
     * The height of the bar should be the total number of sales in that month
     * Hints:
     *   Use the readAllLines method from the lab exercise and a Scanner for each line
     *   After getting the year and month from the Scanner, you will need a loop to add
     *       up all the sales on each line.
     *   Look carefully at the example file and the example output.
     */

    //public static void main(String [] args){ //intellij
    public void graphSales() {
        /*# YOUR CODE HERE */
        String fname = UIFileChooser.open("Please choose the file: ");
        ArrayList<String> Sales = readAllTokens(fname); //adding everything in the file to the sales ArrayList

        for(int i = 0; i < 12; i++){ //12 months
            UI.drawLine(GRAPH_LEFT +  MONTH_WIDTH * i, GRAPH_BASE,GRAPH_LEFT +  MONTH_WIDTH * i,GRAPH_BASE+10); //puts points all the graph to show each month
            UI.drawString(" "+(i+1),GRAPH_LEFT +  MONTH_WIDTH * i + MONTH_WIDTH/2 - 13, GRAPH_BASE+20); // adding values to the x axis
        }

        for(String i : Sales){ //going through the sales arraylist
            Scanner scan = new Scanner(i);
            int y = scan.nextInt(); // scan year
            int m = scan.nextInt(); // scan month
            int t = 0;
            while(scan.hasNext()){
                t = t + scan.nextInt(); // scans each total
            }
            if(y == 2016){
                UI.setColor(Color.red); // if the year is 2016 then it set the color of the bar red for each month
            }
            else if(y == 2017){
                UI.setColor(Color.green);   // if the year is 2017 then it set the color of the bar green for each month
            }
            else{
                UI.setColor(Color.blue);    // if the year is 2018 then it set the color of the bar blue for each month
            }
            double left = GRAPH_LEFT + (m-1) * MONTH_WIDTH + (y-2016) * (BAR_WIDTH);
            UI.fillRect(left,GRAPH_BASE - t * 2,BAR_WIDTH,t * 2);
        }
        UI.setColor(Color.black);
        UI.drawLine(GRAPH_LEFT, GRAPH_BASE, GRAPH_RIGHT, GRAPH_BASE); // x axis
        UI.drawString("Month: ", GRAPH_LEFT - MONTH_WIDTH,GRAPH_BASE+20); //add a month label to the graph

    }

    /**
     * Utility method:
     * Read all the tokens in a file into a list of tokens.
     * Returns the list
     */
    //public static ArrayList<String> readAllTokens(String fname){ //added from Lab Exercise
    public ArrayList<String> readAllTokens(String fname){
        ArrayList<String> ans = new ArrayList<String>();
        /*# YOUR CODE HERE */
        try {
            Scanner scan = new Scanner(new File(fname));
            while (scan.hasNext()){
                if (scan.hasNext()){
                    ans.add(scan.nextLine());
                }
                else {
                    scan.next();  // throw away any non-numbers
                }
            }
            scan.close();
        }
        catch(IOException e){UI.println("File reading failed");}
        return ans;
    }

}
