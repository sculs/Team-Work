package blackJack;


import java.util.Scanner;

public class MultiPlayers {

    public static void main(String[] args) {

        new MultiPlayers().multiPlayers();
    }

    public void multiPlayers() {
        Scanner sc = new Scanner(System.in);


        // method of many players;///////////////////
        System.out.println("1 player? Press \"ENTER\";"+
                "\nOr type in number of players: ");

        int numberOfPlayers = 1;    // default number:1

        String s1 = sc.nextLine();
        boolean inPutNumber = s1.equals("");

        while (!inPutNumber) {
            try {
                numberOfPlayers = Integer.parseInt(s1);
                inPutNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer:");
                s1 = sc.nextLine();
            }
        }

//        System.out.println("Number of Players: " + numberOfPlayers);

//        System.out.println("Name of each players:");
        System.out.println("Use default name(s)? Press \"ENTER\";"+
                        "\nOr enter your name(s).");


        String s2 = sc.nextLine();

        // Attention: Getter & Setter for players name has generated in Player class;

        Player[] players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            // Set value for each player to prevent errors;
            players[i] = new Player("A");

            // default name for each player, in case some will not type in a name;
            if (s2.equals("")) {
                players[i].setName("Gamer" + (i + 1));
                System.out.println("Gamer" + (i + 1) + " is player "+(i + 1) + ";");
            } else {
                players[i].setName(s2);
                System.out.println(s2 + " is player " + (i + 1) + ";");
            }

            if (i < numberOfPlayers - 1) {
                s2 = sc.nextLine();
            }
        }


    }
}
