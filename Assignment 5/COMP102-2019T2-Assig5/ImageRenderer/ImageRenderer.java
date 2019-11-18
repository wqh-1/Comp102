// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 5
 * Name: 
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** Renders plain ppm images onto the graphics panel
 *  ppm images are the simplest possible colour image format.
 */

public class ImageRenderer{
    public static final int TOP = 20;   // top edge of the image
    public static final int LEFT = 20;  // left edge of the image
    public static final int PIXEL_SIZE = 2;  

    // CORE

    /** 
     * Renders a ppm image file.
     * Asks for the name of the file and opens a Scanner
     * then calls renderImageHelper, passing the Scanner.
     */
    public void renderImageCore(){
        /*# YOUR CODE HERE */
        try{
            Scanner scan = new Scanner(new File(UIFileChooser.open("Please choose a file"))); //Lets the user choose a file
            this.renderImageHelper(scan); //calling the renderImageHelper method to the file selected
            scan.close();
        }catch (IOException e){UI.println("File failure: " + e);}

    }

    /** Core:   
     * Renders a ppm image file, given an open Scanner 
     * Renders the image at position (LEFT, TOP).
     * Each pixel of the image is rendered by a square of size PIXEL_SIZE
     * Assumes that
     * - the colour depth is 255,
     * - there is just one image in the file (not "animated"), and
     * - there are no comments in the file.
     * The first four tokens are "P3", number of columns, number of rows, 255
     * The remaining tokens are the pixel values (red, green, blue for each pixel)
     * Hint: to draw the pixels, it is probably easiest to have a nested for loop
     *  that will repeatedly
     *  - read the next three numbers from the scanner
     *  - construct the Color and set UI's color
     *  - draw the pixel.
     *  (ie, don't construct an ArrayList of numbers).
     */
    public void renderImageHelper(Scanner scan){
        /*# YOUR CODE HERE */
        String P3;
        while (scan.hasNextLine()){
            P3 = scan.nextLine();
            if (P3.equals("P3")){ //if it equals the P3 token then carry on
                int rows = scan.nextInt(); //scan the row token, amount of rows of pixels
                int cols = scan.nextInt(); //scan the col token, amount of columns of pixels
                int r,g,b = scan.nextInt(); //sets the r,g,b, colours
                for (int col = 0; col < cols; col++){
                    for (int row = 0; row < rows; row++){
                        r = scan.nextInt(); //scan each int value and set them to the color give by r,g,b
                        g = scan.nextInt();
                        b = scan.nextInt();
                        Color color = new Color (r,g,b); //creates a new color variable
                        UI.setColor(color);
                        UI.fillRect(LEFT+(row * PIXEL_SIZE),TOP+(col * PIXEL_SIZE),PIXEL_SIZE,PIXEL_SIZE); //pos of the image

                    }
                }
            }
        }

    }

    /** Completion
     * Renders a ppm image file which may be animated
     * There may be multiple images in the file, each with their own header and pixel values.
     * Asks for the name of the file, then renders the image at position (LEFT, TOP).
     * Each pixel of the image  is rendered by a square of size PIXEL_SIZE
     * Renders each image in the file in turn with 200 mSec delay.
     * Repeats the whole sequence 3 times.
     */
    public void renderAnimatedImage(){
        /*# YOUR CODE HERE */
        int count = 0;
        try{
            File file = new File(UIFileChooser.open("Please choose a file"));
            Scanner scan = new Scanner(file);
            String P3;

            while (scan.hasNextLine()){
                P3 = scan.nextLine();
                if (P3.equals("P3")){
                    int rows = scan.nextInt();
                    int cols = scan.nextInt();
                    int r,g,b = scan.nextInt();
                    for (int col = 0; col < cols; col++){
                        for (int row = 0; row < rows; row++){
                            r = scan.nextInt();
                            g = scan.nextInt();
                            b = scan.nextInt();
                            Color color = new Color (r,g,b);
                            UI.setColor(color);
                            UI.fillRect(LEFT+(row * PIXEL_SIZE),TOP+(col * PIXEL_SIZE),PIXEL_SIZE,PIXEL_SIZE);

                        }
                        if (scan.hasNext() == false){
                            scan = new Scanner(file); //continues to scan the same file as selected before without asking
                            count++; //increases the count by 1 (count = count + 1)
                            if (count == 3){ //repeats the sequence 3 times before stopping
                                return;
                            }
                        }
                    }
                    UI.sleep(200); //adds a 200 millis delay on the image
                }
            }
            scan.close();
        }catch (IOException e){UI.println("File failure: " + e);}

    }

    public void GreyImage(Scanner scan){
        String P2  = "P2";
        //File file = new File(UIFileChooser.open("Please choose a file"));
        try{
            //Scanner scan = new Scanner(file);
            if(P2.equals("P2")){
                scan.next();
            }
            int cols = scan.nextInt();
            int rows = scan.nextInt();
            int r, g, b, depth, ratio;
            depth = scan.nextInt(); //scan the color depth value
            ratio = (255 / depth); //calculation for the color depth ratio as in on assignment page. eg(255/15)

            int grey = 0;
            for(int row = 0; row < rows;row++){
                for(int col = 0; col < cols;col++){
                    grey = scan.nextInt();
                    r = g = b = grey * ratio; //when making gray with rgb when they are all equal it looks to make the color grey.
                    // g = grey * ratio;      //therefore r,g,b = grey and then we times it by the color depth ratio
                    // b = grey * ratio;      //https://www.rapidtables.com/web/color/gray-color.html
                    Color color = new Color(r,g,b);
                    UI.setColor(color);
                    UI.fillRect(LEFT + col * PIXEL_SIZE, TOP + row * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
                }

            }
            scan.close();
        }catch(Exception e){UI.println("File failure: " + e);}

    }

    public void ChallengeImage(Scanner scan){
        String P3 = "P3";
        //File file = new File(UIFileChooser.open("Please choose a file"));
        try {
            //Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                if (P3.equals("P3")) {
                    scan.next();
                }
                int cols = scan.nextInt();
                int rows = scan.nextInt();
                int r, g, b, depth, ratio;
                depth = scan.nextInt();
                ratio = (255 / depth);

                for (int row = 0; row < rows;row++ ) {
                    for (int col = 0; col < cols;col++ ) {
                        r = scan.nextInt() * ratio;
                        g = scan.nextInt() * ratio;
                        b = scan.nextInt() * ratio;
                        Color color = new Color(r, g, b);
                        UI.setColor(color);
                        UI.fillRect(LEFT + col * PIXEL_SIZE, TOP + row * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
                    }

                }
            }

        }catch(Exception e) { UI.println("File failure: " + e);}
    }

    public String comments(File file){
        String image = " "; 
        String next = " ";
        //UI.println("reading and removing comments");//removes all the comments that start with #
        try{
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
                String line = scan.nextLine(); //scan line
                Scanner scans = new Scanner(line);
                if (line.contains("#")) {
                    if (line.startsWith("#")) {
                        scans.nextLine();
                    }
                    while (scans.hasNext()) {
                        next = scans.next();
                        if (next.startsWith("#")) {
                            scans.nextLine();
                        }else{
                            image = image + next + " ";// makes it empty
                        }
                    }
                }else{
                    image = image + line+" "; //do nothing
                }
            }
            scan.close();
        }catch(IOException e){UI.println("File failure: " + e);}
        //UI.println(image);
        return image; //returns just the values and no comments
    }

    public Scanner StringScanner(String string) {
        Scanner scanner = new Scanner(string);
        return scanner; // this converts it from string to scanner, as then it will be able to read the comments
                        //String cannot be converted to Scanner
    }

    /*
    public void Challenge(){
    String filename = UIFileChooser.open("Please choose a file");
    try{
    File file = new File(filename);
    this.ChallengeImage(StringScanner(comments(file)));
    }catch(Exception e){UI.println("File failure: " + e);}
    }
     */

    public void Format(){
        //this method is meant to recognise and render whatever the first token in the method equal to.
        //for example if the user selected a P3 file it should go through ChallengeImage(StringScanner(comments(file)));
        //and for a P2 file it should go through GreyImage(StringScanner(comments(file))); depending on what the first token is.
        String filename = UIFileChooser.open("Please choose a file");
        File file = new File(filename);
        try{
            Scanner scan = new Scanner(file);
            String format  = scan.next(); //scan the first token as it will either be P3 or P2;
            if(format.equals("P3")){
                this.ChallengeImage(StringScanner(comments(file)));
            }
            else if(format.equals("P2")){
                this.GreyImage(StringScanner(comments(file)));
            }
        }catch(IOException e){UI.println("File failure: " + e);}
    }

    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Render (core)", this::renderImageCore );
        UI.addButton("Render (compl)", this::renderAnimatedImage );
        UI.addButton("Render (chall)", this::Format);
        //UI.addButton("Render ((P3) P3 Image)", this::Challenge);
        //UI.addButton("Render ((P2) Grey Image)", this::GreyImage);
        
        UI.addButton("Quit", UI::quit );
        UI.setWindowSize(850, 700);
        UI.setDivider(0.0);
    }

}



