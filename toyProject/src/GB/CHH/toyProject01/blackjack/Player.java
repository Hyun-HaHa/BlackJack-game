package GB.CHH.toyProject01.blackjack;

public class Player {
	private final String NAME;		// 이름
	private int point;				// 플레이어의 점수


	public Player(String name) {
		this.NAME = name;
	}

	
	public String getName() {
		return NAME;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
}
