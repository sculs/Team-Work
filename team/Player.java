package team;

import java.util.Scanner;

/**
 * This class implements a black jack player
 *
 * @author marcog
 *
 */
public class Player extends Hand{

//    public static void main(String[] args) {
//        new Player().multiPlayers();
//    }

    private String name;
//    private String [] names;
    private int number;

//    private Card[] hand = new Card[10];
    private Hand hand = new Hand();

//    private int numCards;
//    private int betMoney;

//     * Player constructor
//     *
//     * @param aName     the name of the player


//    public Player(String[] names) {
//        this.names = names;
//    }

    public Player(String name) {

        this.name = name;
//        ser a player´s hand to empty
//        this.emptyHand();
    }

    public Player() {
    }

//    public String getPlayerNumber(){
//        Player player = new Player();
//        int a = player.multiPlayers();
//
//    }

    public Player[] multiPlayers() {

        Scanner sc = new Scanner(System.in);

        System.out.println("1 player(Press \"ENTER\")\nMulti players(Enter the number):");

        int numberOfPlayers = 1;    // default number:1

        String s1 = sc.nextLine();
        boolean inPutNumber = s1.equals("");

        while (!inPutNumber) {
            try {

                numberOfPlayers = Integer.parseInt(s1);
                if (numberOfPlayers > 0){
                    inPutNumber = true;
                }
                else {
                    System.out.println("Number should be above 0:");
                    s1 = sc.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer:");
                s1 = sc.nextLine();
            }
        }


//        System.out.println("Number of Players: " + numberOfPlayers);
//        System.out.println("Name of each players:");

        System.out.println("Type in your own name(s), or just click \"ENTER\"):");

        String s2 = sc.nextLine();

        Player[] players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            // Set value for each player to prevent errors;
            players[i] = new Player();

            // default name for each player, in case some will not type in a name;
            if (s2.equals("")) {
                players[i].setName("player" + (i + 1));
//                names[i] = "player" + (i + 1);
//                players[i].setNames(new String[]{"player" + (i + 1)});
                System.out.println("player" + (i + 1) + " is player "+(i + 1) + ";");
            }
            else {
                // Control the length of each Name, to void console har too much content.
                if (s2.length() > 10){
                    s2 = s2.substring(0, 10);
                }
                players[i].setName(s2);
//                names[i] = s2;
//                playersName[i] = s2;     // store each player's name in "playersName[]";
                System.out.println(s2 + " is player " + (i + 1) + ";");
            }

//            System.out.println(players[i].getName());


            if (i < numberOfPlayers - 1) {
                s2 = sc.nextLine();
            }
        }
        sc.close();
        return players;

    }




//     Reset the player´s hand to have no cards

//
//    public void emptyHand() {
//
//        for (int c = 0; c < 10; c++) {
//            this.hand[c] = null;
//
//        }
//        this.numCards = 0;
//    }
//
////
////     * Add a card to the player´s hand
////     *
////     * @param aCard         the card to add
////     * @return              whether the sum of the new hand is below or equal to 21
////
//    public boolean addCard(Card aCard) {
//
//        //print error if we already have the max number of cards
//        if (this.numCards == 10) {
//            System.err.printf("%s's hand has already 10 cards, " + " cannot add another\n ", this.name);
//            System.exit(1);
//        }
//
//        //add new card in the next slot and increment number of cards counter
//        this.hand[this.numCards] = aCard;
//        this.numCards++;
//
//        return (this.getHandSum() <=21);
//
//
//    }
//
////
////     * Get the sum of the cards in the player´s hand
////     *
////     * @return  the sum
////
//    public int getHandSum() {
//
//        int handSum = 0;
//        int cardNum;
//        int numAces = 0;
//
//        //calc each card´s contribution to the hand sum
//        for (int c = 0; c < this.numCards; c++) {
//
//            //get the number for the current card
//            cardNum = this.hand[c].getNumber();
//
//            if (cardNum == 1) { //Ace
//                numAces++;
//                handSum += 11;
//            } else if (cardNum > 10) { // face card
//                handSum += 10;
//            } else {
//                handSum += cardNum;
//            }
//        }
//
//        //if we have Aces and our sum is > 21, set some/all of them to value 1
//        while (handSum > 21 && numAces > 0) {
//            handSum -= 10;
//            numAces --;
//        }
//        return handSum;
//    }
//
//
////     * Print the cards in the player´s hand
////     *
////     * @param showFirstCard  whether the first card is hidden or not
//
//    public void printHand(boolean showFirstCard) {
//
//        System.out.printf("%s's cards:\n", this.name);
//        for (int c = 0; c < this.numCards; c++) {
//            if (c == 0 && !showFirstCard) {
//                System.out.println("  [hidden]");
//            } else {
//                System.out.printf("  %s\n", this.hand[c].toString());
//            }
//        }
//
//
//    }
//


//    /// indicate Player class har en property of betMoney;
//    public int bet(Player player, int betMoney, boolean playerWin){
//
//        if (player.getBetMoney() > 0 && playerWin){
//            return player.betMoney;
//        }
//        else if (player.getBetMoney() > 0 && !playerWin){
//            return -player.getBetMoney();
//        }
//        else return 0;
//    }

//
//    public int getBetMoney() {
//        return betMoney;
//    }
//
//    public void setBetMoney(int betMoney) {
//        if (betMoney < 0) {
//            this.betMoney = 0;
//        }
//        else this.betMoney = betMoney;
//    }

//    public String getName() {
//        return name;
//    }

//    public void setName(String name) {
//        /// in case player’s name is too long, console will be hard to watch;
//        if (name.length() > 10){
//            this.name = name.substring(0, 10);
//        }
//        this.name = name;
//    }

//    public String[] getNames() {
//        return names;
//    }
//
//    public void setNames(String[] names) {
//        this.names = names;
//    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //    public String[] getNames() {
//        return names;
//    }
//
//    public void setNames(String[] names) {
//        this.names = names;
//    }
}

