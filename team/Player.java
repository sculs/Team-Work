package team;

class Player extends Hand{

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }


    protected Player[] multiPlayers() {

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
            players[i] = new Player();

            // default name for each player in case of no input;
            if (s2.equals("")) {
                players[i].setName("player" + (i + 1));
                System.out.println("player" + (i + 1) + " is player "+(i + 1) + ";");
            }
            else {
                // Control the length of each Name, to void console har too much content.
                if (s2.length() > 12){
                    s2 = s2.substring(0, 12);
                }
                players[i].setName(s2);
                System.out.println(s2 + " is player " + (i + 1) + ";");
            }


            if (i < numberOfPlayers - 1) {
                s2 = GameRunner.sc.nextLine();
            }
        }
        return players;

    }


    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}

