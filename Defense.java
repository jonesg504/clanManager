public class Defense {
	private String player;
	private int stars;
	private int thLevel;
	private int defNum;
	private int warNum;
	private double worth;
	public Defense(String player, int stars, int thLevel) {
		this.player = player;
		this.stars = stars;
		this.thLevel = thLevel;
	}
	public Defense(String player, int stars, int thLevel, double worth) {
		this.player = player;
		this.stars = stars;
		this.thLevel = thLevel;
		this.worth = worth;
	}
	public Defense() {
		// TODO Auto-generated constructor stub
	}
	public double calcWorth(int numPlayers, Player play) {
		if (stars == 3) {
			return -2;
		} else if (stars == 2) {
			return 2;
		} else  if (stars == 1) {
			return 3;
		} else {
			return 5;
		}
	}
	
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public int getThLevel() {
		return thLevel;
	}
	public String toString() {
		return player + "(" + stars + ")\n";
	}
	public void setThLevel(int thLevel) {
		this.thLevel = thLevel;
	}
	public int getDefNum() {
		return defNum;
	}
	public void setDefNum(int defNum) {
		this.defNum = defNum;
	}
	public int getWarNum() {
		return warNum;
	}
	public void setWarNum(int warNum) {
		this.warNum = warNum;
	}
	public double getWorth() {
		return worth;
	}
	public void setWorth(double d) {
		this.worth = d;
	}
}
