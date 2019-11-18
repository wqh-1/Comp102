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

/** The snake is created at a random position with a random 360 degree direction.
 * The constructor does not have any parameters.
 * It can move
 *  - makes it go forward one step in its current direction.
 *  - if outside arena boundaries, makes it go backward one step, and
 *         then turn to a new (random) direction.
 *  The walls of the arena are determined by the constants:
 *    FrogSnakeGame.TopWall, FrogSnakeGame.BotWall, FrogSnakeGame.LeftWall
 *    and FrogSnakeGame.RightWall
 * It can report its current position (x and y) with the
 *  getX() and getY() methods.
 *  move() will make it move in the direction it is going. 
 *  draw() will make it draw itself (image size should be 30).
 */

public class Snake{
    /*# YOUR CODE HERE */
    private double x;
    private double y;
    private double direction;
    private double size = 30;

    public Snake(){
        this.x = FrogSnakeGame.LEFT_WALL + size/2 + Math.random() * (FrogSnakeGame.ARENA_SIZE-size/2);
        this.y = FrogSnakeGame.TOP_WALL + size/2 + Math.random() * (FrogSnakeGame.ARENA_SIZE-size/2);
        this.direction = Math.random() * 360;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void move(){
        this.x = this.x + Math.cos(this.direction*Math.PI/180);
        this.y = this.y + Math.sin(this.direction*Math.PI/180);
        if(this.x < FrogSnakeGame.LEFT_WALL+this.size/2 || this.x > FrogSnakeGame.RIGHT_WALL-this.size/2 || this.y < FrogSnakeGame.TOP_WALL+this.size/2 || this.y > FrogSnakeGame.BOT_WALL-this.size/2){
            this.x = this.x * Math.cos(this.direction*Math.PI/180);
            this.y = this.y * Math.sin(this.direction*Math.PI/180);
            direction= Math.random()*360;
        }
    }
    
    public void draw(){
        double left = this.x-this.size/2;
        double top = this.y-this.size/2;
        UI.drawImage("snake.jpg", left, top, this.size, this.size);
    }
}