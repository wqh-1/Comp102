// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 2
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

/**
 * A CartoonCharacter object is a cartoon character, displayed on the screen
 * that can 
 *   move around,
 *   change the direction the character is facing,
 *   change its emotion (smiling or frowning)
 *   speak or "think" a phrase
 */

public class CartoonCharacter {

    /* Fields representing the state of a CartoonCharacter */
    private String imagePrefix;
    private double characterX = -100;   // top left corner of character     
    private double characterY = -100;
    private String direction = "right";
    private String emotion = "smile";

    /* Fields containing dimensions of CartoonCharacters */

    private int characterHeight = 100;
    private int characterWidth = 70;

    private int wordsWidth = 150;
    private int wordsHeight = 35;

    /** Constructor requires the coordinates (left, top) of where it should be placed,
     *    and the prefix of the image file names
     *  For example
     *    new CartoonCharacter(100, 50, "casey");
     */
    public CartoonCharacter(double x, double y, String name ){
        this.characterX = x;
        this.characterY = y;
        this.imagePrefix=name;
        this.draw();
    }

    /** makes the CartoonCharacter turn to the left */
    public void lookLeft() {
        this.erase();
        this.direction = "left";
        this.draw();
    }

    /** makes the CartoonCharacter turn to the right */
    public void lookRight() {
        this.erase();
        this.direction = "right";
        this.draw();
    }

    /** makes the CartoonCharacter smile */
    public void smile() {
        this.erase();
        this.emotion = "smile";
        this.draw();
    }

    /** makes the CartoonCharacter frown */
    public void frown() {
        this.erase();
        this.emotion = "frown";
        this.draw();
    }

    /** makes the CartoonCharacter walk in the direction it is facing */
    public void walk(double dist) {
        this.erase();
        if (this.direction.equals("right")){
            this.characterX = this.characterX + dist;
        }
        else{
            this.characterX = this.characterX - dist;
        }
        this.draw();
    }

    /** makes the CartoonCharacter say something in a speech box */
    public void speak(String words) {
        double boxX = this.characterX;
        double boxY = this.characterY - this.wordsHeight - 20;

        if (this.direction.equals("right"))
            boxX += 15 ;
        else
            boxX +=  this.characterWidth  - 15 - this.wordsWidth;

        UI.eraseRect(boxX, boxY, this.wordsWidth, this.wordsHeight);
        UI.drawRect(boxX, boxY, this.wordsWidth, this.wordsHeight);
        UI.drawString(words, boxX + 5, boxY + this.wordsHeight/2 + 3);

        UI.sleep(1000);

        UI.eraseRect(boxX, boxY, this.wordsWidth+1, this.wordsHeight+1);
    }

    /** makes the CartoonCharacter think something in a speech bubble */
    public void think(String words) {
        double bubbleX = this.characterX;
        double bubbleY = this.characterY - this.wordsHeight - 2;
        double circleX = this.characterX;
        double circleY = this.characterY;

        if (this.direction.equals("right")){
            bubbleX += 15 ;
            circleX += this.characterWidth + 20;
        }
        else{
            bubbleX +=  this.characterWidth  - 15 - this.wordsWidth;
            circleX -= 30;
        }

        UI.eraseOval(bubbleX, bubbleY, this.wordsWidth, this.wordsHeight);
        UI.drawOval(bubbleX, bubbleY, this.wordsWidth, this.wordsHeight);
        UI.drawString(words, bubbleX + 12, bubbleY + this.wordsHeight/2 + 3);

        UI.drawOval(circleX, circleY, 10, 10);

        UI.sleep(1000);

        UI.eraseRect(bubbleX, bubbleY, this.wordsWidth+1, this.wordsHeight+1);
        UI.eraseOval(circleX, circleY, 10, 10);
    }

    /** Helper method that erases the CartoonCharacter 
     * All the public methods that change the character call erase first 
     */
    private void erase() {
        UI.eraseRect(this.characterX, this.characterY, this.characterWidth+1, this.characterHeight+1);
    }

    /** Helper method that draws the CartoonCharacter
     * All the public methods that change the character call draw.
     */

    private void draw(){
        String fname = null;
        fname = this.imagePrefix + "-" + this.direction + "-" + this.emotion + ".png"; 
        UI.drawImage(fname, this.characterX, this.characterY, this.characterWidth, this.characterHeight);
        UI.sleep(500);
    }

}

