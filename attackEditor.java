import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class attackEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private final Action action = new SwingAction();
	private String selectedItem;
	private JCheckBox box;
	private Clan clan;
	private war currWar;
	private int warIndex;
	JList list;
	XMLParser parse = new XMLParser();
	private JLabel lblNewLabel;
	private JTextField textField;
	private final Action action_1 = new SwingAction_1();
	private JTextField textField_3;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public attackEditor(final Clan c, war w, int ind) {
		warIndex = ind;
		clan = c;
		currWar = w;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 395, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 133, 309);
		contentPane.add(scrollPane);
		String[] selections = populate(currWar);
		list = new JList(selections);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel_1 = new JLabel("Base Rank");
		lblNewLabel_1.setBounds(143, 62, 69, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Stars");
		lblNewLabel_2.setBounds(144, 95, 69, 20);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(232, 60, 86, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(232, 92, 86, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(143, 197, 89, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Attacker: ");
		lblNewLabel.setBounds(143, 29, 69, 19);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(232, 25, 86, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		box = new JCheckBox("Three Star?");
		box.setBounds(221, 156, 97, 23);
		contentPane.add(box);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setAction(action_1);
		btnNewButton_1.setBounds(280, 197, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNonBonusStars = new JLabel("Non Bonus Stars");
		lblNonBonusStars.setBounds(135, 129, 97, 20);
		contentPane.add(lblNonBonusStars);
		
		textField_3 = new JTextField();
		textField_3.setBounds(232, 127, 86, 25);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		        	int index = list.getSelectedIndex();
		        	Attack att = currWar.getAttacks().get(index);
		        	textField.setText(att.getPlayer());
		        	textField_1.setText("" + att.getBaseRank());
		        	textField_2.setText("" + att.getStars());
		        	textField_3.setText("" + att.getGhostStars());
		        	if (att.isStar3()) {
		        		box.setSelected(true);
		        	} else {
		        		box.setSelected(false);
		        	}
		         }
		    }
		};
		list.addMouseListener(mouseListener);
	}
	private static String[] populate(war w) {
		ArrayList<String> atts = new ArrayList<String>();
		ArrayList<Attack> attacks = w.getAttacks();
		for (Attack a : attacks) {
			atts.add(a.getPlayer() + "(" + a.getAttNum() + ")");
		}
		return atts.toArray(new String[9]);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			int index = list.getSelectedIndex();
        	Attack att = currWar.getAttacks().get(index);
        	Attack copy = att.clone();
        	Player player = clan.getPlayer(att.getPlayer().toLowerCase());
        	att.setPlayer(textField.getText());
			att.setBaseRank(Integer.parseInt(textField_1.getText()));
			att.setThLevel(currWar.getEnTH(Integer.parseInt(textField_1.getText()) - 1));
			att.setStars(Integer.parseInt(textField_2.getText()));
			att.setStar3(box.isSelected());
			clan.updateWar(currWar, warIndex);
			clan.getPlayer(att.getPlayer()).removeWorth(copy.calcWorth(currWar.getNumPlayers(), player));
			System.out.println(clan.getPlayer(att.getPlayer()).getWorth());
			clan.getPlayer(att.getPlayer()).addWorth(att.calcWorth(currWar.getNumPlayers(), player));
			System.out.println(clan.getPlayer(att.getPlayer()).getWorth());
			test.writeClan(clan, null, null, parse.numWars());
			parse = new XMLParser();
			mainWindow.loadClan();
			list.setListData(populate(currWar));

		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Delete");
			putValue(SHORT_DESCRIPTION, "Delete this attack");
		}
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this attack?");
			if (result == 0) {
				int index = list.getSelectedIndex();
	        	Attack att = currWar.getAttacks().get(index);
	        	Attack copy = att.clone();
	        	Player player = clan.getPlayer(att.getPlayer().toLowerCase());
	        	if (player != null) {
	        		clan.getPlayer(att.getPlayer()).removeWorth(copy.calcWorth(currWar.getNumPlayers(), player));
	        	}
				currWar.removeAttack(att);
				list.setListData(populate(currWar));
			}
		}
	}
}
