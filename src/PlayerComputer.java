import java.util.Scanner;

public class PlayerComputer extends Player {

	private int playerId;

	public PlayerComputer(String name, int totalScores, int roundScores, boolean stayingUp, int playerId) {
		super(name, totalScores, roundScores, stayingUp);
		setPlayerId(playerId);
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getPlayerId() {
		return playerId;
	}

	@Override
	public String chooseName() {
		System.out.println("Who will play with you? (Type \"default\" for " + this.getName() + ")");
		Scanner scan = new Scanner(System.in);

		String name = scan.next();

		if (name.equalsIgnoreCase("default")) {
			name = this.getName();
		}

		return name;
	}

	@Override
	public void chooseCondition(Round round, int numOfActivePlayers, int totalScores, int roundScores) {
		boolean isStayingUp = false;

		switch (this.getPlayerId()) {
		case 1:
			if (totalScores < 10) {
				isStayingUp = true;
			} else {
				isStayingUp = (roundScores < 10) ? true : false;
			}
			break;

		case 2:
			if (numOfActivePlayers != 1) {
				isStayingUp = true;
			} else {
				isStayingUp = (totalScores < 30) ? true : false;
			}
			break;

		case 3:
			if (roundScores < 7) {
				isStayingUp = true;
			} else {
				isStayingUp = (totalScores < 30) ? true : false;
			}
			break;

		default:
			isStayingUp = false;
		} // the end of switch block for playerId
		this.setStayingUp(isStayingUp);
	}

	@Override
	public String toString() {
		return "PlayerComputer [playerId=" + playerId + ", getName()=" + getName() + ", getTotalScores()="
				+ getTotalScores() + ", getRoundScores()=" + getRoundScores() + ", getStayingUp()=" + getStayingUp()
				+ "]";
	}
}
