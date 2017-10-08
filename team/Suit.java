package team;

public enum Suit {

//    Clubs,      //(♣)\u2663
//    Diamonds,   //(♦)\u2666
//    Spades,     //(♠)\u2660
//    Hearts,     //(♥)\u2665

    Clubs("♣"), Diamonds("♦"), Spades("♠"), Hearts("♥");

    private String suit;

    private Suit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString(){
        return suit;
    }
}


