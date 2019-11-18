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

public class CircuitDrawer{

    // fields to store data:
    //  - the tool that the user has selected (which control what component
    //     will be drawn by the mouse)
    //    The tools are "resistor", "capacitor", "source", "wire", "label", or "eraser"
    //  - the mode: whether the component should be horizontal or vertical
    //  - the contents of the label
    //  - the position the mouse was pressed,
    /*# YOUR CODE HERE */
    private double startX;
    private double startY;
    private String Tool = "";
    private String Label = "";
    private String Direction = "Horiz";

    /** Sets up the user interface - mouselistener, buttons, and (completion only) textField */
    public void setupGUI(){
        UI.setMouseListener( this::doMouse );
        UI.setMouseMotionListener(this::doMouse);
        UI.addButton("Clear", UI::clearGraphics);

        /*# YOUR CODE HERE */
        UI.addButton("Resistor",this::setResistor);
        UI.addButton("Capacitor",this::setCapacitor);
        UI.addButton("Source",this::setSource);
        UI.addButton("Wire",this::setWire);
        UI.addTextField("Label",this::setLabel);
        UI.addButton("Eraser",this::setEraser);
        UI.addButton("Horiz/Vert",this::switchDirection);

        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.0);  // Hide the text area.
    }

    // These methods just save Modermation to the fields.
    /** Respond to the resistor button */
    public void setResistor(){
        /*# YOUR CODE HERE */
        eraseTool();
        Mode();
        UI.drawImage("resistor-horizontal.png", 120, 25);
        Tool = "resistor";

    }

    /** Respond to the capacitor button */
    public void setCapacitor(){
        /*# YOUR CODE HERE */
        eraseTool();
        Mode();
        UI.drawImage("capacitor-horizontal.png", 120, 25);
        Tool = "capacitor";

    }

    /** Respond to the source button */
    public void setSource(){
        /*# YOUR CODE HERE */
        eraseTool();
        Mode();
        UI.drawOval(130, 25, 20, 20);
        UI.drawLine(130, 35, 122, 35);
        UI.drawLine(150, 35, 158, 35);
        Tool = "source";
    }

    /** Respond to the wire button */
    public void setWire(){
        /*# YOUR CODE HERE */
        eraseTool();
        Mode();
        UI.drawLine(130, 25, 140, 25);
        Tool = "wire";

    }

    /** Respond to the eraser button */
    public void setEraser(){
        /*# YOUR CODE HERE */
        eraseTool();
        Mode();
        UI.drawString("Eraser", 130, 35);
        Tool = "eraser";

    }
    /** Respond to the text field (completion only) */
    public void setLabel(String v){
        /*# YOUR CODE HERE */
        eraseTool();

        Label = v;
        UI.drawString("Label",130, 35);
        Tool = "label";
    }

    /**
     * Respond to the horiz/vert button (completion only)
     * Switches the mode from horizontal to vertical, or back
     * Ideally, (Challenge) it should show the user what the current state is,
     * eg by drawing a horizonal/vertical bar in the top left corner,
     * or by calling setText on the button to change the label
     */
    public void switchDirection(){
        /*# YOUR CODE HERE */
        Mode();
        if(Direction.equals("Horiz")){
            UI.eraseString("Mode: " + Direction, 15, 13);
            Direction = "Vert";
            UI.drawString("Mode: " + Direction, 15, 13);
            UI.eraseRect(25, 25, 22, 10);
            UI.drawRect(25, 25, 10, 22);

        }
        else {
            UI.eraseString("Mode: " + Direction, 15, 13);
            Direction = "Horiz";
            UI.drawString("Mode: " + Direction, 15, 13);
            UI.eraseRect(25, 25, 10, 22);
            UI.drawRect(25, 25, 22, 10);
        }

    }
    // Methods to change the tool that controls what will be drawn next
    public void Mode(){
        UI.drawString("Mode: " + Direction, 15, 13);
        if(Direction.equals("Horiz")){
            UI.eraseRect(25, 25, 10, 22);

        } else {
            UI.eraseRect(25, 25, 22, 10);

        }
        UI.drawString("Tool: ", 120, 13);
    }

 
    public void eraseTool(){
        if(Tool.equals("source")){
            UI.eraseOval(130, 25, 20, 20);
            UI.eraseLine(130, 35, 122, 35);
            UI.eraseLine(150, 35, 158, 35);
        } else if(Tool.equals("wire")){
            UI.eraseLine(130, 25, 140, 25);
        } else if(Tool.equals("eraser")){
            UI.eraseString("Eraser", 130, 35);
        } else if(Tool.equals("label")){
            UI.eraseString("Label", 130, 35);
        }
        else{
            UI.eraseImage(Tool + "-horizontal.png", 120, 25);
        }
    }

 
    /**
     * Respond to mouse events
     * When pressed, remember the position.
     * When released, draw what is specified by current tool
     * Uses the value stored in the field to determine which kind of component to draw (or to erase)
     *  It should call the drawWire, drawResistor, drawCapacitor, drawSource, drawLabel,
     *  or erase, methods passing the x and y where the mouse was released.
     */
    public void doMouse(String action, double x, double y) {
        /*# YOUR CODE HERE */
        if(action.equals("pressed")) {
            startX = x;
            startY = y;
        }
        else if(action.equals("dragged")){
            if(Tool.equals("eraser")){
                erase(x,y);
            }
        }
        else if (action.equals("released")){
            if(Tool.equals("resistor")){
                drawResistor(x, y);
            }
            else if(Tool.equals("capacitor")) {
                drawCapacitor(x, y);
            }
            else if(Tool.equals("source")){
                drawSource(x, y);
            }
            else if(Tool.equals("wire")){
                drawWire(x, y);
            }
            else if(Tool.equals("eraser")){
                //erase(x, y);
            }
            else if(Tool.equals("label")){
                drawLabel(x, y);
            }
        }

 
    }
    /**
     * Draw a resistor centered at the point x, y.
     * (either a thin rectangle with short wires, or a zig-zag.)
     * Core: only horizontal required
     * Completion: horizontal or vertical depending on the mode.
     */
    public void drawResistor(double x, double y){
        double length = 31;    // size in the longer  dimension
        double width = 11;     // size in the shorter dimension
        double stub = 10;      // the length of the wires on each end
        /*# YOUR CODE HERE */
        if(Direction.equals("Horiz")){
            UI.drawRect(x-16, y-6, length, width);
            UI.drawLine(x-16, y, x-26, y);
            UI.drawLine(x+16, y, x+26, y);
        }
        else {
            UI.drawRect(x-6, y-16, width, length);
            UI.drawLine(x, y-16, x, y-26);
            UI.drawLine(x, y+16, x, y+26);
        }

    }

    /**
     * Draw a capacitor centered at the point x, y.
     *  (Two parallel lines with wires on each side)
     * Core: only horizontal required
     * Completion: horizontal or a vertical, depending on the mode.
     */
    public void drawCapacitor(double x, double y){
        /*# YOUR CODE HERE */
        if(Direction.equals("Horiz")){
            UI.drawLine(x-3, y-9, x-3, y+9);
            UI.drawLine(x+3, y-9, x+3, y+9);
            UI.drawLine(x-3, y, x-15, y);
            UI.drawLine(x+3, y, x+15, y);
        }
        else {
            UI.drawLine(x-9, y-3, x+9, y-3);
            UI.drawLine(x-9, y+3, x+9, y+3);
            UI.drawLine(x, y-3, x, y-15);
            UI.drawLine(x, y+3, x, y+15);
        }
    }

    /**
     * Draw a source centered at the point x, y.
     *  (Circle with wires on each side)
     * Core: only horizontal required
     * Completion: horizontal or vertical, depending on the mode.
     */
    public void drawSource(double x, double y){
        /*# YOUR CODE HERE */
        if(Direction.equals("Horiz")){
            UI.drawOval(x-11, y-11, 20, 20);
            UI.drawLine(x-11, y, x-19, y);
            UI.drawLine(x+11, y, x+19, y);
        }
        else {
            UI.drawOval(x-11, y-11, 20, 20);
            UI.drawLine(x, y-11, x, y-19);
            UI.drawLine(x, y+11, x, y+19);
        }

    }

    /**
     * Draw a wire from the point where the user pressed the mouse to the point x,y.
     * Core: may be a straight line.
     * Completion: The wire should have a horizontal part followed by a vertical part
     * If the distance between the two points is very small, it just puts a circle at (x y)
     */
    public void drawWire(double x, double y){
        /*# YOUR CODE HERE */
        if(x-startX<5 && x-startX > -5 && y-startY<5){
            UI.fillOval(x-3, y-3, 5, 5);
        }
        else {
            UI.drawLine(startX, startY, x, startY); //horizontal line
            UI.drawLine(x, startY, x, y); //veritcal line
        }

    }

    /**
     * Erase a circular region around the position x, y
     * Should be big enough to erase a single component.
     */
    public void erase(double x, double y){
        /*# YOUR CODE HERE */
        UI.eraseOval(x-26, y-26, 52, 52);
    }

    /**
     * Draw a label at position x, y.  Always horizontal.
     * Uses the label that was stored in a field.
     * Completion only.
     */
    public void drawLabel(double x, double y){
        /*# YOUR CODE HERE */
        UI.drawString(Label, x, y);
    }

    // Main:  create a new CircuitDrawer instance and call the setGUI method
    public static void main(String[] arguments){
        CircuitDrawer cd = new CircuitDrawer();
        cd.setupGUI();
    }  

}