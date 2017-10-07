package team;

import java.util.Scanner;

public class Game {

//    private Player player = new Player();
//    private int numberOfPlayers = player.getNumber();
//    private Player[] players = new Player[numberOfPlayers];
    private Player dealer = new Player("Dealer");
    private Deck theDeck = new Deck(7, true);


    private boolean oneDone = false;
    private boolean dealerDone = false;

    public boolean pTurn(Player player) {

//        Scanner sc = new Scanner(System.in);

        boolean inputWrong = false;
        oneDone = false;
        while (!inputWrong && !oneDone) {
            System.out.println(player.getName() + ", Hit or Stay? (Enter H or S): ");
            String ans = GameRunner.sc.next();
            //if the player hits
            if (ans.compareToIgnoreCase("H") == 0) {

                //add next card in the deck and store whether player is busted
                oneDone = !player.addCard(theDeck.dealNextCard());

                player.printHand(player.getName(), true);
                System.out.printf("Total for %s is %s%n", player.getName(), player.getSum());

                if (player.getSum() > 21) {
                    System.out.println("<<<<<<BUSTED>>>>>>");
                    oneDone = true;
                }
                else if (player.getSum() == 21) {
                    System.out.println("Congratulations!!! 21");
                    oneDone = true;
                }
                System.out.println();

            }
            else if (ans.compareToIgnoreCase("S") == 0) {
                oneDone = true;
            }
            else {
                System.err.println("input wrong, try again ...push a button ");
                inputWrong = false;
                ans = GameRunner.sc.nextLine();
            }
        }
        return oneDone;
    }


//    public boolean dTurn(Player player){
    public boolean dTurn(Player dealer){

//        if ((dealer.getSum() < player.getSum() && player.getSum() < 22) || dealer.getSum() < 17) {
        if (dealer.getSum() < 17) {

            System.out.println("The dealer hits...");
//            dealerDone = !dealer.addCard(theDeck.dealNextCard());
            dealer.addCard(theDeck.dealNextCard());
            dealerDone = false;

//            dealer.printHand(dealer.getName(), true);
//            System.out.println("Total for dealer: " + dealer.getSum());

        } else {
            System.out.println("The dealer stays!\n");
            dealerDone = true;
            dealer.printHand(dealer.getName(), true);
            System.out.println("Total for dealer: " + dealer.getSum() + "\n");
        }
        return dealerDone;
    }

    public int bet(int leftMoney, int bet) {

        if (bet != 0) {
        System.out.println("Your actual stack is: " + leftMoney);
            leftMoney -= bet;
            System.out.print("Bet: " + bet + ", and left: " + leftMoney);

        }
        else if (bet == 0){
            System.out.println("No bet, your have " + leftMoney + " left.");
        }
        return leftMoney;
    }


    public boolean blackJack(Player player){
        boolean blackJack = false;
        if (player.getSum() == 21) {
            System.out.println("***************");
            System.out.println("***BLACKJACK***");
            System.out.println("***************");

            blackJack = true;
        }
        return blackJack;
    }

}

