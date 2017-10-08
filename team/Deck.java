import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
		ArrayList<Card> cards = new ArrayList<>();
	

	Deck() {

		addDeck();
		addDeck();
		addDeck();
		addDeck();
		shuffle();

	}

	private void addDeck() {
		
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				cards.add(new Card(rank, suit));

			}

		}
	}

	private void shuffle() {
		Collections.shuffle(cards);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void removeCard(int i) {
		this.cards.remove(i);
	}
	
	public Card getCard(int i) {
		return this.cards.get(i);
		
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public Card dealNextCard(Deck fromDeck) {
		Card top = this.cards.get(0);
		this.cards.add(fromDeck.getCard(0));
		fromDeck.removeCard(0);
		return top;
	} 
	
	
} 
	 
