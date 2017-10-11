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

        String e;


        if (rank.getRankString().equals("10"))
        e =  "" +
                "┏───────┓\n" +
                "┃       ┃\n" +
                "┃       ┃\n" +
                "┃  " + rank.getRankString() + suit.getSuit() + "  ┃\n" +
                "┃       ┃\n" +
                "┃       ┃\n" +
                "┗───────┛";
        else
            e= "" +
                    "┏───────┓\n" +
                    "┃       ┃\n" +
                    "┃       ┃\n" +
                    "┃  " + rank.getRankString() + " " + suit.getSuit() + "  ┃\n" +
                    "┃       ┃\n" +
                    "┃       ┃\n" +
                    "┗───────┛";

        return e;

    }
}
