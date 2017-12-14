import java.util.Random;

public class Dice {
	private int value;
	
	private static final int RANGE = 6;
	private static final int MIN = 1;
	
	public Dice() {
		this.value = this.rollDice();
	}
	
	public int getValueOfDice() {
		return this.value;
	}
	
	public int rollDice() {
		Random rand = new Random();
		this.value = rand.nextInt(RANGE) + MIN;
			
		return this.value;
	}

}
