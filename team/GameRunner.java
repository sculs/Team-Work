package team;

import java.util.Scanner;

public class GameRunner {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new GameRunner().run();

    }

    public void run() {
        System.out.println("----------------------------");
        Game game = new Game();
        Player play = new Player();
        Player[] players = play.multiPlayers();   /// multi players;
        Player dealer = new Player("Dealer");

        Deck theDeck = new Deck(7, true);

        int numberOfPlayers = players.length;
        boolean oneDone = false;
        boolean dealerDone = false;
        int money[] = new int[numberOfPlayers];
        int bet[] = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            money[i] = 100;
        }

        for (;;){

            // Hand out cards for all players;
            for (int i = 0; i < numberOfPlayers; i++) {
                players[i].addCard(theDeck.dealNextCard());
                players[i].addCard(theDeck.dealNextCard());
            }
            // Hand out cards for the dealer;
            dealer.addCard(theDeck.dealNextCard());
            dealer.addCard(theDeck.dealNextCard());

            // Option for bet;
            for (int i = 0; i < numberOfPlayers; i++) {

                System.out.println("\n" + players[i].getName() +
                        ", Bet amount(You have " +money[i] + "$):\nNo bet? press \"ENTER\":");
                String s1 = sc.nextLine();
                while (!game.checkNumber(s1)) {
                    s1 = sc.nextLine();
                }
                bet[i] = s1.equals("")? 0 : Integer.parseInt(s1);
                money[i] -= game.bet(money[i], bet[i]);
            }


            // Show cards;
            System.out.println("\n----------------------------\n");
            System.out.println("Cards are dealt\n");
            for (int i = 0; i < numberOfPlayers; i++) {
                players[i].printHand(players[i].getName(), true);
                System.out.printf("Total points: %s%n%n", players[i].getSum());
            }
            dealer.printHand(dealer.getName(), false);
            System.out.println();


            /// check if anyone win BLACKJACK.
            boolean blackJack = false;
            for (int i = 0; i < numberOfPlayers; i++) {
                if (game.blackJack(players[i])) {
                    System.out.println(players[i].getName() + " win " + 3 * bet[i]);
                    // when a player wins, the bet money should return,
                    // and win additional 3 * bets ?????
                    money[i] += 4 * bet[i];
                    System.out.println(players[i].getName() + "'s actual stack is: " + money[i] + "$\n");
                    blackJack = true;
                }
                if ((i == numberOfPlayers - 1) && blackJack) {
                    System.exit(0);
                }
            }

            // Now each player play respectively with the dealer;
            // Player's turn to play;
            for (int i = 0; i < numberOfPlayers; i++) {
                while (!oneDone) {
                    oneDone = game.pTurn(players[i]);
                }
                if (i < numberOfPlayers - 1) {
                    oneDone = false;
                }

            }


            // Dealer's turn to process;
            dealer.printHand(dealer.getName(), true);
            System.out.println("Total for dealer: " + dealer.getSum());

            while (!dealerDone) {
                dealerDone = game.dTurn(dealer);
            }


            // At last, print final hands;
            System.out.println("\n----------------------------\n");
            System.out.println("RESULT:");
            int dealerSum = dealer.getSum();
            System.out.println("Total for dealer: " + dealer.getSum() + "\n");

            for (int i = 0; i < numberOfPlayers; i++) {
                int oneSum = players[i].getSum();
                System.out.printf("Total for %s: %s%n", players[i].getName(), oneSum);

                if ((oneSum > dealerSum || dealerSum > 21) && oneSum <= 21) {
                    System.out.printf("%s wins against the dealer!%n", players[i].getName());
                    money[i] += 2 * bet[i];
                }

                else if ((oneSum <= dealerSum || oneSum > 21) && dealerSum <= 21) {
                    System.out.println("Dealer wins against " + players[i].getName());
                }

                else {   //(dealerSum > 21 && oneSum > 21)
                    System.out.printf("Both are busted and %s lost.%n", players[i].getName());
                }
                System.out.println(players[i].getName() + "'s actual stack: " +money[i] + "$\n");


            }


            System.out.println("----------------------------\n");
            System.out.println("Play again? y/n");
            boolean inputAgain = true;
            String s2;

            while (inputAgain) {
                s2 = sc.next();
                if (s2.compareToIgnoreCase("Y") == 0) {
                    inputAgain = false;
                    oneDone = false;
                    dealerDone = false;
                    for (Player p : players)
                        p.empty();
                    dealer.empty();
                    theDeck = new Deck(7, true);

                }
                else if (s2.compareToIgnoreCase("N") == 0) {
                    System.out.println("Thank you for playing. Bye Bye!!!");
                    System.exit(0);
                }
                else {
                    System.err.println("input wrong, try again: ");
                    inputAgain = true;
                }
                sc.nextLine();  // aims to clear the scanner content;
            }
        }
    }
}


