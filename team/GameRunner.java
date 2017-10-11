package team;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameRunner {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new GameRunner().run();
    }

    private void run() {

/////// Preface for new variables, Objects, etc.//////////////////////
        System.out.println("---------------------------------------");
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
        Deck theDeck = new Deck();

////////////////////////////////////////////////////////////////////////////////

        // The Game Starts!!!
        labelA:
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
                bet[i] = game.bet(money[i], bet[i]);
                money[i] -= bet[i];
            }

//-------------------------------------------------------------------------------
            // Secondly, Hand out first two cards for all players & the dealer;
            for (int i = 0; i < numberOfPlayers; i++) {
                players[i].addCard(theDeck.dealNextCard(theDeck));
                players[i].addCard(theDeck.dealNextCard(theDeck));
            }
            dealer.addCard(theDeck.dealNextCard(theDeck));
            dealer.addCard(theDeck.dealNextCard(theDeck));

            System.out.print ("\nAll set! Game starts in ");
            game.countDown(3);

            // And show both cards of all players, one card of the dealer;
            System.out.println("\n---------------------------------------\n");
            System.out.println("Cards are dealt\n");
            for (int i = 0; i < numberOfPlayers; i++) {
                players[i].printHand(players[i].getName(), true);
                System.out.printf("Total points: %s%n%n", players[i].getSum());
                game.pause(2);
            }
            dealer.printHand(dealer.getName(), false);
            System.out.println("Total points are hidden\n");
            game.pause(2);

//-------------------------------------------------------------------------------
            //// Function 1: check if any player win BLACKJACK.
            boolean blackJack = false;
            for (int i = 0; i < numberOfPlayers; i++) {
                if (game.blackJack(players[i])) {
                    System.out.println(players[i].getName() + " win " + 3 * bet[i]);
                    // when a player wins, the bet part return, and win additional 3 * bets?
                    money[i] += 4 * bet[i];
                    for (int j = 0; j < numberOfPlayers; j++) {
                        money [j] += j == i ? 0 : bet[j];
                    }
                    System.out.println(players[i].getName() +
                            "'s actual stack is: " + money[i] + "$\n");
                    game.pause(2);
                    blackJack = true;
                }
                if ((i == numberOfPlayers - 1) && blackJack) {
                    if (game.nextRound()) {
                        for (Player p : players)
                            p.empty();
                        dealer.empty();
                        theDeck = new Deck();
                    }
                    else {
                        System.exit(0);
                    }
                    continue labelA;
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
            if (game.blackJack(dealer)) {
                System.out.println(dealer.getName() + " got Blackjack! Dealer win!");
                game.pause(2);

                if (game.nextRound()) {
                    oneDone = dealerDone = false;
                    for (Player p : players)
                        p.empty();
                    dealer.empty();
                    theDeck = new Deck();
                }
                else {
                    System.exit(0);
                }
                continue;
            }

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
                game.pause(2);
            }

//-------------------------------------------------------------------------------
            //// Function 2: Ask players about next round;
            if (game.nextRound()) {
                oneDone = dealerDone = false;
                for (Player p : players)
                    p.empty();
                dealer.empty();
                theDeck = new Deck();
            }
            else System.exit(0);

        } //// End of additional round loop;
    }
}
