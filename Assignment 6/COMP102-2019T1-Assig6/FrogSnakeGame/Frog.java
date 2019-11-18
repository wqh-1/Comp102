// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 6
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/** Frog
 *  A new frog starts at the given position, with the given direction, and 
 *     has either a "light" or "dark" shade.
 *  Frogs can turn in 4 directions: left, right, up, and down. 
 *  Frogs move around at a constant speed in an arena with an enclosing wall,
 *     following its direction, until it hits a wall. In which case it stops moving.
 *  Frog can grow in size, and (for the completion) can also shrink by resetting their size
 *      to the orginal value.
 *
 *  The walls of the arena are determined by the constants:
 *    FrogGame.TOP_WALL, FrogGame.BOT_WALL, FrogGame.LEFT_WALL and FrogGame.RIGHT_WALL
 */

public class Frog {
    // Constants
    public static final int INITIAL_SIZE = 30;
    public static final int GROWTH_RATE = 3;
    public static final int SPEED = 2;

    // Fields

    /*# YOUR CODE HERE */
    private double left;
    private double top;
    private String direction = "Right";
    private String shade;
    private double size = INITIAL_SIZE;

    //Constructor 
    /** 
     * Make a new frog
     * The parameters specify the initial position of the top left corner,
     *   the direction, and the shade of the Frog ("light" or "dark")
     * We assume that the position is within the boundaries of the arena
     */
    public Frog(double left, double top, String dir, String shade)  {
        /*# YOUR CODE HERE */
        this.left = left;
        this.top = top;
        this.direction = dir;
        this.shade = shade;

    }

    /**
     * Turn right  (don't redraw)
     */
    public void turnRight(){
        /*# YOUR CODE HERE */
        this.direction = "Right";
    }

    /**
     * Turn left  (don't redraw)
     */
    public void turnLeft(){
        /*# YOUR CODE HERE */
        this.direction = "Left";
    }

    /**
     * Turn up  (don't redraw)
     */
    public void turnUp(){
        /*# YOUR CODE HERE */
        this.direction = "Up";
    }

    /**
     * Turn down  (don't redraw)
     */
    public void turnDown(){
        /*# YOUR CODE HERE */
        this.direction = "Down";
    }

    /**
     * Moves the frog: 
     *   use SPEED unit forward in the correct direction
     *   by changing the position of the frog.
     * Make sure that the frog does not go outside the arena, by making sure 
     *  - the top of the frog is never smaller than FrogGame.TOP_WALL
     *  - the bottom of the frog is never greater than FrogGame.BOT_WALL
     *  - the left of the frog is never smaller than FrogGame.LEFT_WALL
     *  - the right of the frog is never greater than FrogGame.RIGHT_WALL
     *  DO NOT REDRAW THE FROG!!!
     */
    public void move() {
        /*# YOUR CODE HERE */
        if(this.direction.equals("Up")){
            this.top = Math.max(FrogGame.TOP_WALL, this.top-SPEED);
        }else if(this.direction.equals("Down")){
            this.top = Math.min(FrogGame.BOT_WALL-this.size, this.top+SPEED); 
        }
        else if(this.direction.equals("Left")){
            this.left = Math.max(FrogGame.LEFT_WALL, this.left-SPEED);
        }
        if(this.direction.equals("Right")){
            this.left = Math.min(FrogGame.RIGHT_WALL-this.size, this.left+SPEED);
        }
    }

    /**
     * Check whether the frog is touching the given point, eg, whether the
     *   given point is included in the area covered by the frog.
     * Return true if the frog is on the top of the position (x, y)
     * Return false otherwise
     */
    public boolean touching(double x, double y) {
        /*# YOUR CODE HERE */
        if(x < this.left){
            return false;
        }
        if(x > (this.left+this.size)){
            return false;
        }
        if(y < this.top){
            return false;
        }
        if(y > (this.top+this.size)){
            return false;
        }
        return true;
    }

    /**
     * The Frog has just eaten a bug
     * Makes the frog grow larger by GROWTH_RATE.
     */
    public void grow(){
        /*# YOUR CODE HERE */
        this.size += GROWTH_RATE;

    }

    /**
     * The Frog has just bumped into a snake
     * Makes the frog size reset to its original size
     * ONLY NEEDED FOR COMPLETION
     */
    public void shrink(){
        /*# YOUR CODE HERE */
        this.size = INITIAL_SIZE;
    }

    /**
     * Draws the frog at the current position and the current size.
     * Use the correct image file (darkfrog.jpg or lightfrog.jpg)
     */
    public void draw(){
        /*# YOUR CODE HERE */
        String image = this.shade + "frog.jpg";
        UI.drawImage(image, this.left, this.top, this.size,this.size);
    }
}

