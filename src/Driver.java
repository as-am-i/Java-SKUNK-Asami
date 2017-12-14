import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	
	private GameController gController;
	
	public Driver() {
		gController = new GameController();
	}
	
	public void run() {
		gController.addPlayer(new PlayerHuman("player", 0, 0, true));
		gController.addPlayer(new PlayerComputer("computer1", 0, 0, true, 1));
		gController.addPlayer(new PlayerComputer("computer2", 0, 0, true, 2));
		gController.addPlayer(new PlayerComputer("computer3", 0, 0, true, 3));
		
		gController.playerSetUp();
		gController.playSkunk();
		
		gController.printResult();
		ArrayList<Player> sortedPlayers = gController.sortByTotalScores();
		gController.printRank(sortedPlayers);
	}



	public static void main(String[] args) {

		Driver driver = new Driver();
		driver.run();

	}

}
