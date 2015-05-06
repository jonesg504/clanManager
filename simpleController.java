import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class simpleController
    implements Initializable {
	 XYChart.Series<Number, Number> series = new XYChart.Series<>();
	 private int i = 5;
    @FXML //  fx:id="myButton"
    private LineChart<Number, Number> clanPageChart; // Value injected by FXMLLoader
    @FXML
    private NumberAxis yAxis;
    @FXML
    private NumberAxis xAxis;
    
    @FXML
    private ListView<String> playerList;
    @FXML
    private static ComboBox<String> playerComboBox;
    @FXML 
    private LineChart<Number, Number> playerLineChart;
    @FXML 
    private PieChart playerPieChart;
    @FXML
    private NumberAxis playerStarXAxis;
    @FXML
    private NumberAxis playerStarYAxis;
    @FXML
    private ListView<String> warListView;
    @FXML
    private ToggleButton warBarChart;
    @FXML
    private ToggleButton warStarChart;
    @FXML
    private ToggleButton warWastedAttacks;
    @FXML
    private StackedBarChart<String, Number> warChart;
    @FXML
    private CategoryAxis warXAxis;
    @FXML
    private NumberAxis warYAxis;
    private int warSelection;
    
    
    static XMLParser parse = new XMLParser();
    static Clan clan = parse.loadClan("1.4");
    private ObservableList<String> playerNames;
    private static int graphChoice = 1;
    
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    	loadWarsInClan();
    	loadComboBox();
    	warChart.setAnimated(false);
    	warSelection = clan.getWars().size() - 1;
    	warYAxis.setAutoRanging(false);
    	warYAxis.setLowerBound(0);
    	warYAxis.setUpperBound(30);
    	warYAxis.setTickUnit(1);
    	
    	playerNames  = getPlayerNames(0);
    	playerList.setItems(playerNames);
    	playerLineChart.setAnimated(false);
    	playerLineChart.setLegendVisible(false);
        assert clanPageChart != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";
        warListView.setItems(loadWarList());
        playerStarYAxis.setLowerBound(0);
        playerStarYAxis.setUpperBound(6);
        playerStarXAxis.setTickUnit(1);
        playerStarXAxis.setLowerBound(1);
        playerStarXAxis.setUpperBound(clan.getWars().size());
        playerStarYAxis.setTickUnit(1);
        playerStarYAxis.setAutoRanging(false);
        playerStarXAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(100);
        xAxis.setTickUnit(1);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(clan.getWars().size());
        yAxis.setTickUnit(10);
        yAxis.setAutoRanging(false);
        xAxis.setAutoRanging(false);
        clanPageChart.setAnimated(false);
        clanPageChart.setLegendVisible(false);
        playerList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	handlePlayerList();
            }
        });

    }
    private static ObservableList<String> loadWarList() {
    	ArrayList<war> wars = clan.getWars();
    	ArrayList<String> list = new ArrayList<String>();
    	int i = 1;
    	for (war w : wars) {
    		list.add("War " + i);
    		i++;
    	}
    	ObservableList<String> ths = FXCollections.observableList(list);
    	return ths;
    }
    private static void loadComboBox() {
    	ArrayList<Integer> levels = new ArrayList<Integer>();
    	ArrayList<String> th = new ArrayList<String>();
    	for (Object o : clan) {
    		Player p = (Player) o;
    		if (!levels.contains(p.getThLevel())) { 
    			levels.add(p.getThLevel());
    		}
    	}
    	Collections.sort(levels);
    	for (Integer i : levels) {
    		th.add("Town Hall " + i);
    	}
    	th.add("All Players");
    	ObservableList<String> ths = FXCollections.observableList(th);
    	playerComboBox.setItems(ths);
    }
    private ObservableList<String> getPlayerNames(int thLevel) {
    	ObservableList<String> temp = FXCollections.observableArrayList();
    	for(Object o : clan) {
    		Player p = (Player) o;
    		if (p.getThLevel() == thLevel || thLevel == 0) {
    			temp.add(p.getName());
    		}
    	}
    	return temp;
	}
	@FXML
    private void handleToggle1Action(ActionEvent event) {
		
    	series = new XYChart.Series<>();
    	series.setName("MyPort");
        ArrayList<Double> data = graphData.performanceFX(clan);
        int i = 1;
        for (Double d : data) {
        	series.getData().add(new XYChart.Data(i, d));
        	i++;
        }
        clanPageChart.setTitle("Performance");
        clanPageChart.getData().setAll(series);
       // i++;
    }
    @FXML
    private void handleToggle2Action(ActionEvent event) {
    	series = new XYChart.Series<>();
    	series.setName("MyPort");
        ArrayList<Double> data = graphData.partingFX(clan);
        int i = 1;
        for (Double d : data) {
        	series.getData().add(new XYChart.Data(i, d));
        	i++;
        }
        clanPageChart.setTitle("Participation");
        clanPageChart.getData().setAll(series);
       // i++;
    }
    @FXML
    private void handleToggle3Action(ActionEvent event) {
    	series = new XYChart.Series<>();
    	series.setName("MyPort");
        ArrayList<Double> data = graphData.lossesFX(clan);
        int i = 1;
        for (Double d : data) {
        	series.getData().add(new XYChart.Data(i, d));
        	i++;
        }
        clanPageChart.setTitle("Wasted Attacks");
        clanPageChart.getData().setAll(series);
        clanPageChart.getData().get(0).getNode().setStyle("-fx-stroke: red;");
        Iterator symbols = clanPageChart.getData().get(0).getData().iterator();
        
        while(symbols.hasNext())
            ((Data)symbols.next()).getNode().setStyle("-fx-background-color: red, white;");
        
       // i++;
    }
    @FXML
    private void handlePlayerComboBox() {
    	String thLevel = playerComboBox.getValue();
    	if(thLevel.equals("All Players")){
    		thLevel = "0";
    	}
    	thLevel = thLevel.replaceAll("[A-Za-z ]+", "");
    	int level = Integer.parseInt(thLevel);
    	playerNames  = getPlayerNames(level);
    	playerList.setItems(playerNames);
    }
    @FXML
    public void handleWarToggle() {
    	updateWarGraph();
    	
    }
    
    @FXML
    private void handlePlayerList() {
    	updateGraphs();
    }
    @FXML
    private void handlePlayerGraphStars() {
    	graphChoice = 0;
    	updateGraphs();
    	
    }
    @FXML
    private void handlePlayerGraphPart() {
    	graphChoice = 1;
    	updateGraphs();
    	
    	
    }
    @FXML
    private void handlePlayerGraphWins() {
    	graphChoice = 2;
    	updateGraphs();
    	
    	
    }
    @FXML
    private void handleWarSelection() {
    	String selected = warListView.getSelectionModel().getSelectedItem();
    	selected = selected.replaceAll("[A-Za-z ]+", "");
    	warSelection = Integer.parseInt(selected) - 1;
    	updateWarGraph();
    	
    }
    @FXML
    private void popupExample() {
    	final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    public static void loadWarsInClan() {
		clan.clearWars();
		for (int i = 0; i <= parse.numWars(); i++) {
			war tempWar = parse.loadWar(i, "1.4");
			clan.addWar(tempWar);
			
		}
	}
    private void updateWarGraph() {
    	if(warBarChart.isSelected()) {
    		
    		ObservableList<XYChart.Data<String, Number>> barSeries = graphData.getWarBarData(clan.getWar(warSelection));
    		ArrayList<String> cats = new ArrayList<String>();
    		for (XYChart.Data d : graphData.getWarBarData(clan.getWar(warSelection))) {
    			cats.add((String)d.getXValue());
    		}
    		warXAxis.setCategories(FXCollections.<String> observableArrayList(cats));
    		XYChart.Series mySeries = new XYChart.Series(barSeries);
    		warChart.getData().setAll(mySeries);
    	} else if(warStarChart.isSelected()) {
    		System.out.println("Star");
    	} else if(warWastedAttacks.isSelected()) {
    		System.out.println("Wasted");
    	}
    }
    private void updateGraphs() {
    	 XYChart.Series<Number, Number> playerSeries = new XYChart.Series<>();
    	if (graphChoice == 0) {
    		playerLineChart.setTitle("Performance");
    		playerSeries = graphData.playerWarStatFX(clan, playerList.getSelectionModel().getSelectedItem());
    		
    		playerLineChart.getData().setAll(playerSeries);
    		playerLineChart.setOpacity(1);
        	playerPieChart.setOpacity(0);
    	} else if (graphChoice == 1) {
    		playerPieChart.setTitle("Participation");
    		playerLineChart.setOpacity(0);
    		playerPieChart.setLegendVisible(false);
        	playerPieChart.setOpacity(1);
        	ObservableList<PieChart.Data> pieDat = FXCollections.observableArrayList(graphData.playerWarPartFX(clan, playerList.getSelectionModel().getSelectedItem()));
        	
        	playerPieChart.setData(pieDat);
        	int i = 0;
        	String[] colors = {"green", "red"};
        	for (PieChart.Data data : pieDat) {
        		data.getNode().setStyle(
        				"-fx-pie-color: " + colors[i%2] + ";"
        				);
        		i++;
        	}
    	} else if (graphChoice == 2) {
    		playerPieChart.setTitle("Win Rate");
    		playerLineChart.setOpacity(0);
    		playerPieChart.setLegendVisible(false);
        	playerPieChart.setOpacity(1);
        	ObservableList<PieChart.Data> pieDat = FXCollections.observableArrayList(graphData.playerWarWinsFX(clan, playerList.getSelectionModel().getSelectedItem()));
        	playerPieChart.setData(pieDat);
        	int i = 0;
        	String[] colors = {"green", "red"};
        	for (PieChart.Data data : pieDat) {
        		data.getNode().setStyle(
        				"-fx-pie-color: " + colors[i%2] + ";"
        				);
        		i++;
        	}
    	}
    }
    

}