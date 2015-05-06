import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EnemyTHLevels extends JFrame {
	private war currWar;
	private JPanel contentPane;
	private Clan clan;
	private static JPanel boxHolder1;
	private static JPanel boxHolder2;
	private ArrayList<JLabel> buttons = new ArrayList<>();
	private ArrayList<JTextField> texts = new ArrayList<>();
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					rankEditor frame = new rankEditor();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public EnemyTHLevels(war currWar) {
		setTitle("Set Enemy TownHall Levels");
		setTitle("Set Enemy TownHall Levels");
		this.currWar = currWar;
		boxHolder1 = new JPanel();
		boxHolder2 = new JPanel();
		boxHolder1.setLayout(new GridLayout(currWar.getMembers().size()/2 + 1, 2, 0, 0));
		boxHolder2.setLayout(new GridLayout(currWar.getMembers().size()/2 + 2, 2, 0, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 100 + (10 * currWar.getPlayers().size()));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 3));
		contentPane.add(boxHolder1);
		contentPane.add(boxHolder2);
		showGUI();
	}
	
	
	public void showGUI() {
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		int num = 0;
		for (Integer play : currWar.getEnemyTH() ) {
			if (num >= currWar.getNumPlayers()) {
				break;
			}
			buttons.add(new JLabel("" + (num + 1)));
			buttons.get(num).setBorder(border);
			if (play == null) {
				texts.add(new JTextField("" + currWar.getPlayerInd(num).getThLevel()));
			} else { 
				texts.add(new JTextField("" + play));
			}
			texts.get(num).setBorder(border);
			num++;
		}
		for (int i = 0; i < buttons.size(); i++) {
			if(i < currWar.getMembers().size()/2) {
				boxHolder1.add(buttons.get(i));
				boxHolder1.add(texts.get(i));
			} else {
				boxHolder2.add(buttons.get(i));
				boxHolder2.add(texts.get(i));
			}
		}
		
		JButton button = new JButton("Save All");
		button.addActionListener(new saveButton());
		boxHolder2.add(button);
		
	}
	
	private class buttonListen implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			JButton butt = (JButton) action.getSource();
			int index = buttons.indexOf(butt);
			String name = butt.getText();
			String sRank = ((JTextArea) contentPane.getComponent((index * 2) + 1)).getText();
			sRank.replaceAll("\\s+","");
			int rank = Integer.parseInt(sRank);
			mainWindow.saveRanks(name, rank);
		}
		
		
	}
	private class saveButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			int i = 0;
			Integer[] ranks = new Integer[50];
			for (JLabel butt : buttons) {
				String sRank = texts.get(i).getText();
				sRank = sRank.replaceAll("\\s+","");
				int thLevel = Integer.parseInt(sRank);
				String rank = butt.getText();
				ranks[i] = thLevel;
				i++;
			}
			mainWindow.saveTHLevels(ranks);
			dispose();
		}
		
	}
	
}
