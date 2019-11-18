// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 4
 * Name: 
 * Username: 
 * ID: 
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/**
 * InvoicePrinter
 * Reads an order from a file and prints an invoice
 */

public class InvoicePrinter{

    /**
     * Asks the user for the name of a file containing the details of an order
     *  and then prints out an invoice for the order.
     * Each line of an order file contains an item, a count, and a unit-price.
     * eg: an order line for 3 packets of butter at $4.20 per packet would be
     *    butter      3  4.20
     *  
     * For each line in the order, the invoice must have a line with the
     *  the count, the item, the unit price, and the total price. eg
     *    3 x butter @ $4.20       $12:60
     * 
     * At the end of the invoice, it must print out
     *   the total cost of the order
     *   the GST component of the order
     *   the ex-GST cost.
     * Hints:
     *   This assignment builds directly on the lab exercise!
     *   Use the readAllLines method from the lab exercise and a Scanner for each line
     */
    public void printInvoice() {
        /*# YOUR CODE HERE */
        String fname = UIFileChooser.open();
        ArrayList<String> order = readAllTokens(fname); //using the scanner method below and adding it to the order ArrayList
        double total = 0;
        String item;
        int count;
        double price;
        double cost;
        for(String i : order) {
            Scanner scan = new Scanner(i);
            item = scan.next();
            count = scan.nextInt();
            price = scan.nextDouble();
            cost = count * price;
            UI.print(count + " x ");
            UI.print(item);
            UI.printf(" @ $%.2f ", price);
            UI.printf(" $%.2f\n ", cost);
            total = total + cost;
        }
        
        UI.printf("Total Cost $%.2f\n", total);
        UI.printf("GST $%.2f\n", (total * 0.15/1.15));
        UI.printf("Ex-GST cost $%.2f\n",total/1.15);
    }
         
    /**
     * Utility method: 
     * Read all the tokens in a file into a list of tokens.
     * Returns the list
     */
    public ArrayList<String> readAllTokens(String fname){ //scanner method from Lab Exercise
        ArrayList<String> ans = new ArrayList<String>();
        /*# YOUR CODE HERE */
        try {
            Scanner scan = new Scanner(new File(fname));
            while (scan.hasNext()){
                if (scan.hasNext()){
                    ans.add(scan.nextLine());
                }
                else {
                    scan.next();  // throw away any non-numbers
                }
            }
            scan.close();
        }
        catch(IOException e){UI.println("File reading failed");}
        return ans;
    }
}
