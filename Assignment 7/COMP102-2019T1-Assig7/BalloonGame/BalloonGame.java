// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 7
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;

/** Program for a simple game in which the player has to blow up balloons
 *   on the screen.
 *  The game starts with a collection of randomly placed small balloons
 *    (coloured circles) on the graphics pane.
 *  The player then clicks on balloons to blow them up by a small amount
 *   (randomly increases the radius between 2 and 6 pixels).
 *  If an expanded balloon touches another balloon, then they both "burst" and go grey.
 *  The goal is to get the largest score. The score is the total of the
 *   sizes (areas) of all the active balloons, minus the total size of all
 *   the burst balloons.
 *  At each step, the current score is recalculated and displayed,
 *   along with the highest score that the player has achieved so far.
 *  At any time, the player may choose to stop and "lock in" their score.
 *
 *  The BalloonGame class has a field containing an Arraylist of Balloon objects
 *   to represent the current set of Balloons on the screen.
 *  It has a field to hold the highest score.
 *
 *  The New Game button should start a new game.
 *  The Lock Score button should finish the current game, updating the highest score
 *
 *  Clicking (ie, releasing) the mouse on the graphics pane is the main "action"
 *  of the game. The action should do the following
 *    Find out if the mouse was clicked on top of any balloon.
 *    If so,
 *      Make the balloon a bit larger and redraw it.
 *      Check whether the balloon is touching any other balloon.
 *      If so
 *         burst the two balloons (which will make them go grey)
 *         redraw the burst Balloons
 *      Recalculate and redisplay the score
 *   If all the balloons are gone, the game is over.
 *    
 *   To start a game, the program should
 *       Clear the graphics pane
 *       Initialise the score information
 *       Make a new list of Balloons at random positions
 *       Print a message 
 *
 *   If the game is over, the program should
 *      Update the highest score if the current score is better,
 *      Print a message reporting the scores,
 *      Start a new game.
 *     
 *   There are lots of ways of designing the program. It is not a good idea
 *   to try to put everything into one big method.
 *        
 *  Note that the Balloon class is written for you. Make sure that you know
 *   all its methods - no marks for redoing code that is given to you.
 *    
 */
public class BalloonGame {
    private static final int MAX_BALLOONS = 20;

    private ArrayList <Balloon> balloons = new ArrayList<Balloon>(); // The list of balloons
    // (initially empty)

    // Fields
    /*# YOUR CODE HERE */
    private int score;
    private int highestScore;
    private double startX;
    private double startY;
    boolean isOn = false;
    boolean isTouching = false;

    /** Start the game:
     *  Clear the graphics pane
     *  Initialise the score information 
     *  Make a new set of Balloons at random positions
     */
    public void restartGame(){
        /*# YOUR CODE HERE */
        UI.clearGraphics();
        this.balloons = new ArrayList<Balloon>();
        while(this.balloons.size() < MAX_BALLOONS){
            double x = 30 + Math.random()*520;
            double y = 30 + Math.random()*520;
            Balloon b = new Balloon(x,y);
            if(this.findTouching(b) == null){
                b.draw();
                this.balloons.add(b);
            }
        }
        UI.printMessage("New Game: click on a balloon. Highest Score =" + this.highestScore);

    }

    /**
     * Main game action:
     *  Find the balloon at (x,y) if any,
     *  Expand it 
     *  Check whether it is touching another balloon,
     *  If so, update the penalty, burst both balloons, and remove them from the list
     *  Report the score.
     *  If there are no active balloons left, end the game.
     */
    public void  doMouse(String action, double x, double y){
        /*# YOUR CODE HERE */
        if(action.equals("released")){
            Balloon clickedBalloon = this.findBalloonOn(x,y);
            if(clickedBalloon ==null)return;
            clickedBalloon.expand();
            Balloon otherBalloon = findTouching(clickedBalloon);
            if(otherBalloon!=null){
                clickedBalloon.burst();
                otherBalloon.burst();
                otherBalloon.draw();
            }
            clickedBalloon.draw();
            int score = this.calculateScore();
            UI.printMessage("Score = "+score+" Highest score = "+this.highestScore);
            if(this.allBalloonsBurst()){
                this.endGame();
            }
        }

    }

    // Possible additional helper methods:
    public Balloon findBalloonOn(double x, double y){
        for(Balloon b : this.balloons){
            if(b.on(x,y) && b.isActive()){
                return b;
            }
        }
        return null;
    }

    public Balloon findTouching(Balloon balloon){
        for(Balloon other: this.balloons){
            if(other!= balloon && balloon.isTouching(other) && other.isActive()){
                return other;
            }
        }
        return null;
    }

    public int calculateScore(){
        int score = 0;
        for(Balloon b: this.balloons){
            if(b.isActive()){
                score = score + b.size();
            }
            else{
                score = score - b.size();
            }
        }
        return score;
    }

    //     public int calculateScore(){
    //   The score is the total size of all the active (coloured) balloons minus the total size of all the burst balloons
    // return score;
    //}

    public boolean allBalloonsBurst(){
        for(Balloon balloon: this.balloons){
            if(balloon.isActive()){
                return false;
            }
        }
        return true;
    }
    //        to update the highestScore and print a message
    public void endGame(){
        int score = this.calculateScore();
        if(score>this.highestScore){
            this.highestScore = score;
            UI.printMessage("Game over. New Highest score =" + this.highestScore);
        }else{
            UI.printMessage("Game over. Score was" + score+" Highest score = " + this.highestScore);
        }
        this.balloons.clear();
    }

    /*# YOUR CODE HERE */

    /**
     * Main:  set up the GUI
     */
    public static void main(String[] arguments){
        UI.setWindowSize(600,600);
        /*# YOUR CODE HERE */
        BalloonGame Ball = new BalloonGame();
        UI.addButton("New Game",Ball::restartGame);
        // UI.addSlider("Number of Balloons")1,20,20,B::restartGame);
        UI.addButton("Lock Score",Ball::endGame);
        UI.setDivider(0.0);
    }   

}
