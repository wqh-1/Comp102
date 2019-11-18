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

/** Runs a simulation of balls falling to the ground.
 *      creates two DroppingBalls
 *  Repeatedly
 *     makes each ball take a step until it has got to the ground
 *     then make a new ball and starts the outer loop again.
 */

public class BallDropper{

    public void dropTwoBalls(){
        UI.drawLine(0,DroppingBall.GROUND, 600 ,DroppingBall.GROUND);    // the ground
        DroppingBall ballA = this.makeNewBall();
        DroppingBall ballB = this.makeNewBall();
        ballA.draw();
        ballB.draw();

        while (true){
            // make them step
            ballA.step();
            ballB.step();

            // redraw the balls in their new position and pause
            UI.clearGraphics();      
            UI.setColor(Color.black);
            UI.drawLine(0,DroppingBall.GROUND, 600 ,DroppingBall.GROUND);    // the ground

            ballA.draw();
            ballB.draw();

            if (ballA.getHeight() <= 0) {      // if at ground edge, make new one
                ballA = this.makeNewBall();
            }
            if (ballB.getHeight() <= 0) {      // if at right edge, make new one
                ballB = this.makeNewBall();
            }

            UI.sleep(40); // pause of 40 milliseconds
        }
    }

    /** Helper method that makes a new DroppingBall with random initial values */
    public DroppingBall makeNewBall(){
        double initHeight = 100 + Math.random()*300; // random height between 100 and 400.
        double xSpeed = 0.5 + Math.random()*4;       // random step size between 0.5 and 4.5.
        Color col = Color.getHSBColor((float)Math.random(),1,1);
        return new DroppingBall(0,initHeight,xSpeed,col);
    }	

    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.addButton("drop", this::dropTwoBalls);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(650,500);
        UI.setDivider(0);
    }

    // Main
    /** Create a new BallDropper object */
    public static void main(String[] arguments){
        BallDropper bouncer = new BallDropper();
        bouncer.setupGUI();
    }	

}
