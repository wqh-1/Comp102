// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 2
 * Name:
 * Username: 
 * ID:
 */

import ecs100.*;

/** Program to create simple animated cartoon strips using the
 *  CartoonCharacter class.  
 */

public class CartoonStrip{

    /** tellStory creates two cartoon characters on the window.
     *  Then animates them according to a fixed script by calling a series
     *  of methods on the characters.
     */
    public void tellStory(){
        /*# YOUR CODE HERE */
        CartoonCharacter cc1 = new CartoonCharacter(100,100,"alice");
        CartoonCharacter cc2 = new CartoonCharacter(200,100,"bob");
        
        cc1.walk(10);
        cc1.speak("Hello");
        cc2.lookLeft();
        cc2.speak("hi");
        cc1.speak("do you watch the nfl?");
        cc2.speak("Yes, I'm a patriots fan");
        cc1.speak("So, you like to deflate");
        cc2.frown();
        cc2.speak("No,");
        cc2.speak("why would you say that");
        cc1.speak("Because...");
        cc1.speak("The patriots sucks");
        cc1.smile();
        cc2.speak("I'm done with you");
        cc2.speak("Bye");
        cc2.walk(1000);
        cc1.frown();
        cc1.lookRight();
        cc1.speak("Oh,");
        cc1.speak("It was only a joke");
        cc1.speak("Comeback!");
        cc1.walk(1000);
        
    }

    /** threeDancers creates three cartoon characters on the window.
     *  Then makes each character do the same little dance in turn,
     *  by calling the dance method.
     */
    public void threeDancers(){
        /*# YOUR CODE HERE */
        CartoonCharacter face1 = new CartoonCharacter(150,300, "casey");
        CartoonCharacter face2 = new CartoonCharacter(350,300, "casey");
        CartoonCharacter face3 = new CartoonCharacter(550,300, "casey");
        this.dance(face1);
        this.dance(face2);
        this.dance(face3);
    }

    /** Makes a character do a little dance.
     * Has one parameter - a CartoonCharacter object
     */
    public void dance(CartoonCharacter face){
        /*# YOUR CODE HERE */
        face.lookLeft();
        face.walk(30);
        face.speak("Hey");
        face.lookRight();
        face.walk(30);
        face.speak("Hey");
        face.lookLeft();
        face.walk(30);
        face.speak("Hola!");
    }

}

