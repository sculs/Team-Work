package team;

public enum Suit {

    Clubs("♣︎"), Diamonds("♦︎"), Spades("♠︎"), Hearts("♥︎");

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

		

