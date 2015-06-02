import java.util.ArrayList;
import java.util.Collections;


public class RankHandler {
	private static ArrayList<Player> sortedClan = mainWindow.clan.getMembers();
	public static String setBest() {
		Collections.sort(sortedClan);
		String bestPlayers = "Top Members: \n";
		for (int i = 0; i < 10 && i < sortedClan.size(); i++) {
			bestPlayers = bestPlayers + (i + 1) + ") "
					+ sortedClan.get(i).getName() + "\n";
		}
		System.out.println(bestPlayers);
		return bestPlayers;

	}

	public static String setWorst() {
		Collections.sort(sortedClan);
		int s = sortedClan.size() - 1;
		String worstPlayers = "Bottom Members: \n";
		for (int i = s; i > s - 10 && i > 0; i--) {
			if (sortedClan.get(i).getWorth() != -1) {

				worstPlayers = worstPlayers + (i + 1) + ") "
						+ sortedClan.get(i).getName() + "\n";
			} else {
				s--;
			}
		}
		return worstPlayers;
	}
}
