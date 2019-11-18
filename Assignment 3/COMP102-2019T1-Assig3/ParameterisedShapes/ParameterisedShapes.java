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
import javax.swing.JColorChooser;

/** Paramterised Shapes: draw tricolour flags and game boards */
public class ParameterisedShapes{

    //Constants for CORE  (three strip flags)
    public static final double FLAG_WIDTH = 200;
    public static final double FLAG_HEIGHT = 133;

    //Constants for COMPLETION
    public static final double BOARD_LEFT = 15;  // Left side of each row
    public static final double BOARD_TOP = 15;   // Top of the first row
    public static final double ROW_SIZE = 40;    // Height of each row.
    public static final double DISH_WIDTH = ROW_SIZE-4;      // Size of the dishes
    public static final double DISH_HEIGHT = DISH_WIDTH-10;  
    public static final double PEBBLE_DIAM = 10; // Size of the pebbles

    /**   CORE
     * asks user for a position and three colours, then calls the
     * drawTriColorFlag method, passing the appropriate arguments
     */
    public void doCore(){
        double left = UI.askDouble("Left of flag");
        double top = UI.askDouble("Top of flag");
        boolean horiz = UI.askBoolean("Are the stripes horizontal?");
        UI.println("Now choose the colours");
        Color stripe1 = JColorChooser.showDialog(null, "First Stripe", Color.white);
        Color stripe2 = JColorChooser.showDialog(null, "Second Stripe", Color.white);
        Color stripe3 = JColorChooser.showDialog(null, "Third Stripe", Color.white);
        this.drawThreeStripeFlag(left,top,horiz,stripe1,stripe2,stripe3);
    }

    /**   CORE
     * draws a three colour flag at the given position consisting of
     * three equal size stripes of the given colors
     * The stripes are horizontal or vertical
     * The size of the flag is specified by the constants FLAG_WIDTH and FLAG_HEIGHT
     */
    public void drawThreeStripeFlag(double left, double top, boolean horiz, Color stripe1, Color stripe2, Color stripe3){ //left and top for position of flag, boolean horiz to see if the user wanted horiz or vert lines
        /*# YOUR CODE HERE */                                                                                             //Color stripe1,2,3 for the colour for each section
        if (horiz){
            UI.setColor(stripe1);
            UI.fillRect(left,top,FLAG_WIDTH,FLAG_HEIGHT/3);
            UI.setColor(stripe2);
            UI.fillRect(left,top + FLAG_HEIGHT/3,FLAG_WIDTH,FLAG_HEIGHT/3);
            UI.setColor(stripe3);
            UI.fillRect(left,top + FLAG_HEIGHT * 2/3,FLAG_WIDTH,FLAG_HEIGHT/3); //This was the opposite of the verticle
        }
        else{
            UI.setColor(stripe1);
            UI.fillRect(left,top,FLAG_WIDTH/3,FLAG_HEIGHT);
            UI.setColor(stripe2);
            UI.fillRect(left + FLAG_WIDTH/3,top,FLAG_WIDTH/3,FLAG_HEIGHT);
            UI.setColor(stripe3);
            UI.fillRect(left + FLAG_WIDTH * 2/3,top,FLAG_WIDTH/3,FLAG_HEIGHT); //I notice that verticle flag was the same as france flag method in the first assignment
        }
    }

    /**   COMPLETION
     * Draws a pebble game board with five rows of increasing size
     *   The first row has 6 dishes, the second has 7 dishes, the third has 8, etc.
     *   The positions of the red and blue pebbles are shown in this table:
     *   (where the |'s separate the dishes)
     *     |   | r |   |   |   | b |
     *     |   | b | r |   |   |   |   |
     *     |   |   |   |   |   | r |   | b |
     *     | b |   |   | r |   |   |   |   |   |
     *     |   |   | b |   |   |   |   |   |   | r |
     *
     *  It uses the drawPebbleRow method which draws one row and the two pebbles in it.
     */
    public void doCompletion(){
        UI.clearGraphics();             // the dishes start at 0
        this.drawPebbleRow(0,6,1,5);    //row , amount of dishes in the row, red - which dish is the red pebble on, blue - which dish is the blue pebble on.
        this.drawPebbleRow(1,7,2,1);    //first column - row, second column - amount of dishes, third column - which dish is the red pebble on, fourth column - which dish is the blue pebble on.
        this.drawPebbleRow(2,8,5,7);
        this.drawPebbleRow(3,9,3,0);
        this.drawPebbleRow(4,10,9,2);
    }

    /**   COMPLETION
     * Draws a row of a pebble game. Parameters must be sufficient to specify
     * the position and size of the row, and the positions of the pebbles
     * Hint: It makes the code easier if you count from 0 rather than from 1
     * Hint: use the drawDish and drawPebble methods!
     */
    public void drawPebbleRow(int row, int dishes, double red, double blue){
        /*# YOUR CODE HERE */
        UI.setColor(Color.black); //setting all the rectangle holding the dishes black
        UI.drawRect(BOARD_LEFT, BOARD_TOP + row * ROW_SIZE, ROW_SIZE * dishes, ROW_SIZE); // drawing the rectangles
        for(int i = 0; i < dishes; i++){
            this.drawDish(BOARD_LEFT + i * ROW_SIZE, BOARD_TOP + row * ROW_SIZE);//drawing multiple dishes in the rows
        }
        this.drawPebble(BOARD_LEFT + red * ROW_SIZE, BOARD_TOP + row * ROW_SIZE, Color.red); //makes the pebbles appear on the dishes
        this.drawPebble(BOARD_LEFT + blue * ROW_SIZE, BOARD_TOP + row * ROW_SIZE, Color.blue);
    }

    /**
     * Draw a dish in the square cell at (left,top)
     * Assumes the rows can be viewed as a sequence of square cells, each of
     * which contains a dish centered in the cell.
     */
    public void drawDish(double cellLeft, double cellTop){
        double dishLeft = cellLeft + ROW_SIZE/2 - DISH_WIDTH/2 ;
        double dishTop = cellTop + ROW_SIZE/2 - DISH_HEIGHT/2;
        UI.setColor(new Color(230, 230, 230));   // very light grey
        UI.fillOval(dishLeft, dishTop, DISH_WIDTH, DISH_HEIGHT);
        UI.setColor(Color.black);
        UI.drawOval(dishLeft, dishTop, DISH_WIDTH, DISH_HEIGHT);
    }

    /**
     * Draw a pebble in the square cell at (left,top)
     * Assumes the rows can be viewed as a sequence of square cells, each of
     * which contains a dish centered in the cell.
     */
    public void drawPebble(double cellLeft, double cellTop, Color pebbleColor){
        double pebbleTop = cellTop + ROW_SIZE/2 - PEBBLE_DIAM/2;
        double pebbleLeft = cellLeft + ROW_SIZE/2 - PEBBLE_DIAM/2;
        UI.setColor(pebbleColor);
        UI.fillOval(pebbleLeft, pebbleTop, PEBBLE_DIAM, PEBBLE_DIAM);
    }
    
    public void doChallenge(){
        double rows = UI.askDouble("How many dishes in the row: ");
        //not done
        
        
    }

    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes );
        UI.addButton("Core", this::doCore );
        UI.addButton("Completion", this::doCompletion );
        UI.addButton("Challenge", this::doChallenge );
        UI.addButton("Quit", UI::quit );
    }

}
