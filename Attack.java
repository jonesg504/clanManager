public class Attack implements Cloneable {
	private String player;
	private int ghostStars;
	private int stars;
	private int baseRank;
	private int thLevel;
	private int worth;
	private boolean star3;
	private int attNum;
	private int warNum;
	
	
	
	public Attack(String player, int stars, int baseRank, int thLevel) {
		this.player = player.toLowerCase();
		this.stars = stars;
		this.baseRank = baseRank;
		this.thLevel = thLevel;
	}
	public Attack(String player, int stars, int baseRank, int thLevel, int ghostStars) {
		this.player = player.toLowerCase();
		this.stars = stars;
		this.baseRank = baseRank;
		this.thLevel = thLevel;
		this.ghostStars = ghostStars;
	}



	public Attack() {
	}



	public String getPlayer() {
		return player;
	}
	public Attack clone() {
		try {
			return (Attack) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public double calcWorth(int numPlayers, Player play) {
		if(ghostStars + stars >= 3) {
			star3 = true;
		}
		if (stars == 0) {
			return (ghostStars * 20);
		}
		if (play == null) {
			return 0;
		}
		double nop = numPlayers;
		if (nop < 10) {
			nop = 10;
		}
		double modifier = 1.3 + (((nop - baseRank)/nop) + (play.getRank() / baseRank)/4);
		
		modifier =  1 + Math.log10(modifier);
		double multiplier = (play.getRank() / nop);
		
		//multiplier += (nop * 2 - play.getRank() * 2)/(nop/2);
		multiplier = Math.log10(multiplier);
		multiplier += Math.log(stars);
		if (multiplier < 1) {
			multiplier = 1;
		}
		double worth = 1 + Math.log10((Math.pow(2, (thLevel - play.getThLevel()) )));
		
		if (play.getThLevel() == 8 && thLevel == 9) {
			worth = 1.1;
		}
		if (play.getThLevel() == 9 && thLevel == 10) {
			worth = 1.1;
		}
		if (play.getThLevel() <= 7 && thLevel - play.getThLevel() >= 1) {
			worth = 1.08;
		}
		if (stars == 0) {
			worth = 0;
		}
		worth = worth * modifier * multiplier;
		
		worth = Math.round(worth * 100);
		if (star3) {
			worth*= 1.2;
		} else {
			if (stars == 2 || ghostStars + stars == 2) {
				worth*= 1.1;
			}
		}
		if (!star3) {
			worth += (ghostStars * 5);
		}
		setWorth((int) Math.round(worth));
		return Math.round(worth);
	}

	public String toString() {
		return player + "(" + stars + ")\n";
	}
	public String attString() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}



	public int getBaseRank() {
		return baseRank;
	}



	public void setBaseRank(int baseRank) {
		this.baseRank = baseRank;
	}



	public int getThLevel() {
		return thLevel;
	}



	public void setThLevel(int thLevel) {
		this.thLevel = thLevel;
	}


	public boolean equals(Object other) {
		Attack att = (Attack)other;
		if(this.getBaseRank() == (att.getBaseRank())) {
			return true;
		}
		return false;
	}
	public int getStars() {
		return stars;
	}



	public void setStars(int stars) {
		this.stars = stars;
	}
	public int getGhostStars() {
		return ghostStars;
	}



	public void setGhostStars(int ghostStars) {
		this.ghostStars = ghostStars;
	}



	public int getWorth() {
		return worth;
	}



	public void setWorth(int worth) {
		this.worth = worth;
	}



	public boolean isStar3() {
		return star3;
	}



	public void setStar3(boolean star3) {
		this.star3 = star3;
	}



	public int getAttNum() {
		return attNum;
	}



	public void setAttNum(int attNum) {
		this.attNum = attNum;
	}



	public int getWarNum() {
		return warNum;
	}



	public void setWarNum(int warNum) {
		this.warNum = warNum;
	}
}
