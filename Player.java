public class Player implements Comparable{
	private String name;
	private int stars;
	private boolean inClan = true;
	private int totalStars;
	private int thLevel;
	private double worth = 0;
	private int attackU = 0;
	private int attackW = 0;
	private int rank; 
	private int numWars = 0;
	public Player (String name, int stars, int thLevel) {
		this.name = name.toLowerCase();
		this.stars = stars;
		this.thLevel = thLevel;
	}
	public Player (String name, int stars, int thLevel, int attackU, int attackW) {
		this.name = name.toLowerCase();
		this.stars = stars;
		this.thLevel = thLevel;
		this.attackU = attackU;
		this.attackW = attackW;
	}
	public Player (String name, int stars, int thLevel, int attackU, int attackW, int rank) {
		this.name = name.toLowerCase();
		this.stars = stars;
		this.thLevel = thLevel;
		this.attackU = attackU;
		this.attackW = attackW;
		this.setRank(rank);
	}
	public Player (String name, int thLevel, double worth) {
		this(name, thLevel, worth, 51);
	}
	public Player (String name, int thLevel, double worth, int rank) {
		this.name = name.toLowerCase();
		this.setWorth(worth);
		this.thLevel = thLevel;
		this.rank = rank;
		stars = 0;
	}
	public Player (String name, int thLevel) {
		this(name, thLevel, -1.0, 51);
	}
	public Player (String name) {
		this.name = name.toLowerCase();
		stars = 0;
		this.thLevel = 0;
		this.worth = -1.0;
		this.rank = 51;
	}
	public boolean inClan() {
		return inClan;
	}
	public void setInClan(boolean choice) {
		inClan = choice;
	}
	public int getThLevel() {
		return thLevel;
	}
	public void setThLevel(int thLevel) {
		this.thLevel = thLevel;
	}
	public String getName() {
		return name;
	}
	public int getStars() {
		return stars;
	}
	public void addWorth(double num) {
		setWorth(getWorth() + num);
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public void addStars(int stars) {
		this.stars += stars;
		totalStars += stars;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return name + "\n";
	}
	public boolean equals(Object other) {
		Player playa = (Player) other;
		if(this.name.equals(playa.name)) {
			return true;
		}
		return false;
	}
	public int getAttackW() {
		return attackW;
	}
	public void addAttackW() {
		this.attackW++;
	}
	public void removeWorth(double worth2) {
		worth-= worth2;
	}
	public int getAttackU() {
		return attackU;
	}
	public void addAttackU() {
		this.attackU++;
	}
	public void addWar() {
		numWars++;
	}
	public void setAttackU(int num) {
		this.attackU = num;
	}
	public void setAttackW(int num) {
		this.attackW = num;
	}
	public double getWorth() {
		return worth;
	}
	public void setWorth(double worth) {
		this.worth = worth;
	}
	@Override
	public int compareTo(Object o) {
		Player other = (Player) o;
		if(other.getNumWars() != 0 && this.getNumWars() != 0) {
			return (int) ((other.getWorth() * 100)/other.getNumWars() - (this.getWorth() * 100) /this.getNumWars());
		} else  if(other.getNumWars() == 0 && this.getNumWars() == 0) {
			return 0;
		} else if(this.getNumWars() == 0) {
			return 1;
		} else {
			return -1;
		}
		
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getNumWars() {
		return numWars;
	}
	public void setNumWars(int numWars) {
		this.numWars = numWars;
	}
	public void removeWar() {
		numWars--;
	}
	
	
}
