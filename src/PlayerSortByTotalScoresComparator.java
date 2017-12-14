import java.util.Comparator;

public class PlayerSortByTotalScoresComparator implements Comparator<Player>{
	
	@Override
	public int compare(Player p1, Player p2) {
		return p2.getTotalScores() - p1.getTotalScores();
	}
	
}
