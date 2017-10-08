package team;


public class Card {

    private Suit mySuit;

    private int myNumber;

    /// Constructors //////////
    public Card() {
    }

    public Card(Suit aSuit, int aNumber) {

        this.mySuit = aSuit;

        if (aNumber >=1 && aNumber <=13) {
            this.myNumber = aNumber;
        }else {
            System.err.println(aNumber + " is not a valid card number");
            System.exit(1);
        }
    }

    public int getNumber() {
        return myNumber;
    }

    public String toString() { //String array is another option

        String numStr = "Error";
        switch (this.myNumber) {

            case 1: numStr = "A"; break;
            case 2: numStr = "2"; break;
            case 3: numStr = "3"; break;
            case 4: numStr = "4"; break;
            case 5: numStr = "5"; break;
            case 6: numStr = "6"; break;
            case 7: numStr = "7"; break;
            case 8: numStr = "8";break;
            case 9: numStr = "9"; break;
            case 10: numStr = "10"; break;
            case 11: numStr = "J"; break;
            case 12: numStr = "Q"; break;
            case 13: numStr = "K";break;

        }

        return "    " + mySuit.toString() + " " + numStr;
    }

}

