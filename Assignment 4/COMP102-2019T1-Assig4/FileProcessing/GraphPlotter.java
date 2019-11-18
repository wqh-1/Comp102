// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 4
 * Name:
 * Username: 
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;

public class GraphPlotter {

    // Constants for plotting the graph
    public static final double GRAPH_LEFT = 50;
    public static final double GRAPH_RIGHT = 550;
    public static final double GRAPH_BASE = 400;
    /**
     * Plot a graph of a sequence of numbers read from a file using +'s for each point.
     * The origin of the graph should be at (GRAPH_LEFT, GRAPH_BASE)
     * The method should ask the user for the name of a file
     *  then read all the numbers from the file into an ArrayList.
     * It should then plot the numbers:
     *  - Draw two axes
     *  - Plot each number as a small +.
     *  - The x value of the first point should be at GRAPH_LEFT, and
     *    the last point should be at GRAPH_RIGHT.
     *  - The points should be separated by (GRAPH_RIGHT - GRAPH_LEFT)/(number of points - 1)
     * Hints:
     *   use the readAllNumbers method from the lab exercise
     *   look at the model answers for the Temperature Analyser problem from assignment 3.
     */
    public void plotGraph() {
        /*# YOUR CODE HERE */
        ArrayList<Double>numbers = new ArrayList<Double>(); //creates an new arraylist.
        double space;
        try{
            Scanner scan = new Scanner(new File(UIFileChooser.open())); //letting the user choose the file
            while(scan.hasNext()){
                double num = scan.nextDouble();
                numbers.add(num); //adding each double to the arraylist
            }
            space = (GRAPH_RIGHT - GRAPH_LEFT)/(numbers.size()-1); //points separated by this
            for(int i = 0; i < numbers.size(); i++){
                UI.drawLine(GRAPH_LEFT,GRAPH_BASE,GRAPH_RIGHT,GRAPH_BASE); // x axis
                UI.drawLine(GRAPH_LEFT,GRAPH_BASE,GRAPH_LEFT,GRAPH_LEFT); // y axis
                UI.drawString("+",GRAPH_LEFT + space * (i),GRAPH_BASE - numbers.get(i)); //for every individual point in the list, plot the "+" on the graph
            }
            scan.close();
        } catch (IOException e){UI.println("File failure: " + e);
        }
    }
}

    


