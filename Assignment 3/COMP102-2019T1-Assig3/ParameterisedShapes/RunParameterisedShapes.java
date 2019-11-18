// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 3
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

/** Run ParameterisedShapes methods */
public class RunParameterisedShapes{

    public static void main(String[] arguments){
        ParameterisedShapes ps = new ParameterisedShapes ();
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes );
        UI.addButton("Core", ps::doCore );
        UI.addButton("Completion", ps::doCompletion );
        UI.addButton("Quit", UI::quit );
    }
}
