import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.FlowLayout;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JScrollPane;

import org.apache.commons.codec.binary.Base64;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class mainWindow extends JFrame {
	private static JRAWHandler redditHandle;
	private static boolean firstTime = false;
	private static JPanel panel_5 = new JPanel();
	private static JPanel contentPane;
	private static JComboBox comboBox;
	private static int choice = 1;
	private static DefaultComboBoxModel model;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private final Action action_4 = new SwingAction_4();
	protected static Clan clan = new Clan();
	protected static JProgressBar loading = new JProgressBar(0);
	private final Action action_5 = new SwingAction_5();
	private final Action action_6 = new SwingAction_6();
	protected static war currWar = new war();
	private static JTextArea textArea = new JTextArea();
	private static JTextArea txtrHelloMyName = new JTextArea();
	private static int currentWarNumber;
	private static String save = "Saving Clan";
	private static JFrame loadingFrame = new JFrame();
	private final Action action_7 = new SwingAction_7();
	private final Action action_8 = new SwingAction_8();
	private final Action action_9 = new SwingAction_9();
	private static ArrayList<Player> sortedClan;
	private static String bestPlayers = "";
	private static String worstPlayers = "";
	private static JLabel loadingLabel = new JLabel("Loading...");
	private static JTextArea txtrTopPlayers = new JTextArea();
	private static JTextArea txtrWorstPlayers = new JTextArea();
	private final Action action_10 = new SwingAction_10();
	private final Action action_11 = new SwingAction_11();
	private final Action action_12 = new SwingAction_12();
	private final Action action_13 = new SwingAction_13();
	private final Action action_14 = new SwingAction_14();
	private static XMLParser parse;
	private static boolean star3 = false;
	private final Action action_15 = new SwingAction_15();
	private final Action action_16 = new SwingAction_16();
	private HashMap<String, String> redditNames = new HashMap<String, String>();
	private final Action action_17 = new SwingAction_17();
	private final Action action_18 = new SwingAction_18();
	private final Action action_19 = new SwingAction_19();
	private final Action action_20 = new SwingAction_20();
	private final Action action_21 = new SwingAction_21();
	private final Action action_22 = new SwingAction_22();
	private final Action action_23 = new SwingAction_23();
	private final Action action_24 = new SwingAction_24();
	private static String version;
	private final Action action_25 = new SwingAction_25();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		loadingFrame.setResizable(false);
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		findFile();
		parse = new XMLParser();
		version = parse.canUpdate();
		currentWarNumber = parse.numWars();
		testVersionAndLoad();
		loadingLabel.setText("Load Successful!");
		loading.setValue(92);
		sortedClan = clan.getList();
		Collections.sort(sortedClan);
		currentWarNumber = parse.numWars();
		setBest();
		setWorst();
		loading.setValue(95);
		// for (Object o : clan) {
		// Player p = (Player) o;
		// if (p.getNumWars() >= 1 && p.getWorth() <= 0) {
		// p.setWorth(1);
		// } else if (p.getNumWars() == 0) {
		// p.setWorth(-1);
		// }
		// }
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				editGraph();
				try {
					// recalculateWorths();
					testRedditUserName();
					loading.setValue(100);
					mainWindow frame = new mainWindow();
					loadingFrame.dispose();
					frame.setTitle(clan.getClanName());
					frame.setVisible(true);
					loading.setValue(0);
					frame.setResizable(false);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							final JFrame closingFrame = new JFrame();
							closingFrame.setSize(200, 100);
							Dimension dim = Toolkit.getDefaultToolkit()
									.getScreenSize();
							closingFrame.setLocation(dim.width / 2
									- loadingFrame.getSize().width / 2,
									dim.height / 2
											- loadingFrame.getSize().height / 2);
							JPanel pan = new JPanel(new GridLayout(1, 1));
							pan.add(new JLabel(save));
							closingFrame.setContentPane(pan);
							closingFrame
									.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							closingFrame.setVisible(true);
							test.writeClan(clan, null, null, clan.getWars()
									.size() - 1);

						}
					});
					if (firstTime) {
						EnemyTHLevels thLevels = new EnemyTHLevels(currWar);
						thLevels.setVisible(true);
						firstTime = false;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void updateSave() {
		save = "POOP";
	}
	public static void testRedditUserName() {
		if( clan.getUserName().equals("ClanManagerApp")) {;
			final JTextField redditUser = new JTextField(10);
			final JPasswordField redditPass = new JPasswordField(10);
			final JPanel panel = new JPanel(new GridLayout(4, 1));
			panel.add(new JLabel("Reddit Username:"));
			panel.add(redditUser);
			panel.add(new JLabel("Reddit Password:"));
			panel.add(redditPass);
			Object[] options = { "OK", "Cancel" };
			int result = JOptionPane.showOptionDialog(null, panel,
					"Data Entry", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, null);

			if (result == JOptionPane.OK_OPTION) {
				clan.setUserName(redditUser.getText());
				clan.setPassword(encrypt(redditPass.getText()));
				test.writeClan(clan, null, null, clan.getWars()
						.size() - 1);
			}
		}
	}
	public static void showLoadingWindow() {

		loadingFrame.setSize(200, 100);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		loadingFrame.setLocation(dim.width / 2 - loadingFrame.getSize().width
				/ 2, dim.height / 2 - loadingFrame.getSize().height / 2);
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(loadingLabel);
		loading.setValue(0);
		panel.add(loading);
		loadingFrame.setContentPane(panel);
		loadingFrame.setVisible(true);
	}

	public static void loadWarsInClan() {
		clan.clearWars();
		for (int i = 0; i <= parse.numWars(); i++) {
			war tempWar = parse.loadWar(i, version);
			clan.addWar(tempWar);
			loadingLabel.setText("Loading War " + (i + 1));
			loading.setValue((int) (10 + ((((double) i + 1) / ((double) parse
					.numWars() + 1)) * 100) - 20));
		}
	}

	public static void refresh() {
		model = new DefaultComboBoxModel();
		populateComboBox();
		comboBox.setModel(model);
		comboBox.setSelectedIndex(currentWarNumber);
		setBest();
		setWorst();

		currentWarNumber = clan.getWars().size() - 1;
		txtrHelloMyName.setText("War: " + (currentWarNumber + 1) + "\n"
				+ currWar.toString() + "\n" + topAttackers());
		choice = 1;
		editGraph();

		test.writeClan(clan, null, null, clan.getWars().size() - 1);
	}

	public static void testVersionAndLoad() {
		if (!version.equals("1.6")) {
			showLoadingWindow();
			clan = parse.loadClan(version);
			loadWarsInClan();
			storeWorthsInAttack();
			test.writeClan(clan, null, null, clan.getWars().size() - 1);
			firstTime = true;
		} else {
			loadingLabel.setText("Loading File");
			showLoadingWindow();
			clan = parse.loadClan(version);
			loading.setValue(10);
			loadWarsInClan();

		}

	}

	public static void findFile() {

		//String pass = encrypt("mypass");
		File f = new File("./save.xml");
		if (f.exists() && !f.isDirectory()) {
			System.out.println("File Exists");
		} else {
			Clan newClan = new Clan();
			final JTextField clanName = new JTextField(10);
			final JTextField redditUser = new JTextField(10);
			final JPasswordField redditPass = new JPasswordField(10);
			final JTextField subreddit = new JTextField(10);
			final JPanel panel = new JPanel(new GridLayout(8, 1));
			panel.add(new JLabel("Clan Name:"));
			panel.add(clanName);
			panel.add(new JLabel("Clan Subreddit: /r/"));
			panel.add(subreddit);
			panel.add(new JLabel("Reddit Username:"));
			panel.add(redditUser);
			panel.add(new JLabel("Reddit Password:"));
			panel.add(redditPass);
			Object[] options = { "OK", "Cancel" };
			int result = JOptionPane.showOptionDialog(null, panel,
					"Data Entry", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, null);

			if (result == JOptionPane.OK_OPTION) {
				newClan.setClanName(clanName.getText());
				newClan.setUserName(redditUser.getText());
				newClan.setPassword(encrypt(redditPass.getText()));
				newClan.setSubreddit(subreddit.getText());
				test.newClan(newClan);
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public mainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JButton btnNewButton_12 = new JButton("New button");
		panel.add(btnNewButton_12);
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_12.setAction(action_12);

		JButton btnNewButton_9 = new JButton("New button");
		btnNewButton_9.setAction(action_5);
		panel.add(btnNewButton_9);

		JButton btnNewButton_15 = new JButton("New button");
		panel.add(btnNewButton_15);
		btnNewButton_15.setAction(action_20);

		JButton btnNewButton_8 = new JButton("New button");
		btnNewButton_8.setAction(action_9);
		panel.add(btnNewButton_8);

		JButton btnNewButton_7 = new JButton("New button");
		btnNewButton_7.setAction(action_10);
		panel.add(btnNewButton_7);

		JButton btnNewButton_17 = new JButton("New button");
		panel.add(btnNewButton_17);
		btnNewButton_17.setAction(action_17);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnSubredditManager = new JButton("Subreddit Manager");
		btnSubredditManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubredditManager.setAction(action_19);
		panel_1.add(btnSubredditManager);
		
		JButton btnNewButton_19 = new JButton("New button");
		btnNewButton_19.setAction(action_25);
		panel_1.add(btnNewButton_19);

		JButton btnNewButton = new JButton("New Player(s)");
		btnNewButton.setAction(action_3);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Remove Player");
		btnNewButton_1.setAction(action_4);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_11 = new JButton("New button");
		panel_1.add(btnNewButton_11);
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_11.setAction(action_11);

		JButton btnNewButton_14 = new JButton("New button");
		panel_1.add(btnNewButton_14);
		btnNewButton_14.setAction(action_14);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		panel_2.setPreferredSize(new Dimension(100, 200));

		txtrTopPlayers.setEditable(false);
		txtrTopPlayers.setBackground(new Color(190, 196, 198));
		txtrTopPlayers.setText(bestPlayers);
		panel_2.add(txtrTopPlayers);

		txtrWorstPlayers.setEditable(false);
		txtrWorstPlayers.setText(worstPlayers);
		txtrWorstPlayers.setBackground(new Color(190, 196, 198));
		panel_2.add(txtrWorstPlayers);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(null);

		panel_5.setBounds(1, 41, 506, 294);
		panel_5.setBackground(new Color(213, 217, 223));
		panel_4.add(panel_5);
		txtrHelloMyName.setLocation(509, 31);
		txtrHelloMyName.setBackground(new Color(190, 196, 198));
		panel_4.add(txtrHelloMyName);
		txtrHelloMyName.setSize(new Dimension(152, 304));
		txtrHelloMyName.setMinimumSize(new Dimension(35, 35));
		txtrHelloMyName.setEditable(false);
		currWar = clan.getWar(currentWarNumber);
		if (currWar == null) {
			currWar = new war();
		}
		txtrHelloMyName.setLineWrap(true);
		txtrHelloMyName.setText("War: " + (currentWarNumber + 1) + "\n"
				+ currWar.toString() + "\n" + topAttackers());
		model = new DefaultComboBoxModel();
		populateComboBox();

		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_6.setAction(action_22);
		btnNewButton_6.setBounds(529, 336, 115, 23);
		panel_4.add(btnNewButton_6);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 346, 510, 23);
		panel_4.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_2 = new JButton("War Performance");
		panel_3.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setAction(action);

		JButton btnNewButton_3 = new JButton("New button");
		panel_3.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setAction(action_1);

		JButton btnNewButton_4 = new JButton("New button");
		panel_3.add(btnNewButton_4);
		btnNewButton_4.setAction(action_2);

		JButton btnNewButton_18 = new JButton("New button");
		btnNewButton_18.setAction(action_24);
		panel_3.add(btnNewButton_18);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(4, 9, 499, 24);
		panel_4.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_10 = new JButton("New button");
		panel_6.add(btnNewButton_10);
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_10.setAction(action_6);

		JButton btnNewButton_13 = new JButton("New button");
		panel_6.add(btnNewButton_13);
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_13.setAction(action_13);

		JButton btnNewButton_5 = new JButton("New button");
		panel_6.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currWar = clan.getCurrentWar();
				currentWarNumber = clan.getWars().size() - 1;
				comboBox.setSelectedIndex(currentWarNumber);
				editGraph();
				txtrHelloMyName.setText("War: " + (currentWarNumber + 1) + "\n"
						+ currWar.toString() + "\n" + topAttackers());
			}
		});
		btnNewButton_5.setAction(action_21);
		comboBox = new JComboBox(model);
		panel_6.add(comboBox);
		comboBox.setSelectedIndex(currentWarNumber);

		JButton btnNewButton_16 = new JButton("New button");
		btnNewButton_16.setBounds(528, 6, 117, 23);
		panel_4.add(btnNewButton_16);
		btnNewButton_16.setAction(action_23);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();
				int warNum = combo.getSelectedIndex();

				currWar = clan.getWar(warNum);
				currentWarNumber = warNum;
				editGraph();
				txtrHelloMyName.setText("War: " + (currentWarNumber + 1) + "\n"
						+ currWar.toString() + "\n" + topAttackers());
			}
		});
		currWar = clan.getWar(currentWarNumber);
	}

	public static void populateComboBox() {
		ArrayList<war> warList = clan.getWars();
		String[] myWars = new String[warList.size()];
		int i = 0;
		for (war w : warList) {
			if (w.getEnemyName() == null || w.getEnemyName().contains("NotSet")) {
				model.addElement("War " + (i + 1));
				i++;
			} else {
				model.addElement("Vs. " + w.getEnemyName());
			}
		}

	}

	public static void loadClan() {
		parse = new XMLParser();
		clan = parse.loadClan(version);
		loadWarsInClan();
		setBest();
		setWorst();
		txtrHelloMyName.setText("War: " + (currentWarNumber + 1) + "\n"
				+ currWar.toString() + "\n" + topAttackers());

	}

	// GRAPH HANDLERS
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	private static void editGraph() {
		if (parse.numWars() == -1) {

		} else if (choice == 1) {
			JFreeChart xylineChart = ChartFactory.createXYLineChart(null,
					"War Number", "Percent of Stars Obtained",
					graphData.performance(clan), PlotOrientation.VERTICAL,
					true, true, false);
			final ChartPanel chartPanel = new ChartPanel(xylineChart);
			chartPanel.setPreferredSize(new java.awt.Dimension(491, 280));
			final XYPlot plot = xylineChart.getXYPlot();
			ValueAxis yAxis = plot.getRangeAxis();
			yAxis.setRange(0.0, 100.0);
			NumberAxis xAxis = new NumberAxis();
			xAxis.setTickUnit(new NumberTickUnit(1));
			plot.setDomainAxis(xAxis);
			if (clan.getWars().size() == 1) {
				xAxis.setRange(1, clan.getWars().size() + 2);
			} else {
				xAxis.setRange(1, clan.getWars().size());
			}
			// plot.setBackgroundPaint(new Color(213,217,223,100));
			xylineChart.setBackgroundPaint(new Color(0, 0, 0, 0));
			xAxis.setLabel("War Number");
			xAxis.setLabelFont(yAxis.getLabelFont());
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesPaint(1, Color.GREEN);
			renderer.setSeriesPaint(2, Color.YELLOW);
			renderer.setSeriesStroke(0, new BasicStroke(4.0f));
			renderer.setSeriesStroke(1, new BasicStroke(3.0f));
			renderer.setSeriesStroke(2, new BasicStroke(2.0f));
			plot.setRenderer(renderer);
			chartPanel.getChart().removeLegend();
			panel_5.removeAll();
			panel_5.add(chartPanel);
			panel_5.repaint();
			panel_5.validate();
		} else if (choice == 2) {
			JFreeChart xylineChart = ChartFactory.createXYLineChart(null,
					"War Number", "Participation", graphData.parting(clan),
					PlotOrientation.VERTICAL, true, true, false);
			final ChartPanel chartPanel = new ChartPanel(xylineChart);
			chartPanel.setPreferredSize(new java.awt.Dimension(491, 280));
			final XYPlot plot = xylineChart.getXYPlot();
			ValueAxis yAxis = plot.getRangeAxis();
			yAxis.setRange(0.0, 100.0);
			NumberAxis xAxis = new NumberAxis();
			xAxis.setTickUnit(new NumberTickUnit(1));
			plot.setDomainAxis(xAxis);
			if (clan.getWars().size() == 1) {
				xAxis.setRange(1, clan.getWars().size() + 2);
			} else {
				xAxis.setRange(1, clan.getWars().size());
			}
			xylineChart.setBackgroundPaint(new Color(0, 0, 0, 0));
			xAxis.setLabel("War Number");
			xAxis.setLabelFont(yAxis.getLabelFont());
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesPaint(1, Color.GREEN);
			renderer.setSeriesPaint(2, Color.YELLOW);
			renderer.setSeriesStroke(0, new BasicStroke(4.0f));
			renderer.setSeriesStroke(1, new BasicStroke(3.0f));
			renderer.setSeriesStroke(2, new BasicStroke(2.0f));
			plot.setRenderer(renderer);
			chartPanel.getChart().removeLegend();
			panel_5.removeAll();
			panel_5.add(chartPanel);
			panel_5.repaint();
			panel_5.validate();
		} else if (choice == 3) {
			JFreeChart xylineChart = ChartFactory.createXYLineChart(null,
					"War Number", "Percent of Wasted Attacks",
					graphData.losses(clan), PlotOrientation.VERTICAL, true,
					true, false);
			final ChartPanel chartPanel = new ChartPanel(xylineChart);
			chartPanel.setPreferredSize(new java.awt.Dimension(491, 280));
			final XYPlot plot = xylineChart.getXYPlot();
			ValueAxis yAxis = plot.getRangeAxis();
			yAxis.setRange(0.0, 100.0);
			NumberAxis xAxis = new NumberAxis();
			xAxis.setTickUnit(new NumberTickUnit(1));
			plot.setDomainAxis(xAxis);
			if (clan.getWars().size() == 1) {
				xAxis.setRange(1, clan.getWars().size() + 2);
			} else {
				xAxis.setRange(1, clan.getWars().size());
			}
			xylineChart.setBackgroundPaint(new Color(0, 0, 0, 0));
			xAxis.setLabel("War Number");
			xAxis.setLabelFont(yAxis.getLabelFont());
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesPaint(1, Color.GREEN);
			renderer.setSeriesPaint(2, Color.YELLOW);
			renderer.setSeriesStroke(0, new BasicStroke(4.0f));
			renderer.setSeriesStroke(1, new BasicStroke(3.0f));
			renderer.setSeriesStroke(2, new BasicStroke(2.0f));
			plot.setRenderer(renderer);
			chartPanel.getChart().removeLegend();
			panel_5.removeAll();
			panel_5.add(chartPanel);
			panel_5.repaint();
			panel_5.validate();
		} else if (choice == 4) {
			DefaultPieDataset dataset = (DefaultPieDataset) graphData
					.attackBreakDown(clan, currWar);
			JFreeChart chart = ChartFactory.createPieChart("War "
					+ (currentWarNumber + 1) + " Stars Breakdown", // chart
					// title
					dataset, // data
					true, // include legend
					true, false);

			final ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.getChart().removeLegend();
			chart.setBackgroundPaint(new Color(0, 0, 0, 0));
			chartPanel.setPreferredSize(new java.awt.Dimension(450, 280));
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
			plot.setNoDataMessage("No data available");
			plot.setCircular(false);
			plot.setLabelGap(0.02);
			PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator(
					"{0} {2}", new DecimalFormat("0"), new DecimalFormat(
							"0.00%"));
			plot.setLabelGenerator(generator);
			panel_5.removeAll();
			panel_5.add(chartPanel);
			panel_5.repaint();
			panel_5.validate();
		}
	}

	private static void runPlayerStats() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					playerStats frame = new playerStats(clan, parse);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// CLAN HANDLERS
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	public static void setRedditNames() {

	}

	private static void setBest() {
		Collections.sort(sortedClan);
		bestPlayers = "Top Members: \n";
		for (int i = 0; i < 10 && i < sortedClan.size(); i++) {
			bestPlayers = bestPlayers + (i + 1) + ") "
					+ sortedClan.get(i).getName() + "\n";
		}
		txtrTopPlayers.setText(bestPlayers);

	}

	private static void setWorst() {
		Collections.sort(sortedClan);
		int s = sortedClan.size() - 1;
		worstPlayers = "Bottom Members: \n";
		for (int i = s; i > s - 10 && i > 0; i--) {
			if (sortedClan.get(i).getWorth() != -1) {

				worstPlayers = worstPlayers + (i + 1) + ") "
						+ sortedClan.get(i).getName() + "\n";
			} else {
				s--;
			}
		}
		txtrWorstPlayers.setText(worstPlayers);
	}

	private static void recalculateWorths() {
		ArrayList<war> allWars = clan.getWars();
		for (Object o : clan) {
			Player p = (Player) o;
			p.setWorth(0);
			p.setNumWars(0);
		}
		for (war w : allWars) {
			for (Object o : w) {
				Player play = (Player) o;
				if (clan.getPlayer(play.getName()) != null) {
					clan.getPlayer(play.getName()).addWar();
				}
			}
			for (Attack a : w.getAttacks()) {
				// a.setWorth(a.calcWorth(w.getNumPlayers, play));
				System.out.println(a.getPlayer());
				if (clan.getPlayer(a.getPlayer()) != null) {
					clan.getPlayer(a.getPlayer()).addWorth(
							a.calcWorth(w.getNumPlayers(),
									clan.getPlayer(a.getPlayer())));
				}
			}
		}
	}

	private static void storeWorthsInAttack() {
		ArrayList<war> allWars = clan.getWars();
		for (war w : allWars) {
			for (Attack a : w.getAttacks()) {
				if (clan.getPlayer(a.getPlayer()) != null)
					a.setWorth((int) a.calcWorth(w.getNumPlayers(),
							clan.getPlayer(a.getPlayer())));

			}
		}
	}

	private static void removePlayers() {
		int num = Integer
				.parseInt(JOptionPane
						.showInputDialog("How many players are you removing?(Integer plz)"));
		for (int i = 0; i < num; i++) {
			String removed = JOptionPane.showInputDialog("Enter Name:")
					.toLowerCase();
			Player gone = clan.remove(new Player(removed));
			if (gone == null) {
				JOptionPane.showMessageDialog(null, "Player doesnt Exist!");
			}

		}

		currWar = clan.getCurrentWar();
		test.writeClan(clan, currWar, null, parse.numWars());
		parse = new XMLParser();
		setWorst();
		setBest();
	}

	private static void addPlayers() {
		int num = Integer
				.parseInt(JOptionPane
						.showInputDialog("How many players are you adding?(Integer plz)"));
		for (int i = 0; i < num; i++) {
			final JTextField username = new JTextField(10);
			final JTextField thLevel = new JTextField(10);
			final JPanel panel = new JPanel(new GridLayout(4, 1));
			panel.add(new JLabel("Username:"));
			panel.add(username);
			panel.add(new JLabel("Townhall Level:"));
			panel.add(thLevel);

			Object[] options = { "OK", "Cancel" };
			int result = JOptionPane.showOptionDialog(null, panel,
					"Data Entry", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, null);
			if (result == 1) {
				break;
			}
			if (result == JOptionPane.OK_OPTION) {
				ArrayList<Player> removedList = (ArrayList<Player>) clan
						.getRemoved().clone();
				boolean unique = true;
				for (Player p : removedList) {
					if (p.getName().equals(username.getText().toLowerCase())) {
						int choice = JOptionPane.showConfirmDialog(null,
								"Do you want to Re-Add " + p.getName()
										+ " to the clan with their old data?",
								"Confirm Re-Adding", JOptionPane.YES_NO_OPTION);
						if (choice == 0) {
							clan.addBackToClan(p);
							unique = false;
						}
					}
				}
				if (unique) {
					clan.add(new Player(username.getText().toLowerCase(),
							Integer.parseInt(thLevel.getText())));
				}
				test.writeClan(clan, null, null, parse.numWars());

				parse = new XMLParser();

			}
		}
	}

	private static String topAttackers() {
		String list = "Top Attacks:   \n";
		war testWar = clan.getWar(currentWarNumber);
		if (testWar == null) {
			testWar = new war();
		}
		testWar.updateWorths(clan, testWar.getNumPlayers());
		ArrayList<Attack> attacks = testWar.getAttacks();

		Collections.sort(attacks, new attackComp(testWar, clan));
		int i = 1;
		for (Attack att : attacks) {
			list += i + ")" + att.attString() + "(" + att.getWorth() + ")  \n";
			i++;
			if (i == 11) {
				break;
			}
		}
		return list;

	}

	public static String topFromTH() {
		String list = "**Clan Rankings**     \n     \nRanking ";
		ArrayList<Player> playas = (ArrayList<Player>) clan.getList().clone();
		Collections.sort(playas);
		int i = 1;
		HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();
		for (int num = 1; num <= 10; num++) {
			numbers.put(num, 0);
		}
		ArrayList<Integer> thLevels = new ArrayList<Integer>();
		for (Object o : clan) {
			Player p = (Player) o;
			if (!thLevels.contains(p.getThLevel())) {
				thLevels.add(p.getThLevel());
			}
			int th = p.getThLevel();
			numbers.put(th, numbers.get(th) + 1);

		}
		Collections.sort(thLevels);
		// Collections.reverse(thLevels);
		String[][] ranks = new String[5][thLevels.size()];
		for (int j = thLevels.size() - 1; j >= 0; j--) {
			list += "|Townhall " + thLevels.get(j);
		}
		list += "   \n ---";
		for (Integer num : thLevels) {
			list += "|---";
		}
		list += "  \n";
		int highest = thLevels.get(thLevels.size() - 1);
		for (Integer num : thLevels) {
			int numba = 0;
			for (Player play : playas) {
				if (play.getThLevel() == num) {
					// list += (numba + 1) + ") " + play.getName() + "  \n";
					System.out.println(play);
					ranks[numba][highest - play.getThLevel()] = play.getName();
					numba++;
				}
				if (numba == 5 || numba >= numbers.get(num)) {
					break;
				}
			}

		}
		for (int r = 0; r < ranks.length; r++) {
			list += (r + 1) + "";
			for (int c = 0; c < ranks[0].length; c++) {
				if (ranks[r][c] == null) {
					list += "| ";
				} else {
					list += "|" + ranks[r][c];
				}
			}
			list += "   \n";
		}
		return list;

		// int numba = 0;
		// list += "**Townhall " + num + "**   \n";
		// for (Player play : playas) {
		//
		// if (play.getThLevel() == num) {
		// list += (numba + 1) + ") " + play.getName() + "  \n";
		//
		// numba++;
		// }
		// if (numba == 5 || numba >= numbers.get(num)) {
		// break;
		// }
		// }
		//
		// }
		// return list;
	}

	public static String rankings() {
		String list = "**Clan Leaderboard:**   \n";
		ArrayList<Player> playas = (ArrayList<Player>) clan.getList().clone();
		Collections.sort(playas);
		int i = 1;
		for (Player p : playas) {
			if (p.getWorth() == -1) {
				list += "No data yet) " + p.getName() + "  \n";
				i++;
			} else {
				list += i + ") " + p.getName() + "  \n";
				i++;
			}
		}
		return list;
	}

	static String totalAttacks() {
		String list = "Attacks:   \n";
		war testWar = clan.getWar(currentWarNumber);
		// testWar.updateWorths(clan, testWar.getNumPlayers());
		ArrayList<Attack> attacks = testWar.getAttacks();

		for (Attack a : attacks) {
			if (a.isStar3()) {
				list += "**" + a.getPlayer() + " (3 Starred!) against "
						+ a.getBaseRank() + "**  \n";
			} else {
				list += a.getPlayer() + " (" + a.getStars() + ") against "
						+ a.getBaseRank() + "  \n";
			}
		}
		return list;
	}

	static String topAttackersforPost() {
		String list = "Top Attacks:   \n";
		war testWar = clan.getWar(currentWarNumber);
		// testWar.updateWorths(clan, testWar.getNumPlayers());
		ArrayList<Attack> attacks = testWar.getAttacks();

		Collections.sort(attacks, new attackComp(testWar, clan));
		int i = 1;
		HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();
		for (int num = 1; num <= 10; num++) {
			numbers.put(num, 0);
		}
		ArrayList<Integer> thLevels = new ArrayList<Integer>();
		for (Object o : clan) {
			Player p = (Player) o;
			if (!thLevels.contains(p.getThLevel())) {
				thLevels.add(p.getThLevel());
			}
			int th = p.getThLevel();
			numbers.put(th, numbers.get(th) + 1);

		}
		Collections.sort(thLevels);
		for (Integer num : thLevels) {
			int numba = 0;
			list += "**Townhall " + num + "**   \n";
			for (Attack att : attacks) {
				if (clan.getPlayer(att.getPlayer()) != null) {
					if (clan.getPlayer(att.getPlayer()).getThLevel() == num) {
						if (!att.isStar3()) {
							list += (numba + 1) + ") " + att.getPlayer()
									+ " (Attack " + att.getAttNum() + ": "
									+ (att.getStars() + att.getGhostStars())
									+ " Stars)  \n";
						} else {
							list += (numba + 1) + ") " + att.getPlayer()
									+ " (Attack " + att.getAttNum()
									+ ": 3 starred!)  \n";
						}
						numba++;
					}
				}
				if (numba == 5 || numba >= numbers.get(num)) {
					break;
				}
			}

		}
		list += "**Attacks Unused:**   \n";
		for (Object o : currWar) {
			Player play = (Player) o;
			if (play.getAttackU() < 2) {
				list += play.getName() + "(" + (2 - play.getAttackU())
						+ ")  \n";
			}
		}
		return list;

	}

	static void saveRanks(String name, int rank) {
		Player player = clan.getPlayer(name);
		player.setRank(rank);

	}

	public static void saveClan() {
		test.writeClan(clan, null, null, parse.numWars());
		parse = new XMLParser();
	}

	private static void addAttack() {

		int num = Integer
				.parseInt(JOptionPane
						.showInputDialog("How many attacks are you adding?(Integer plz)"));
		for (int i = 0; i < num; i++) {
			star3 = false;
			final JComboBox username = new JComboBox(currWar.getMembersNamesWithAttacks());
			final JTextField stars = new JTextField(10);
			final JTextField rank = new JTextField(10);
			final JCheckBox box = new JCheckBox();
			final JCheckBox star1 = new JCheckBox("1 Star");
			final JCheckBox star2 = new JCheckBox("2 Star");
			final JCheckBox star03 = new JCheckBox("3 Star");
			final JCheckBox ghostStar1 = new JCheckBox("1 Star");
			final JCheckBox ghostStar2 = new JCheckBox("2 Star");
			final JCheckBox ghostStar03 = new JCheckBox("3 Star");
			final JPanel panel = new JPanel(new GridLayout(8, 1));
			final JPanel boxPanel = new JPanel(new GridLayout(1, 3));
			final JPanel ghostBoxPanel = new JPanel(new GridLayout(1, 3));
			panel.add(new JLabel("Attacker:"));
			panel.add(username);
			panel.add(new JLabel("Bonus Stars:"));
			boxPanel.add(star1);
			boxPanel.add(star2);
			boxPanel.add(star03);
			ghostBoxPanel.add(ghostStar1);
			ghostBoxPanel.add(ghostStar2);
			ghostBoxPanel.add(ghostStar03);
			panel.add(boxPanel);
			panel.add(new JLabel("Opponent Rank: "));
			panel.add(rank);
			panel.add(new JLabel("How many non bonus stars?"));
			panel.add(ghostBoxPanel);
			box.addItemListener(new CheckBoxListener());
			Object[] options = { "OK", "Cancel" };
			int result = JOptionPane.showOptionDialog(null, panel,
					"Data Entry", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, null);

			if (result == 1) {
				JOptionPane.showMessageDialog(null, "Attack Adding cancelled");
				break;
			}
			if (result == JOptionPane.OK_OPTION) {
				if (clan.getPlayer((String) username.getSelectedItem()) != null) {
					int numStars = 0;
					currWar = clan.getWar(parse.numWars());
					int enRank = Integer.parseInt(rank.getText());
					if (star1.isSelected()) {
						numStars = 1;
					} else if (star2.isSelected()) {
						numStars = 2;
					} else if (star03.isSelected()) {
						numStars = 3;
					}
					int numGhostStars = 0;
					if (ghostStar1.isSelected()) {
						numGhostStars = 1;
					} else if (ghostStar2.isSelected()) {
						numGhostStars = 2;
					} else if (ghostStar03.isSelected()) {
						numGhostStars = 3;
					}
					Attack att = new Attack(
							(String) username.getSelectedItem(), numStars,
							enRank, currWar.getEnTH(enRank - 1), numGhostStars);
					// boolean did3Star = currWar.check3Star(att);

					if (star3) {
						att.setStar3(true);
					}
					for (Object o : currWar) {
						Player p = (Player) o;
						if (!p.inClan()) {
							currWar.remove(p);
						} else {
							p.setThLevel(clan.getPlayer(p.getName())
									.getThLevel());
						}
					}
					currWar.getPlayer((String) username.getSelectedItem())
							.setRank(
									clan.getPlayer(
											(String) username.getSelectedItem())
											.getRank());
					att.setAttNum(currWar.getAttacks().size() + 1);
					double worth = currWar.attack(currWar
							.getPlayer((String) username.getSelectedItem()),
							att);
					clan.getPlayer((String) username.getSelectedItem())
							.addWorth(worth);
					att.setWorth((int) worth);
					currWar.loadAttack(att);
					clan.updateWar(currWar);

					test.writeClan(clan, null, null, parse.numWars());
					parse = new XMLParser();
					currentWarNumber = parse.numWars();
					txtrHelloMyName
							.setText("War: " + (clan.getWars().size()) + "\n"
									+ currWar.toString() + "\n"
									+ topAttackers());
					txtrHelloMyName.setCaretPosition(0);
					setWorst();
					setBest();
					choice = 1;
					editGraph();
				} else {
					JOptionPane
							.showMessageDialog(null,
									"ERROR: I think you typed the name wrong? Check clan list!");
					i--;
				}
			}
		}

	}

	private static class CheckBoxListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (star3) {
				star3 = false;
			} else {
				star3 = true;
			}
		}
	}

	private static void addDefense() {
		int num = Integer
				.parseInt(JOptionPane
						.showInputDialog("How many defenses are you adding?(Integer plz)"));
		for (int i = 0; i < num; i++) {
			final JComboBox username = new JComboBox(currWar.getMembersNames());
			final JTextField defLevel = new JTextField(10);
			final JTextField stars = new JTextField(10);
			final JPanel panel = new JPanel(new GridLayout(4, 1));
			final JPanel boxPanel = new JPanel(new GridLayout(1, 4));
			final JCheckBox star0 = new JCheckBox("0");
			final JCheckBox star1 = new JCheckBox("1");
			final JCheckBox star2 = new JCheckBox("2");
			final JCheckBox star03 = new JCheckBox("3");
			panel.add(new JLabel("Defender:"));
			panel.add(username);
			panel.add(new JLabel("Added Stars:"));
			boxPanel.add(star0);
			boxPanel.add(star1);
			boxPanel.add(star2);
			boxPanel.add(star03);
			panel.add(boxPanel);

			defLevel.setText("1");
			Object[] options = { "OK", "Cancel" };
			int result = JOptionPane.showOptionDialog(null, panel,
					"Data Entry", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, null);

			if (result == JOptionPane.OK_OPTION) {
				if (clan.getPlayer(((String) username.getSelectedItem())
						.toLowerCase()) != null) {
					if (star1.isSelected()) {
						stars.setText("1");
					} else if (star2.isSelected()) {
						stars.setText("2");
					} else if (star03.isSelected()) {
						stars.setText("3");
					} else {
						stars.setText("0");
					}
					currWar = clan.getCurrentWar();
					System.out.println(Integer.parseInt(stars.getText()));
					double worth = currWar.defense(clan
							.getPlayer(((String) username.getSelectedItem())
									.toLowerCase()), currWar.getEnTH(Integer
							.parseInt(defLevel.getText()) - 1), Integer
							.parseInt(stars.getText()));

					clan.getPlayer((String) username.getSelectedItem())
							.addWorth(worth);
					currWar.loadDefense(new Defense(((String) username
							.getSelectedItem()).toLowerCase().toLowerCase(),
							Integer.parseInt(stars.getText()), currWar
									.getEnTH(Integer.parseInt(defLevel
											.getText())), worth));

					clan.updateWar(currWar);
					test.writeClan(clan, null, null, parse.numWars());
					parse = new XMLParser();
					txtrHelloMyName.setText("War: " + currentWarNumber + "\n"
							+ currWar.toString() + "\n" + topAttackers());
					txtrHelloMyName.setCaretPosition(0);
					setWorst();
					setBest();
				} else {
					JOptionPane
							.showMessageDialog(null,
									"ERROR: I think you typed the name wrong? Check clan list!");
					i--;
				}

			}
		}
	}

	private static void editAttack() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					currWar = clan.getWar(currentWarNumber);
					attackEditor frame = new attackEditor(clan, currWar,
							currentWarNumber);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// String name = JOptionPane.showInputDialog("Who's Attacks?");
		// String choice = JOptionPane
		// .showInputDialog("Have they attacked more than once? (y/n)");
		// int num = 0;
		// if (choice.equalsIgnoreCase("y")) {
		// num = 2;
		// } else if (choice.equalsIgnoreCase("n")) {
		// num = 1;
		// }
		// currWar = clan.getCurrentWar();
		// Player player = currWar.getPlayer(name);
		// player.setAttackU(0);
		// player.setStars(0);
		// player.setAttackW(0);
		// player = clan.getPlayer(name);
		// ArrayList<Attack> removed = currWar.eraseAttacks(name);
		// for (Attack att : removed) {
		// double worth = att.calcWorth(currWar.getNumPlayers(), player);
		// player.removeWorth(worth);
		// }
		// for (int i = 0; i < num; i++) {
		// final JTextField defLevel = new JTextField(10);
		// final JTextField stars = new JTextField(10);
		// final JTextField rank = new JTextField(10);
		// final JPanel panel = new JPanel(new GridLayout(7, 1));
		// panel.add(new JLabel("Attack " + (i + 1)));
		// panel.add(new JLabel("Added Stars:"));
		// panel.add(stars);
		// panel.add(new JLabel("Attacker TownHall Level: "));
		// panel.add(defLevel);
		// panel.add(new JLabel("Opponent Rank: "));
		// panel.add(rank);
		// Object[] options = { "OK", "Cancel" };
		// int result = JOptionPane.showOptionDialog(null, panel,
		// "Data Entry", JOptionPane.OK_CANCEL_OPTION,
		// JOptionPane.QUESTION_MESSAGE, null, options, null);
		//
		// if (result == JOptionPane.OK_OPTION) {
		// // System.out.println(currWar.getAttacks());
		// Attack att = new Attack(player.getName(),
		// Integer.parseInt(stars.getText()),
		// Integer.parseInt(rank.getText()),
		// Integer.parseInt(defLevel.getText()));
		// if (star3) {
		// att.setStar3(true);
		// }
		// double worth = currWar.attack(player, att);
		//
		// currWar.loadAttack(att);
		// test.writeClan(clan, currWar, null, parse.numWars());
		// parse = new XMLParser();
		// currWar.updateStars();
		// txtrHelloMyName.setText("War: " + (parse.numWars() + 1) + "\n"
		// + currWar.toString() + "\n" + topAttackers());
		// txtrHelloMyName.setCaretPosition(0);
		// setWorst();
		// setBest();
		//
		// }
		// }
	}

	// WAR HANDLERS
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	public static void startOpted() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptOutMenu frame = new OptOutMenu(clan);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static void addWar(ArrayList<String> playas) {

		Clan tempClan = clan.copy();
		final war addWar = new war(tempClan.getMembers().toArray(new Player[1]));
		for (String s : playas) {
			Player gone = addWar.warRemove(new Player(s));
		}
		addWar.setEnemyName(JOptionPane.showInputDialog("Enemy Clan name?"));
		ArrayList<Player> sortClan = clan.getList();
		String text = "";
		rankComp ranksort = new rankComp();
		Collections.sort(sortClan, ranksort);
		addWar.resetPlayers();
		for (Object play : addWar) {
			Player p = (Player) play;
			clan.getPlayer(p.getName()).addWar();
		}
		clan.addWar(addWar);
		if (parse.numWars() == -1) {
			test.writeClan(clan, null, null, parse.numWars() + 1);
		} else {
			test.writeClan(clan, null, null, parse.numWars() + 1);
		}
		parse = new XMLParser();
		currentWarNumber = clan.getWars().size() - 1;
		currWar = clan.getCurrentWar();
		txtrHelloMyName.setText("War: " + (currentWarNumber + 1) + "\n"
				+ currWar.toString() + "\n" + topAttackers());
		txtrHelloMyName.setCaretPosition(0);
		parse = new XMLParser();
		System.out.println(parse.numWars());
		choice = 4;
		editGraph();
		final ArrayList<Player> sortedClan = addWar.getList();
		rankComp rankedSort = new rankComp();
		Collections.sort(sortedClan, rankedSort);
		currWar = clan.getCurrentWar();
		rankEditor rankEdit = new rankEditor(sortedClan, addWar);
		rankEdit.setVisible(true);

		currWar = clan.getCurrentWar();
		EnemyTHLevels thLevels = new EnemyTHLevels(addWar);
		thLevels.setVisible(true);
		comboBox.addItem("War " + (currentWarNumber + 1));
		comboBox.setSelectedIndex(currentWarNumber);

	}

	private static void loadPreviousWar() {
		if (currentWarNumber <= 0) {
			JOptionPane.showMessageDialog(null, "ALREADY ON EARLIEST WAR");
		} else {
			currWar = clan.getWar(currentWarNumber - 1);
			currentWarNumber--;
			txtrHelloMyName.setText("War: " + (currentWarNumber + 1) + "\n"
					+ currWar.toString() + "\n" + topAttackers());
			txtrHelloMyName.setCaretPosition(0);
			editGraph();

		}
	}

	private static void loadNextWar() {
		if (currentWarNumber >= clan.getWars().size() - 1) {
			JOptionPane.showMessageDialog(null, "ALREADY ON CURRENT WAR");
		} else {
			currWar = clan.getWar(currentWarNumber + 1);
			currentWarNumber++;
			txtrHelloMyName.setText("War: " + (currentWarNumber + 1) + "\n"
					+ currWar.toString() + "\n" + topAttackers());
			txtrHelloMyName.setCaretPosition(0);
			editGraph();
		}
	}

	// SWING ACTIONS
	// -----------------------------------------------------------------------------------------------------------------------------------------------
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Performance");
			putValue(SHORT_DESCRIPTION, "Performance of the clan");
		}

		public void actionPerformed(ActionEvent e) {
			choice = 1;
			editGraph();
		}
	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Participation");
			putValue(SHORT_DESCRIPTION,
					"Participation of the clan through wars");
		}

		public void actionPerformed(ActionEvent e) {
			choice = 2;
			editGraph();
		}
	}

	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Wasted Attacks");
			putValue(SHORT_DESCRIPTION, "Show Wasted attacks");
		}

		public void actionPerformed(ActionEvent e) {
			choice = 3;
			editGraph();
		}
	}

	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "New Player(s)");
			putValue(SHORT_DESCRIPTION, "Add a new player or players");
		}

		public void actionPerformed(ActionEvent e) {
			addPlayers();
		}
	}

	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Remove Player(s)");
			putValue(SHORT_DESCRIPTION, "Remove players from clan");
		}

		public void actionPerformed(ActionEvent e) {
			removeWindow frame = new removeWindow(clan);
			frame.setVisible(true);

		}
	}

	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "Add Attack");
			putValue(SHORT_DESCRIPTION, "Add an attack to the current war");
		}

		public void actionPerformed(ActionEvent e) {
			addAttack();
		}
	}

	private class SwingAction_6 extends AbstractAction {
		public SwingAction_6() {
			putValue(NAME, "New War");
			putValue(SHORT_DESCRIPTION, "Start a new war");
		}

		public void actionPerformed(ActionEvent e) {
			startOpted();
		}
	}

	private class SwingAction_7 extends AbstractAction {
		public SwingAction_7() {
			putValue(NAME, "Prev War");
			putValue(SHORT_DESCRIPTION, "Show Previous war");
		}

		public void actionPerformed(ActionEvent e) {
			loadPreviousWar();
		}
	}

	private class SwingAction_8 extends AbstractAction {
		public SwingAction_8() {
			putValue(NAME, "Next War");
			putValue(SHORT_DESCRIPTION, "Show next war");
		}

		public void actionPerformed(ActionEvent e) {
			loadNextWar();
		}
	}

	private class SwingAction_9 extends AbstractAction {
		public SwingAction_9() {
			putValue(NAME, "Add Defense");
			putValue(SHORT_DESCRIPTION, "Add a defense");
		}

		public void actionPerformed(ActionEvent e) {
			addDefense();
		}
	}

	private class SwingAction_10 extends AbstractAction {
		public SwingAction_10() {
			putValue(NAME, "Edit Attack");
			putValue(SHORT_DESCRIPTION, "Edit someones attacks");
		}

		public void actionPerformed(ActionEvent e) {
			editAttack();
		}
	}

	private class SwingAction_11 extends AbstractAction {
		public SwingAction_11() {
			putValue(NAME, "Clan List");
			putValue(SHORT_DESCRIPTION, "Show everyone in the clan");
		}

		public void actionPerformed(ActionEvent e) {

			// test.writeClan(clan, wars.get(currentWarNumber), null,
			// parse.numWars());
			// parse = new XMLParser();
			ArrayList<Player> sortClan = clan.getList();
			String text = "";
			Collections.sort(sortClan, new playerComp());
			for (Player play : sortClan) {
				text = text + play.getName() + "(" + play.getThLevel() + ")\n";
			}

			JTextArea textArea = new JTextArea(text);
			Font font = new Font("Verdana", Font.BOLD, 20);
			textArea.setFont(font);
			textArea.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(textArea);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			Object[] options1 = { "Copy To Clipboard", "Quit" };
			int result = JOptionPane.showOptionDialog(null, scrollPane,
					"Clan List", JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options1, null);
			if (result == 0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(textArea.getText());
				clipboard.setContents(strSel, null);
			}
		}
	}

	private class SwingAction_12 extends AbstractAction {
		public SwingAction_12() {
			putValue(NAME, "Player Stats");
			putValue(SHORT_DESCRIPTION, "Individual player stats");
		}

		public void actionPerformed(ActionEvent e) {
			runPlayerStats();
		}
	}

	private class SwingAction_13 extends AbstractAction {
		public SwingAction_13() {
			putValue(NAME, "Base Ranks");
			putValue(SHORT_DESCRIPTION, "Edit/View War Base Ranks");
		}

		public void actionPerformed(ActionEvent e) {
			currWar = clan.getCurrentWar();
			ArrayList<Player> sortClan = currWar.getList();
			String text = "";
			rankComp ranksort = new rankComp();
			Collections.sort(sortClan, ranksort);
			rankEditor rankEdit = new rankEditor(sortedClan, currWar);
			rankEdit.setVisible(true);

		}
	}

	private class editRanks implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currWar = clan.getCurrentWar();
			ArrayList<Player> sortClan = currWar.getList();
			String text = "";
			rankComp ranksort = new rankComp();
			Collections.sort(sortClan, ranksort);

			rankEditor rankEdit = new rankEditor(sortedClan, currWar);
			rankEdit.setVisible(true);

		}

	}

	private class SwingAction_14 extends AbstractAction {
		public SwingAction_14() {
			putValue(NAME, "Clan Rankings");
			putValue(SHORT_DESCRIPTION, "Clan rankings");
		}

		public void actionPerformed(ActionEvent e) {
			ArrayList<Player> sortClan = clan.getList();
			String text = "";
			Collections.sort(sortClan);
			int i = 1;
			for (Player play : sortClan) {
				int playWars = play.getNumWars();
				if (play.getNumWars() == 0) {
					playWars = 1;
				}
				text = text + i + ") " + play.getName() + "("
						+ Math.round(play.getWorth() / playWars) + ")\n";
				i++;
			}

			JTextArea textArea = new JTextArea(text);
			Font font = new Font("Verdana", Font.BOLD, 20);
			textArea.setFont(font);
			textArea.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(textArea);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			Object[] options1 = { "Copy To Clipboard", "Quit" };
			int result = JOptionPane.showOptionDialog(null, scrollPane,
					"Clan Rankings", JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options1, null);
			if (result == 0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(textArea.getText());
				clipboard.setContents(strSel, null);
			}
		}
	}

	private class SwingAction_15 extends AbstractAction {
		public SwingAction_15() {
			putValue(NAME, "Make Claim Post");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			int choice = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to make a claim post?",
					"Confirm Claim Post", JOptionPane.YES_NO_OPTION);
			if (choice == 0) {
				redditHandle.MakeClaimPost();
				
			}

		}
	}

	private class SwingAction_16 extends AbstractAction {
		public SwingAction_16() {
			putValue(NAME, "Update Claim Post");
			putValue(SHORT_DESCRIPTION, "Updates the claim post");
		}

		public void actionPerformed(ActionEvent e) {
			redditHandle.editPost(20);
		}
	}

	private class SwingAction_17 extends AbstractAction {
		public SwingAction_17() {
			putValue(NAME, "Edit Player Stats");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						currWar = clan.getCurrentWar();
						playerEditor frame = new playerEditor(clan, currWar);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	private class SwingAction_18 extends AbstractAction {
		public SwingAction_18() {
			putValue(NAME, "WarStat Post");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			int choice = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to make a warStat post?",
					"Confirm WarStat Post", JOptionPane.YES_NO_OPTION);
			if (choice == 0) {
				// currWar = clan.getCurrentWar();
				// redditHandle.MakeWarStatPost(currWar, topAttackersforPost());
			}
		}
	}

	public static String encrypt(String password) {
		Base64 codec = new Base64();
		byte[] temp;
		String encodedPassword = null;
		temp = codec.encode(password.getBytes());
		encodedPassword = new String(temp);
		return encodedPassword;
	}

	// Decrypt method
	public static String decrypt(String encodedPassword) {
		Base64 codec = new Base64();
		byte[] temp;
		String decodedPassword;
		temp = codec.decode(encodedPassword.getBytes());
		decodedPassword = new String(temp);
		return decodedPassword;
	}

	private class SwingAction_19 extends AbstractAction {
		public SwingAction_19() {
			putValue(NAME, "Subreddit Manager");
			putValue(SHORT_DESCRIPTION, "Manage your subreddit!");
		}

		public void actionPerformed(ActionEvent e) {
			// JOptionPane.showMessageDialog(null,
			// "Not complete yet! Contact me about info!");
			
				System.out.println(clan.getUserName());
				System.out.println(clan.getPassword());
				redditHandle = new JRAWHandler(clan.getUserName(),
						clan.getPassword(), clan.getSubreddit());

			

			subredditMan frame = new subredditMan(currWar, redditHandle, clan
					.getWars().size());
			frame.setVisible(true);

		}
	}

	public static void saveTHLevels(Integer[] ranks) {
		clan.getWar(currentWarNumber).setEnemyTH(ranks);

	}

	private class SwingAction_20 extends AbstractAction {
		public SwingAction_20() {
			putValue(NAME, "Enemy TH's");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			EnemyTHLevels thLevels = new EnemyTHLevels(currWar);
			thLevels.setVisible(true);

		}
	}

	private class SwingAction_21 extends AbstractAction {
		public SwingAction_21() {
			putValue(NAME, "Current War");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class SwingAction_22 extends AbstractAction {
		public SwingAction_22() {
			putValue(NAME, "Delete War");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			int res1 = JOptionPane.showConfirmDialog(null,
					"Are You sure you wish to delete this war?");
			if (res1 == 0) {
				int res2 = JOptionPane.showConfirmDialog(null,
						"This cannot be undone! Are you positive?");
				if (res2 == 0) {
					war tempWar = clan.getWar(currentWarNumber);
					for (Object o : tempWar) {
						Player p = (Player) o;
						Player play = clan.getPlayer(p.getName());
						if (play != null) {
							clan.getPlayer(p.getName()).setNumWars(
									play.getNumWars() - 1);
						}
					}
					clan.removeWar(currentWarNumber);
					test.writeClan(clan, null, null, clan.getWars().size() - 1);
					parse = new XMLParser();
					if (currentWarNumber >= clan.getWars().size()) {
						currentWarNumber = clan.getWars().size() - 1;
					}
					refresh();
				}
			}

		}
	}

	private class SwingAction_23 extends AbstractAction {
		public SwingAction_23() {
			putValue(NAME, "Edit War");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			currWar = clan.getWar(currentWarNumber);
			EditWar edit = new EditWar(currWar, clan);
			edit.setVisible(true);
		}
	}

	private class SwingAction_24 extends AbstractAction {
		public SwingAction_24() {
			putValue(NAME, "War Breakdown");
			putValue(SHORT_DESCRIPTION, "Stars Per Attack Breakdown");
		}

		public void actionPerformed(ActionEvent e) {
			choice = 4;
			editGraph();
		}
	}

	public static void windowRemove(ArrayList<String> optedOut) {
		for (String name : optedOut) {
			clan.remove(clan.getPlayer(name));
		}

	}

	public static void warRemovePlay(ArrayList<String> remover) {
		for (String name : remover) {
			if (clan.getPlayer(name) != null) {
				clan.getPlayer(name).removeWar();
				currWar.warRemove(clan.getPlayer(name));
			}
		}

	}

	public static void updateComboBox() {
		refresh();

	}
	private class SwingAction_25 extends AbstractAction {
		public SwingAction_25() {
			putValue(NAME, "Clan/Reddit Info");
			putValue(SHORT_DESCRIPTION, "Edit Clan Info");
		}
		public void actionPerformed(ActionEvent e) {
			JPanel pane = new JPanel();
			pane.setLayout(new GridLayout(8,2));
			JTextField name = new JTextField(clan.getClanName());
			JTextField sub = new JTextField(clan.getSubreddit());
			JTextField user = new JTextField(clan.getUserName());
			JPasswordField pass = new JPasswordField(clan.getPassword());
			pane.add(new JLabel("Clan Name"));
			pane.add(name);
			pane.add(new JLabel("Clan Subreddit: /r/"));
			pane.add(sub);
			pane.add(new JLabel("Reddit UserName: "));
			pane.add(user);
			pane.add(new JLabel("Reddit Password: "));
			pane.add(pass);
			Object[] options1 = { "Save", "Cancel" };
			int result = JOptionPane.showOptionDialog(null, pane,
					"Edit Clan Info", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options1, null);
			if (result == 0) {
				clan.setClanName(name.getText());
				clan.setSubreddit(sub.getText());
				clan.setUserName(user.getText());
				clan.setPassword(encrypt(pass.getText()));
			}
		}
	}
}
