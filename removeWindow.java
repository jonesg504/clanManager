import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class removeWindow extends JFrame {

	private static JPanel contentPane;
	private static Clan clan;
	private static JPanel boxHolder1;
	private static JPanel boxHolder2; 
	private static ArrayList<String> optedOut = new ArrayList<String>();
	private final Action action = new SwingAction();
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
	public removeWindow(Clan clan) {
		optedOut.clear();
		this.clan = clan;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 376, 600);
		contentPane = new JPanel();
		boxHolder1 = new JPanel();
		boxHolder2 = new JPanel();
		boxHolder1.setLayout(new GridLayout(clan.getMembers().size()/2 + 2, 1, 0, 0));
		boxHolder2.setLayout(new GridLayout(clan.getMembers().size()/2 + 2, 1, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scroll = new JScrollPane(contentPane);
		setContentPane(scroll);
		
		contentPane.setLayout(new GridLayout(1,2 , 0, 0));
		contentPane.add(boxHolder1);
		contentPane.add(boxHolder2);
		this.populateWindow();
	}
	public void populateWindow() {
		boxHolder1.add(new JLabel("Check the remove"));
		JButton button = new JButton("Remove Selected");
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
	
	

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Remove Players");
			putValue(SHORT_DESCRIPTION, "Remove these Players");
		}
		public void actionPerformed(ActionEvent e) {
			for(JCheckBox b : boxes) {
				if(b.isSelected()) {
					optedOut.add(b.getText());
				}
			}
			mainWindow.windowRemove(optedOut);
			dispose();
		}
	}
}

