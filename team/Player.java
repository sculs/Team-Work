package team;

import java.util.Scanner;

/**
 * This class implements a black jack player
 *
 * @author marcog
 *
 */
public class Player extends Hand{

    private String name;
    private int number;

    private Hand hand = new Hand();

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

//    Scanner sc = new Scanner(System.in);


    public Player[] multiPlayers() {

        System.out.println("One player? press \"ENTER\":\nMulti players, enter the number:");

        int numberOfPlayers = 1;    // default number:1

        String s1 = GameRunner.sc.nextLine();
        boolean inPutNumber = s1.equals("");

        while (!inPutNumber) {
            try {

                numberOfPlayers = Integer.parseInt(s1);
                if (numberOfPlayers > 0){
                    inPutNumber = true;
                }
                else {
                    System.out.println("Number should be above 0:");
                    s1 = GameRunner.sc.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer:");
                s1 = GameRunner.sc.nextLine();
            }
        }



        System.out.println("Default name(s)? press \"ENTER\":\nOr type in all your name(s):");

        String s2 = GameRunner.sc.nextLine();

        Player[] players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            // Set value for each player to prevent errors;
            players[i] = new Player();

            // default name for each player, in case some will not type in a name;
            if (s2.equals("")) {
                players[i].setName("player" + (i + 1));
                System.out.println("player" + (i + 1) + " is player "+(i + 1) + ";");
            }
            else {
                // Control the length of each Name, to void console har too much content.
                if (s2.length() > 10){
                    s2 = s2.substring(0, 10);
                }
                players[i].setName(s2);
                System.out.println(s2 + " is player " + (i + 1) + ";");
            }



            if (i < numberOfPlayers - 1) {
                s2 = GameRunner.sc.nextLine();
            }
        }
//        sc.close();
        return players;

    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

