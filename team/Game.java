package team;

public class Game {

    private Player dealer = new Player("Dealer");
    private Deck theDeck = new Deck();
    private boolean oneDone = false;
    private boolean dealerDone = false;

    //// Method for player HIT/STAY /////////////////////////////////////////
    protected boolean playerTurn(Player player) {

        oneDone = false;
        while (!oneDone) {
            System.out.println(player.getName() + ", Hit or Stay? (Enter H or S): ");
            String ans = GameRunner.sc.next();

            if (ans.compareToIgnoreCase("H") == 0) {
                // add next card in the deck and store whether player is busted
                oneDone = !player.addCard(theDeck.dealNextCard(theDeck));
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
            }
        }
        return oneDone;
    }


    //// Method for the dealer HIT/STAY /////////////////////////////////////////
    protected boolean dealerTurn(Player dealer){

        // if ((dealer.getSum() < player.getSum() && player.getSum() < 22) || dealer.getSum() < 17) {
        if (dealer.getSum() < 17) {
            System.out.println("The dealer hits...");
            dealer.addCard(theDeck.dealNextCard(theDeck));
            dealerDone = false;
        }
        else {
            System.out.println("The dealer stays!\n");
            dealerDone = true;
            dealer.printHand(dealer.getName(), true);
            System.out.println("Total for dealer: " + dealer.getSum());
            if (dealer.getSum() > 21){
                System.out.println("<<<<<<BUSTED>>>>>>");
            }
        }
        return dealerDone;
    }

    //// Method for player's bet /////////////////////////////////////////
    protected int bet(int leftMoney, int bet) {

        if (leftMoney <= 0) {
            System.out.println("You have no money to bet!");
            return 0;
        }
        else if (bet != 0) {
            if (bet > leftMoney){
                System.out.println("Only " + leftMoney + "$ available, bet "
                        + leftMoney + "$");
                bet = leftMoney;
            }
            else
                System.out.print("Bet: " + bet + "$, and left: "
                        + (leftMoney - bet) + "$\n");
        }
        else {      // bet == 0
            System.out.println("No bet, you have " + leftMoney + "$ left.\n");
        }
        return bet;
    }

    //// Method to check Blackjack /////////////////////////////////////////
    protected boolean blackJack(Player player){
        boolean blackJack = false;
        if (player.getSum() == 21) {
            System.out.println("***************");
            System.out.println("***BLACKJACK***");
            System.out.println("***************");
            blackJack = true;
        }
        return blackJack;
    }

    protected boolean nextRound() {
        System.out.println("---------------------------------------\n");
        System.out.println("Next round? y/n");
        boolean inputAgain = true;
        boolean replay = false;
        String answer;
        int waitTime = 0;

        while (inputAgain) {
            answer = GameRunner.sc.next();
            if (answer.compareToIgnoreCase("Y") == 0) {
                inputAgain = false;
                replay = true;
            }
            else if (answer.compareToIgnoreCase("N") == 0) {
                System.out.println("Thank you for playing. Bye Bye!!!");
                inputAgain = false;
                replay = false;
            }
            else {
                waitTime++;
                if (waitTime > 3)
                    replay = false;
                System.err.println("input wrong, try again: ");
                inputAgain = true;
            }
            GameRunner.sc.nextLine();  // aims to clear the scanner content;
        }
        return replay;
    }

    //// Method to check if user's input is number or blank ///////////////////////
    protected boolean checkNumber(String s){

        if (s.equals("")) {
            return true;
        }

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

    protected boolean checkInput(String s){
        return !s.equals("");
    }
}