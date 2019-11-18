// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 7
 * Name: 
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

public class MiniPaint{

    // fields to remember:
    //  - the shape that will be drawn when the mouse is next released.
    //  - whether the shape should be filled or not
    //  - the position the mouse was pressed,
    //  - the name of the image file
    //  - the colour used to draw later shapes
    /*# YOUR CODE HERE */
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private String shapes;
    private String imagename;
    private Color c;
    private boolean fill = false;

    // Methods to change the shape that will be drawn when the mouse is next released.
    // These methods just save information to the fields.
    /** Respond to the Line button */
    public void doSetLine(){
        /*# YOUR CODE HERE */
        shapes = "Line";
    }

    /** Respond to the Rect button */
    public void doSetRect(){
        /*# YOUR CODE HERE */
        shapes = "Rectangle";
    }

    /** Respond to the Oval button */
    public void doSetOval(){
        /*# YOUR CODE HERE */
        shapes = "Oval";
    }

    /** Respond to the Image button */
    public void doSetImage(){
        /*# YOUR CODE HERE */
        imagename = UIFileChooser.open();
        shapes = "Image";
    }

    /** Respond to the Colour button */
    public void doSetColour(){
        /*# YOUR CODE HERE */
        this.c = JColorChooser.showDialog(null, "Choose Color", null);
    }

    /** Respond to the Fill/Nofill button */
    public void doSetFill(){
        /*# YOUR CODE HERE */
        fill = !fill;  
    }

    /**
     * Respond to mouse events
     * When pressed, remember the position.
     * When released, draw what is specified by current shape
     * Uses the value stored in the field to determine which kind of shape to draw.
     *  It should call the drawALine, drawARectangle, drawAnOval, or drawAnImage, methods
     *  passing the x and y where the mouse was released.
     */
    public void doMouse(String action, double x, double y){
        /*# YOUR CODE HERE */
        if(action.equals("pressed")){
            this.startX = x;
            this.startY = y;
        }
        else if(action.equals("released")){
            UI.setColor(c);
            if(shapes.equals("Line")){
                drawALine(x,y);
            }
            else if(shapes.equals("Rectangle")){
                drawARectangle(x,y);
            }
            else if(shapes.equals("Oval")){
                drawAnOval(x,y);
            }
            else if(shapes.equals("Image")){
                drawAnImage(x,y);
            }
        }  
    }

    /**
     * Draw a line between the mouse pressed and mouse released points.
     * x and y are the position the mouse was released.
     */
    public void drawALine(double x, double y){
        /*# YOUR CODE HERE */
        UI.drawLine(startX,startY,x,y);
    }

    /**
     * Draw a rectangle between the mouse pressed and mouse released points.
     * x and y are the position the mouse was released.
     * Works out the left, top, width, and height from x, y, lastX and lastY
     * Then draws the rectangle, filled or outline, depending on the fill field.
     */
    public void drawARectangle(double x, double y){
        /*# YOUR CODE HERE */
        if(fill){
            UI.fillRect(startX,startY,x - startX,y - startY);
        }
        else{
            UI.drawRect(startX,startY,x - startX,y - startY);
        }
    }

    /**
     * Draw an oval between the mouse pressed and mouse released points.
     * x and y are the position the mouse was released.
     * Works out the left, top, width, and height from x, y, lastX and lastY
     * Then draws the oval, filled or outline, depending on the fill field.
     */
    public void drawAnOval(double x, double y){
        /*# YOUR CODE HERE */
        if(fill){
            UI.fillOval(startX,startY,x - startX,y - startY);
        }else{
            UI.drawOval(startX,startY,x - startX,y - startY);
        }
    }

    /**
     * Draws the current image between the mouse pressed and mouse released points.
     * x and y are the position the mouse was released.
     * Works out the left, top, width, and height from x, y, lastX and lastY
     * Then draws the image, if there is one.
     */
    public void drawAnImage(double x, double y){
        /*# YOUR CODE HERE */
        UI.drawImage(imagename,startX,startY,x-startX,y-startY);
    }

    // Main:  constructs a new MiniPaint object and set up GUI
    public static void main(String[] arguments){
        MiniPaint mp = new MiniPaint();
        UI.setMouseListener(mp::doMouse);
        UI.addButton("Clear", UI::clearGraphics);
        /*# YOUR CODE HERE */
        //UI.setMouseMotionListener(mp::doMouse);
        UI.addButton("Line", mp::doSetLine);
        UI.addButton("Rectangle", mp::doSetRect);
        UI.addButton("Oval", mp::doSetOval);
        UI.addButton("Image",mp::doSetImage);
        UI.addButton("Colour",mp::doSetColour);
        UI.addButton("Fill/Nofill",mp::doSetFill);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.0);  // Hide the text area.
    }
}