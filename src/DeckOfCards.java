import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class Card {
    public String suit, value;
    int rank;

    Card(String suit, String value, int rank) {
        this.suit = suit;
        this.value = value;
        this.rank = rank;
    }
}

class Player {
    ArrayList<Card> playerCards = new ArrayList<Card>();

    public void saveCards(Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
            playerCards.add(cards[i]);
        }
    }

    public void sortCards() {
        Collections.sort(playerCards, new cardComparator());
    }

    public void showCards() {
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.print(playerCards.get(i).suit + playerCards.get(i).value + " ");
        }
        System.out.println("\n");
    }
}

class cardComparator implements Comparator<Card> {
    @Override
    public int compare(Card a, Card b) {
        if (a.rank == b.rank)
            return 0;
        else if (a.rank > b.rank)
            return -1;
        else
            return 1;
    }
}

class playerComparator implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        for (int i = 0; i < a.playerCards.size(); i++) {
            if (a.playerCards.get(i).rank > b.playerCards.get(i).rank) {
                return -1;
            }
            if (a.playerCards.get(i).rank < b.playerCards.get(i).rank) {
                return 1;
            }
        }
        return 0;
    }
}

public class DeckOfCards {
    static String Suit[] = { "Clubs", "Diamonds", "Hearts", "Spades" };
    static String ranks[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
    static Card Players[][] = new Card[4][9];

    public static void distributeCards(ArrayList<Card> allCards) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                Players[i][j] = allCards.get((i * 9) + j);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("\n");

        ArrayList<Card> allCards = new ArrayList<Card>();
        HashMap<String, Integer> cardsRank = new HashMap<String, Integer>();

        for (int i = 0; i < ranks.length; i++) {
            cardsRank.put(ranks[i], i);
        }
        for (int i = 0; i < Suit.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                String suit = Suit[i];
                String value = ranks[j];
                int rank = cardsRank.get(value);
                Card newCard = new Card(suit, value, rank);
                allCards.add(newCard);
            }
        }

        Collections.shuffle(allCards);

        distributeCards(allCards);

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        player1.saveCards(Players[0]);
        player2.saveCards(Players[1]);
        player3.saveCards(Players[2]);
        player4.saveCards(Players[3]);

        System.out.println("Cards of Player 1 before sorting\n");
        player1.showCards();
        player1.sortCards();
        System.out.println("Cards of Player 1 after sorting\n");
        player1.showCards();

        player2.sortCards();
        player3.sortCards();
        player4.sortCards();

        ArrayList<Player> PlayersList = new ArrayList<Player>();
        PlayersList.add(player1);
        PlayersList.add(player2);
        PlayersList.add(player3);
        PlayersList.add(player4);

        System.out.println("Players before sorting\n");
        for (int i = 0; i < PlayersList.size(); i++) {
            PlayersList.get(i).showCards();
        }

        Collections.sort(PlayersList, new playerComparator());

        System.out.println("Players after sorting\n");
        for (int i = 0; i < PlayersList.size(); i++) {
            PlayersList.get(i).showCards();
        }
    }
}
