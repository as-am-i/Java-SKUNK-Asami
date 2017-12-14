
public class Round {
	private int count;
	private boolean isNextRound = false;

	private final String ROUNDS_NAME = "SKUNK";

	public Round(int count, boolean isNextRound) {
		setCount(count);
		setIsNextRound(isNextRound);
	}

	public void setCount(int count) {
		if (count > 0) {
			this.count = count;
		}
	}

	public int getCount() {
		return count;
	}

	public void setIsNextRound(boolean isNextRound) {
		this.isNextRound = isNextRound;
	}

	public boolean getIsNextRound() {
		return isNextRound;
	}

	// option
	public void confirmNextRound() {
		if (this.getIsNextRound() == true) {
			try {
				setIsNextRound(false);
				System.out.println("We'll start next round in 3 seconds...");
				Thread.sleep(3000); // 3seconds
			} catch (InterruptedException e) {
			}
		}
	}

	public void printRoundName() {
		// round starts from 1 but index starts from 0
		char currentRound = ROUNDS_NAME.charAt(count - 1);
		System.out.println("\n------------");
		System.out.println("| Round: " + currentRound + " |");
		System.out.println("------------");
	}

	public void incrementRound() {
		if (count < 5) {
			setIsNextRound(true);
			count++;
		} else {
			System.out.println("This SKUNK game is already finished");
		}
	}

}
