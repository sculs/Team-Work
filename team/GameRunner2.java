package team;

import java.util.Scanner;

public class GameRunner2 {

    public static void main(String[] args) {

        /// This part aims to get multi players number & their names;
        Player play = new Player();
        Player [] players = play.multiPlayers();

        int numberOfPlayers = players.length;

//        System.out.println(numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            String s = players[i].getName();
            System.out.println(s);
        }
        //// Multi players part ends here. When we submit, Print lines should be deleted.



        boolean playAgain = true;

///// ???
        Scanner playSC = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        String ans2;

        Scanner dollarSC = new Scanner(System.in);
//        Scanner sc = new Scanner(System.in);

//        System.out.println("How many decks:");
//        int numDecks = sc.nextInt();
//        if (numDecks < 1){
//            numDecks = 1;
//        }

        // initialize deck

        //// change hard code 7 to variable "numDecks";
        Deck theDeck = new Deck(7, true);

        // initialize player objects

        // We have adjusted one player to multiPlayers;
//        Player one = new Player("Eddy");

        Player dealer = new Player ("Dealer");

        int money = 100;
        int bet = 0;
        int left;

        // player's turn to play (play one after one with the dealer);
        for (int i = 0; i < numberOfPlayers; i++) {


            while (playAgain) {

                players[i].addCard(theDeck.dealNextCard());
                dealer.addCard(theDeck.dealNextCard());
                players[i].addCard(theDeck.dealNextCard());
                dealer.addCard(theDeck.dealNextCard());
                System.out.println("Your actual stack is: " + money);
                System.out.println("How much do you want to bet?");

                bet = sc.nextInt();
                System.out.print("Your bet is: " + bet + ". ");
                left = money - bet;
                System.out.println("Money left: " + left);


                // flags for when each player is finished hitting
                boolean oneDone = false;
                boolean dealerDone = false;
                boolean blackJack = false;
                String ans;

                // print initial hands
                System.out.println("Cards are dealt\n");
                players[i].printHand(players[i].getName(),true);
                System.out.println("total for player: " + players[i].getSum());
                dealer.printHand(dealer.getName(), false);
                System.out.println("\n");
                if (players[i].getSum() == 21) {
                    System.out.println("***************");
                    System.out.println("***BLACKJACK***");
                    System.out.println("***************");
                    oneDone = true;
                    dealerDone = true;

                    blackJack = true;
                    int bj = 22;
                    bj = dealer.getSum();
                    System.out.println("You win " + 3 * bet);
                    money = left + 3 * bet;


                }


                while (!oneDone) {

                    //player´s turn
                    if (!oneDone) {

                        System.out.println("Hit or Stay? (Enter H or S): ");
                        ans = sc.next();
                        System.out.println();

                        //if the player hits
                        if (ans.compareToIgnoreCase("H") == 0) {

                            //add next card in the deck and store whether player is busted
                            oneDone = !players[i].addCard(theDeck.dealNextCard());

                            players[i].printHand(players[i].getName(), true);
                            System.out.println("total for player: " + players[i].getSum());

                            if (players[i].getSum() > 21) {
                                System.out.println("<<<<<<BUSTED>>>>>>");
                                dealerDone = true;
                            }
                            if (players[i].getSum() == 21) {
                                System.out.println("Congratulations!!! 21");
                                oneDone = true;
                            }

                        } else if (ans.compareToIgnoreCase("S") == 0) {

                            oneDone = true;
                        } else {
                            System.err.println("input wrong, try again ...push a button ");

                        }


                    }
                    System.out.println();

                }
                dealer.printHand(dealer.getName(), true);

                while (!dealerDone) {

                    //dealer´s turn
                    if (!dealerDone) {


                        if (dealer.getSum() < players[i].getSum() && (players[i].getSum() >= 17 && players[i].getSum() < 22)) {
                            System.out.println("\n");
                            System.out.println("The dealer hits\n");
                            dealerDone = !dealer.addCard(theDeck.dealNextCard());

                            dealer.printHand(dealer.getName(), true);
                            System.out.println("total for dealer: " + dealer.getSum());

                            if (dealer.getSum() < 17) {
                                System.out.println("\n");
                                System.out.println("The dealer hits\n");
                                dealerDone = !dealer.addCard(theDeck.dealNextCard());
                                dealer.printHand(dealer.getName(), true);
                                System.out.println("total: " + dealer.getSum());

                            }
                        } else {
                            System.out.println("\n");
                            System.out.println("The dealer stays\n");
                            dealerDone = true;
                            System.out.println("total for dealer: " + dealer.getSum());
                        }
                    }
                    System.out.println();
                }


                //print final hands
                players[i].printHand(players[i].getName(), true);
                dealer.printHand(dealer.getName(), true);

                int oneSum = players[i].getSum();
                int dealerSum = dealer.getSum();


                if ((oneSum > dealerSum && oneSum <= 21 || dealerSum > 21)) {
                    System.out.println("Total for player: " + players[i].getSum());
                    System.out.println("You win " + 2 * bet);


                    money = left + 2 * bet;

                    System.out.println("Your actual stack is: " + money);


                }
                if (blackJack == true) {
                    System.out.println("You win " + 3 * bet);
                    money = left + 3 * bet;
                    System.out.println("Your actual stack is: " + money);
                } else if (oneSum < dealerSum && dealerSum <= 21 || oneSum > 21) {
                    System.out.println("Total for dealer: " + dealer.getSum());
                    System.out.println("Dealer wins ");
                    money = money - bet;
                    System.out.println("Your actual stack is: " + money);
                }

                System.out.println();
                System.out.println();
                System.out.println("Play again? y/n");
                ans2 = sc.next();

                if (ans2.compareToIgnoreCase("Y") == 0) {
                    playAgain = true;
                    players[i].empty();
                    dealer.empty();
                    bet = 0;


                } else if (ans2.compareToIgnoreCase("N") == 0) {
                    playAgain = false;
                    sc.close();
//                    sc.close();
//                    sc.close();

                    System.exit(0);
                }

            }
        }

    }

}

