package presentationLayer;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;

public class ManageWindow {

	private JFrame frame;
	
	private Container frameContentPane;
	private JTable table;
	private JTable table_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageWindow window = new ManageWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManageWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frameContentPane = frame.getContentPane();
		frame.setSize(800, 600);
		centerFrame(frame);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblCustomers = new JLabel("Customers");
		springLayout.putConstraint(SpringLayout.NORTH, lblCustomers, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCustomers, 70, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblCustomers);
		
		JLabel lblBankAccounts = new JLabel("Bank accounts");
		springLayout.putConstraint(SpringLayout.NORTH, lblBankAccounts, 0, SpringLayout.NORTH, lblCustomers);
		springLayout.putConstraint(SpringLayout.EAST, lblBankAccounts, -294, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(lblBankAccounts);
		
		JButton btnAdd = new JButton("Add");
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 190, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnAdd, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, btnDelete, 12, SpringLayout.SOUTH, btnAdd);
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 0, SpringLayout.WEST, btnAdd);
		springLayout.putConstraint(SpringLayout.EAST, btnDelete, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnDelete);
		
		JButton btnChange = new JButton("Change");
		springLayout.putConstraint(SpringLayout.NORTH, btnChange, 18, SpringLayout.SOUTH, btnDelete);
		springLayout.putConstraint(SpringLayout.WEST, btnChange, 669, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnChange, 0, SpringLayout.EAST, btnAdd);
		frame.getContentPane().add(btnChange);
		
		table = new JTable();
		springLayout.putConstraint(SpringLayout.NORTH, table, 6, SpringLayout.SOUTH, lblCustomers);
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, 527, SpringLayout.SOUTH, lblCustomers);
		springLayout.putConstraint(SpringLayout.EAST, table, 319, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(table);
		
		table_1 = new JTable();
		springLayout.putConstraint(SpringLayout.WEST, btnAdd, 16, SpringLayout.EAST, table_1);
		springLayout.putConstraint(SpringLayout.NORTH, table_1, 6, SpringLayout.SOUTH, lblBankAccounts);
		springLayout.putConstraint(SpringLayout.WEST, table_1, 6, SpringLayout.EAST, table);
		springLayout.putConstraint(SpringLayout.SOUTH, table_1, 527, SpringLayout.SOUTH, lblBankAccounts);
		springLayout.putConstraint(SpringLayout.EAST, table_1, 334, SpringLayout.EAST, table);
		frame.getContentPane().add(table_1);
	}
	
	private void centerFrame(JFrame frame) {
		frame.setLocationRelativeTo(null);
	}
}
