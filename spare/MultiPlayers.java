//package team;
//
//import java.util.Scanner;
//
//public class MultiPlayers {
//
//    public static void main(String[] args) {
//        new MultiPlayers().multiPlayers();
//    }
//
//
//
////    private int numberOfPlayers;
////
////    private String [] playersName = new String[numberOfPlayers];
//
//
//    public void multiPlayers() {
//
////        MultiPlayers multi = new MultiPlayers();
//
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("1 player(Press \"ENTER\")\nMulti players(Enter the number):");
//
//        int numberOfPlayers = 1;    // default number:1
////        multi.setNumberOfPlayers(1);
//
//        String s1 = sc.nextLine();
//        boolean inPutNumber = s1.equals("");
//
//        while (!inPutNumber) {
//            try {
//
//                numberOfPlayers = Integer.parseInt(s1);
////                multi.setNumberOfPlayers(numberOfPlayers);
//                if (numberOfPlayers > 0){
//                    inPutNumber = true;
//                }
//                else {
//                    System.out.println("Number should be above 0:");
//                    s1 = sc.nextLine();
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter an integer:");
//                s1 = sc.nextLine();
//            }
//        }
//
////        System.out.println("Number of Players: " + numberOfPlayers);
////        System.out.println("Name of each players:");
//
//        System.out.println("Type in your own name(s), or just click \"ENTER\"):");
//
//        String s2 = sc.nextLine();
//
//        Player[] players = new Player[numberOfPlayers];
//
//        for (int i = 0; i < numberOfPlayers; i++) {
//            // Set value for each player to prevent errors;
//            players[i] = new Player(" ");
//
//            // default name for each player, in case some will not type in a name;
//            if (s2.equals("")) {
//                players[i].setName("player" + (i + 1));
//                System.out.println("player" + (i + 1) + " is player "+(i + 1) + ";");
//            }
//            else {
//                // Control the length of each Name, to void console har too much content.
//                if (s2.length() > 10){
//                    s2 = s2.substring(0, 10);
//                }
//                players[i].setName(s2);
////                playersName[i] = s2;     // store each player's name in "playersName[]";
//                System.out.println(s2 + " is player " + (i + 1) + ";");
//            }
//
////            System.out.println(players[i].getName());
////            playersName[i] = players[i].getName();
////            System.out.println(playersName[i]);
////            multi.setPlayersName(new String[i]players[i].getName());
////            multi.setPlayersName();
//
////            System.out.println(players[i].getName());
//
//            if (i < numberOfPlayers - 1) {
//                s2 = sc.nextLine();
//            }
//        }
//        sc.close();
//
////        System.out.println(multi.getNumberOfPlayers());
////        System.out.println(multi.getPlayersName());
//
////        return multi;
//    }
//
//
////    public int getNumberOfPlayers() {
////        return numberOfPlayers;
////    }
////
////    public void setNumberOfPlayers(int numberOfPlayers) {
////        this.numberOfPlayers = numberOfPlayers;
////    }
////
////    public void setPlayersName(String[] playersName) {
////        this.playersName = playersName;
////    }
////
////    public String[] getPlayersName() {
////        return playersName;
////    }
//}
//
