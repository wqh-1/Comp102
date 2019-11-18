// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 1
 * Name:
 * Username: 
 * ID:
 */

import ecs100.*;

/** Program for calculating amount of paint required to paint a room */

public class PaintCalculator{

    public static final double DOOR_HEIGHT = 2.1;        // Height of the doors
    public static final double DOOR_WIDTH = 0.8;         // Width of the doors
    public static final double SQ_METERS_PER_LITRE = 15.0; // Area covered by 1 litre of paint

    /** Calculates and prints litres of paint needed to paint a room
     *  with four walls (not counting the doors, floor, and ceiling)
     */
    public void calculatePaintCore(){
        /*# YOUR CODE HERE */
        double l = UI.askDouble("Length of room in meters: "); // asking the user for the length
        double w = UI.askDouble("Width of room in meters: "); // asking the user for the width
        double h = UI.askDouble("Height of room in meters: "); // asking the user for the height
        
        int a = UI.askInt("Number of doors"); // asking the user for the number of doors
        
        double Area1 = l * h * 2;
        double Area2 = w * h * 2;
        double Door = DOOR_WIDTH * DOOR_HEIGHT * a;
        
        double paint = (Area1 + Area2 - Door)/SQ_METERS_PER_LITRE; // calculating the number of paint needed
        
        UI.printf("You will need %.2f",paint," litres of paint for the walls"); // "%.2f" this rounds the numbers up to 2dp.
       
        
        
    }

    /** Calculates and prints litres of paint needed to paint
     *  - the four walls of a room (not counting the doors and windows)
     *  - the ceiling (different type of paint)
     */
    public void calculatePaintCompletion(){
        /*# YOUR CODE HERE */
        double l = UI.askDouble("Length of room: "); // asking the user for the length of room
        double w = UI.askDouble("Width of room: "); // asking the user for the width of room
        double h = UI.askDouble("Height of room: "); // asking the user for the height of the room
        int a = UI.askInt("Number of doors in the room: "); //asking the user for the number of doors
        
        double hw = UI.askDouble("Height of window: "); // asking the user for the height of the window
        double ww = UI.askDouble("Width of window: "); // asking the user for the width of the window
        int b = UI.askInt("Number of windows in the room: "); // asking the user for the number of windows
        
        double Area1 = l * h * 2;
        double Area2 = w * h * 2;
        double Door = DOOR_WIDTH * DOOR_HEIGHT * a;
        
        double Window_Area = b * ww * hw;
        
        double walls = (Area1 + Area2 - Door - Window_Area)/SQ_METERS_PER_LITRE; // calculating the amount of paint needed for the walls
        double ceiling = l * w /SQ_METERS_PER_LITRE; // calculating the number of paint needed for the ceiling
        
        UI.printf("You will need %.2f" ,walls, " litres of paint for the walls"); // "%.2f" this rounds the numbers up to 2dp.
        UI.printf("You will need %.2f",ceiling, " litres of paint for the ceiling"); 
        
    }

    public void calculatePaintChallenge(){
        /*# YOUR CODE HERE */
        int Amount = UI.askInt("How many litres of paint:");
        double TotalCost = 0;
        //Litre1 = 20.00;
        //Litre2 = 32.60;
        //Litre4 = 55.80;
        //Litre10 = 103.50;
        
        int nTen = Amount/10;
        int nFour = Amount - nTen*10/4;
        int nTwo = Amount - (nTen*10 - nFour*4)/2;
        int nOne = Amount - (nTen*10 - nFour*4 - nTwo*2)/1;
        
        
        if(Amount < 1.0 || Amount > 1.0){
            TotalCost = Amount * nOne;
        }
        else if(Amount < 2.0 || Amount > 2.0){
            TotalCost = Amount * nTwo;
        }
        else if(Amount < 4.0 || Amount > 4.0){
            TotalCost = Amount * nFour;
        }
        else{
            TotalCost = Amount * nTen;
        }
        TotalCost = nTen + nFour + nTwo + nOne; 
        
        //UI.println("You will need " + Amount + " litres of paint");
        UI.println("You will need: " + nOne/10 + ", 1 Litres of paint");
        UI.println("You will need: " + nTwo/10 + ", 2 Litres of paint");
        UI.println("You will need: " + nFour/10 + ", 4 Litres of paint");
        UI.println("You will need: " + nTen + ", 10 Litres of paint");
        UI.printf("The total cost will be $ %.2f\n",TotalCost); //Note this isn't finish so it wont work as you want it to.
        
    }


    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.addButton("Core", this::calculatePaintCore ); 
        UI.addButton("Completion", this::calculatePaintCompletion );
        UI.addButton("Challenge", this::calculatePaintChallenge );
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);    // Expand the Text pane
    }   

}
