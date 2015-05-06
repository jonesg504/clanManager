import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

public class EditWar extends JFrame {

	private static JPanel contentPane;
	private static JPanel boxHolder1;
	private static JPanel boxHolder2;
	private static JPanel innerPanel;
	private static JPanel otherHolder;
	private static JPanel enemyStarHolder;
	private static JPanel optedInPanel;
	private static JPanel blank;
	private static Clan actualClan;
	private static war clan;
	private static JComboBox optedIn;
	private static JTextField enStars;
	private static JButton addToWarButt;
	private static JTextField nameTextField;
	private static JPanel otherPanelHolder;
	private static ArrayList<String> optedOut = new ArrayList<String>();
	private final Action action = new SwingAction();
	private final Action enButtSave = new EnButtSave();
	private final Action addWarAction = new AddWarAction();
	private final Action saveAction = new SaveButtAction();
	ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OptOutMenu frame = new OptOutMenu();
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
	public EditWar(war clan, Clan actualClan) {
		this.clan = clan;
		addToWarButt = new JButton();
		addToWarButt.setAction(addWarAction);
		this.actualClan = actualClan;
		populateOptedIn();
		optedOut.clear();
		this.clan = clan;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		boxHolder1 = new JPanel();
		blank = new JPanel();
		//blank.add(new JLabel("here"));
		boxHolder2 = new JPanel();
		otherHolder = new JPanel();
		enemyStarHolder = new JPanel();
		otherPanelHolder = new JPanel();
		contentPane = new JPanel();
		innerPanel = new JPanel();
		optedInPanel = new JPanel();
		optedInPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		enemyStarHolder.setBorder(BorderFactory.createRaisedBevelBorder());
		optedInPanel.add(optedIn);
		optedInPanel.add(addToWarButt);
		innerPanel.setLayout(new GridLayout(1,2 , 0, 0));
		innerPanel.add(boxHolder1);
		innerPanel.add(boxHolder2);
		innerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		otherHolder.setBorder(BorderFactory.createRaisedBevelBorder());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scroll = new JScrollPane(contentPane);
		setContentPane(scroll);
		otherHolder.setLayout(new GridLayout(2,2 , 0, 0));
		otherPanelHolder.setLayout(new GridLayout(6,1 , 0, 0));
		
		otherPanelHolder.add(otherHolder);
		otherPanelHolder.add(optedInPanel);
		otherPanelHolder.add(enemyStarHolder);
		otherPanelHolder.add(blank);
		otherPanelHolder.add(blank);
		otherPanelHolder.add(blank);
		boxHolder1.setLayout(new GridLayout(clan.getMembers().size()/2 + 2, 1, 0, 0));
		boxHolder2.setLayout(new GridLayout(clan.getMembers().size()/2 + 2, 1, 0, 0));
		contentPane.setLayout(new GridLayout(1,2 , 0, 0));
		contentPane.add(innerPanel);
		contentPane.add(otherPanelHolder);
		this.populateWindow();
	}
	public void populateWindow() {
		enemyStarHolder.add(new JLabel("Edit Enemy Stars: "));
		enStars = new JTextField("" + clan.getEnemyStars(), 4);
		JButton enButt = new JButton("Save");
		enemyStarHolder.add(enStars);
		enemyStarHolder.add(enButt);
		enButt.setAction(enButtSave);
		nameTextField = new JTextField(clan.getEnemyName(), 10);
		otherHolder.add(new JLabel("Enemy Clan Name:"));
		otherHolder.add(nameTextField);
		JButton myButt = new JButton("Save");
		myButt.setAction(saveAction);
		otherHolder.add(myButt);
		boxHolder1.add(new JLabel("Remove Players: "));
		JButton button = new JButton("Save");
		button.setAction(action);
		boxHolder2.add(button);
		ArrayList<Player> playas = clan.getMembers();
		Collections.sort(playas, new optPlayerComp());
		int ind = 1;
		for(Object o : clan) {
			Player p = (Player) o;
			JCheckBox box = new JCheckBox(p.getName());
			box.setBorderPaintedFlat(true);
			boxes.add(box);
			if (ind <= clan.getMembers().size()/2) {
				boxHolder1.add(box);
			} else {
				boxHolder2.add(box);
			}
			ind++;
			
		}
	}
	private static void populateOptedIn() {
		ArrayList<String> namesNotInWar = new ArrayList<>();
		for(String s : actualClan.getMembersNames()) {
			namesNotInWar.add(s);
		}
		for(String s : clan.getMembersNames()) {
			if (namesNotInWar.contains(s)) {
				namesNotInWar.remove(s);
			}
		}
		optedIn = new JComboBox(namesNotInWar.toArray(new String[0]));
	}
	private class AddWarAction extends AbstractAction {
		public AddWarAction() {
			putValue(NAME, "Add To War");
			putValue(SHORT_DESCRIPTION, "Cannot be Undone");
		}
		public void actionPerformed(ActionEvent e) {
			clan.add(actualClan.getPlayer((String)optedIn.getSelectedItem()));
			actualClan.getPlayer((String)optedIn.getSelectedItem()).addWar();
			populateOptedIn();
		}
	}
	private class SaveButtAction extends AbstractAction {
		public SaveButtAction() {
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Cannot be Undone");
		}
		public void actionPerformed(ActionEvent e) {
			clan.setEnemyName(nameTextField.getText());
			mainWindow.updateComboBox();
		}
	}
	

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Remove from War");
			putValue(SHORT_DESCRIPTION, "Cannot be Undone");
		}
		public void actionPerformed(ActionEvent e) {
			for(JCheckBox b : boxes) {
				if(b.isSelected()) {
					optedOut.add(b.getText());
				}
			}
			mainWindow.warRemovePlay(optedOut);
			dispose();
		}
	}
	private class EnButtSave extends AbstractAction {
		public EnButtSave() {
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Cannot be Undone");
		}
		public void actionPerformed(ActionEvent e) {
			try { 
				clan.setEnemyStars(Integer.parseInt(enStars.getText()));
				mainWindow.refresh();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Please Enter an Integer!");
			}
			
		}
	}
}

