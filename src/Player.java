
public abstract class Player {
	private String name;
	private int totalScores;
	private int roundScores;

	private boolean stayingUp;

	public Player(String name, int totalScores, int roundScores, boolean stayingUp) {
		setName(name);
		setTotalScores(totalScores);
		setRoundScores(roundScores);
		setStayingUp(stayingUp);
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public String getName() {
		return name;
	}

	public void setTotalScores(int totalScores) {
		if (totalScores >= 0) {
			this.totalScores = totalScores;
		}
	}

	public int getTotalScores() {
		return totalScores;
	}

	public void setRoundScores(int roundScores) {
		if (roundScores >= 0) {
			this.roundScores = roundScores;
		}
	}

	public int getRoundScores() {
		return roundScores;
	}

	public void setStayingUp(boolean stayingUp) {
		this.stayingUp = stayingUp;
	}

	public boolean getStayingUp() {
		return stayingUp;
	}

	public void addScores(int sumOfDices) {
		this.roundScores += sumOfDices;
		this.totalScores += sumOfDices;

	}

	public abstract String chooseName();

	public abstract void chooseCondition(Round round, int numOfActivePlayers, int totalScores, int roundScores);

	@Override
	public String toString() {
		return "Player [name=" + name + ", totalScores=" + totalScores + ", roundScores=" + roundScores + ", stayingUp="
				+ stayingUp + "]";
	}
}
