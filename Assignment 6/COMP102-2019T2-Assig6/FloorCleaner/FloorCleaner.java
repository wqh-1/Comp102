// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 6
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/** Runs a simulation of a robot vacuum cleaner that moves around a floor area,
 *      changing to a new random direction every time it hits the edge of the
 *      floor area.
 */
public class FloorCleaner{

    // Constants
    /*# YOUR CODE HERE */
    private double radius = 30;
    private double top = 50;
    private double left = 50;
    private double right = 550;
    private double bot = 420;

    /* Method to create robot and make it run around for ever.
     *  Makes a dirty floor (a grey rectangle), and then makes a robot floor
     *  cleaner run around on the floor to clean it all up.
     *  The robot will go forward a small amount on each step, and
     *  erase the "dirt".
     *  After each step, the program should ask for the robot's
     *  position and check the position against the edges of the floor.
     *
     *  For the core, the robot stops if its position is off the floor.
     *
     *  For the completion, if the robot has gone over the edge, it should
     *  tell the robot to "bump", which will make it back up a bit and then
     *  turn to a new random direction.
     */
    public void cleanFloor(){
        /*# YOUR CODE HERE */
        UI.setColor(Color.gray);
        UI.fillRect(left, top, right - left, bot - top);
        
        double x = left + Math.random()*(right-left-radius);
        double y = top + Math.random()*(bot - top - radius);
        Robot r = new Robot(radius*2, x, y, Color.red);
        while(true){
            r.erase();
            r.step();
            double posX = r.getX();
            double posY = r.getY();
            if(posX-radius < left || posX+radius > right || posY-radius < top || posY+radius > bot){
                r.bump();
            }
            r.draw();
            UI.sleep(20);
        }
    }

   
    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.addButton("start", this::cleanFloor);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(650,500);
        UI.setDivider(0);
    }    

    // Main
    public static void main(String[] arguments){
        FloorCleaner fc = new FloorCleaner();
        fc.setupGUI();
    }   

}
