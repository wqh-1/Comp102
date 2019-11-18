// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 3
 * Name: 
 * Username: 
 * ID: 
 */

import ecs100.*;
import java.awt.Color;
import java.util.ArrayList;

/**
 * The program contains two methods for analysing hourly Nitrogen Dioxide air pollution levels over
 * the course of a day. There are several things about the pollution level that
 * a user may be interested in:
 *    The average pollution level during the day.
 *    How the pollution rose and fell.
 *    The maximum and the minimum pollution level at any point during the day.
 *    Any times when the pollution level went over the danger threshold.
 *    Whether the pollution level was over the danger threshold for more than 50% of the day.
 *    The median pollution value of a day.
 *
 *
 */
public class PollutionAnalyser {

    public static final int NO2_DANGER_LEVEL = 120;

    /**
     * analyse() method reads a sequence of levels from the user and calls 6 methods
     * which each do one part of the analysis.
     *
     * [Core]
     *   - printAverage method which computes and prints out the average of the pollution levels.
     *   - printDangerLevels method which prints out all the values where the pollution level is
     *     above the danger threshold (120 ppb).
     * [Completion]
     *   - maximumOfList method which finds and returns the maximum levels in the list.
     *   - minimumOfList method which finds and returns the minimum levels in the list.
     *   - reportDanger method which calculates if the pollution level was above the danger
     *     threshold continuously for more than half (50%) of the readings, and what fraction
     *     of the readings were above that level.
     * [Challenge]
     *   - medianOfList method that will compute and return the median value in the list.
     */
    public void analyse() {
        UI.clearText();
        // Reads a sequence of levels from the user,
        ArrayList<Double> levels = UI.askNumbers("Enter levels, end with 'done': ");
        if (levels.size() != 0) {
            this.printAverage(levels);
            this.printDangerLevels(levels);
            this.plotLevels(levels);
            this.reportDanger(levels);
            this.MedianList(levels); //returns the median
            UI.printf("Maximum level was:  %f\n", this.maximumOfList(levels));
            UI.printf("Minimum level was:  %f\n", this.minimumOfList(levels));
        }
        else {
            UI.println("No readings");
        }
    }

    /** Print the average of the pollution levels
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the sum, which
     *     needs to be initialised to an appropriate value.
     *  CORE
     */
    public void printAverage(ArrayList<Double> levels) {
        //UI.println("method printAverage() is not implemented yet"); // remove when you have implemented your method
        /* # YOUR CODE HERE */
        double sum = 0;
        for(Double avg : levels){
            sum = sum + avg;
        }
        double average = (sum/levels.size()); //sum of all the numbers in the arraylist and divided by the size of the arraylsit
        UI.println("Average level is: " + average); 
    }

    /** Find and return all the values where the pollution level is above the danger threshold (120 ppb).
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the counts of danger levels, which
     *     needs to be initialised to an appropriate value.
     *  CORE
     */
    public void printDangerLevels(ArrayList<Double> levels) {
        /* # YOUR CODE HERE */
        double danger = 0;
        int count = 0;
        for(Double num : levels){
            if(num > NO2_DANGER_LEVEL){
                danger = num;
                UI.println(danger + " is dangerous!");//prints out every value that was above 120
                count++;
            }
        }
        UI.println("Level was dangerous " + count + " times"); //prints out the amount of times the pollution level was over 120
    }

    /**
     * Find and calculates if the pollution level was above the danger threshold
     * continuously for more than half (50%) of the readings, and what fraction of
     * the readings were above that level.
     *
     * COMPLETION
     */
    public void reportDanger(ArrayList<Double> levels) {
        /* # YOUR CODE HERE */
        double count = 0;
        double continuousDanger = 0;
        double maxDanger = 0;

        for(Double level : levels){
            count++;
            if(level > NO2_DANGER_LEVEL){
                continuousDanger++;
                if(continuousDanger > maxDanger){
                    maxDanger = continuousDanger;

                }
            }else{
                continuousDanger = 0;
            }
            if(maxDanger >= count/2){
                UI.printf("Level was continuously dangerous for %d readings (%.0f%%)\n", maxDanger * 100/ count);
            }
        }
    }

    /**
     * Find and return the maximum level in the list
     *  - There is guaranteed to be at least one level,
     *  - The method will need a variable to keep track of the maximum, which
     *    needs to be initialised to an appropriate value.
     * COMPLETION
     */
    public double maximumOfList(ArrayList<Double> levels) {
        //UI.println("method maximumOfList() is not implemented yet"); // remove when you have implemented your method
        /* # YOUR CODE HERE */
        double max = Double.MIN_VALUE;
        for(Double maximum : levels){
            if(maximum > max){
                max = maximum;
            }
        }
        return max;  //returns the maximum number in the arraylist
    }

    /**
     * Find and return the minimum level in the list
     *  - There is guaranteed to be at least one level,
     *  - The method will need a variable to keep track of the minimum, which
     *    needs to be initialised to an appropriate value.
     * COMPLETION
     */
    public double minimumOfList(ArrayList<Double> levels) {
        //UI.println("method minimumOfList() is not implemented yet"); // remove when you have implemented your method
        /* # YOUR CODE HERE */
        double min = Double.MAX_VALUE;
        for(Double minimum : levels){
            if(minimum < min){
                min = minimum;
            }
        }
        return min;  //returns the minimum number in the arraylist
    }

    /**
     * Plot a bar graph of the sequence of levels,
     * using narrow rectangles whose heights are equal to the level.
     * [Core]
     *   - Plot the bars.
     * [Completion]
     *   - Draws a horizontal line for the x-axis (or baseline) without any labels.
     *   - Any level greater than 400 should be plotted as if it were just 400, putting an
     *         asterisk ("*") above it to show that it has been cut off.
     * [Challenge:]
     *   - The graph should also have labels on the axes, roughly every 50 pixels.
     *   - The graph should also draw pollution levels over the danger threshold in red.
     *   - Scale the y-axis so that the maximum level would reach just to the top of the graph.
     *   - Scale the x-axis so that all the bars fit in the window.
     *   - Compute the standard deviation (sd) and show the mean and 1sd on the plot.
     */
    public void plotLevels(ArrayList<Double> levels) {
        //UI.println("method plotLevels() is not implemented yet");  // remove when you have implemented your method
        int base = 400; // base of the graph
        int left = 50; // left of the graph
        int step = 25; // distance between plotted points
        /* # YOUR CODE HERE */

        UI.drawLine(left,base,1000,base); //x axis
        UI.drawLine(left,base,left,base - 1000); // y axis
        UI.setColor(Color.red);
        UI.drawLine(left,base - 120,1000,base - 120);
        //Note in the demo, shows a gap between each bar, and that code is done below.
        /*
        for(Double num: levels){
        if(num > 400){
        UI.drawRect(left, base - 400, step - 2, 400);
        UI.drawString("*", left+step/3, base - 400); // you may need to change the base to see the "*"
        UI.drawString(String.valueOf(num), left+step/3,base);
        }
        else{
        UI.setColor(Color.black);
        UI.drawRect(left,base-num,step - 2,num);
        UI.drawString(String.valueOf(num), left+step/3,base);
        }
        left = left + step;
        }
         */
        // Code without a gap in between each bar.
        for(Double num: levels){
            if(num > 400){
                UI.setColor(Color.black);
                UI.drawRect(left, base - 400, step, 400);
                UI.drawString("*", left+step/3, base - 400); // you may need to change the base to see the "*"
                UI.drawString(String.valueOf(num), left+step/3,base); //prints out the value of each bar but the text is too big so its hard to see the numbers
            }
            else{
                UI.setColor(Color.black);
                UI.drawRect(left,base-num,step,num);
                UI.drawString(String.valueOf(num), left+step/3,base);
            }
            left = left + step;
        }
        UI.nextLine(); // to clear out the input
        UI.println("Finished plotting");
    }

    public void MedianList(ArrayList<Double> levels){ //Calculates and returns the median
        double list = levels.size(); //getting how many numbers are in the list
        double median = (list + 1)/2; // formula to calculate the median
        UI.println("The median is " + median);
    }

    public void StandardDeviation(ArrayList<Double> levels){ //Calculates the standard deviation
        //Not done
    }

    public void plotLevelsCompletion(ArrayList<Double> levels) {
        /* # YOUR CODE HERE */
        //done completion in plotLevels.

        UI.nextLine(); // to clear out the input
        UI.println("Finished plotting");
    }

    // ------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI() {
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Analyse Levels", this::analyse);
        UI.addButton("Quit", UI::quit);
    }

}
