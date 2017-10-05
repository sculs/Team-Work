package team;

import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // initialize deck
        Deck theDeck = new Deck(1, true);

        // initialize player objects
        Player one = new Player("Marco");
        Player dealer = new Player ("Dealer");

        one.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());
        one.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());

        // print initial hands
        System.out.println("Cards are dealt\n");
        one.printHand(true);
        dealer.printHand(false);
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
                    one.printHand(true);

                } else {
                    oneDone = true;
                }
            }
            System.out.println();
        }

        while(!dealerDone) {

            //dealer´s turn
            if (!dealerDone) {
                if (dealer.getHandSum() < 17) {
                    System.out.println("The dealer hits\n");
                    dealerDone = !dealer.addCard(theDeck.dealNextCard());
                    dealer.printHand(false);
                } else {
                    System.out.println("The dealer stays\n");
                    dealerDone = true;
                }
            }
            System.out.println();
        }

        sc.close();

//        print final hands
        one.printHand(true);
        dealer.printHand(true);

        int oneSum = one.getHandSum();
        int dealerSum = dealer.getHandSum();

        if (oneSum > dealerSum && oneSum <= 21 || dealerSum > 21) {
            System.out.println("You win");
        } else {
            System.out.println("Dealer wins ");
        }


    }

}

