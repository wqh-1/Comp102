// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 2
 * Name: 
 * Username: 
 * ID: 
 */

import ecs100.*;
import java.awt.Color;

/**
 * Reads a date from the user as three integers, and then checks that the date is valid
 */

public class DateValidator {

    /**
     * Asks user for three integers and then checks if it is a valid date.
     */
    public void doCore(){
        int day = UI.askInt("Day:   ");
        int month = UI.askInt("Month: ");
        int year = UI.askInt("Year:  ");
        this.validateDateCore(day, month, year);
    }

    /** CORE
     * Determines whether the date specified by the three integers is a valid date.
     * Should report any components of the date that are invalid
     *  and report whether the whole date is valid or not.
     * For the Core, you may assume that
     * - All months have 31 days, numbered 1 to 31
     * - The months run from 1 to 12
     * - Years start from 1
     */
    public void validateDateCore(int day, int month, int year){
        /*# YOUR CODE HERE */
        if((day >= 1 && day <= 31) && (month >= 1 && month <=12) && (year >=1)){
            UI.println("The date " + day + "/" + month + "/" + year + " is valid"); //if date is between these variables then it is valid
        }
        else{
            if(day < 1 || day > 31){
                UI.println(day + " is not a valid day");
            }
            if(month < 1 || month > 12){
                UI.println(month + " is not a valid month");
            }   
            if(year < 1){
                UI.println(year + " is not a valid year");
            }
            UI.println("The date " + day + "/" + month + "/" + year + " is not valid"); //if not between these variables then it isn't valid
        }
    }

    /**
     * Asks user for three integers and then checks if it is a valid date.
     */
    public void doCompletion(){
        int day = UI.askInt("Day:   ");
        int month = UI.askInt("Month: ");
        int year = UI.askInt("Year:  ");
        this.validateDateCompletion(day, month, year);
    }

    /** COMPLETION
     * Determines whether the date specified by the three integers is a valid date.
     * Reports any components of the date that are invalid
     * Prints if the date is valid or not valid
     * For the Completion, you should check that
     * - Months have the correct number of days
     * - On leap years February can have 29 days.
     *    A year is a leap year if:
     *       - The year can be evenly divided by 4 but not 100, (eg 2016 but not 1900) OR
     *       - The year can be evenly divided by 400    (eg 2000)
     */
    public void validateDateCompletion(int day, int month, int year){
        /*# YOUR CODE HERE */
        if((day>= 1 && day<= 31) && (month>= 1 && month <= 12) && (year >= 1)){
            if(day<= 31 && month== 1|| month== 3|| month== 5|| month== 7||month== 8 ||month== 10||month== 12){ //for the months that have 31 days
                UI.println("The date " + day + "/" + month + "/" + year + " is valid");
            }else if(day<= 30 && month== 4|| month== 6|| month== 9|| month== 11){   //for the months that have 30 days
                UI.println("The date " + day + "/" + month + "/" + year + " is valid");
            }else if(day<= 28 && month== 2){//This only for the month Feb has it has 28 days
                UI.println("The date " + day + "/" + month + "/" + year + " is valid");
            }
            else if((day<= 29 && year%400== 0)||(year%4== 0 && year%100!= 0)){ //leap year
               UI.println("The date " + day + "/" + month + "/" + year + " is valid");
            }else
            {
               UI.println(year +" is not a leap year");
               UI.println(day + "/" + month + "/" + year + " is not valid");
            }        
         }else{
             UI.println("The date " + day + "/" + month + "/" + year + " is valid");  
        }
    }
    
    public void doChallenge(){//This year Dates only
        int day = UI.askInt("Day: ");
        int month = UI.askInt("Month: ");
        int year = 2019;
        this.validateDateChallenge(day,month,year);
    }
    
    public void validateDateChallenge(int day,int month, int year){//Not finished
        if((day>= 1 && day<= 31) && (month>= 1 && month <= 12) && (year >= 1)){
            if(day<= 31 && month== 1|| month== 3|| month== 5|| month== 7||month== 8 ||month== 10||month== 12){ //for the months that have 31 days
                UI.println("The date " + day + "/" + month + "/" + year + " is valid");
            }else if(day<= 30 && month== 4|| month== 6|| month== 9|| month== 11){   //for the months that have 30 days
                UI.println("The date " + day + "/" + month + "/" + year + " is valid");
            }else if(day<= 28 && month== 2){//This only for the month Feb has it has 28 days
                UI.println("The date " + day + "/" + month + "/" + year + " is valid");
            }
            else if((day<= 29 && year%400== 0)||(year%4== 0 && year%100!= 0)){ //leap year
               UI.println("The date " + day + "/" + month + "/" + year + " is valid");
            }else
            {
               UI.println(year +" is not a leap year");
               UI.println(day + "/" + month + "/" + year + " is not valid");
            }        
         }else{
             UI.println("The date " + day + "/" + month + "/" + year + " is valid");  
        }
        
        //Zeller Rule!
        //k = day
        //m = month
        //d = last two digits of the year
        //c = century, this case it is 21
        //template of forumla = k + ((13 * m-1)/5) + d + (d/4) + (c/4) - 2 * c.
        
        int g;
        g = day + ((13*month-1)/5) + 19 + (19/4) + (21/4) - 2 * 21;
        g = g%7;//remainder testing
        //UI.println(g);
        if(g == 0){
            UI.println("The day falls on Sunday");
        }
        else if(g == 1 || g == -1){
            UI.println("The day falls on Monday");
        }
        else if(g == 2 || g == -2){
            UI.println("The day falls on Tuesday");
        }
        else if(g == 3 || g == -3){
            UI.println("The day falls on Wednesday");
        }
        else if(g == 4 || g == -4){
            UI.println("The day falls on Thursday");
        }
        else if(g == 5 || g == -5){
            UI.println("The day falls on Friday");
        }
        else if(g == 6 || g == -6){
            UI.println("The day falls on Saturday");
        }
        else{
            UI.println("error"); //Not 100% complete
        }
        //Note this isn't 100% and not working properly yet.
       
       
    }
    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Validate Date Core", this::doCore );
        UI.addButton("Validate Date Completion", this::doCompletion );
        UI.addButton("Day of week since 1st Jan", this::doChallenge);
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
    }

}