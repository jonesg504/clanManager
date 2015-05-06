import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JTextArea;

public class subredditMan extends JFrame {
	
	private static JRAWHandler redditHandle;
	private JPanel contentPane;
	private JTextArea textArea;
	private static int number;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private int warSize;
	private war currWar;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public subredditMan(war currWar, JRAWHandler redditHandle, int number) {
		this.currWar = currWar;
		this.number = number;
		this.redditHandle = redditHandle;
		warSize = currWar.getNumPlayers();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 682, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1,2));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2,2));
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setAction(action_2);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setAction(action_3);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setAction(action);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setAction(action_1);
		panel.add(btnNewButton);
		
		 textArea = new JTextArea();
		 textArea.setEditable(false);
		contentPane.add(textArea);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Self Post");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Not implemented yet!");
			
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Make War Stat Post");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to list all attacks? (Press No for top 5 from each TH)", "Confirm WarStat Post", 
                    JOptionPane.YES_NO_CANCEL_OPTION);
			try {
				if (choice == 0) {
					redditHandle.MakeWarStatPost(currWar, mainWindow.totalAttacks(), number);
				} else if (choice == 1) {
					redditHandle.MakeWarStatPost(currWar, mainWindow.topAttackersforPost(), number);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR, did not post: " + ex.toString());
			} 
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Leaderboards Post");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to include the whole leaderboard (Press no for only top 5 from each TH)", "Confirm Leaderboard Post", 
	                    JOptionPane.YES_NO_CANCEL_OPTION);
				if (choice == 0) {
					redditHandle.MakeLeaderBoardsPost(currWar, mainWindow.topFromTH(), mainWindow.rankings(), true);
				} else if (choice == 1) {
					redditHandle.MakeLeaderBoardsPost(currWar, mainWindow.topFromTH(), mainWindow.rankings(), false);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,  "ERROR, did not post: " + ex.toString());
			}
			
		//String text = redditHandle.editPost(warSize);
			//textArea.setText(text);
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Send Reminder mail");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Not implemented yet!");
		}
	}
}
