package presentationLayer;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JScrollPane;

public class ManageWindow {

	private final Dimension frameSize = new Dimension(800, 600);
	
	private JFrame frame;
	private Container frameContentPane;
	private JLabel cutomersLabel;
	private JLabel bankAccountsLabel;
	private JLabel addressLabel;
	
	private JButton addCustomerButton;
	private JButton changeCustomerButton;
	private JButton deleteCustomerButton;
	
	private JButton addBankAccountButton;
	private JButton deleteBankAccountButton;
	private JButton changeBankAccountButton;
	
	private JScrollPane customersTableScrollPane;
	private DefaultTableModel customersTableModel;
	private JTable customersTable;
	
	private JScrollPane bankAccountsTableScrollPane;
	private DefaultTableModel bankAccountsTableModel;
	private JTable bankAccountsTable;
	
	private JScrollPane addressTableScrollPane;
	private DefaultTableModel addressesTableModel;
	private JTable addressesTable;
	
	private final int TABLE_TITLE_FONT_SIZE = 14;
	
	public ManageWindow() {
		initialize();
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void putCustomersInfoIntoTable(String[][] allCustomersInfoForTable) {
		for(int rowIndex = 0; rowIndex < allCustomersInfoForTable.length; rowIndex++) {
			customersTableModel.addRow(allCustomersInfoForTable[rowIndex]);
		}
	}

	private void initialize() {
		initializeFrame();
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
	
	private void initializeLabels() {
		cutomersLabel = new JLabel("Customers");
		cutomersLabel.setFont(new Font("Tahoma", Font.BOLD, TABLE_TITLE_FONT_SIZE));
		
		bankAccountsLabel = new JLabel("Customer's bank accounts");
		bankAccountsLabel.setFont(new Font("Tahoma", Font.BOLD, TABLE_TITLE_FONT_SIZE));
		
		addressLabel = new JLabel("Customer's address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, TABLE_TITLE_FONT_SIZE));
	}
	
	private void initializeButtons() {
		addCustomerButton = new JButton("Add");
		changeCustomerButton = new JButton("Change");
		deleteCustomerButton = new JButton("Delete");
		addBankAccountButton = new JButton("Add");
		deleteBankAccountButton = new JButton("Delete");
		changeBankAccountButton = new JButton("Change");
	}
	
	private void initializeTables() {
		initializeCustomersTable();
		initializeBankAccountsTable();
		
		
		String[] addressesTableColumnNames = {"STREET", "CITY", "POSTCODE"};
		int initialNumberOfRows = 0;
		addressesTableModel = new DefaultTableModel(initialNumberOfRows, addressesTableColumnNames.length);
		addressesTableModel.setColumnIdentifiers(addressesTableColumnNames);
		addressesTable = new JTable(addressesTableModel);
		addressTableScrollPane = new JScrollPane();
		addressTableScrollPane.add(addressesTable);
		addressTableScrollPane.setViewportView(addressesTable);
	}
	
	private void initializeCustomersTable() {
		String[] customersTableColumnNames = {"ID", "NAME", "SURNAME", "PASSWORD"};
		int initialNumberOfRows = 0;
		customersTableModel = new DefaultTableModel(initialNumberOfRows, customersTableColumnNames.length);
		customersTableModel.setColumnIdentifiers(customersTableColumnNames);
		customersTable = new JTable(customersTableModel);
		customersTableScrollPane = new JScrollPane();
		customersTableScrollPane.add(customersTable);
		customersTableScrollPane.setViewportView(customersTable);
	}
	
	private void initializeBankAccountsTable() {
		String[] bankAccountsTableColumnNames = {"ACCOUNT NUMBER", "BALANCE"};
		int initialNumberOfRows = 0;
		bankAccountsTableModel = new DefaultTableModel(initialNumberOfRows, bankAccountsTableColumnNames.length);
		bankAccountsTableModel.setColumnIdentifiers(bankAccountsTableColumnNames);
		bankAccountsTable = new JTable(bankAccountsTableModel);
		bankAccountsTableScrollPane = new JScrollPane();
		bankAccountsTableScrollPane.add(bankAccountsTable);
		bankAccountsTableScrollPane.setViewportView(bankAccountsTable);
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
		frameContentPane.add(bankAccountsTableScrollPane);
		frameContentPane.add(addressTableScrollPane);
		
		
		addressLabel.setBounds(539, 31, 168, 14);
		frame.getContentPane().add(addressLabel);
	}

	private void setBounds() {
		//code auto-generated by Window Designer
		cutomersLabel.setBounds(206, 30, 75, 17);
		bankAccountsLabel.setBounds(516, 118, 191, 17);
		addCustomerButton.setBounds(47, 313, 105, 23);
		changeCustomerButton.setBounds(198, 313, 105, 23);
		deleteCustomerButton.setBounds(347, 313, 105, 23);
		addBankAccountButton.setBounds(478, 313, 80, 23);
		deleteBankAccountButton.setBounds(664, 313, 80, 23);
		changeBankAccountButton.setBounds(568, 313, 80, 23);
		customersTableScrollPane.setBounds(47, 58, 405, 244);
		bankAccountsTableScrollPane.setBounds(478, 146, 264, 156);
		addressTableScrollPane.setBounds(478, 58, 264, 50);
	}
}
