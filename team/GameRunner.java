package team;

import java.util.Scanner;

public class GameRunner {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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
        String ans, ans2;
        int money = 100;
        int bet;

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
            System.out.println("Wanna bet?(Enter amount):\nOr press \"ENTER\" to skip");
            String ans3 = sc.nextLine();
            bet = run.checkNumber(ans3);
            if (bet != 0){
                money = game.bet(money, bet);
            }


            // Show cards;
            System.out.println("Cards are dealt\n");
            for (int i = 0; i < numberOfPlayers; i++) {
                players[i].printHand(players[i].getName(), true);
                System.out.printf(" total for  %s is %s\n", players[i].getName(), players[i].getSum());
            }
            dealer.printHand(dealer.getName(), false);
            System.out.println("\n");


            // Now each player play respectively with the dealer;
            for (int i = 0; i < numberOfPlayers; i++) {

                /// check if anyone win BLACKJACK.
                if (game.blackJack(players[i])){
                    System.out.println("You win " + 3 * bet);
                    money += 3 * bet;
                    System.out.println("Your actual stack is: " +money);
                    System.exit(0);
                }

                // Player's turn to play;
                while (!oneDone) {
                     oneDone = game.pTurn(players[i]);
                }


                // Dealer's turn to process;
                dealer.printHand(dealer.getName(), true);
                while (!dealerDone) {
                    dealerDone = game.dTurn(players[i]);
                }

                // At last, print final hands;
                dealer.printHand(dealer.getName(), true);
                int dealerSum = dealer.getSum();
                players[i].printHand(players[i].getName(), true);
                int oneSum = players[i].getSum();

                if ((oneSum > dealerSum || dealerSum > 21) && oneSum <= 21) {
                    System.out.printf("Total for %s is %s%n", players[i].getName(), players[i].getSum());
                    System.out.printf("%s wins ", players[i].getName());

                    money += 2*bet;
                    System.out.println("Your actual stack is: " + money);
                }

                else if ((oneSum <= dealerSum || oneSum > 21) && dealerSum <= 21) {
                    System.out.println("Total for dealer: " + dealer.getSum());
                    System.out.println("Dealer wins ");
                    money -= bet;
                    System.out.println("Your actual stack is: " +money);
                }
                else {   //(dealerSum > 21 && oneSum > 21)
                    System.out.printf("It should be impossible to enter here.(Both are busted)");
                }

                if (i < numberOfPlayers - 1) {
                    // Hand out cards for the dealer for the next round;
                    dealer.addCard(theDeck.dealNextCard());
                    dealer.addCard(theDeck.dealNextCard());
                }

            }


            ans2 = sc.next();
            boolean replay = true;

            while (replay) {
                System.out.println("\n\nPlay again? y/n");
                if (ans2.compareToIgnoreCase("Y") == 0) {
                    playAgain = true;
                    replay = false;
                    for (int i = 0; i < numberOfPlayers; i++) {
                        players[i].empty();
                    }
                    dealer.empty();
                    money = 100;


                } else if (ans2.compareToIgnoreCase("N") == 0) {
                    playAgain = false;
                    replay = false;
                    sc.close();
                    System.out.println("Thank you for playing. Bye Bye!!!");
                    System.exit(0);

                } else {
                    System.err.println("input wrong, try again ");
                    replay = true;
                    ans2 = sc.next();
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

    public int checkNumber(String s){

        int a;

        boolean inPutNumber = true;
        if (s.equals(""))
            return 0;

        while ( !s.equals("")) {
            try {
                a = Integer.parseInt(s);
                if (a > 0){
                    return a;
                }
                else {
                    System.out.println("Number should be above 0:");
                    return 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer:");
                return 0;
            }
        }
        return 0;
    }


}



