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
import java.util.*;

/** Runs a simulation of launching balls at a target.
 */

public class BallGame{

    public static final double GROUND = 450;
    public static final double LAUNCHER_X = 20;      // The initial position of the ball being launched
    public static final double LAUNCHER_HEIGHT = 20; // The initial height of the ball being launched
    public static final double RIGHT_END = 600;
    public static final double SHELF_X = 400;
    public static final double MAX_SPEED = 14;

    private Ball ball; // the ball that is being launched towards the target
    // need to be in a field because two different methods need to access it.

    /**
     * Main loop for the Core version of the game
     * It creates a ball (to be launched) and a target ball (on a shelf)
     * It has a loop that repeatedly
     *   - Makes a new ball if the old one has gone off the right end.
     *   - Makes the ball and the target ball take one step
     *     (unless they are still on the launcher or shelf)
     *   - Checks whether the ball is hitting the target
     *   - redraws the state of the game
     * The loop stops when the target has gone off the right end and the ball is on the launcher.
     */
    public void runGameCore(){
        UI.printMessage("Click Mouse to launch the ball");
        this.ball = new Ball(LAUNCHER_X, LAUNCHER_HEIGHT);

        double shelfHeight = 50+Math.random()*400;
        Ball target = new Ball(SHELF_X, shelfHeight);

        this.drawGameCore(this.ball, target, shelfHeight);
        int count = 0; 

        // run until the target is gone (ie, off the right end)
        while (ball.getX()!=LAUNCHER_X || target.getX()<RIGHT_END){

            //if the ball is over the right end, make a new one.
            if (ball.getX()>=RIGHT_END) {
                this.ball = new Ball(LAUNCHER_X, LAUNCHER_HEIGHT);
                count++;
            }

            //move the ball, if it isn't on the launcher
            if (ball.getX() > LAUNCHER_X){
                this.ball.step();
            }            

            // move target if it isn't on the shelf
            if (target.getX()!= SHELF_X){
                target.step();
            }

            //if ball is hitting the target ball on the shelf, then make it start moving too
            double dist = Math.hypot(target.getX()-this.ball.getX(), target.getHeight()-this.ball.getHeight());
            if (target.getX()==SHELF_X && dist <=2* Ball.RADIUS){
                target.setSpeed(2, 0);
                target.step();
            }

            //redraw the game and pause
            this.drawGameCore(this.ball, target, shelfHeight);

            UI.sleep(40); // pause of 40 milliseconds

        }
        UI.setFontSize(40);
        UI.drawString(count+" tries", 200, 200);
    }

    /**
     * Launch the current ball, if it is still in the catapult,
     * Speed is based on the position of the mouse relative to the ground.
     */
    public void launch(String action, double x, double y){
        if (action.equals("released")){
            if (this.ball==null) {
                UI.printMessage("Press Core/Completion button first to create a ball");
                return;  // the ball hasn't been constructed yet.
            }            
            if (this.ball.getX()==LAUNCHER_X && this.ball.getHeight()==LAUNCHER_HEIGHT){
                double speedX = (x-LAUNCHER_X)/10;
                double speedUp = (GROUND - LAUNCHER_HEIGHT - y)/10;
                double speed = Math.hypot(speedUp, speedX);
                //scale down if over the maximum allowed speed
                if (speed> MAX_SPEED){
                    speedUp = speedUp * MAX_SPEED/speed;
                    speedX = speedX * MAX_SPEED/speed;
                }
                this.ball.setSpeed(speedX, speedUp);
                this.ball.step();
            }
        }
    }

    /**
     * Draw the game: ball, target, ground, launcher and shelf.
     */
    public void drawGameCore(Ball ball, Ball target, double shelfHeight){        
        UI.clearGraphics();
        ball.draw();
        target.draw();

        // draw ground, wall, launcher, and shelf
        UI.setColor(Color.black);
        UI.setLineWidth(2);
        UI.eraseRect(RIGHT_END, 0, RIGHT_END+100, GROUND);
        UI.drawLine(LAUNCHER_X, GROUND, RIGHT_END, GROUND);
        UI.drawLine(RIGHT_END, GROUND, RIGHT_END, 0);
        UI.drawLine(LAUNCHER_X, GROUND, LAUNCHER_X, GROUND-LAUNCHER_HEIGHT);
        UI.drawLine(LAUNCHER_X-Ball.RADIUS, GROUND-LAUNCHER_HEIGHT, LAUNCHER_X+Ball.RADIUS, GROUND-LAUNCHER_HEIGHT);
        UI.drawLine(SHELF_X-Ball.RADIUS, GROUND-shelfHeight, SHELF_X+Ball.RADIUS, GROUND-shelfHeight);
    }

    /** Version of the game with two targets.
     *  You will need a modified version of drawGame also.
     */
    public void runGameCompletion(){
        UI.printMessage("Click Mouse to launch the ball");
        /*# YOUR CODE HERE */
        this.ball = new Ball(LAUNCHER_X,LAUNCHER_HEIGHT);
        double shelf1Ht = 50 + Math.random() * 200;
        double shelf2Ht = 250 + Math.random() * 200;
        
        Ball target1 = new Ball(SHELF_X, shelf1Ht);
        Ball target2 = new Ball(SHELF_X, shelf2Ht);
        
        this.drawGameCompletion(this.ball,target1,target2,shelf1Ht,shelf2Ht);
        int count = 0;
        while(ball.getX()!=LAUNCHER_X || target1.getX()<RIGHT_END || target2.getX()<RIGHT_END){
            if(ball.getX() >= RIGHT_END){
                this.ball = new Ball(LAUNCHER_X,LAUNCHER_HEIGHT);
                count++;
            }
            if(ball.getX() > LAUNCHER_X){
                this.ball.step();
            }
            if(target1.getX()!= SHELF_X){
                target1.step();
            }
            if(target2.getX()!= SHELF_X){
                target2.step();
            }
            double dist = Math.hypot(target1.getX()-this.ball.getX(),target1.getHeight()-this.ball.getHeight());
            if(target1.getX() == SHELF_X && dist <=2 * Ball.RADIUS){
                target2.setSpeed(2,0);
                target2.step();
            }
            this.drawGameCompletion(this.ball,target1,target2,shelf1Ht,shelf2Ht);
            UI.sleep(60);
        }
        UI.setFontSize(30);
        UI.drawString(count+ " tries", 200,200);
    }

    /**
     * Draw the game: ball, two targets, ground, launcher and shelves.
     */
    public void drawGameCompletion(Ball ball, Ball target1, Ball target2, double shelf1Ht, double shelf2Ht){        
        UI.clearGraphics();
        ball.draw();
        target1.draw();
        target2.draw();

        UI.setColor(Color.black);
        UI.setLineWidth(2);
        // draw ground, wall, launcher, and shelf
        UI.eraseRect(RIGHT_END, 0, RIGHT_END+100, GROUND);
        UI.drawLine(LAUNCHER_X, GROUND, RIGHT_END, GROUND);
        UI.drawLine(RIGHT_END, GROUND, RIGHT_END, 0);
        UI.drawLine(LAUNCHER_X, GROUND, LAUNCHER_X, GROUND-LAUNCHER_HEIGHT);
        UI.drawLine(LAUNCHER_X-Ball.RADIUS, GROUND-LAUNCHER_HEIGHT, LAUNCHER_X+Ball.RADIUS, GROUND-LAUNCHER_HEIGHT);
        UI.drawLine(SHELF_X-Ball.RADIUS, GROUND-shelf1Ht, SHELF_X+Ball.RADIUS, GROUND-shelf1Ht);
        UI.drawLine(SHELF_X-Ball.RADIUS, GROUND-shelf2Ht, SHELF_X+Ball.RADIUS, GROUND-shelf2Ht);
    }

    // Main
    /** Create a new BallGame object and setup the interface */
    public static void main(String[] arguments){
        BallGame bg = new BallGame();
        UI.setMouseListener(bg::launch);         // the mouse will launch the ball
        UI.addButton("Core", bg::runGameCore);   // Initialises the game and runs the simulation loop
        UI.addButton("Completion", bg::runGameCompletion);   // Initialises the game and runs the simulation loop
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(650,500);
        UI.setDivider(0);
    }   

}
