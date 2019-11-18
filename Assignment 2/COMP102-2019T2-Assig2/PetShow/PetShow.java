// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 2
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

/** Program to create simple animated animal character using the
 *  Animal class.  
 */

public class PetShow{

    /** animate creates two animals on the window.
     *  Then animates them according to a fixed script by calling a series
     *  of methods on the animals.
     *  
     *  CORE
     */
    public void animate(){
        /*# YOUR CODE HERE */
        Animal a1 = new Animal("grasshoper","Berthie",140,100);
        Animal a2 = new Animal("Turtle","leo",350,100);
        a1.goForward(20);
        
        a1.introduce("Hi ");
        a2.shout("Hi ");
        
        a2.goForward(90);
        a2.introduce("Hi");
        a1.speak("Why did you, say that twice");
        a2.speak("Thought you didn't hear me");
        
        a1.speak("Well it was nice to meet you");
        a1.jump(25);
        a2.speak("Nice to meet you too");
        a2.jump(15);
        
        a1.speak("Goodbye");
        a2.speak("Cya");
        a1.goForward(1000);
        a2.goBackward(1000);
        

    }

    /** threeAnimalsRoutine creates three animals on the window.
     *  Then makes each animal do the same routine in turn.
     *  You should define a routine method, and threeAnimalsRoutine
     *   should call the routine method three times, to make
     *   each of the three animals perform the routine in turn.
     *   
     *   COMPLETION
     */
    public void threeAnimalsRoutine(){
        /*# YOUR CODE HERE */
        Animal a3 = new Animal("Turtle","Berthie",200,150);
        Animal a4 = new Animal("Tiger","Pat",300,80);
        Animal a5 = new Animal("Snake", "Ben",400,120);
        this.Routine(a3);
        this.Routine(a4);
        this.Routine(a5);
        
    }
    
    public void Routine(Animal Animal){
        Animal.goForward(20);
        Animal.introduce("Hi ");
        Animal.jump(20);
        Animal.goBackward(40);
        Animal.speak("I've gotta go");
        Animal.speak("Cya later");
        Animal.shout("GoodBye");
        Animal.goForward(1000);
       
    }

    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Animate", this::animate );
        UI.addButton("Three", this::threeAnimalsRoutine );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(0);       // Expand the graphics area
    }

}
