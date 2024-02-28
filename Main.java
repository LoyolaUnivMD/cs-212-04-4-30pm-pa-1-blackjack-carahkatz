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

        player.hit(playerHand, playerCount);
        playerCount++;
        player.hit(playerHand, playerCount);
        playerCount++;
        playerValue = player.total(playerHand, playerCount, playerValue);

        // Dealer's hand
        String [] dealerHand = new String[11];
        int dealerCount = 0;
        int dealerValue = 0;
        Deck dealer = new Deck(dealerHand, dealerCount);

        dealer.hit(dealerHand, dealerCount);
        dealerCount++;
        dealerHand[1] = "XX";

        // ***BEGINNING GAME***
        // Initial display
        Deck.display(dealerHand, playerHand, playerCount, playerValue);

        // ***PLAYER TURN***
        boolean dealerWins = false;
        // Initial choice

        if (playerValue != 21) {
            System.out.println("Hit(1) or Stay(2)");
            // Get choice to hit or stay with error checking
            int choice = input.nextInt();
            while (choice != 1 && choice != 2) {
                System.out.println("Please enter 1 or 2: ");
                choice = input.nextInt();
            }

            // Loop through choices to hit
            while (choice == 1 && !dealerWins) {
                player.hit(playerHand, playerCount);
                playerCount++;

                playerValue = player.total(playerHand, playerCount, playerValue);
                //System.out.println("Debug: " + playerValue);

                if (playerValue < 21) {
                    Deck.display(dealerHand, playerHand, playerCount, playerValue);
                    System.out.println("Hit(1) or Stay(2)");

                    // Get next choice with error checking
                    choice = input.nextInt();
                    while (choice != 1 && choice != 2) {
                        System.out.println("Please enter 1 or 2: ");
                        choice = input.nextInt();
                    }
                } else if (playerValue == 21) {
                    // If playerValue has become 21, Go to dealer's turn
                    Deck.display(dealerHand, playerHand, playerCount, playerValue);
                    break;
                } else {
                    dealerWins = true;
                    Deck.display(dealerHand, playerHand, playerCount, playerValue);
                    System.out.println("Dealer wins");
                    //break;
                }
            }

            // Exit while loop, aka: stay has been chosen, go to dealer's turn

        }
        // If initial playerValue IS 21, go to dealer's turn IF dealer hasn't already won

        // ***DEALER TURN***
        if (!dealerWins) {
            // Give dealer second card
            dealer.hit(dealerHand, dealerCount);
            dealerCount++;
            dealerValue = dealer.total(dealerHand, dealerCount, dealerValue);
            // Reveal hand & value
            System.out.println("Dealer: " + dealerHand[0] + " " + dealerHand[1]);
            System.out.println(dealerValue);

            while (dealerValue < 17) {
                // Give dealer another card
                dealer.hit(dealerHand, dealerCount);
                dealerCount++;
                dealerValue = dealer.total(dealerHand, dealerCount, dealerValue);

                // Show updated hand/value
                System.out.print("Dealer: ");
                for (int i = 0; i < dealerCount; i++) {
                    //System.out.println("Dealer: " + dealerHand[0] + " " + dealerHand[1]);
                    System.out.print(dealerHand[i] + " ");
                }
                System.out.print("\n");
                System.out.println(dealerValue);
            }
        }

        // ***SEE WHO WON***
        if (playerValue > dealerValue && !dealerWins || dealerValue > 21) {
            System.out.println("Player wins");
        }
        else if (dealerValue > playerValue) {
            System.out.println("Dealer wins");
        }
        else if (dealerValue == playerValue) {
            System.out.println("Tie");
        }
    }
}