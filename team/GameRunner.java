package team;

import java.util.Scanner;

public class GameRunner {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new GameRunner().run();

    }

    private void run() {

/////// Preface for new variables, Objects, etc.//////////////////////

        System.out.println("Welcome to BLACKJACK created by Team 2!");
        System.out.println("---------------------------------------");
        Game game = new Game();
        Player play = new Player();
        Player[] players = play.multiPlayers();   /// multi players;
        Player dealer = new Player("Dealer");

        int numberOfPlayers = players.length;
        boolean oneDone = false;
        boolean dealerDone = false;
        int money[] = new int[numberOfPlayers];
        int bet[] = new int[numberOfPlayers];
        int points[] = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++)
            money[i] = 100;
        Deck theDeck = new Deck(numberOfPlayers, true);

////////////////////////////////////////////////////////////////////////////////

        // The Game Starts!!!
        for (;;){   // Loop here is prepared for additional rounds;

//-------------------------------------------------------------------------------

            // Firstly, Request for options of individual bet;
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("\n" + players[i].getName() +
                        ", Bet amount(You have " +money[i] + "$):" +
                        "\nNo bet? press \"ENTER\":");
                String answer1 = sc.nextLine();
                while (!game.checkNumber(answer1)) {
                    answer1 = sc.nextLine();
                }
                bet[i] = answer1.equals("") ? 0 : Integer.parseInt(answer1);
                money[i] -= game.bet(money[i], bet[i]);
            }

//-------------------------------------------------------------------------------

            // Secondly, Hand out first two cards for all players & the dealer;
            for (int i = 0; i < numberOfPlayers; i++) {
                players[i].addCard(theDeck.dealNextCard());
                players[i].addCard(theDeck.dealNextCard());
            }
            dealer.addCard(theDeck.dealNextCard());
            dealer.addCard(theDeck.dealNextCard());

            // And show both cards of all players, one card of the dealer;
            System.out.println("\n---------------------------------------\n");
            System.out.println("Cards are dealt\n");
            for (int i = 0; i < numberOfPlayers; i++) {
                players[i].printHand(players[i].getName(), true);
                System.out.printf("Total points: %s%n%n", players[i].getSum());
            }
            dealer.printHand(dealer.getName(), false);
            System.out.println("Total points are hidden\n");

//-------------------------------------------------------------------------------

            //// Function 1: check if anyone win BLACKJACK.
            boolean blackJack = false;
            for (int i = 0; i < numberOfPlayers; i++) {
                if (game.blackJack(players[i])) {
                    System.out.println(players[i].getName() + " win " + 3 * bet[i]);
                    // when a player wins, the bet part return, and win additional 3 * bets?
                    money[i] += 4 * bet[i];
                    System.out.println(players[i].getName() +
                            "'s actual stack is: " + money[i] + "$\n");
                    blackJack = true;
                }
                if ((i == numberOfPlayers - 1) && blackJack) {
                    System.exit(0);
                }
            }

//-------------------------------------------------------------------------------

            // Thirdly, each player HIT/STAY respectively, then dealer process afterwards;
            for (int i = 0; i < numberOfPlayers; i++) {
                while (!oneDone) {
                    oneDone = game.playerTurn(players[i]);
                    points[i] = players[i].getSum();
                }
                if (i < numberOfPlayers - 1)
                    oneDone = false;
            }
            dealer.printHand(dealer.getName(), true);
            System.out.println("Total for dealer: " + dealer.getSum() + "\n");
            while (!dealerDone) {
                Boolean allPlayerBusted = false;
                for (int point : points) {
                    if (point > 21)
                        allPlayerBusted = true;
                    else {
                        allPlayerBusted = false;
                        break;
                    }
                }
                if (allPlayerBusted){
                    dealerDone = true;
                    System.out.println("The dealer stays!\n");
                    dealer.printHand(dealer.getName(), true);
                    System.out.println("Total for dealer: " + dealer.getSum());
                }
                else dealerDone = game.dealerTurn(dealer);
            }

//-------------------------------------------------------------------------------

            // Finally, print all cards;
            System.out.println("\n---------------------------------------\n");
            System.out.println("RESULT:");
            int dealerSum = dealer.getSum();
            System.out.println("Total for dealer: " + dealerSum + "\n");

            for (int i = 0; i < numberOfPlayers; i++) {
                int oneSum = players[i].getSum();
                System.out.printf("Total for %s: %s%n", players[i].getName(), oneSum);

                // player wins when his points below 21 and higher and the dealer, or dealer busted;
                if ((oneSum > dealerSum && oneSum <= 21) || dealerSum > 21 ) {
                    System.out.printf("%s wins against the dealer!%n", players[i].getName());
                    money[i] += 2 * bet[i];
                }
                // (oneSum <= dealerSum || oneSum > 21) && dealerSum <= 21
                else {
                    System.out.println("Dealer wins against " + players[i].getName());
                }
                System.out.println(players[i].getName() + "'s actual stack: " +money[i] + "$\n");
            }

//-------------------------------------------------------------------------------

            //// Function 2: Ask players about next round;
            System.out.println("---------------------------------------\n");
            System.out.println("Next round? y/n");
            boolean inputAgain = true;
            String answer2;

            while (inputAgain) {
                answer2 = sc.next();
                if (answer2.compareToIgnoreCase("Y") == 0) {
                    inputAgain = false;
                    oneDone = false;
                    dealerDone = false;
                    for (Player p : players)
                        p.empty();
                    dealer.empty();
                    theDeck = new Deck(numberOfPlayers, true);
                }
                else if (answer2.compareToIgnoreCase("N") == 0) {
                    System.out.println("Thank you for playing. Bye Bye!!!");
                    System.exit(0);
                }
                else {
                    System.err.println("input wrong, try again: ");
                    inputAgain = true;
                }
                sc.nextLine();  // aims to clear the scanner content;
            }
        } //// End of additional round loop;

    }
}

