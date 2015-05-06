import java.util.Comparator;

public class attackComp implements Comparator{
	private war currWar;
	private Clan clan;
	public attackComp (war currWar, Clan clan) {
		this.currWar = currWar;
		this.clan = clan;
	}
	
	@Override
	public int compare(Object at1, Object at2) {
		Attack a1 = (Attack)at1;
		Attack a2 = (Attack)at2;
		return (int) (a2.getWorth() - a1.getWorth());
	}
	
}