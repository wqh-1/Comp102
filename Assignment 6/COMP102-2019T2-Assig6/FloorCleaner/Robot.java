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

/** The robot is a circular vacuum cleaner than runs around
 * a floor, erasing any "dirt".
 * The parameters of the constructor should include the initial position,
 * and possibly its size and color.
 * It can step and it can bump:
 *  step() makes it go forward one step in its current direction.
 *  bump() makes it go backward one step, and then turn to a new (random)
 *         direction.
 * It can report its current position (x and y) with the
 *  getX() and getY() methods.
 *  draw() will make it draw itself,
 *  erase() will make it erase itself
 */

public class Robot{

    public static final int SIZE = 60; // diameter of the robot

    // Fields to store
    /*# YOUR CODE HERE */
    private double x;
    private double y;
    private double direction;
    private final double diam;
    private final Color color;

    /** Construct a new Robot object.
     *  set its direction to a random direction, 0..360
     */
    public Robot(double diam, double xpos, double ypos, Color color){
        /*# YOUR CODE HERE */
        this.diam = diam;
        this.x = xpos;
        this.y = ypos;
        this.color = color;
        this.direction = Math.random() * 360;
        UI.setImmediateRepaint(false);
    }

    /** Return the x coordinate of the current position  */
    public double getX(){
        /*# YOUR CODE HERE */
        return this.x;
    }

    /** Return the y coordinate of the current position  */
    public double getY(){
        /*# YOUR CODE HERE */
        return this.y;
    }

    /** Step one unit in the current direction (but don't redraw) */
    public void step(){
        /*# YOUR CODE HERE */
        this.x = this.x + Math.cos(this.direction * Math.PI/180);
        this.y = this.y + Math.sin(this.direction * Math.PI/180);
        
    }
    /** bump: move backwards one unit and change direction randomly */
    public void bump(){
        /*# YOUR CODE HERE */
        this.x = this.x - Math.cos(this.direction * Math.PI/180);
        this.y = this.y - Math.sin(this.direction * Math.PI/180);
        direction = Math.random() * 360;
    }

    /** Draw the robot */
    public void draw(){
        /*# YOUR CODE HERE */
        double left = this.x-this.diam/2;
        double top = this.y-this.diam/2;
        UI.setColor(this.color);
        UI.fillOval(left,top,this.diam,this.diam);
        UI.setColor(Color.black);
        UI.drawOval(left,top,this.diam,this.diam);
        
        //need to draw small oval on the robot
    }

    /** Erase the robot */
    public void erase(){
        /*# YOUR CODE HERE */
        double left = this.x-this.diam/2;
        double top = this.y-this.diam/2;
        UI.eraseOval(left,top,this.diam,this.diam);
    }
}
