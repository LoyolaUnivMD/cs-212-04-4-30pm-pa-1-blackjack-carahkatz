//pa1
import java.util.Scanner;  // Import the Scanner class

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("A game of Blackjack:");

        // Create starting hands
        // PLAYER
        String [] playerHand = new String[11];
        int playerCount = 0;
        Deck player = new Deck(playerHand, playerCount);

        Deck.hit(playerHand, playerCount);
        playerCount++;
        Deck.hit(playerHand, playerCount);
        playerCount++;


        // DEALER
        String [] dealerHand = new String[11];
        int dealerCount = 0;
        Deck dealer = new Deck(dealerHand, dealerCount);

        Deck.hit(dealerHand, dealerCount);
        dealerCount++;
        dealerHand[1] = "XX";
        dealerCount++;

        // Initial display
        Deck.display(dealerHand, playerHand);



    }
}