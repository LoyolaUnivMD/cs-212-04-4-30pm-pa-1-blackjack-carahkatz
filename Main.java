/**
 *
 // Input for isDigit equivalent failsafe: https://howtodoinjava.com/java/io/read-typesafe-input-scanner-class/
 while (!input.hasNextInt()) {
 input.nextLine();
 System.out.println("Please enter 1 or 2: ");
 }
 */

//pa1
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("A game of Blackjack:");

        // ***CREATE STARTING HANDS***
        // Player's hand
        String [] playerHand = new String[11];
        int playerCount = 0;
        int playerValue = 0;
        Deck player = new Deck(playerHand, playerCount);

        Deck.hit(playerHand, playerCount);
        playerCount++;
        Deck.hit(playerHand, playerCount);
        playerCount++;
        playerValue = Deck.total(playerHand, playerCount, playerValue);

        // Dealer's hand
        String [] dealerHand = new String[11];
        int dealerCount = 0;
        int dealerValue = 0;
        Deck dealer = new Deck(dealerHand, dealerCount);

        Deck.hit(dealerHand, dealerCount);
        dealerCount++;
        dealerHand[1] = "XX";

        // ***BEGINNING GAME***
        // Initial display
        Deck.display(dealerHand, playerHand, playerCount, playerValue);

        // Initial choice
        if (playerValue != 21) {
            // Get choice to hit or stay with error checking
            int choice = input.nextInt();
            while (choice != 1 && choice != 2) {
                System.out.println("Please enter 1 or 2: ");
                choice = input.nextInt();
            }

            // Loop through choices to hit
            while (choice == 1) {
                Deck.hit(playerHand, playerCount);
                playerCount++;

                playerValue = Deck.total(playerHand, playerCount, playerValue);
                //System.out.println("Debug: " + playerValue);

                if (playerValue < 21) {
                    Deck.display(dealerHand, playerHand, playerCount, playerValue);

                    // Get next choice with error checking
                    choice = input.nextInt();
                    while (choice != 1 && choice != 2) {
                        System.out.println("Please enter 1 or 2: ");
                        choice = input.nextInt();
                    }
                } else if (playerValue == 21) {
                    // Go to dealer's turn
                } else {
                    System.out.println("Dealer wins");
                    break;
                }
            }

            // Once stay is chosen, go to dealer's turn


        }
        // If initial playerValue IS 21, go to dealer's turn
    }
}