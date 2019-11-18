// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 2
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

/**
 * An Animal object is an animal character, displayed on the screen
 * that can 
 *   go forward,
 *   go backward,
 *   jump,
 *   speak, "think" or shout a phrase,
 *   introduce itself,
 */

public class Animal {

    /* Fields representing the state of a Animal */
    private String animal = "turtle";
    private String name = "Berthie";
    private double imageX = -100;   // top left corner of image
    private double imageY = -100;
    private String direction = "left";

    /* Fields containing dimensions of Animals */
    private int imageHeight = 100;
    private int imageWidth = 70;

    private int wordsWidth = 160;
    private int wordsHeight = 45;
    private int wordSize = 12;

    /** Constructor requires
     *  - the type of animal,
     *  - the name of the animal,
     *  - and the coordinates (left, top) of where it should be placed.
     *    For example
     *    new Animal("dog", "Medor", 100, 50);
     */
    public Animal(String typeOfAnimal, String nameOfAnimal, double x, double y ){
        this.animal=typeOfAnimal;
        this.name=nameOfAnimal;
        this.imageX = x;
        this.imageY = y;
        if (this.animal.equals("grasshoper") || this.animal.equals("tiger")){
            this.direction="right";
        }
        else {
            this.direction="left";
        }
        UI.setFontSize(wordSize);
        this.draw();
    }

    /** move the Animal in the direction it is facing */
    public void goForward(double dist) {
        this.erase();
        if (this.direction.equals("right")){
            this.imageX = this.imageX + dist;
        }
        else{
            this.imageX = this.imageX - dist;
        }
        this.draw();
    }

    /** move the Animal in the opposite direction it is facing */
    public void goBackward(double dist) {
        this.erase();
        if (this.direction.equals("right")){
            this.imageX = this.imageX - dist;
        }
        else{
            this.imageX = this.imageX + dist;
        }
        this.draw();
    }

    /** move the Animal up then down */
    public void jump(double dist) {
        this.erase();
        this.imageY = this.imageY - dist;
        this.draw();

        UI.sleep(300);
        this.erase();
        this.imageY = this.imageY + dist;
        this.draw();
    }

    /** makes the Animal say something in a speech box */
    public void speak(String words) {
        double boxX = this.imageX;
        double boxY = this.imageY - this.wordsHeight - 20;

        if (this.direction.equals("right"))
            boxX += 15 ;
        else
            boxX +=  this.imageWidth  - 15 - this.wordsWidth;

        UI.eraseRect(boxX, boxY, this.wordsWidth, this.wordsHeight);
        UI.drawRect(boxX, boxY, this.wordsWidth, this.wordsHeight);
        UI.drawString(words, boxX + 5, boxY + this.wordsHeight/2 + 3);

        UI.sleep(1000);

        UI.eraseRect(boxX, boxY, this.wordsWidth+1, this.wordsHeight+1);
    }

    /** makes the Animal introduce itself with a greeting word */
    public void introduce(String greeting) {
        this.speak(greeting + " my name is " + name);
        this.speak("I am a " + animal);
    }

    /** makes the Animal shout in big block letter */
    public void shout(String words) {
        UI.setFontSize(20);
        this.speak(words.toUpperCase());
        UI.setFontSize(wordSize);
    }

    /** makes the Animal think something in a speech bubble */
    public void think(String words) {
        double bubbleX = this.imageX;
        double bubbleY = this.imageY - this.wordsHeight - 2;
        double circleX = this.imageX;
        double circleY = this.imageY;

        if (this.direction.equals("right")){
            bubbleX += 15 ;
            circleX += this.imageWidth + 20;
        }
        else{
            bubbleX +=  this.imageWidth  - 15 - this.wordsWidth;
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

    /** Helper method that erases the Animal 
     * All the public methods that change the image call erase first */

    private void erase() {
        UI.eraseRect(this.imageX, this.imageY, this.imageWidth+1, this.imageHeight+1);
    }

    /** Helper method that draws the Animal
     * All the public methods that change the image call draw.
     */

    private void draw(){
        String fname = null;
        fname = "animals/" + this.animal + ".gif"; 
        UI.drawImage(fname, this.imageX, this.imageY, this.imageWidth, this.imageHeight);
        UI.sleep(500);
    }

}

