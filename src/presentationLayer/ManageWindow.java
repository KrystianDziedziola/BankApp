package presentationLayer;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JScrollPane;

public class ManageWindow {

	private final Dimension frameSize = new Dimension(800, 400);
	
	private JFrame frame;
	private Container frameContentPane;
	private JLabel cutomersLabel;
	private JLabel bankAccountsLabel;
	
	private JButton addCustomerButton;
	private JButton changeCustomerButton;
	private JButton deleteCustomerButton;
	
	private JButton addBankAccountButton;
	private JButton deleteBankAccountButton;
	private JButton changeBankAccountButton;
	
	private JScrollPane customersTableScrollPane;
	private JTable customersTable;
	private JTable bankAccountsTable;

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
		initializeFrame();
		setupFrameLayout();
		initializeLabels();
		initializeButtons();
		initializeTables();
		addComponentsToFrame();
	}
	
	private void initializeFrame() {
		frame = new JFrame("Bank App - Manage");
		frameContentPane = frame.getContentPane();
		frameContentPane.setLayout(null);
		frame.setSize(frameSize);
		centerFrame(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void centerFrame(JFrame frame) {
		frame.setLocationRelativeTo(null);
	}
	
	private void setupFrameLayout() {
	}
	
	private void initializeLabels() {
		cutomersLabel = new JLabel("Customers");
		cutomersLabel.setBounds(168, 30, 75, 17);
		cutomersLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		bankAccountsLabel = new JLabel("Bank accounts");
		bankAccountsLabel.setBounds(553, 118, 101, 17);
		bankAccountsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	}
	
	private void initializeButtons() {
		addCustomerButton = new JButton("Add");
		addCustomerButton.setBounds(47, 313, 105, 23);
		changeCustomerButton = new JButton("Change");
		changeCustomerButton.setBounds(198, 313, 105, 23);
		deleteCustomerButton = new JButton("Delete");
		deleteCustomerButton.setBounds(347, 313, 105, 23);
		
		addBankAccountButton = new JButton("Add");
		addBankAccountButton.setBounds(478, 313, 80, 23);
		deleteBankAccountButton = new JButton("Delete");
		deleteBankAccountButton.setBounds(664, 313, 80, 23);
		changeBankAccountButton = new JButton("Change");
		changeBankAccountButton.setBounds(568, 313, 80, 23);
	}
	
	private void initializeTables() {
		String[] customerTableColumnNames = {"ID", "NAME", "SURNAME", "PASSWORD"/*, "STREET", "CITY", "POSTCODE"*/};
		String[][] customerTableData = {{"","", "","", "", ""},{"", "", "","", "", ""}};
		customersTableScrollPane = new JScrollPane();
		
		customersTable = new JTable(customerTableData, customerTableColumnNames);
		customersTableScrollPane.add(customersTable);
		customersTableScrollPane.setViewportView(customersTable);
		
		bankAccountsTable = new JTable();
		bankAccountsTable.setBounds(478, 146, 268, 156);
	}
	
	private void addComponentsToFrame() {
		setBounds();
		frameContentPane.add(cutomersLabel);
		frameContentPane.add(bankAccountsLabel);
		frameContentPane.add(addCustomerButton);
		frameContentPane.add(changeCustomerButton);
		frameContentPane.add(deleteCustomerButton);
		frameContentPane.add(addBankAccountButton);
		frameContentPane.add(deleteBankAccountButton);
		frameContentPane.add(changeBankAccountButton);
		frameContentPane.add(customersTableScrollPane);
		frameContentPane.add(bankAccountsTable);
	}

	private void setBounds() {
		customersTableScrollPane.setBounds(47, 58, 405, 244);
	}
}
