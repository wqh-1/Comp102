// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 7
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/**
 * A game in which the player has to shoot disks spread out over a shooting range.
 * The gun is stuck on a horizontal line below the shooting range, and shoots upwards.
 * The player can move the gun sideways using the left and right arrow keys, and
 * can fire the gun with the space key.
 * Each shot that hits a disk will damage it some more.
 * When a disk has a high enough damage level, it will explode and disappear.
 * When it explodes it will also damage the disks around it that are close enough.
 * The player has a limited number of shots, (the remaining ammunition is displayed at
 * the side of the window).
 * When the game is over (either the player has run out of ammunition or all the
 * disks have exploded), the score is computed. The score of each disk depends on
 * its damage - the greater the damage, the greater the score.
 */

public class DiskGame{
    // Constants for the game geometry: the disks in the shooting range should
    // all be in the rectangle starting at (0,0) with a width of 500 and a height of 150
    // The gun should be on the line at y = 300
    private static final double GAME_WIDTH = 500;
    private static final double SHOOTING_RANGE_Y = 150; // lowest point that a disk can be
    private static final double GUN_Y = 300;

    //Constants for game logic
    private static final int INITIAL_SHOTS = 30;
    private static final int NUMBER_OF_DISKS = 30;

    //Fields for the game state
    private double gunPosition = GAME_WIDTH/2;  // current x position of the gun
    private double score = 0;                   // current score
    private int shotsRemaining = INITIAL_SHOTS; // How many shots are left

    private ArrayList <Disk> disks = new ArrayList<Disk>(); // The list of disks

    /**
     * Sets up the user interface:
     * Set up the buttons and the keylistener
     */
    public void setupGUI(){
        UI.setKeyListener(this::doKey);
        UI.addButton("Restart", this::startGame);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.16);
    }

    /**
     * Set the fields of the game to their initial values,
     * Create a new list of disks
     * redraw the game
     */
    private void startGame(){ 
        /*# YOUR CODE HERE */
        this.shotsRemaining = INITIAL_SHOTS;
        this.gunPosition = GAME_WIDTH/2;
        this.score = 0;
        this.initialiseDisks();
        this.redraw();
    }

    /**
     * Make a new ArrayList of disks with new disks at random positions
     * within the shooting range.
     * Completion: ensure than none of them are overlapping.
     */
    private void initialiseDisks(){
        /*# YOUR CODE HERE */
        this.disks = new ArrayList<Disk>();
        while(this.disks.size() < NUMBER_OF_DISKS){
            double x = Math.random() * GAME_WIDTH;
            double y = Math.random() * SHOOTING_RANGE_Y;
            Disk disk = new Disk(x,y);
            this.disks.add(disk);
        }
    }

    /**
     * Respond to the key presses.
     * Left and Right arrows change the gun position;
     * Space fires a shot
     */
    public void doKey(String key){
        if (key.equals("Right")){
            /*# YOUR CODE HERE */
            gunPosition = gunPosition += 5;
            this.redraw();
        }
        else if (key.equals("Left")){
            /*# YOUR CODE HERE */
            gunPosition = gunPosition -= 5;
            this.redraw();
        }
        else if (key.equals("Space")|| key.equals(" ")){
            /*# YOUR CODE HERE */   
            this.fireShot();
        }
    }

    /**
     * The core mechanic of the game is to fire a shot.
     * - Check that there is a shot remaining.
     * - Move the shot up the screen from the gun, step by step, until 
     *   it either goes off the screen or hits a disk.
     *   The shot is constantly redrawn as a line from the gun to its current position.
     * - If the shot hits a disk, it damages the disk, 
     * - If the disk is now broken, then it will damage its neighbours
     *  (ie, all the other disks within range will be damaged also)
     * - If it hit a disk, exit the loop.
     * - Finally, update the score,
     * - If the game is now over,  print out the score 
     * (You should define additional methods - don't do it all in one big method!)
     */
    private void fireShot(){
        if (shotsRemaining < 1 ) { return; }
        shotsRemaining--; //We now have one less shot left
        double shotPosX = gunPosition; //The shot starts at the top of the gun
        double shotPosY = GUN_Y; //The shot starts at the top of the gun
        UI.setColor(Color.black);
        while (shotPosY > 0){ 
            shotPosY -= 1;
            UI.drawLine(shotPosX, GUN_Y, shotPosX, shotPosY);
            //check if it hits a disk... 
            /*# YOUR CODE HERE */
            Disk hitDisk = this.getHitDisk(shotPosX,shotPosY);
            if(hitDisk != null){
                hitDisk.damage();
                if(hitDisk.isBroken()){
                    hitDisk.explode();
                    this.damageNeighbors(hitDisk);
                }
                break;
            }
            UI.sleep(1);
        }
        redraw();
        this.updateScore();
        //If game is over, print out the score
        if ((this.allDisksExploded() || this.shotsRemaining < 1)){
            UI.println("Your score: " + score);
        }
    }

    /*
     * Does the given shot hit a disk? If yes, return that disk. Else return null
     * Useful when firing a shot
     * Hint: use the on method of the Disk class
     */
    private Disk getHitDisk(double shotX, double shotY){
        /*# YOUR CODE HERE */
        for(Disk dk : this.disks){
            if(dk.on(shotX, shotY)){
                return dk;
            }
        }
        return null;
    }

    /*
     * Inflict damage on all the neighbours of the given disk
     * (ie, all disks that are within range of the disk, and are not already broken)
     * Note, it should not inflict more damage on the given disk.
     * Useful when firing a shot
     * Hint: make use of Disk class methods
     *
     * For the CHALLENGE, this should be able to cause a chain reaction 
     *  so that neighbours that are damaged to their limit will explode and
     *  damage their neighbours, ....
     */
    private void damageNeighbors(Disk disk){
        /*# YOUR CODE HERE */
        for(Disk other : this.disks){
            if(other!=disk && !other.isBroken()  && other.isWithinRange(disk)){
                other.damage();
            }
        }
    }

    /**
     * Are all the disks exploded?
     * Useful for telling whether the game is over.
     */
    private boolean allDisksExploded(){
        /*# YOUR CODE HERE */
        for(Disk disk : this.disks){
            if(!disk.isBroken()){
                return false;
            }
        }
        return true;
    }

    /**
     * Update the score field, by summing the scores of each disk
     * Score is 150 for exploded disks, 50 for disks with 2 hits, and 20 for disks with 1 hit.
     */
    private void updateScore(){
        //Each Disk can report how many points they are worth:
        //Iterate through the ArrayList, adding up the total score of the disks.
        /*# YOUR CODE HERE */
        this.score = 0;
        for(Disk disk : this.disks){
            this.score += disk.score();
        }
    }

    /**
     *  Redraws the game:
     *  - Redraws the disks
     *  - Redraws the gun
     *  - Redraws the pile of remaining shot (Completion)
     *  - Displays the current score (Completion)
     *  - calls repaintGraphics to make them visible
     * 
     */
    private void redraw(){
        UI.clearGraphics();
        //Redraw the boundary of the shooting range
        UI.setColor(Color.black);
        UI.drawRect(0,0, GAME_WIDTH, GUN_Y);
        UI.setColor(Color.gray);
        UI.drawLine(0, SHOOTING_RANGE_Y, GAME_WIDTH, SHOOTING_RANGE_Y);

        //Redraw the gun, the disks, and the pile of remaining rounds, and
        /*# YOUR CODE HERE */
        UI.fillRect(gunPosition-5,GUN_Y-5,10,10);
        for(int i = 1; i <= this.shotsRemaining; i++){
            UI.fillRect(2,GUN_Y - i * 5, 4, 4);
        }
        if(disks != null){
            for(Disk disk : disks){
                disk.draw();
            }
        }
    }

    public static void main(String[] args){
        DiskGame dg = new DiskGame();
        dg.setupGUI();
        dg.startGame();
    }

}
