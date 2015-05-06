import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ComboBoxModel;

public class war extends Clan {
	private int totalStars = 0;
	private int attacksWon = 0;
	private int attacksUsed = 0;
	private int warNum = 0;
	private int rank;
	private int enemyStars;
	private String enemyName;
	private Integer[] enemyTH = new Integer[50];
	private ArrayList<Attack> attacks = new ArrayList<Attack>();
	private ArrayList<Player> players = new ArrayList<Player>(50);
	private ArrayList<Defense> defends = new ArrayList<Defense>();
	public war (Player...players) {
		super(players);
	}
	public war (int stars) {
		totalStars = stars;
	}
	
	public double getStars() {
		return totalStars;
	}
	public int getNumPlayers() {
		return super.players.size();
	}
	public ArrayList<Player> getPlayers() {
		return super.players;
	}

	
	public void changeName(String name, String text) {
		if (getPlayer(name) != null) {
			getPlayer(name).setName(text);
		}
		
		for(Attack att : attacks) {
			if (att.getPlayer().equals(name)) {
				att.setPlayer(text);
			}
		}
		for(Defense def : defends) {
			if (def.getPlayer().equals(name)) {
				def.setPlayer(text);
			}
		}
	}
	public double updateWL() {
		attacksWon = 0;
		attacksUsed = 0;
		for (Player play : players) {
			attacksWon += play.getAttackW();
			attacksUsed += play.getAttackU();
		}
		if(attacksUsed == 0) {
			return 0;
		}
		return (attacksWon/attacksUsed);
	}
	public void resetPlayers() {
		for (Player play : super.players) {
			play.setStars(0);
			play.setAttackU(0);
			play.setAttackW(0);
		}
	}
	public double parting() {
		double full = super.players.size() * 2;
		double part = 0;
		for (Player play : super.players) {
			part += play.getAttackU();
		}
		return (part/full) * 100 ;
	}
	public double losing() {
		double losses = 0;
		double total = 0;
		for (Player play : super.players) {
			total += play.getAttackU();
			losses += play.getAttackU() - play.getAttackW();
		}
		
		return losses;
	}
	public double winning() {
		double wins = 0;
		for (Player play : super.players) {
			wins += play.getAttackW();
		}
		return wins;
	}
	public double attack(Player player, Attack att) {
		player.addStars(att.getStars());
		if(att.getStars() == 3) {
			att.setStar3(true);
		}
		player.addAttackU();
		if (att.getStars() > 0) {
			player.addAttackW();
		}
		return att.calcWorth(super.players.size(), player);
	}
	public void loadAttack(Attack att) {
		attacks.add(att);
		totalStars += att.getStars();
	}
	public void updateWorths(Clan clan, int numPlayers) {
		for (Attack att : attacks) {
			att.setWorth((int)att.calcWorth(numPlayers, clan.getPlayer(att.getPlayer())));
		}
	}
	public String toString() {
		//updateStars();
		String warString = "";
		warString += "Score: " + totalStars + " - " + enemyStars + "\n";
		warString += "Total Attacks: " + (this.winning() + this.losing()) + "\n";
		warString += "Attacks Won: " + this.winning();
		warString += "\nAttacks Lost: " + this.losing();
		//warString += "\nStars: " + totalStars;
		
		
		return warString;
	}
	public double defense(Player player, int opponent, int starsAdded) {
		Defense defense = new Defense(player.getName(), starsAdded, opponent);
		enemyStars += defense.getStars();
		return defense.calcWorth(super.players.size(), player);
	}
	public ArrayList<Attack> getAttacks() {
		
		return (ArrayList<Attack>) attacks.clone();
	}
	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
	}
	public int getRank() {
		return rank;
	}
	public ArrayList<Attack> eraseAttacks(String player) {
		ArrayList<Attack> removeAtt = new ArrayList<>();
		for(int i = 0; i < attacks.size(); i++){
			if (attacks.get(i).getPlayer().equals(player)) {
				removeAtt.add(attacks.remove(i));
				i--;
			}
		}
		return removeAtt;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void updateWarNums() {
		for(Player play : super.players) {
			play.setNumWars(play.getNumWars() + 1);
		}
		
	}
	public void loadDefense(Defense def) {
		defends.add(def);
		//enemyStars += def.getStars();
	}
	public ArrayList<Defense> getDefends() {
		return defends;
	}
	public void setDefends(ArrayList<Defense> defends) {
		this.defends = defends;
	}
	public Integer[] getEnemyTH() {
		return enemyTH;
	}
	public boolean check3Star(Attack att) {
		int check3 = 3;
		for(Attack a : attacks) {
			if(a.equals(att)) {
				check3 -= (a.getStars());
			}
		}
		check3 -= att.getStars();
		if (check3 == 0 && att.getStars() != 0) {
			return true;
		}
		
		
		return false;
		
	}
	public void setEnemyTH(Integer[] enemyTH) {
		this.enemyTH = enemyTH;
	}
	public void addEnTH(int parseInt, int i) {
		enemyTH[i] = parseInt;		
	}
	public int getEnTH(int index) {
		System.out.println(enemyTH);
		return enemyTH[index];
	}
	public String[] getMembersNames() {
		String[] names = new String[super.players.size()];
		ArrayList<Player> playas = (ArrayList<Player>) super.players.clone();
		Collections.sort(playas, new playerComp());
		int i = 0;
		for (Player play : playas) {
			names[i] = play.getName();
			i++;
		}
		return names;
	}
	public double starsPerc(int i) {
		double num = 0;
		for (Attack att : attacks) {
			if(i == 3 && att.getStars() < 3 && att.isStar3() && att.getStars() != 0) {
				num++;
			} else if (att.getStars() == i) {
				num += 1;
				if(att.isStar3() && att.getStars() != 3 && att.getStars() != 0) {
					num--;
				}
			}
		}
		return num;
	}
	public Player warRemove(Player player) {
		if(super.players.remove(player)) {
			return player;
		} else {
			return null;
		}
	}
	public Player getPlayerInd(int num) {
		ArrayList<Player> playas = (ArrayList<Player>) super.players.clone();
		Collections.sort(playas, new THComp());
		return playas.get(num);
	}
	public String getEnemyName() {
		return enemyName;
	}
	public void setEnemyName(String enemyName) {
		this.enemyName = enemyName;
	}
	public String[] getMembersNamesWithAttacks() {
		String[] names = new String[super.players.size()];
		ArrayList<Player> playas = (ArrayList<Player>) super.players.clone();
		Collections.sort(playas, new playerComp());
		int i = 0;
		for (Player play : playas) {
			if(play.getAttackU() < 2) {
				names[i] = play.getName();
				i++;
			}
		}
		return names;
	}
	public void removeAttack(Attack att) {
		attacks.remove(att);
		
	}
	public int getEnemyStars() {
		return enemyStars;
	}
	public void setEnemyStars(int enemyStars) {
		this.enemyStars = enemyStars;
	}
	
	
}
