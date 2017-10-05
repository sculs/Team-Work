package team;

/**
 * This class implements a black jack player
 *
 * @author marcog
 *
 */
public class Player {
    /**
     * Player´s name
     */
    private String name;

    private Card[] hand = new Card[10];

    private int numCards;

    private int betMoney;

    /**
     * Player constructor
     *
     * @param aName     the name of the player
     */
    public Player(String aName) {

        this.name = aName;

        //ser a player´s hand to empty
//        this.emptyHand();


    }

    public Player() {
    }

    /**
     * Reset the player´s hand to have no cards
     */
    public void emptyHand() {

        for (int c = 0; c < 10; c++) {
            this.hand[c] = null;

        }
        this.numCards = 0;
    }

    /**
     * Add a card to the player´s hand
     *
     * @param aCard         the card to add
     * @return              whether the sum of the new hand is below or equal to 21
     */
    public boolean addCard(Card aCard) {

        //print error if we already have the max number of cards
        if (this.numCards == 10) {
            System.err.printf("%s's hand has already 10 cards, " + " cannot add another\n ", this.name);
            System.exit(1);
        }

        //add new card in the next slot and increment number of cards counter
        this.hand[this.numCards] = aCard;
        this.numCards++;

        return (this.getHandSum() <=21);


    }

    /**
     * Get the sum of the cards in the player´s hand
     *
     * @return  the sum
     */
    public int getHandSum() {

        int handSum = 0;
        int cardNum;
        int numAces = 0;

        //calc each card´s contribution to the hand sum
        for (int c = 0; c < this.numCards; c++) {

            //get the number for the current card
            cardNum = this.hand[c].getNumber();

            if (cardNum == 1) { //Ace
                numAces++;
                handSum += 11;
            } else if (cardNum > 10) { // face card
                handSum += 10;
            } else {
                handSum += cardNum;
            }
        }

        //if we have Aces and our sum is > 21, set some/all of them to value 1
        while (handSum > 21 && numAces > 0) {
            handSum -= 10;
            numAces --;
        }
        return handSum;
    }

    /**
     * Print the cards in the player´s hand
     *
     * @param showFirstCard  whether the first card is hidden or not
     */
    public void printHand(boolean showFirstCard) {

        System.out.printf("%s's cards:\n", this.name);
        for (int c = 0; c < this.numCards; c++) {
            if (c == 0 && !showFirstCard) {
                System.out.println("  [hidden]");
            } else {
                System.out.printf("  %s\n", this.hand[c].toString());
            }
        }


    }


    /// indicate Player class har en property of betMoney;
    public int bet(Player player, int betMoney, boolean playerWin){

        if (player.getBetMoney() > 0 && playerWin){
            return player.betMoney;
        }
        else if (player.getBetMoney() > 0 && !playerWin){
            return -player.getBetMoney();
        }
        else return 0;
    }


    public int getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(int betMoney) {
        if (betMoney < 0) {
            this.betMoney = 0;
        }
        else this.betMoney = betMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        /// in case player’s name is too long, console will be hard to watch;
        if (name.length() > 10){
            this.name = name.substring(0, 10);
        }
        this.name = name;
    }


}
