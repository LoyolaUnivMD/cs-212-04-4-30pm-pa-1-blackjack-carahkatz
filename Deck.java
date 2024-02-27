import java.util.Scanner;
import java.util.Random;

public class Deck {
    Scanner input = new Scanner(System.in);

    // Initialize deck array
    String [] deck = new String[]{"AS", "1S", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AD", "1D", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AC", "1C", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AH", "1H", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH"};

    // Initialize values from call instance
    int count;
    String [] cards;
    int cardValue;

    public Deck(int count, String [] cards){
        this.count = count;
        this.cards = cards;
    }

    // Draw method returns random value from this array
    public String[] hit() {
        // From the 52 card deck, select a random card
        int randomIndex = new Random().nextInt(52);
        String drawnCard = deck[randomIndex];
        // Put this card into the next open slot
        // Logic: If count is 1, there is only a card in slot 0, so the next card is put into slot cards[count] aka slot 1
        cards[count] = drawnCard;
        return cards;
    }

    // Total method tallies each card's value
    public int total(String[] cards, int count, int total) {
        // Initialize total to 0
        total = 0;
        // For each card until the total amount of cards has been reviewed,
        for (int i = 0; i <= count; i++) {
            // Select the current card
            String card = cards[i];
            // If a face card or 10, value 10
            if (card.contains("J") || card.contains("Q") || card.contains("K") || card.contains("10")) {
                cardValue = 10;
            }
            // If an ace, value 11 if total is less than or equal to 10 and 1 if more than 10
            else if (card.contains("A")) {
                if (total <= 10) {
                    cardValue = 11;
                } else {
                    cardValue = 1;
                }
            }
            // All other cards are single digit number cards and their value is the number they represent
            else {
                cardValue = card.charAt(0);
            }
            total += cardValue;
        }
        return total;
    }



}
