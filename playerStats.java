import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.ActionListener;

public class playerStats extends JFrame {

	private JPanel contentPane;
	private static JPanel panel_1 = new JPanel();
	private static Clan clan;
	String selected = "Select";
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	final JLabel lblNewLabel = new JLabel(selected);
	final int curr;
	final XMLParser parse;
	private int choice = 1;
	private final Action action_3 = new SwingAction_3();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					//playerStats frame = new playerStats();
//					//frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public playerStats(Clan clan, XMLParser parser) {
		this.clan = clan;
		this.parse = parser;
		curr = parse.numWars();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 103, 367);
		contentPane.add(panel);
		String[] selections = populate(clan);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 83, 345);
		panel.add(scrollPane);
		
		final JList list = new JList(selections);
		scrollPane.setViewportView(list);
		
		panel_1.setBounds(115, 55, 470, 323);
		contentPane.add(panel_1);
		
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(123, 11, 144, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(247, 21, 78, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setAction(action_1);
		btnNewButton_1.setBounds(322, 21, 116, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setAction(action_2);
		btnNewButton_2.setBounds(435, 21, 78, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setAction(action_3);
		btnNewButton_3.setBounds(511, 21, 84, 23);
		contentPane.add(btnNewButton_3);
		
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		        	if (choice == 1) {
		           String selectedItem = (String) list.getSelectedValue();
		           stars(selectedItem, curr, parse);
		           selected = selectedItem;
		           lblNewLabel.setText(selected);
		        	} else if (choice == 2) {
		        		String selectedItem = (String) list.getSelectedValue();
				           parting(selectedItem, curr, parse);
				           selected = selectedItem;
				           lblNewLabel.setText(selected);
		        	} else if (choice == 3){
		        		String selectedItem = (String) list.getSelectedValue();
				           wins(selectedItem, curr, parse);
				           selected = selectedItem;
				           lblNewLabel.setText(selected);
		        	} else {
		        		String selectedItem = (String) list.getSelectedValue();
				           rank(selectedItem, curr, parse);
				           selected = selectedItem;
				           lblNewLabel.setText(selected);
		        	}
		         }
		    }
		};
		list.addMouseListener(mouseListener);
	}
	private static void stars(String name, int curr, XMLParser parse) {
		
		JFreeChart xylineChart = ChartFactory.createXYLineChart(null,
				"War Number", "Stars Gotten",
				graphData.playerWarStat(clan.getPlayer(name), parse),
				PlotOrientation.VERTICAL, true, true, false);
		final ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(450, 280));
		final XYPlot plot = xylineChart.getXYPlot();
		ValueAxis yAxis = plot.getRangeAxis();
		yAxis.setRange(0.0, 7.0);
		NumberAxis xAxis = new NumberAxis();
		xAxis.setTickUnit(new NumberTickUnit(1));
		plot.setDomainAxis(xAxis);
		if (curr == 0) {
			curr = 2;
		}
		xAxis.setRange(1, curr + 1);
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
		panel_1.removeAll();
        panel_1.add(chartPanel);
        panel_1.validate();
    }

	private static void parting(String name, int curr, XMLParser parse) {

		DefaultPieDataset dataset = (DefaultPieDataset) graphData
				.playerWarPart(clan.getPlayer(name), parse);
		JFreeChart chart = ChartFactory.createPieChart("Participation", // chart
																			// title
				dataset, // data
				true, // include legend
				true, false);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(450, 280));
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator(
				"{2}", new DecimalFormat("0"), new DecimalFormat("0.00%"));
		plot.setLabelGenerator(generator);
		// ValueAxis yAxis = plot.getRangeAxis();
		// yAxis.setRange(0.0, 7.0);
		// NumberAxis xAxis = new NumberAxis();
		// xAxis.setTickUnit(new NumberTickUnit(1));
		// plot.setDomainAxis(xAxis);
		// xAxis.setRange(1, curr + 1);
		// xAxis.setLabel("War Number");
		// xAxis.setLabelFont(yAxis.getLabelFont());
		// XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		// renderer.setSeriesPaint(0, Color.RED);
		// renderer.setSeriesPaint(1, Color.GREEN);
		// renderer.setSeriesPaint(2, Color.YELLOW);
		// renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		// renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		// renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		// chartPanel.getChart().removeLegend();/		
        panel_1.removeAll();
        panel_1.add(chartPanel);
        panel_1.validate();
    }
	private static void wins(String name, int curr, XMLParser parse) {

		DefaultPieDataset dataset = (DefaultPieDataset) graphData
				.playerWarWin(clan.getPlayer(name), parse);
		JFreeChart chart = ChartFactory.createPieChart("Wins vs. Losses", // chart
																			// title
				dataset, // data
				true, // include legend
				true, false);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(450, 280));
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator(
				"{2}", new DecimalFormat("0"), new DecimalFormat("0.00%"));
		plot.setLabelGenerator(generator);
		// ValueAxis yAxis = plot.getRangeAxis();
		// yAxis.setRange(0.0, 7.0);
		// NumberAxis xAxis = new NumberAxis();
		// xAxis.setTickUnit(new NumberTickUnit(1));
		// plot.setDomainAxis(xAxis);
		// xAxis.setRange(1, curr + 1);
		// xAxis.setLabel("War Number");
		// xAxis.setLabelFont(yAxis.getLabelFont());
		// XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		// renderer.setSeriesPaint(0, Color.RED);
		// renderer.setSeriesPaint(1, Color.GREEN);
		// renderer.setSeriesPaint(2, Color.YELLOW);
		// renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		// renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		// renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		// chartPanel.getChart().removeLegend();/		
        panel_1.removeAll();
        panel_1.add(chartPanel);
        panel_1.validate();
    }
	private static void rank(String name, int curr, XMLParser parse) {

		CategoryDataset  dataset = (CategoryDataset) graphData
				.playerWarRank(clan.getPlayer(name), parse);
		 final JFreeChart chart = ChartFactory.createBarChart(
		            "Ranks",         // chart title
		            "War",               // domain axis label
		            "Rank",                  // range axis label
		            dataset,                  // data
		            PlotOrientation.VERTICAL, // orientation
		            true,                     // include legend
		            true,                     // tooltips?
		            false                     // URLs?
		        );

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(450, 280));
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
		
		
		// ValueAxis yAxis = plot.getRangeAxis();
		// yAxis.setRange(0.0, 7.0);
		// NumberAxis xAxis = new NumberAxis();
		// xAxis.setTickUnit(new NumberTickUnit(1));
		// plot.setDomainAxis(xAxis);
		// xAxis.setRange(1, curr + 1);
		// xAxis.setLabel("War Number");
		// xAxis.setLabelFont(yAxis.getLabelFont());
		// XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		// renderer.setSeriesPaint(0, Color.RED);
		// renderer.setSeriesPaint(1, Color.GREEN);
		// renderer.setSeriesPaint(2, Color.YELLOW);
		// renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		// renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		// renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		// chartPanel.getChart().removeLegend();/		
        panel_1.removeAll();
        panel_1.add(chartPanel);
        panel_1.validate();
    }
	private static String[] populate(Clan clan) {
		ArrayList<String> names = new ArrayList<String>();
		for (Object o : clan) {
			Player player = (Player) o;
			names.add(player.getName());
		}
		return names.toArray(new String[9]);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Stars");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			stars(selected, curr, parse);
	        lblNewLabel.setText(selected);
	        choice = 1;
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Participation");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			parting(selected, curr, parse);
	        lblNewLabel.setText(selected);
	        choice = 2;
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Win %");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			wins(selected, curr, parse);
	        lblNewLabel.setText(selected);
	        choice = 3;
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Ranks");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			rank(selected, curr, parse);
	        lblNewLabel.setText(selected);
	        choice = 4;
		}
	}
}
