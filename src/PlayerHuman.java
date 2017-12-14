import java.util.Scanner;

public class PlayerHuman extends Player {

	public PlayerHuman(String name, int totalScores, int roundScores, boolean stayingUp) {
		super(name, totalScores, roundScores, stayingUp);
	}

	@Override
	public String chooseName() {
		System.out.println("What is your name?");
		Scanner scan = new Scanner(System.in);

		return scan.next();
	}

	@Override
	public void chooseCondition(Round round, int numOfActivePlayers, int totalScores, int roundScores) {
		if (this.getStayingUp() == true) {
			System.out.println("\nDo you want to still stay up? - y/n");
			Scanner scan = new Scanner(System.in);
			String condition = scan.next();

			if (condition.equals("y")) {
				// stay up
				setStayingUp(true);
			} else {
				setStayingUp(false);
			}
		}

	}

	@Override
	public String toString() {
		return "PlayerHuman [getName()=" + getName() + ", getTotalScores()=" + getTotalScores() + ", getRoundScores()="
				+ getRoundScores() + ", getStayingUp()=" + getStayingUp() + "]";
	}
}
