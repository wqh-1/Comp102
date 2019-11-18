// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T1, Assignment 4
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

public class RunFileProcessing {
    public static void main(String[] args){
        WordSearcher ws = new WordSearcher();
        GraphPlotter gp = new GraphPlotter();
        InvoicePrinter ip = new InvoicePrinter();
        SalesVisualiser sv = new SalesVisualiser();

        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Plot", gp::plotGraph);
        UI.addButton("Search words", ws::search);
        UI.addButton("Invoice", ip::printInvoice);
        UI.addButton("Sales", sv::graphSales); 
        UI.addButton("quit", UI::quit);

    }
}
