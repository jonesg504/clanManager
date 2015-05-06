import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class playerEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private final Action action = new SwingAction();
	private String selectedItem;
	private Clan clan;
	private war currWar;
	JList list;
	XMLParser parse = new XMLParser();
	private JLabel lblNewLabel;
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public playerEditor(final Clan c, war w) {
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
		String[] selections = populate(clan);
		list = new JList(selections);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel_1 = new JLabel("TownHall");
		lblNewLabel_1.setBounds(143, 62, 69, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Total Worth");
		lblNewLabel_2.setBounds(143, 101, 69, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Num Wars");
		lblNewLabel_3.setBounds(143, 143, 69, 21);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(222, 59, 86, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(222, 98, 86, 28);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(222, 140, 86, 27);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(222, 197, 89, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Name: ");
		lblNewLabel.setBounds(143, 29, 46, 20);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(222, 26, 86, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		        	selectedItem = (String) list.getSelectedValue();
		        	Player play = clan.getPlayer(selectedItem);
		        	textField.setText(play.getName());
		        	textField_1.setText("" + play.getThLevel());
		        	textField_2.setText("" + Math.round(play.getWorth()));
		        	textField_3.setText("" + play.getNumWars());
		         }
		    }
		};
		list.addMouseListener(mouseListener);
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
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String name = selectedItem;
			
			clan.getPlayer(name).setThLevel(Integer.parseInt(textField_1.getText()));
			clan.getPlayer(name).setWorth(Double.parseDouble(textField_2.getText()));
			clan.getPlayer(name).setNumWars(Integer.parseInt(textField_3.getText()));
			clan.changeName(name, textField.getText().toLowerCase());
			test.writeClan(clan, null, null, parse.numWars());
			parse = new XMLParser();
			mainWindow.loadClan();
			list.setListData(populate(clan));

		}
	}
}
