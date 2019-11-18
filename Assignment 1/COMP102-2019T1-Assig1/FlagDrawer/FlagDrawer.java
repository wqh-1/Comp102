// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 1
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

    public static final double LEFT = 100;  // the left side of the flags
    public static final double TOP = 50;    // the top of the flags

    /**   CORE
     * Draw the flag of Belgium.
     * The flag has three vertical stripes;
     * The left is black, the middle is yellow, and the right is red.
     * The flag is 13/15 as high as it is wide (ratio 13:15).
     */
    public void drawBelgiumFlag(){
        UI.clearGraphics();
        UI.println("Belgium Flag");
        double width = UI.askDouble("How wide: ");
        double height = (13.0/15 * width);
        double center_x = (LEFT + width / 3.0); //center stripe
        double right_x = (LEFT + width * (2.0 / 3)); //right stripe
        UI.setLineWidth(width * 0.005);
        UI.setColor(Color.BLACK);
        UI.fillRect(LEFT, TOP, width, height);
        UI.setColor(Color.YELLOW);
        UI.fillRect(center_x,TOP, width / 3, height);
        UI.setColor(Color.RED);
        UI.fillRect(right_x, TOP, width / 3, height);
        UI.setColor(Color.BLACK);
        UI.drawRect(LEFT, TOP, width, height);
    
    }
    
    /** CORE
     *  The Swiss flag consists of a red square with a white cross in the center
     *  The cross can be drawn as a horizontal rectangle and a vertical rectangle.
     */
    public void drawSwissFlag() {
        UI.clearGraphics();
        UI.println("Swiss Flag: ");
        double size = UI.askDouble("How wide: ");
        double rect_x = (size * (13.0 / 32)); //rectangle top left corner
        double rect_y = (size * (6.0 / 32));  //rectangle top left corner
        double rect_n = (size * (6.0 / 32)); //rectangle narrow side
        double rect_w = (size * (13.0 / 32)) + (size * (13.0 / 32)) - (size * (3.0 / 16)); //rectangle wide side
        UI.setLineWidth(size * 0.005);
        UI.setColor(Color.RED);
        UI.fillRect(LEFT, TOP, size, size);
        UI.setColor(Color.WHITE);
        UI.fillRect(LEFT + rect_x, TOP + rect_y, rect_n, rect_w);
        UI.fillRect(LEFT + rect_y, TOP + rect_x, rect_w, rect_n);
        UI.setColor(Color.BLACK);
        UI.drawRect(LEFT, TOP, size, size);
        

        

    }

    /** COMPLETION
     *  The unofficial flag of Palmyra Atoll has three horizontal stripes with part of a yellow circle.
     *  The flag is 2/3 as high as it is wide (ratio 2:3).
     *  The top stripe is red and is 10/20 of the height of the flag. ci
     *  The middle stripe is blue and is 7/20 of the height of the flag.
     *  The bottom stripe is yellow and is 3/20 of the height of the flag.
     *  The diameter of the yellow circle is 1/2 of the width of the flag, 
     *      and its top is slightly below the top of the flag.
     *  HINT: think in which order to paint the different parts of the flag.
     */
    public void drawPalmyraFlag() {
        UI.clearGraphics();
        UI.println("Palmyra Flag");
        double width = UI.askDouble("How wide: ");
        double height = width * (2 / 3.0);
        double circ_top = TOP + (height / 25.0); //top of yellow circle
        double mid_rect = TOP + (height / 2.0); // top of middle stripe
        double low_rect = TOP + (height * 0.85); //top of lower stripe
        double circ_dia = width / 2.0; //circle width/height
        double circ_left = LEFT + (width / 4.0);
        UI.setLineWidth(width * 0.005);
        UI.setColor(Color.RED);
        UI.fillRect(LEFT, TOP, width, height);
        UI.setColor(Color.YELLOW);
        UI.fillOval(circ_left, circ_top, circ_dia, circ_dia);
        UI.setColor(Color.BLUE);
        UI.fillRect(LEFT, mid_rect, width, height * 0.35);
        UI.setColor(Color.YELLOW);
        UI.fillRect(LEFT, low_rect, width, height * 0.15);
        UI.setColor(Color.BLACK);
        UI.drawRect(LEFT, TOP, width, height);
        
    }


    /** COMPLETION
     * Pacman flag
     * A yellow pacman on a black background chasing white, red, and green dots to the right.
     */
    public  void drawPacman() {
        UI.clearGraphics();        
        UI.println("Pacman Flag");
        double width = UI.askDouble("How wide: ");
        double height = width * (3.0 / 5);
        double pac_dia = width / 2.5;
        double pac_top = TOP + ((height / 2.0) - (pac_dia * 0.5));
        double pac_left = LEFT + (width / 25.0);
        double dot_dia = width / 12.0;
        double dot_top = TOP + (height / 2.0) - (dot_dia / 2.0);
        double dot1_left = LEFT + (width / 2.0);
        double dot2_left = LEFT + (width / 2.0) + (dot_dia * (3.0 / 4)) + dot_dia;
        double dot3_left = LEFT + (width / 2.0) + (2 * (dot_dia * (3.0 / 4)) + (2 * dot_dia));
        UI.setLineWidth(width * 0.005);
        UI.setColor(Color.BLACK);
        UI.fillRect(LEFT, TOP, width, height);
        UI.setColor(Color.YELLOW);
        UI.fillArc(pac_left, pac_top, pac_dia, pac_dia, 35, 290);
        UI.setColor(Color.WHITE);
        UI.fillOval(dot1_left, dot_top, dot_dia, dot_dia);
        UI.setColor(Color.RED);
        UI.fillOval(dot2_left, dot_top, dot_dia, dot_dia);
        UI.setColor(Color.GREEN);
        UI.fillOval(dot3_left, dot_top, dot_dia, dot_dia);
        UI.drawRect(LEFT, TOP, width, height);
    }

    /** CHALLENGE
     *  The flag of the Seychelles
     *  Width = 2 x height
     *  Radiating triangles.
     */
    public  void drawSeychellesFlag(){
        UI.clearGraphics();        
        /*# YOUR CODE HERE */

    }


    /**   CHALLENGE
     * Draw the flag of China,
     * Width = 2/3 height,
     * Red background with larger star in corner with four gold stars around it
     * The little stars all point towards the larger star.
     * A full marks solution will have a method for drawing a 5 pointed star,
     * and call that method for each of the stars
     */
    public void drawChinaFlag() {
        UI.clearGraphics();        
        /*# YOUR CODE HERE */
        UI.setColor(Color.red);
        UI.fillRect(LEFT,TOP,600,400);
        UI.setColor(Color.yellow);
        UI.fillOval(LEFT+50,TOP+50,85,85);
        UI.setColor(Color.red);
        UI.fillArc(LEFT+45,TOP+45,70,70,74,108);
        UI.fillArc(LEFT+40,TOP+65,70,70,145,108);
        UI.fillArc(LEFT+55,TOP+75,70,70,218,108);
        UI.fillArc(LEFT+70,TOP+65,70,70,291,108);
        UI.fillArc(LEFT+65,TOP+45,70,70,0,108);
        UI.setColor(Color.yellow);
        UI.fillOval(LEFT+187,TOP+33,43,43);
        UI.setColor(Color.red);
        UI.fillArc(LEFT+186,TOP+31,35,35,75,108);
        UI.fillArc(LEFT+184,TOP+39,35,35,145,108);
        UI.fillArc(LEFT+190,TOP+43,35,35,218,108);
        UI.fillArc(LEFT+196,TOP+39,35,35,291,108);
        UI.fillArc(LEFT+194,TOP+31,35,35,0,108);
        UI.setColor(Color.yellow);
        UI.fillOval(LEFT+173,TOP+123,43,43);
        UI.setColor(Color.red);
        UI.fillArc(LEFT+172,TOP+121,35,35,75,108);
        UI.fillArc(LEFT+170,TOP+129,35,35,145,108);
        UI.fillArc(LEFT+176,TOP+133,35,35,218,108);
        UI.fillArc(LEFT+182,TOP+129,35,35,291,108);
        UI.fillArc(LEFT+180,TOP+121,35,35,0,108);
        UI.setColor(Color.yellow);
        UI.fillOval(LEFT+130,TOP+160,43,43);
        UI.setColor(Color.red);
        UI.fillArc(LEFT+129,TOP+158,35,35,75,108);
        UI.fillArc(LEFT+127,TOP+166,35,35,145,108);
        UI.fillArc(LEFT+133,TOP+170,35,35,218,108);
        UI.fillArc(LEFT+139,TOP+166,35,35,291,108);
        UI.fillArc(LEFT+137,TOP+158,35,35,0,108);
        UI.setColor(Color.yellow);
        UI.fillOval(LEFT+187,TOP+75,43,43);
        UI.setColor(Color.red);
        UI.fillArc(LEFT+186,TOP+73,35,35,75,108);
        UI.fillArc(LEFT+184,TOP+81,35,35,145,108);
        UI.fillArc(LEFT+190,TOP+85,35,35,218,108);
        UI.fillArc(LEFT+196,TOP+81,35,35,291,108);
        UI.fillArc(LEFT+194,TOP+73,35,35,0,108);
    
    }



}