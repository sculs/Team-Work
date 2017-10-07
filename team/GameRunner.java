package team;

import java.util.Scanner;

public class GameRunner {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Game game = new Game();
        GameRunner run = new GameRunner();
        Player player = new Player();
        Player[] players = player.multiPlayers();   /// multi players;
        Player dealer = new Player("Dealer");

        Deck theDeck = new Deck(7, true);

        int numberOfPlayers = players.length;
        boolean playAgain = true;
        boolean oneDone = false;
        boolean dealerDone = false;
        int money[] = new int[numberOfPlayers];
        int bet[] = new int[numberOfPlayers];

        while (playAgain) {

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
                money[i] = 100;

                System.out.println("\n" + players[i].getName() +
                        ", Bet amount(You have " +money[i] + "$):\nOr press \"ENTER\" to skip");
                String s1 = sc.nextLine();

                while (!run.checkNumber(s1)) {
                    s1 = sc.nextLine();
                }
                bet[i] = Integer.parseInt(s1);
                money[i] = game.bet(money[i], bet[i]);
            }


            // Show cards;
            System.out.println("\n\nCards are dealt\n");
            for (int i = 0; i < numberOfPlayers; i++) {
                players[i].printHand(players[i].getName(), true);
//                System.out.printf("Total for %s is %s%n%n", players[i].getName(), players[i].getSum());
                System.out.printf("Total points: %s%n%n", players[i].getSum());
            }
            dealer.printHand(dealer.getName(), false);
            System.out.println();


            /// check if anyone win BLACKJACK.
            boolean blackJack = false;
            for (int i = 0; i < numberOfPlayers; i++) {
                if (game.blackJack(players[i])) {
                    System.out.println(players[i].getName() + " win " + 3 * bet[i]);
                    money[i] += 3 * bet[i];
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
//                    System.out.println(oneDone);
                }
                if (i < numberOfPlayers - 1) {
                    oneDone = false;
//                    System.out.println("debug 3");
                }

            }


                // Dealer's turn to process;
            dealer.printHand(dealer.getName(), true);
            System.out.println("Total for dealer: " + dealer.getSum());

            while (!dealerDone) {
                dealerDone = game.dTurn(dealer);
            }


            // At last, print final hands;
//            dealer.printHand(dealer.getName(), true);
            System.out.println("\nRESULT:");
            int dealerSum = dealer.getSum();
            System.out.println("Total for dealer: " + dealer.getSum());

            for (int i = 0; i < numberOfPlayers; i++) {
                int oneSum = players[i].getSum();
//                players[i].printHand(players[i].getName(), true);
                System.out.printf("Total for %s is %s%n%n", players[i].getName(), oneSum);

                if ((oneSum > dealerSum || dealerSum > 21) && oneSum <= 21) {
//                    System.out.printf("Total for %s is %s%n", players[i].getName(), oneSum);
                    System.out.printf("%s wins against the dealer!", players[i].getName());

                    money[i] += 2 * bet[i];
//                    System.out.println(players[i].getName() + " actual stack is: " + money[i] + "\n");
                }

                else if ((oneSum <= dealerSum || oneSum > 21) && dealerSum <= 21) {
//                    System.out.println("Total for dealer: " + dealer.getSum());
                    System.out.println("Dealer wins against " + players[i].getName());
                    money[i] -= bet[i];
//                    System.out.println(players[i].getName() + "'s actual stack is: " +money[i] + "\n");
                }
                else {   //(dealerSum > 21 && oneSum > 21)
                    System.out.printf("It should be impossible to enter here.(Both are busted)");
                }
                System.out.println(players[i].getName() + "'s actual stack is: " +money[i] + "$\n");


            }


            boolean replay = true;
            String s2;
            while (replay) {
                System.out.println("\nPlay again? y/n\n");
                s2 = sc.next();
                if (s2.compareToIgnoreCase("Y") == 0) {
                    playAgain = true;
                    replay = false;
                    for (int i = 0; i < numberOfPlayers; i++) {
                        players[i].empty();
                        money[i] = 100;
                    }
                    dealer.empty();

                }
                else if (s2.compareToIgnoreCase("N") == 0) {
                    playAgain = false;
                    replay = false;
                    System.out.println("Thank you for playing. Bye Bye!!!");
                    System.exit(0);

                }
                else {
                    System.err.println("input wrong, try again ");
                    replay = true;
                    s2 = sc.next();
                }

            }

        }
    }




    public boolean checkInput(String s){
        if (s.equals(""))
            return false;
        else
            return true;
    }

    public boolean checkNumber(String s){

        if (s.equals("")) {
            return false;
        }

        while ( !s.equals("")) {
            try {
                int a = Integer.parseInt(s);
                if (a > 0){
                    return true;
                }
                else {
                    System.out.println("Number should be above 0:");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer:");
                return false;
            }
        }

        return false;
    }


}



