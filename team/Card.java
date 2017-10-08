
public class Card {

	private Suit suit;
	private Rank rank;
	

	public Card(Rank rank, Suit suit) {
		super();
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public String toString() {
		String str = "\t" +rank.getRankString() + " " + suit.getSuit();		
		return str;

	}
}
