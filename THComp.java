import java.util.Comparator;

public class THComp implements Comparator {

	@Override
	public int compare(Object a0, Object a1) {
		Player p1 = (Player) a0;
		Player p2 = (Player) a1;
		return p2.getThLevel() - p1.getThLevel();
	}

}