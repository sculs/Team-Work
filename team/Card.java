package team;

public class Card {

    private Suit suit;
    private Rank rank;


    public Card(Rank rank, Suit suit) {
        super();
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public String toString() {

        String s;


        if (rank.getRankString().equals("10"))
        s =  "" +
                "┏───────┓\n" +
                "┃       ┃\n" +
                "┃       ┃\n" +
                "┃  " + rank.getRankString() + suit.getSuit() + "  ┃\n" +
                "┃       ┃\n" +
                "┃       ┃\n" +
                "┗───────┛";
        else
            s= "" +
                    "┏───────┓\n" +
                    "┃       ┃\n" +
                    "┃       ┃\n" +
                    "┃  " + rank.getRankString() + " " + suit.getSuit() + "  ┃\n" +
                    "┃       ┃\n" +
                    "┃       ┃\n" +
                    "┗───────┛";

        return s;

    }
}
