import java.awt.List;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.util.Callback;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class graphData {
	public static XYSeriesCollection performance(Clan clan) {
		 final XYSeries sample = new XYSeries("Firefox");   
		 int numWars = clan.getWars().size() - 1;
			for (int i = 0; i <= numWars; i++) {
				
				war current = clan.getWar(i);
				//current.updateStars();
				//current.updateStars();
				double starDat = current.getStars();
				sample.add(i + 1, (starDat/(current.getNumPlayers() * 3)) * 100);
				
				
			}
	      final XYSeriesCollection dataset = new XYSeriesCollection( );          
	      dataset.addSeries( sample );          
	      
	      return dataset;
	}
	public static ArrayList<Double> performanceFX(Clan clan) {
		 ArrayList<Double> data = new ArrayList<>();   
		 int numWars = clan.getWars().size() - 1;
		 System.out.println(numWars);
			for (int i = 0; i <= numWars; i++) {
				
				war current = clan.getWar(i);
				//current.updateStars();
				//current.updateStars();
				double starDat = current.getStars();
				data.add((starDat/(current.getNumPlayers() * 3)) * 100);
				
				
			}  
	      
	      return data;
	}
	public static XYSeriesCollection parting(Clan clan) {
		final XYSeries sample = new XYSeries("Firefox");   
		  int numWars = clan.getWars().size() - 1;
			for (int i = 0; i <= numWars; i++) {
				
				war current = clan.getWar(i);
				sample.add((double)i + 1, current.parting());
			}
	      final XYSeriesCollection dataset = new XYSeriesCollection( );          
	      dataset.addSeries( sample );          
	      
	      return dataset;
	}
	public static ArrayList<Double> partingFX(Clan clan) {
		 ArrayList<Double> data = new ArrayList<>(); 
		  int numWars = clan.getWars().size() - 1;
			for (int i = 0; i <= numWars; i++) {
				
				war current = clan.getWar(i);
				data.add(current.parting());
			}         
	      
	      return data;
	}
	public static XYSeriesCollection losses(Clan clan) {
		final XYSeries sample = new XYSeries("Losses");   
		//final XYSeries wins = new XYSeries("Wins");   
		int numWars = clan.getWars().size() - 1;
			for (int i = 0; i <= numWars; i++) {
				
				war current = clan.getWar(i);
				sample.add((double)i + 1, current.losing());
				//wins.add((double)i + 1, current.winning());
			}
	      final XYSeriesCollection dataset = new XYSeriesCollection( );          
	      dataset.addSeries(sample);
	      //dataset.addSeries(wins);
	      
	      return dataset;
	}
	public static ArrayList<Double> lossesFX(Clan clan) {
		 ArrayList<Double> data = new ArrayList<>(); 
		//final XYSeries wins = new XYSeries("Wins");   
		int numWars = clan.getWars().size() - 1;
			for (int i = 0; i <= numWars; i++) {
				
				war current = clan.getWar(i);
				data.add(current.losing());
				//wins.add((double)i + 1, current.winning());
			}
	      
	      //dataset.addSeries(wins);
	      
	      return data;
	}
	public static XYSeriesCollection playerWarStat(Player player, XMLParser parse) {
		final XYSeries sample = new XYSeries("Stars");
		ArrayList<Integer>  starList = parse.getPlayerStars(player.getName());
		int xPoint = 1;
		for (int i : starList) {
			sample.add(xPoint, i);
			xPoint++;
		}
		
		final XYSeriesCollection dataset = new XYSeriesCollection( ); 
		dataset.addSeries( sample );   
		return dataset;
		
	}
	public static DefaultPieDataset playerWarPart(Player player, XMLParser parse) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<Integer>  starList = parse.getPlayerPart(player.getName());
		int xPoint = 1;
		double percentUsed = 0;
		for (int i : starList) {
			percentUsed += ((double)i/ (starList.size() * 2));
		}
		double percentUnused = 1- percentUsed;
		dataset.setValue("Attacked", percentUsed);
		dataset.setValue("Didn't attack", percentUnused);
		return dataset;
		
	}
	public static DefaultPieDataset playerWarWin(Player player, XMLParser parse) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<Integer>  starList = parse.getPlayerWin(player.getName());
		int xPoint = 1;
		double percentUsed = 0;
		for (int i : starList) {
			percentUsed += ((double)i/ (starList.size() * 2));
		}
		double percentUnused = 1- percentUsed;
		dataset.setValue("Wins", percentUsed);
		dataset.setValue("Losses", percentUnused);
		return dataset;
		
	}
	public static DefaultCategoryDataset playerWarRank(Player player, XMLParser parse) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<ArrayList>  array = parse.getPlayerRank(player.getName());
		ArrayList<Attack>  attacks = array.get(0);
		ArrayList<Integer>  warNum = array.get(1);
		ArrayList<String> catagory = new ArrayList<>();
		int previous = warNum.get(0) - 1;
		for (int i : warNum) {
			if (previous != i) {
				catagory.add("" + i);
				previous = i;
			}
		}
		System.out.println(attacks);
		final String series1 = "Attack1";
		final String series2 = "Attack2";
		int warTrack = warNum.get(0);
		int attackTrack = 0;
		System.out.println(attacks);
		System.out.println(warNum);
		for (int i = 0; i < attacks.size(); i++) {
			if (warTrack == warNum.get(i)) {
				if (attackTrack == 0) {
					System.out.println(warTrack);
					int index = catagory.indexOf("" + warNum.get(i));
					dataset.addValue(attacks.get(i).getBaseRank(), series1, catagory.get(index));
					attackTrack = 1;
				} else {
					int index = catagory.indexOf("" + warNum.get(i));
					dataset.addValue(attacks.get(i).getBaseRank(), series2, catagory.get(index));
					attackTrack = 0;
				}
			} else {
				warTrack++;
				attackTrack = 0;
				if (attackTrack == 0) {
					int index = catagory.indexOf("" + warNum.get(i));
					dataset.addValue(attacks.get(i).getBaseRank(), series1, catagory.get(index));
					attackTrack = 1;
				} else {
					int index = catagory.indexOf("" + warNum.get(i));
					dataset.addValue(attacks.get(i).getBaseRank(), series2, catagory.get(index));
					attackTrack = 0;
				}
			}
		}
		
		return dataset;
		
	}
	public static DefaultPieDataset attackBreakDown(Clan clan, war cW) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("3 Stars", cW.starsPerc(3));
		dataset.setValue("2 Stars", cW.starsPerc(2));
		dataset.setValue("1 Stars", cW.starsPerc(1));
		dataset.setValue("0 Stars", cW.starsPerc(0));
		return dataset;
	}
	public static ArrayList<PieChart.Data> playerWarPartFX(Clan clan, String player) {
		ArrayList<PieChart.Data> data = new ArrayList<PieChart.Data>(); 
		ArrayList<Integer>  part = clan.getPlayerPart(player);
		data.add(new PieChart.Data("Attacked" , part.get(0)));
		data.add(new PieChart.Data("Didn't Attack", part.get(1)));
		return data;
		
	}
	public static Series<Number, Number> playerWarStatFX(Clan clan,
			String player) {
		 XYChart.Series<Number, Number> playerSeries = new XYChart.Series<>();
		ArrayList<Integer>  starList = clan.getPlayerStars(player);
		int xPoint = 1;
		for (int i : starList) {
			playerSeries.getData().add(new XYChart.Data(xPoint, i));
			xPoint++;
		}
		   
		return playerSeries;
	}
	public static ArrayList<Data> playerWarWinsFX(Clan clan, String player) {
		ArrayList<PieChart.Data> data = new ArrayList<PieChart.Data>(); 
		ArrayList<Integer>  part = clan.getPlayerWins(player);
		data.add(new PieChart.Data("Wins" , part.get(0)));
		data.add(new PieChart.Data("Losses", part.get(1)));
		return data;
	}
	public static ObservableList<XYChart.Data<String, Number>> getWarBarData(war clan) {
		ArrayList<XYChart.Data<String, Number>> data = new ArrayList<>();
		int i = 1;
		int stars = 0;
		for (Object o : clan) {
			Player p = (Player)o;
			stars += p.getStars();
			if(i % 5 == 0) {
				data.add(new XYChart.Data("" + (i-4) + "-" + i, stars));
				stars = 0;
			}
			i++;
		}
		
		
		return FXCollections.observableArrayList(data);
		
				
	}
}
