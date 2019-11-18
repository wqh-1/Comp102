// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 4
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;

public class WordSearcher {

    /**
     * Asks the user for a pattern and then finds and prints out (one per line) all
     *  the words in a dictionary that contain that pattern.
     * At the end, it prints out how many words contained the pattern.
     * It stops printing out words after it has found 100 of them, but it still
     *  prints the total number of matches at the end.
     * The dictionary is in the file dictionary.txt.
     * Hints:
     *   Use the readAllTokens method from the lab exercise
     */
     
    public void search() {
        /*# YOUR CODE HERE */
        ArrayList<String>wordsearch = new ArrayList<String>();
        String FileName = "dictionary.txt"; //file
        String SearchWord = UI.askString("Pattern to search for: ");
        int count = 0;
        try{
            Scanner scan = new Scanner(new File(FileName)); //scan the dictionary.txt file
            while (scan.hasNext()){
                String word = scan.next();
                wordsearch.add(word); //adds it to the wordsearch ArrayList
            }
            for (String words: wordsearch){ // goes through the wordsearch ArrayList
                if (words.contains(SearchWord)){
                    if(count < 100) {
                        UI.println(words);
                    }
                    count++;    //keeps counting
                }
            }
            scan.close();
            UI.println("\nTotal matching words: "+ count);
        } catch (IOException e){UI.println("File failure: " + e);}
    }
}