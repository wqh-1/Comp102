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

/** DroppingBall represents a ball that falls towards the ground.
 *    Each time the step() method is called, it will take one step.  
 * For the Challenge part, the ball bounces when it reaches the ground.
 */

public class DroppingBall{
    // Fields to store
    //   the state of the ball:  x, height, stepX, stepY, colour
    //   other constants for the ball: size, position of the ground
    /*# YOUR CODE HERE */

    public static final int GROUND = 450;   // ground level.
    public static final int SIZE = 30;      // diameter of the ball
    
    private double x;
    private double height;
    private double stepX; 
    private double stepY;
    private Color color;
    // Constructor
    /** Construct a new DroppingBall object.
     *  Parameters are the initial x position, the height above the ground,
     *  the initial speed to the right (ie, the horizontal step size), and 
     *  the colour.
     *  Stores the parameters into fields (computing the y position from the height)
     *  and initialises the other fields,
     */
    public DroppingBall(double x, double h, double s, Color c){
        /*# YOUR CODE HERE */
        this.x = x;
        this.height = h;
        this.stepX = s;
        this.color = c;
        
    }

    // Methods
    /** Move the ball one step.
     *  Changes its height and x position using the vertical and horizonal steps
     *  If it would go below ground, then change its y position to ground level
     */
    public void step(){
        /*# YOUR CODE HERE */
        this.height = this.height + this.stepY;
        this.x = this.x + this.stepX;
        if(this.height < 0){
            this.height = 0;
        }
        this.stepY = this.stepY + 0.1;//makes the ball speed faster.
    }

    /** Return the height of the ball above the ground */
    public double getHeight(){
        /*# YOUR CODE HERE */
        return this.height;
    }

    /** Draw the ball on the Graphics Pane in its current position */
    public void draw(){
        /*# YOUR CODE HERE */
        UI.setColor(color);
        UI.fillOval(x,x+height,SIZE,SIZE);
    }
}
