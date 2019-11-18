// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 1
 * Name:     
 * Username: 
 * ID:
 */

import ecs100.*;

/** Program for calculating cost of shipping a package */

public class ShippingCalculator{

    public static final double HANDLING = 1.60;    // cost of handling a shipment
    public static final double SIZE_RATE = 300;           // cost per cubic meter
    public static final double WEIGHT_RATE = 1.50;        // cost per kg

    /**
     * Calculates and prints cost of shipping a package.
     */
    public void calculateShippingCore(){
        /*# YOUR CODE HERE */
        double length = UI.askDouble("Length Of Package (cm)");
        double width = UI.askDouble("Width Of Package (cm)");
        double height = UI.askDouble("Height Of Package (cm)");
        double weight = UI.askDouble("Weight Of Package (kg)");
        double zones = UI.askDouble("Number Of Zones (minimum 1)");
        
        double Package = (length/100 * width/100 * height/100 * SIZE_RATE);
        UI.println("size charge per zone: " + ("$") + Package); 
        
        double weight_zone = (weight * 1.50);
        UI.println("weight charge per zone: " + ("$") + weight_zone);
        UI.println("zones: " + ("$") + zones);
        
        double total_charge = (Package + weight_zone) * zones + HANDLING;
        UI.println("Total Charge: " + ("$") + total_charge);
        
    }

    /**
     * Calculates and prints cost of shipping a collection of packages.
     */
    public void calculateShippingCompletion(){
        /*# YOUR CODE HERE */
        double zones = UI.askDouble("Number Of Zones (minimum 1)");
        double Package = UI.askDouble("Number of packages of first size");
        double length = UI.askDouble("Length (cm)");
        double width = UI.askDouble("Width (cm)");
        double height = UI.askDouble("Height (cm)");
        double weight = UI.askDouble("Weight (kg)");
        double sizeCharge1 = (length/100 * width/100 * height/100) * Package * SIZE_RATE;
        double weight_zone1 = (weight * Package * 1.50);
        
        double Package2 = UI.askDouble("Number of packages of second size");
        double length2 = UI.askDouble("Length (cm)");
        double width2 = UI.askDouble("Width (cm)");
        double height2 = UI.askDouble("Height (cm)");
        double weight2 = UI.askDouble("Weight (kg)");
        double sizeCharge2 = (length2/100 * width2/100 * height2/100) * Package2 * SIZE_RATE;
        double weight_zone2 = (weight2 * Package2 * 1.50);
        
        
        double sizeCharge = sizeCharge1 + sizeCharge2;
        double weightCharge = weight_zone1 + weight_zone2;
        double total = HANDLING + (sizeCharge + weightCharge) * zones;
        double num = Package + Package2;
        double discount = total * (1 - 1.0/num)/3;
        
        UI.println("--------------");
        UI.printf("Group 1 size charge per zone: $%.2f%n", sizeCharge1);
        UI.printf("Group 1 weight charge per zone: $%.2f%n", weight_zone1);
        
        UI.printf("Group 2 size charge per zone: $%.2f%n", sizeCharge2);
        UI.printf("Group 2 weight charge per zone: $%.2f%n", weight_zone2);
        
        UI.printf("Total before discount: $%.2f%n", total);
        UI.printf("Discount for " + num +" items: $%.2f%n", discount);
        UI.printf("Total after discount: $%.2f%n", (total - discount));
    }


}
