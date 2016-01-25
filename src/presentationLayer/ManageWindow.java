package presentationLayer;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;

public class ManageWindow {

	private final Dimension frameSize = new Dimension(800, 400);
	
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
	
	private JButton addAddressButton;
	private JButton deleteAddressButton;
	private JButton changeAddressButton;
	
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
	
	public void updateCustomersTable(String[][] allCustomersInfoForTable) {
		clearCustomersTable();
		addContentToCustomersTable(allCustomersInfoForTable);
	}
	
	public void addAddCustomerButtonListener(ActionListener actionListener) {
		addCustomerButton.addActionListener(actionListener);
	}
	
	public void addChangeCustomerButtonListener(ActionListener actionListener) {
		changeCustomerButton.addActionListener(actionListener);
	}
	
	public void addDeleteCustomerButtonListener(ActionListener actionListener) {
		deleteCustomerButton.addActionListener(actionListener);
	}
	
	public void addCustomersTableSelectionListener(ListSelectionListener listSelectionListener) {
		customersTable.getSelectionModel().addListSelectionListener(listSelectionListener);
	}
	
	public ArrayList<String> getCustomersTableSelectedRow() {
		ArrayList<String> customerInfo = new ArrayList<String>();
		int selectedRowIndex = customersTable.getSelectedRow();
		int numberOfColumns = customersTableModel.getColumnCount();
		
		for(int columnId = 0; columnId < numberOfColumns; columnId++) {
			customerInfo.add((String) customersTable.getValueAt(selectedRowIndex, columnId));
		}
		return customerInfo;
	}
	
	public boolean isChangeCustomerButtonEnabled() {
		return changeCustomerButton.isEnabled();
	}
	
	public void setChangeCustomerButtonEnabled(boolean isEnabled) {
		changeCustomerButton.setEnabled(isEnabled);
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
		frame.setResizable(false);
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
		initializeCustomerButtons();
		initializeBankAccountButtons();
		initializeAddressButtons();
	}
	
	private void initializeCustomerButtons() {
		addCustomerButton = new JButton("Add");
		changeCustomerButton = new JButton("Change");
		changeCustomerButton.setEnabled(false);
		deleteCustomerButton = new JButton("Delete");
	}
	
	private void initializeBankAccountButtons() {
		addBankAccountButton = new JButton("Add");
		changeBankAccountButton = new JButton("Change");
		deleteBankAccountButton = new JButton("Delete");
	}
	
	private void initializeAddressButtons() {
		addAddressButton = new JButton("Add");
		changeAddressButton = new JButton("Change");
		deleteAddressButton = new JButton("Delete");
	}

	private void initializeTables() {
		initializeCustomersTable();
		initializeBankAccountsTable();
		initializeAddressTable();
	}
	
	private void initializeCustomersTable() {
		String[] customersTableColumnNames = {"ID", "NAME", "SURNAME", "PASSWORD"};
		customersTableModel = getInitializedTableModel(customersTableColumnNames);
		customersTable = new JTable(customersTableModel);
		//customersTable.setEnabled(false);
		customersTableScrollPane = new JScrollPane();
		initializeScrollPane(customersTableScrollPane, customersTable);
	}
	
	private void initializeBankAccountsTable() {
		String[] bankAccountsTableColumnNames = {"ACCOUNT NUMBER", "BALANCE"};
		bankAccountsTableModel = getInitializedTableModel(bankAccountsTableColumnNames);
		bankAccountsTable = new JTable(bankAccountsTableModel);
		bankAccountsTable.setEnabled(false);
		bankAccountsTableScrollPane = new JScrollPane();
		initializeScrollPane(bankAccountsTableScrollPane, bankAccountsTable);
	}
	
	private void initializeAddressTable() {
		String[] addressesTableColumnNames = {"STREET", "CITY", "POSTCODE"};
		addressesTableModel = getInitializedTableModel(addressesTableColumnNames);
		addressesTable = new JTable(addressesTableModel);
		addressesTable.setEnabled(false);
		addressTableScrollPane = new JScrollPane();
		initializeScrollPane(addressTableScrollPane, addressesTable);
	}
	
	private DefaultTableModel getInitializedTableModel(String[] columnNames) {
		int initialNumberOfRows = 0;
		DefaultTableModel tableModel = new DefaultTableModel(initialNumberOfRows, columnNames.length);
		tableModel.setColumnIdentifiers(columnNames);
		return tableModel; 
	}
	
	private void initializeScrollPane(JScrollPane scrollPane, JTable table ) {
		scrollPane.add(table);
		scrollPane.setViewportView(table);
	}
	
	private void addComponentsToFrame() {
		setBounds();
		addLabels();
		addButtons();
		addScrollPanes();
	}

	private void addLabels() {
		frameContentPane.add(cutomersLabel);
		frameContentPane.add(bankAccountsLabel);
		frameContentPane.add(addressLabel);
	}
	
	private void addButtons() {
		frameContentPane.add(addCustomerButton);
		frameContentPane.add(changeCustomerButton);
		frameContentPane.add(deleteCustomerButton);
		frameContentPane.add(addBankAccountButton);
		frameContentPane.add(deleteBankAccountButton);
		frameContentPane.add(changeBankAccountButton);
		frameContentPane.add(addAddressButton);
		frameContentPane.add(deleteAddressButton);
		frameContentPane.add(changeAddressButton);
	}
	
	private void addScrollPanes() {
		frameContentPane.add(customersTableScrollPane);
		frameContentPane.add(bankAccountsTableScrollPane);
		frameContentPane.add(addressTableScrollPane);
	}
	
	private void clearCustomersTable() {
		for(int rowNumber = customersTableModel.getRowCount() - 1; rowNumber >= 0; rowNumber--) {
			customersTableModel.removeRow(rowNumber);
		}
	}
	
	private void addContentToCustomersTable(String[][] allCustomersInfoForTable) {
		for(int rowIndex = 0; rowIndex < allCustomersInfoForTable.length; rowIndex++) {
			customersTableModel.addRow(allCustomersInfoForTable[rowIndex]);
		}
	}
	
	private void setBounds() {
		//code auto-generated by Window Designer
		cutomersLabel.setBounds(206, 30, 75, 17);
		bankAccountsLabel.setBounds(516, 169, 191, 17);
		addCustomerButton.setBounds(47, 313, 105, 23);
		changeCustomerButton.setBounds(198, 313, 105, 23);
		deleteCustomerButton.setBounds(347, 313, 105, 23);
		addBankAccountButton.setBounds(478, 313, 80, 23);
		deleteBankAccountButton.setBounds(664, 313, 80, 23);
		changeBankAccountButton.setBounds(568, 313, 86, 23);
		customersTableScrollPane.setBounds(47, 58, 405, 244);
		bankAccountsTableScrollPane.setBounds(478, 197, 264, 105);
		addressTableScrollPane.setBounds(478, 58, 264, 50);
		addressLabel.setBounds(539, 31, 168, 14);
		addAddressButton.setBounds(478, 119, 80, 23);
		deleteAddressButton.setBounds(664, 119, 80, 23);
		changeAddressButton.setBounds(568, 119, 86, 23);
	}

}
