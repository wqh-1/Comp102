// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 1
 * Name:    
 * Username: 
 * ID: 
 */

import ecs100.*;
import java.awt.Color;

/**
 * Draws various flags
 *
 * You can find lots of flag details (including the correct dimensions and colours)
 * from  http://www.crwflags.com/fotw/flags/    
 */

public class FlagDrawer{

    /**   CORE
     * Draw the flag of France.
     * The flag has three vertical stripes;
     * The left is blue, the right is red and the middle is white.
     * The flag is 2/3 as high as it is wide (ratio 2:3).
     */
    public void drawFranceFlag(double left, double top, double width){
        UI.clearGraphics();
        UI.println("French Flag");
        /*# YOUR CODE HERE */
        double height = (width * 2/3);//Ratio of 2:3 for the height

        UI.setColor(Color.blue);
        UI.fillRect(left,top,width/3,height); // first stripe of the flag
        UI.setColor(Color.white);
        UI.fillRect(left + width/3, top, width/3,height); // second stripe of the flag
        UI.setColor(Color.red);
        UI.fillRect(left + width * 2/3, top, width/3,height); // third stripe of the flag

        UI.setColor(Color.black);
        UI.drawRect(left,top,width,height); //black outline of the flag
    }

    /**   CORE
     * The flag for Uganda consists of 6 strips (black, yellow, red, black, yellow, red)
     * with a white circle in the center.
     */
    public void drawSimplifiedUgandaFlag(double left, double top, double width) {
        UI.println("Simplified version of the flag of Uganda");
        UI.clearGraphics();        
        /*# YOUR CODE HERE */
        double height = width * 2/3;

        UI.setColor(Color.black);
        UI.fillRect(left,top,width,height); // first stripe of the flag, note the height is divide by 6 as there are 6 stripes.

        UI.setColor(Color.yellow);
        UI.fillRect(left,top+height/6,width,height/6); // second stripe of the flag

        UI.setColor(Color.red);
        UI.fillRect(left,top+height/3,width,height/6); // third stripe of the flag

        UI.setColor(Color.black);
        UI.fillRect(left,top+height/2,width,height/6); //4th stripe of the flag

        UI.setColor(Color.yellow);
        UI.fillRect(left,top+height*2/3,width,height/6); //5th stripe of the flag

        UI.setColor(Color.red);
        UI.fillRect(left,top+height*5/6,width,height/6); //6th stripe of the flag

        UI.setColor(Color.white);
        UI.fillOval(left+(width/2-height/6),top+height/3,height/3,height/3); //middle white circle of the flag

        UI.setColor(Color.black);
        UI.drawRect(left,top,width,height); //black outline of the flag

    }

    /**   COMPLETION
     * Draw the flag of Greenland.
     * The top half of the flag is white, and the bottom half is red.
     * There is a circle in the middle (off-set to left)  which is
     * also half white/red but on the opposite sides.
     * See the assignment for dimensions
     */
    public void drawGreenlandFlag(double left, double top, double width) {
        UI.println("Flag of Greenland");
        UI.clearGraphics();
        /*# YOUR CODE HERE */
        double height = width * 2/3;//found the height calculation on http://www.crwflags.com/fotw/flags/

        UI.setColor(Color.red);
        UI.fillRect(left,top,width,height);//Making the Overall background colour of the flag

        UI.setColor(Color.white);
        UI.fillRect(left,top,width,height/2);//The white Rect will overlap half of the flag

        UI.setColor(Color.red);
        UI.fillArc(left+width/6,top+height/6,width*4/9,width*4/9,0,180);//Making the semi circles, in this case it was the red top half. 

        UI.setColor(Color.white);
        UI.fillArc(left+width/6,top+height/6,width*4/9,width*4/9,0,-180);//Making another semi circle

        UI.setColor(Color.black);
        UI.drawRect(left,top,width,height);

    }

    /**   COMPLETION
     * Pacman flag
     * A red pacman on a black background chasing dots to the left.
     * From left to right the dots are blue, yellow, and green
     */
    public  void drawPacman(double left, double top, double width) {
        UI.clearGraphics();        
        UI.println("Pacman Flag");
        /*# YOUR CODE HERE */
        double height = width * 2/3;

        UI.setColor(Color.black);
        UI.fillRect(left,top,width,height);//Overall background

        UI.setColor(Color.red);
        UI.fillArc(left+width*3/5,top+width/6,width/3,width/3,200,320);//Red Pacman on the right handside of the flag 

        UI.setColor(Color.green);
        UI.fillOval(left+width/2,top+width/3-width/22,width/16,width/16);//Green dot closest to the Pacman

        UI.setColor(Color.yellow);
        UI.fillOval(left+width/2-width/6,top+width/3-width/22,width/16,width/16);//Middle dot

        UI.setColor(Color.blue);
        UI.fillOval(left+width/2-width/3,top+width/3-width/22,width/16,width/16);//Furthest away from Pacman/left handside of the flag

        UI.setColor(Color.black);
        UI.drawRect(left,top,width,height);
    }

    /**   CHALLENGE
     * The flag of the Seychelles
     * Width = 2 x height
     * Radiating triangles.
     */
    public  void drawSeychellesFlag(double left, double top, double width){
        UI.clearGraphics();        
        /*# YOUR CODE HERE */
        double height = width/2;

        double x[] = { top, left + width/3, top};
        double y[] = { top, left, top + height};
        UI.setColor(Color.blue);
        UI.fillPolygon(x,y,3);

        double x1[] = {left + width/3, left + width * 2/3, top};
        double y1[] = {top, left,top + height};
        UI.setColor(Color.yellow);
        UI.fillPolygon(x1,y1,3);

        double x2[] = {left + width * 2/3, left + width, top};
        double y2[] = {top, left, top + height};
        UI.setColor(Color.red);
        UI.fillPolygon(x2,y2,3);

        double x3[] = {left + width, left + width, left};
        double y3[] = {top, top + height /3, top + height};     
        UI.setColor(Color.red);
        UI.fillPolygon(x3,y3,3);
        
        double x4[] = {left + width, left + width, left};
        double y4[] = {top + height /3, top + height * 2/3, top + height};
        UI.setColor(Color.white);
        UI.fillPolygon(x4,y4,3);
        
        double x5[] = {left + width, left + width, left};
        double y5[] = {top + height * 2/3, top + height, top + height};
        UI.setColor(Color.green);
        UI.fillPolygon(x5,y5,3);
        
        
        UI.setColor(Color.black);
        UI.drawRect(left,top,width,height); // outline of the flag;
    }

    /**   CHALLENGE
     * Draw the Koru Flag.
     * It was one of the new flag designs for the 2016 referendum,
     * designed by Sven Baker from Wellington
     * The flag is 1/2 as high as it is wide (ratio 1:2).
     */
    public void drawKoruFlag(double left, double top, double width) {
        UI.println("Koru flag");
        UI.clearGraphics();
        /*# YOUR CODE HERE */
        double height = width * 1/2;

        UI.setColor(Color.blue);
        UI.fillRect(left,top,width,height);//right hand side of the flag, makes the whole flag blue before red

        UI.setColor(Color.red);
        UI.fillRect(left,top,width/2,height);//makes half of the flag turn red

        UI.setColor(Color.white);
        UI.fillOval(left+width/4,top-height/16,height+height/8,height+height/8); //creates a white circle in the middle for white outterline of the oval for the koru

        UI.setColor(Color.blue);
        UI.fillOval(left+width*5/16,top+height/16,height-height/8,height-height/8); //creates a blue circle in the middle of the koru flag

        UI.setColor(Color.blue);
        UI.fillOval(left+width*21/32,top+height*2/5,width*4/25,width*4/25); //makes a blue circle on the white ring of the koru flag

        UI.setColor(Color.white);
        UI.fillOval(left+width*42/64,top+height*1/3,width*4/25,width*4/25);//the white circle is above the blue one just made and slightly overlaps it aswell

        UI.setColor(Color.black);
        UI.drawRect(left,top,width,height);//creates the outline of the flag

    }

    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes);
        // CORE
        UI.addButton("Core: Flag of France", ()->this.drawFranceFlag(100,100,UI.askDouble("How wide:")));
        UI.addButton("Core: Simplified Ugandan Flag", ()->this.drawSimplifiedUgandaFlag(100,100,UI.askDouble("How wide:")));
        // COMPLETION
        UI.addButton("Completion: Flag of Greenland", ()->this.drawGreenlandFlag(100,100,UI.askDouble("How wide:")));
        UI.addButton("Completion: Pacman Flag", ()->this.drawPacman(100,100,UI.askDouble("How wide:")));
        // CHALLENGE
        UI.addButton("Challenge: Flag of Seychelles", ()->this.drawSeychellesFlag(100,100,UI.askDouble("How wide:")));
        UI.addButton("Challenge: Koru flag", ()->this.drawKoruFlag(100,100,UI.askDouble("How wide:")));
        UI.addButton("Quit", UI::quit);

        UI.setDivider(0.3);
    }

}
