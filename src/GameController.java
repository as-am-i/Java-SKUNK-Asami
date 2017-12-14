import java.util.ArrayList;
import java.util.Collections;

public class GameController {
	private ArrayList<Player> players;
	private ArrayList<Player> activePlayers;
	private Round round;
	private Dice dice1;
	private Dice dice2;

	public GameController() {
		players = new ArrayList<>();
		activePlayers = new ArrayList<>();
		round = new Round(1, false);
		dice1 = new Dice();
		dice2 = new Dice();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void setActivePlayer() {
		activePlayers.clear();
		for (Player player : players) {
			if (player.getStayingUp() == true) {
				activePlayers.add(player);
			}
		}
	}

	public void playerSetUp() {
		// Name
		for (Player player : players) {
			String answer = player.chooseName();
			player.setName(answer);
		}
	}

	public void playerStandUp() {
		for (Player player : players) {
			player.setStayingUp(true);
			activePlayers.add(player);
		}
	}

	public void resetRoundScores() {
		for (Player player : players) {
			player.setRoundScores(0);
		}
	}

	public void printScoresAndConditions() {
		System.out.println("\n[SCORE and CONDITION]");
		for (Player player : players) {
			System.out.printf("%-16s", player.getName());
		}
		System.out.println();

		for (Player player : players) {
			String total = "TOTAL: " + player.getTotalScores();
			System.out.printf("%-16s", total);
		}
		System.out.println();

		for (Player player : players) {
			String round = "round: " + player.getRoundScores();
			System.out.printf("%-16s", round);
		}
		System.out.println();

		for (Player player : players) {
			String result;
			if (player.getStayingUp() == true) {
				result = "stay up: yes";
			} else {
				result = "stay up: no";
			}
			System.out.printf("%-16s", result);
		}
		System.out.println();
	}

	public void printResult() {
		System.out.println("\n[FINAL SCORES]");
		for (Player player : players) {
			System.out.printf("%-16s", player.getName());
		}
		System.out.println();

		for (Player player : players) {
			String total = "TOTAL: " + player.getTotalScores();
			System.out.printf("%-16s", total);
		}
		System.out.println();
	}

	public void printRank() {
		System.out.println("\n[RANK]");
		printPlayersByRank();

		System.out.println("Congratulations on " + players.get(0).getName() + "!");
		System.out.println("\nThank you for playing!");
	}

	public void printPlayersByRank() {
		for (int rank = 1; rank < players.size() + 1; rank++) {
			System.out.println(rank + " - " + players.get(rank - 1).getName());
		}
	}

	public void sortByTotalScores() {
		Collections.sort(players, new PlayerSortByTotalScoresComparator());
	}

	public void printDices(int valueOfDice1, int valueOfDice2, int sumOfDices) {
		System.out.println("\n-------- Rolling dices... --------");

		// print each value
		System.out.println("dice1: " + valueOfDice1);
		System.out.println("dice2: " + valueOfDice2);
		System.out.println("----------------------------------");
	}

	public void branchOff(int sumOfDices) {
		// check who are staying up
		setActivePlayer();

		// double 1
		// total score should be 0, and go to the next round
		if (dice1.getValueOfDice() == 1 && dice2.getValueOfDice() == 1) {
			if (activePlayers.size() != players.size()) {
				for (Player activePlayer : activePlayers) {
					activePlayer.setRoundScores(0);
					activePlayer.setTotalScores(0);
				}
				System.out.println("Oops! \"double 1\" happens! \nThe players who stayed up lost the total scores!");
				// round.incrementRound();
				activePlayers.clear();
			} else {
				System.out.println("Roll the dices again, since everyone is staying up");
			}
		}

		// single 1
		// round score should be 0, and go to the next round
		else if (dice1.getValueOfDice() == 1 || dice2.getValueOfDice() == 1) {
			if (activePlayers.size() != players.size()) {
				for (Player activePlayer : activePlayers) {
					activePlayer.setTotalScores(activePlayer.getTotalScores() - activePlayer.getRoundScores());
					activePlayer.setRoundScores(0);
				}
				System.out.println("Oops! \"single 1\" happens! \nThe players who stayed up lost the round scores!");
				// round.incrementRound();
				activePlayers.clear();

			} else {
				System.out.println("Roll the dices again, since everyone is staying up");
			}
		}

		// except for 1
		// add the scores, ask if the player wants to stay up, check if there's someone
		// still staying up
		else if (dice1.getValueOfDice() != 1 && dice2.getValueOfDice() != 1) {
			for (Player activePlayer : activePlayers) {
				activePlayer.addScores(sumOfDices);
			}
			printScoresAndConditions();
			System.out.println("");

			// ask condition
			int numOfActivePlayers = activePlayers.size();
			for (Player player1 : players) {
				int totalScores = player1.getTotalScores();
				int roundScores = player1.getRoundScores();
				player1.chooseCondition(round, numOfActivePlayers, totalScores, roundScores);

				if (player1.getStayingUp() == true) {
					System.out.println(player1.getName() + " - stay");
				} else {
					System.out.println(player1.getName() + " - down");
				}

			}
			setActivePlayer();
		} // the end of else if condition(neither 1)
	} // the end of this method

	public void playSkunk() {
		// conditions to move on the next round
		// 1. when everyone is sitting down
		// 2. when someone is sitting down AND 1 comes up

		for (int rTimes = 0; rTimes < 5; rTimes++) {
			// round.confirmNextRound();
			round.setIsNextRound(false);
			round.printRoundName();

			// TODO make everyone stand up, and reset the round scores
			playerStandUp();
			resetRoundScores();

			// TODO print the current roundScores and totalScores BEFORE rolling
			printScoresAndConditions();

			boolean stop = false;
			while (stop == false) {
				// Roll dices and store the sum of these values
				int valueOfDice1 = dice1.rollDice();
				int valueOfDice2 = dice2.rollDice();
				int sumOfDices = valueOfDice1 + valueOfDice2;

				// TODO show values of dices
				printDices(valueOfDice1, valueOfDice2, sumOfDices);

				// go in different directions
				branchOff(sumOfDices);
				if (activePlayers.isEmpty()) {
					round.incrementRound();
					stop = true;
				}
			} // the end of while loop
		} // the end of for loop of rTimes
	} // the end of playSkunk()
}
