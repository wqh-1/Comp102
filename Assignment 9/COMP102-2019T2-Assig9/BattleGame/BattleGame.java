// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2019T2, Assignment 9
 * Name: 
 * Username: 
 * ID: 
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 *  Lets a player play a two-player card game (a modified version of Battle).
 *  The player takes up to 5 cards to form a hand of cards. 
 *  The player can reorder the cards in their hand, they can place cards
 *  from their hand onto the table, and they can pick up more cards from the deck of cards
 *  to fill the gaps in their "hand".
 *  For each battle, the player chooses a card from their hand to put on the table.
 *  The other player is controlled by the computer, who will simply take the top card from the deck.
 *
 *  PROGRAM DESIGN
 *  The cards are represented by objects of the Card class.
 *  The Card constructor will construct a new card of the given suit and rank.
 *  Cards have a draw(double left, double top) method that will draw the
 *  Card on the graphics pane at the specified position.
 *  the Card class has a method that returns an ArrayList containing a shuffled deck of 52 cards.
 *  
 *  The program has three key fields:
 *    deck:  an ArrayList containing a shuffled deck of 52 cards.
 *    hand:  an array that can hold 5 Cards. 
 *    table: an arrayList of the Cards that have been placed on the table.
 *    
 *  The hand should be displayed near the bottom of the Graphics pane with a
 *   rectangular border and each card drawn at its place in the hand.
 *  Empty spaces in the hand should be represented by nulls and displayed as empty.
 *
 *  The user can select a position on the hand using the mouse.
 *  The selected card (or empty space) should be highlighted with
 *  a border around it.
 *  
 *  The user can use the "Left" or "Right" button to move the selected card
 *  (or the space) to the left or the right, in which case the card is
 *  swapped with the contents of the adjacent position in the hand.
 *  If the selected position contains a card, the user
 *  can use the "Battle" button to move the selected card on the table and initiate a battle.
 *  
 *  If there are any empty positions in the hand, the user can use the
 *  "Pickup" button to get the top card from the deck which will be added to
 *  the hand at the leftmost empty position.
 *
 *  The table is represented by an ArrayList of cards.
 *  At the beginning of the game the table should be empty, and the hand
 *    should have 5 cards
 *  Cards should be added to the end of the table.
 *  The table should be displayed in rows at the top of the graphics pane.
 */

public class BattleGame{

    public static final int NUM_HAND = 5;      // Number of cards in hand
    public static final int NUM_BATTLE = 12;   // Number of battles per game
    public static final int NUM_REPLACE = 3;   // Number of cards the player is allowed to replace per game

    // Fields: deck, hand, and table
    /*# YOUR CODE HERE */
    private Card[] hand = new Card[NUM_HAND];
    private ArrayList<Card> table = new ArrayList<Card>();
    private ArrayList<Card> deck;

    private int selectedPos = 0;      // selected position in the hand.
    private int battle = 0;           // keep track of how many battles have been played
    private int compScore = 0;        // the number of points scored by the computer player
    private int playScore = 0;        // the number of points scored by the user player
    private int remainingReplaces = NUM_REPLACE;  // 

    // constants for the layout
    public static final Color RACK_COLOR = new Color(122,61,0);

    public static final int HAND_LEFT = 60;      // x-position of the leftmost Card in the hand
    public static final int HAND_TOP = 500;      // y-Position of all the Cards in the hand 
    public static final int CARD_SPACING = 80;   // spacing is the distance from left side of a card
    // to left side of the next card
    public static final int CARD_HEIGHT = 110; 

    public static final int TABLE_LEFT = 10;                
    public static final int TABLE_TOP = 80;

    public static final int SCORES_TOP = 20;

    /**
     * Set up the user interface
     */
    public void setupGUI(){
        /*# YOUR CODE HERE */
        UI.setMouseListener(this::doMouse);
        UI.addButton("Pickup",this::pickupCard);
        UI.addButton("Battle",this::playBattle);
        UI.addButton("Replace",this::replaceCard);
        UI.addButton("Left",this::moveLeft);
        UI.addButton("Right",this::moveRight);
        UI.addButton("Restart",this::restart);
        UI.addButton("Quit",UI::quit);
    }

    /**
     * CORE
     * 
     * Restart the game:
     *  get a shuffled deck,
     *  set the battle, compScore, playScore and remainingReplaces to their initial values
     *  set the table to be empty,
     *  set the hand to have NUM_HAND cards
     */
    public void restart(){
        this.deck = Card.getDeck();
        //UI.println(this.deck);
        battle = 0;
        compScore = 0;
        playScore = 0;
        remainingReplaces = NUM_REPLACE;
        /*# YOUR CODE HERE */
        this.table = new ArrayList<Card>();
        for(int i = 0;i < NUM_HAND;i++){
            this.hand[i] = this.deck.remove(0); 
        }
        this.redraw();
    }

    /**
     * CORE
     * 
     * If the deck is not empty and there is at least one empty position on the hand, then
     * pick up the top card from the deck and put it into the first empty position on the hand.
     * (needs to search along the array for an empty position.)
     */
    public void pickupCard(){
        /*# YOUR CODE HERE */
        if(!this.deck.isEmpty()){
            for(int i = 0; i < this.hand.length;i++){
                if(this.hand[i]== null){
                    this.hand[i] = this.deck.remove(0);
                    break;
                }
            }
        }

        this.redraw();
    }

    /**
     * CORE
     * 
     * Draws the outline of the hand,
     * draws all the Cards in the hand,
     * highlights the selected position in some way
     *
     *  This needs to use the constants:
     *   - CARD_SPACING, HAND_HEIGHT, HAND_LEFT, HAND_TOP
     *   See the descriptions where these fields are defined.
     */
    public void drawHand(){
        /*# YOUR CODE HERE */
        for(int i = 0; i < this.hand.length; i++){
            if(this.hand[i] != null){
                int left = HAND_LEFT + i *(CARD_SPACING);
                this.hand[i].draw(left,HAND_TOP);
            }
        }
        // draw the rack top
        UI.setColor(RACK_COLOR);
        UI.fillRect(HAND_LEFT-10, HAND_TOP-28, (CARD_SPACING)*NUM_HAND+20, 20);

        UI.setLineWidth(2);
        UI.setColor(Color.black);
        UI.drawRect(HAND_LEFT-4,HAND_TOP-4,(CARD_SPACING)*NUM_HAND+8,CARD_HEIGHT+8);
        UI.setLineWidth(2);
        UI.setColor(Color.green);
        UI.drawRect(HAND_LEFT + (this.selectedPos * (CARD_SPACING)) -2, HAND_TOP-2,CARD_SPACING,CARD_HEIGHT+4);

    }

    /**
     * CORE
     *
     * Draws the list of Cards on the table in one long row.
     *  This needs to use the constants:
     *   - CARD_SPACING, TABLE_LEFT, TABLE_TOP, CARD_HEIGHT
     *   See the descriptions where these fields are defined.
     *
     * COMPLETION:
     * 
     * Draws the list of Cards on the table, by pairs representing each battle
     * For each pair:
     * - the computer card is placed on the top row and the player's car on the bottom row.
     * - the card with the highest rank is outlined
     * 
     * CHALLENGE:
     * 
     * For each battle,
     * - the card with the highest rank is outlined
     * 
     * Extend to correctly handle draws in a battle
     * - Stack the secondary battle on top of the previous cards.
     * 
     */
    public void drawTable(){
        UI.setFontSize(20);
        UI.setColor(Color.black);
        UI.drawString("Battle: " + battle, TABLE_LEFT, SCORES_TOP);
        UI.drawString("Player: " + playScore + " Computer: " + compScore, TABLE_LEFT+150, SCORES_TOP);
        UI.drawString("Remaining replaces: " + remainingReplaces, TABLE_LEFT+600, SCORES_TOP);
        if (this.table.isEmpty()) return;
        /*# YOUR CODE HERE */
        for(Card cards:this.table){
            int left = TABLE_LEFT;
            int top = TABLE_TOP;
            int count = 0;
            if(count % 2 ==0){
                cards.draw(TABLE_LEFT,TABLE_TOP);
            }
            else{
                cards.draw(TABLE_LEFT,TABLE_TOP);
                left = left + CARD_SPACING;
            }
            count++;
        }
    }

    /**
     * CORE
     * 
     * Place card from selected position on hand (if there is a card there) to the table
     * The selectedPos field contains the index of the selected card.
     * If a card has been placed on the table, then
     * - gets the top card from the deck for the computer player and places it to the table
     * - compares the ranks of the two cards and award a point to the player with the highest card.
     *   For the core, neither player gets a point if it's a draw.
     * - update the number of battles that have been played
     * 
     */
    public void playBattle(){
        if (battle >= NUM_BATTLE){
            UI.printMessage("Game is finished");
            return;
        }
        /*# YOUR CODE HERE */
        if(this.hand[this.selectedPos]!=null){
            Card play = this.hand[this.selectedPos];
            this.table.add(play);
            this.hand[this.selectedPos] = null;
            if(this.selectedPos<this.hand.length-1){
                this.selectedPos++;
            }

            Card comp = this.deck.remove(0);
            this.table.add(comp);
            this.drawTable();

            if(comp.getRank() < play.getRank()){
                playScore++;
            }else if(comp.getRank() > play.getRank()){
                compScore++;
            }
            battle++;
        }else{
            UI.printMessage("You must select a card to play a battle");
        }
        this.redraw();
    }

    /**
     * COMPLETION
     * 
     * If there is a card at the selected position in the hand, 
     * replace it by another card.
     */
    public void replaceCard(){
        if (remainingReplaces > 0 ){
            /*# YOUR CODE HERE */
            if(this.hand[this.selectedPos]!=null){
                Card temp = this.hand[this.selectedPos];
                this.hand[this.selectedPos] = this.deck.remove(0);
                this.deck.add(temp);
                remainingReplaces--;
            }
        }
        this.redraw();
    }

    /**
     * COMPLETION
     *
     * Swap the contents of the selected position on hand with the
     * position on its left (if there is such a position)
     * and also decrement the selected position to follow the card 
     */
    public void moveLeft(){
        /*# YOUR CODE HERE */
        if(this.selectedPos>0){
            Card leftPos = this.hand[this.selectedPos];
            this.hand[this.selectedPos] = this.hand[this.selectedPos -1];
            this.hand[this.selectedPos - 1] = leftPos;
            this.selectedPos--;
        }
        this.redraw();
    }

    /**
     * COMPLETION
     *
     * Swap the contents of the selected position on hand with the
     *  position on its right (if there is such a position)
     *  and also increment the selected position to follow the card 
     */
    public void moveRight(){
        /*# YOUR CODE HERE */
        if(this.selectedPos<this.hand.length-1){
            Card rightPos = this.hand[this.selectedPos];
            this.hand[this.selectedPos] = this.hand[this.selectedPos +1];
            this.hand[this.selectedPos + 1] = rightPos;
            this.selectedPos++;
        }
        this.redraw();
    }

    /** ---------- The code below is already written for you ---------- **/

    /** 
     * Allows the user to select a position in the hand using the mouse.
     * If the mouse is released over the hand, then sets  selectedPos
     * to be the index into the hand array.
     * Redraws the hand and table 
     */
    public void doMouse(String action, double x, double y){
        if (action.equals("released")){
            if (y >= HAND_TOP && y <= HAND_TOP+CARD_HEIGHT && 
            x >= HAND_LEFT && x <= HAND_LEFT + NUM_HAND*CARD_SPACING) {
                this.selectedPos = (int) ((x-HAND_LEFT)/CARD_SPACING);
                //UI.clearText();UI.println("selected "+this.selectedPos);
                this.redraw();
            }
        }
    }

    /**
     *  Redraw the table and the hand.
     */
    public void redraw(){
        UI.clearGraphics();
        this.drawHand();
        this.drawTable();
        this.endGame();
    }

    /**
     * If game is over display who won.
     */
    public void endGame(){
        if (battle == NUM_BATTLE){
            UI.setFontSize(40);
            UI.setColor(Color.red);
            if (playScore > compScore){
                UI.drawString("YOU WIN!!!", 500, HAND_TOP-80);
            }
            else if (compScore > playScore){
                UI.drawString("YOU LOOSE", 500, HAND_TOP-80);
            }
            else{
                UI.drawString("DRAW", 500, HAND_TOP-80);
            }
        }
    }

    public static void main(String[] args){
        BattleGame bg = new BattleGame();
        bg.setupGUI();
        bg.restart();
    }   

}
