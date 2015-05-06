import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Clan implements Iterable {
	private static ArrayList<war> wars = new ArrayList<war>();
	protected ArrayList<Player> players = new ArrayList(50);
	protected ArrayList<Player> removed = new ArrayList();
	private String clanName;
	private String userName;
	private String password;
	private String subreddit;

	public Clan(Player... players) {
		for (int i = 0; i < players.length; i++) {
			this.players.add(players[i]);
		}
	}

	public String toString() {
		String clanString = "";
		for (int i = 0; i < players.size(); i++) {
			clanString = clanString + players.get(i).toString() + "Rank: "
					+ players.get(i).getRank() + "\n";
		}
		return clanString;
	}
	public String[] getMembersNames() {
		String[] names = new String[players.size()];
		ArrayList<Player> playas = (ArrayList<Player>) players.clone();
		Collections.sort(playas, new playerComp());
		int i = 0;
		for (Player play : playas) {
			names[i] = play.getName();
			i++;
		}
		return names;
	}
	public ArrayList<Player> getMembers() {
		return players;
	}

	public war getWar(int i) {
		if (wars.size() > 0) {
			return wars.get(i);
		}
		return null;
	}

	public String printWar() {
		String warString = "";
		for (int i = 0; i < players.size(); i++) {
			warString = warString + players.get(i).toString();
			warString += "  Attacks: " + players.get(i).getAttackU()
					+ "\n  Won: " + players.get(i).getAttackW() + "\n";
		}
		return warString;
	}

	public static void addWar(war war) {
		wars.add(war);
	}

	public Player getPlayer(String name) {
		for (Player play : players) {
			if (play.getName().equals(name.toLowerCase())) {
				return play;
			}
		}
		return null;
	}

	public ArrayList<war> getWars() {
		return wars;
	}

	public ArrayList<Player> getList() {
		return players;
	}

	public Player remove(Player player) {
		for (int i = 0; i < players.size(); i++) {
			if (player.equals(players.get(i))) {
				players.get(i).setInClan(false);
				removed.add(players.remove(i));
				return player;
			}
		}

		return null;
	}

	public void addRemoved(Player p) {
		removed.add(p);
	}

	public Player addBackToClan(Player p) {
		for (int i = 0; i < removed.size(); i++) {
			if (p.equals(removed.get(i))) {
				removed.get(i).setInClan(true);
				players.add(removed.remove(i));
				return p;
			}
		}

		return null;
	}

	public Clan copy() {
		Clan copy = this;
		return copy;
	}

	public void add(Player player) {
		players.add(player);
	}

	@Override
	public Iterator iterator() {
		return new ClanIterator();
	}

	private class ClanIterator implements Iterator {
		int current = 0;

		public boolean hasNext() {
			return (current < players.size() && players.get(current) != null);
		}

		@Override
		public Player next() {
			Player returnVal = null;
			if (hasNext()) {
				returnVal = players.get(current);
				current++;
				return returnVal;
			} else {
				throw new java.util.NoSuchElementException("No next element");
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Can't Remove");

		}

	}

	public boolean doesExist(Player play) {
		return players.contains(play);
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubreddit() {
		return subreddit;
	}

	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}

	public void updateWar(war currWar) {
		wars.set(wars.size() - 1, currWar);

	}

	public void updateWar(war currWar, int index) {
		wars.set(index, currWar);

	}

	public war getCurrentWar() {
		return wars.get(wars.size() - 1);
	}

	public void changeName(String name, String text) {

		for (war w : wars) {
			w.changeName(name, text);
		}
		this.getPlayer(name).setName(text);

	}

	public void clearWars() {
		wars = new ArrayList<war>();

	}

	public void removeWar(int currentWarNumber) {
		for (Attack att : wars.get(currentWarNumber).getAttacks()) {
			if (this.getPlayer(att.getPlayer()) != null) {
				this.getPlayer(att.getPlayer()).removeWorth(att.getWorth());
			}
		}
		for (Object o : wars.get(currentWarNumber)) {
			Player p = (Player) o;
			if (this.getPlayer(p.getName()) != null) {
				this.getPlayer(p.getName()).setNumWars(this.getPlayer(p.getName()).getNumWars());
			}
		}
		wars.remove(currentWarNumber);

	}

	public ArrayList<Integer> getPlayerPart(String name) {
		ArrayList<Integer> part = new ArrayList<Integer>(2);
		part.add(0);
		part.add(0);
		for (war w : wars) {
			if (w.getPlayer(name) != null) {
				int atts = w.getPlayer(name).getAttackU();
				part.set(0, part.get(0) + atts);
				part.set(1, part.get(1) + (2 - atts));
			}
		}
		return part;
	}

	public ArrayList<Integer> getPlayerStars(String player) {
		ArrayList<Integer> stars = new ArrayList<>();
		for (war w : wars) {
			if (w.getPlayer(player) != null) {
				stars.add(w.getPlayer(player).getStars());
			}
		}
		return stars;
	}

	public ArrayList<Integer> getPlayerWins(String name) {
		ArrayList<Integer> part = new ArrayList<Integer>(2);
		part.add(0);
		part.add(0);
		for (war w : wars) {
			if (w.getPlayer(name) != null) {
				int atts = w.getPlayer(name).getAttackW();
				part.set(0, part.get(0) + atts);
				part.set(1, part.get(1) + (2 - atts));
			}
		}
		return part;
	}

	public ArrayList<Player> getRemoved() {
		return removed;
	}

	public boolean isRemoved(Player p) {
		for (Player play : removed) {
			if (play.equals(p)) {
				return true;
			}
		}
		return false;
	}
}
