package team;

import java.util.Random;

public class Deck {

    private Card[] myCards;

    private int numCards;

    public Deck() {
        this(1, false);
    }

    public Deck(int numDecks, boolean shuffle) {

        this.numCards = numDecks * 52;
        this.myCards = new Card[this.numCards];

        //initialize card index
        int c = 0;

        //for each deck
        for(int i = 0; i < numDecks; i++) {

            //for each suit
            for(int j = 0; j < 4; j++) {

                //for each number
                for(int n = 1; n <= 13; n++) {

                    //add a new card to the deck
                    this.myCards[c] = new Card(Suit.values()[j], n);
                    c++;
                }

            }
        }

        //shuffle, if necessary
        if (shuffle) {
            this.shuffle();
        }
    }


    public void shuffle() {

        //initialize random number generator
        Random random = new Random();

        //temporary card
        Card temp;

        int j;
        for(int i = 0; i < this.numCards; i++) {

            // get a random card j to swap i´s value with
            j = random.nextInt(this.numCards);

            //do swap
            temp = this.myCards[i];
            this.myCards[i] = this.myCards[j];
            this.myCards[j] = temp;

        }

    }


    public Card dealNextCard() {

        //get the top card
        Card top = this.myCards[0];

        //shift all the subsequent cards to the left by one index
        for ( int c = 1; c < this.numCards; c++) {
            this.myCards[c-1] = this.myCards[c];
        }

        this.myCards[this.numCards -1] = null;

        //decrement the number of cards in our deck
        this.numCards--;

        return top;

    }


//    public void printDeck(int numToPrint) {
//
//        for (int i = 0; i < numToPrint; i++) {
//            System.out.printf("% 3d/%d %s\n", i+1, this.numCards, this.myCards[i].toString());
//        }
//        System.out.printf("\t\t[%d left]\n", this.numCards-numToPrint);
//    }


}

