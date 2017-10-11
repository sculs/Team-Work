package team;

public class Hand {

    private Card [] cards = new Card[10];
    private int numCards;


    // Constructor ////////////////////////////
    public Hand(Card[] cards, int numCards) {
        this.cards = cards;
        this.numCards = numCards;
    }

    public Hand() {
    }


    /// Methods //////////////////////////////
    public void empty(){
        for (int i = 0; i < 10; i++) {
            cards[i] = null;
        }
        this.numCards = 0;
    }

    public boolean addCard(Card card){

        if (numCards == 10) {
            System.out.println("Player may max have 10 cards.");
            System.exit(1);
        }

        //add new card in the next slot and increment number of cards counter
        this.cards[numCards] = card;
        this.numCards++;

        return (this.getSum() <=21);
    }

    public int getSum() {
        int sum = 0;
        int cardNum;
        int numAces = 0;

        //calculate each card´s contribution to the sum
        for (int i = 0; i < this.numCards; i++) {

            //get the number for the current card
            cardNum = cards[i].getRank().getRankValue();

            if (cardNum == 11) {    //Ace
                numAces ++;
                sum += 11;
            }
            else if (cardNum > 10) { // face card
                sum += 10;
            }
            else {
                sum += cardNum;
            }
        }

        //if we have Aces and our sum is > 21, set some/all of them to value 1
        while (sum > 21 && numAces > 0) {
            sum -= 10;
            numAces --;
        }

        return sum;
    }


    public void printHand(String name, boolean showFirstCard){

        System.out.printf("%s's cards:%n", name);

        for (int i = 0; i < this.numCards; i++) {

            if (i == 0 && !showFirstCard) {
                System.out.println(" [hidden]");

            } else {
                System.out.println(cards[i].toString());
            }
        }
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public int getNumCards() {
        return numCards;
    }

    public void setNumCards(int numCards) {
        if (numCards < 0){
            this.numCards = 0;
        }
        this.numCards = numCards;
    }

}  