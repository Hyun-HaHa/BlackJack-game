package GB.CHH.toyProject01.blackjack;

// 카드의 모양, 값 
public class Card {
	String[] suits = {"♦", "♣", "♠", "♥"};
	String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10",
						"J", "Q", "k"};
	int[] point = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10,
						10, 10, 10};
	
	int suit;		// 카드 모양
	int rank;		// 카드 종류

	public Card(int suit, int rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}

	public int cardPoint() {		// 카드 종류에 따라 점수 리턴
		return point[rank];
	}

	@Override
	public String toString() {
		return "["+ suits[suit] + ranks[rank] + "]";
	}
	
	
}
