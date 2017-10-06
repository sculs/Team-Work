package team;

import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {


        /// This part aims to get multi players number & their names;
        Player play = new Player();
        Player [] players = play.multiPlayers();

        int numberOfPlayers = players.length;
        System.out.println(numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            String s = players[i].getName();
            System.out.println(s);
        }
        //// Multi players part ends here. When we submit, Print lines should be deleted.



        Scanner sc = new Scanner(System.in);

        // initialize deck
        Deck theDeck = new Deck(1, true);

        // initialize player objects
        Player one = new Player("Marco");
//        Hand one = new Hand();
        Player dealer = new Player ("Dealer");

        one.addCard(theDeck.dealNextCard());

        dealer.addCard(theDeck.dealNextCard());
        one.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());

        // print initial hands
        System.out.println("Cards are dealt\n");
        one.printHand(one.getName(), true);
        dealer.printHand(dealer.getName(), false);
        System.out.println("\n");

        // flags for when each player is finished hitting
        boolean oneDone = false;
        boolean dealerDone = false;
        String ans;

        while(!oneDone ) {

            //player´s turn
            if (!oneDone) {

                System.out.println("Hit or Stay? (Enter H or S): " );
                ans = sc.next();
                System.out.println();

                //if the player hits
                if (ans.compareToIgnoreCase("H") == 0) {

                    //add next card in the deck and store whether player is busted
                    oneDone = !one.addCard(theDeck.dealNextCard());
                    one.printHand(one.getName(),true);

                } else {
                    oneDone = true;
                }
            }
            System.out.println();
        }

        while(!dealerDone) {

            //dealer´s turn
            if (!dealerDone) {
                if (dealer.getSum() < 17) {
                    System.out.println("The dealer hits\n");
                    dealerDone = !dealer.addCard(theDeck.dealNextCard());
                    dealer.printHand(dealer.getName(),false);
                } else {
                    System.out.println("The dealer stays\n");
                    dealerDone = true;
                }
            }
            System.out.println();
        }

        sc.close();

//        print final hands
        one.printHand(one.getName(), true);
        dealer.printHand(dealer.getName(), true);

        int oneSum = one.getSum();
        int dealerSum = dealer.getSum();

        if (oneSum > dealerSum && oneSum <= 21 || dealerSum > 21) {
            System.out.println("You win");
        } else {
            System.out.println("Dealer wins ");
        }


    }


}

