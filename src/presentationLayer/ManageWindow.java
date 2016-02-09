package presentationLayer;

import java.awt.Component;
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
import java.awt.event.WindowListener;
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
		clearTable(customersTable);
		addContentToCustomersTable(allCustomersInfoForTable);
	}
	
	public void addWindowListenerToFrame(WindowListener windowListener) {
		frame.addWindowListener(windowListener);
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
	
	public void setChangeCustomerButtonEnabled(boolean isEnabled) {
		changeCustomerButton.setEnabled(isEnabled);
	}
	
	public void setDeleteCustomerButtonEnabler(boolean isEnabled) {
		deleteCustomerButton.setEnabled(isEnabled);
	}
	
	public Component getFrame() {
		return frame;
	}
	
	public boolean isAnyRowInCustomersTableSelected() {
		if(customersTable.getSelectedRow() == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setAddressTableContent(String[] currentlySelectedCustomerAddress) {
		clearTable(addressesTable);
		if(currentlySelectedCustomerAddress != null) {
			addressesTableModel.addRow(currentlySelectedCustomerAddress);
		}
	}
	
	public void setBankAccountsTableContent(String[][] allBankAccountsArray) {
		clearTable(bankAccountsTable);
		for(int rowIndex = 0; rowIndex < allBankAccountsArray.length; rowIndex++) {
			bankAccountsTableModel.addRow(allBankAccountsArray[rowIndex]);
		}
	}
	
	public boolean isAddressTableEmpty() {
		return addressesTableModel.getRowCount() > 0 ? false : true;
	}
	
	public void setAddAddressButtonEnabled(boolean isEnabled) {
		addAddressButton.setEnabled(isEnabled);
	}

	public void setChangeAddressButtonEnabled(boolean isEnabled) {
		changeAddressButton.setEnabled(isEnabled);
	}

	public void setDeleteAddressButtonEnabled(boolean isEnabled) {
		deleteAddressButton.setEnabled(isEnabled);
	}
	
	public void addAddAddressButtonListener(ActionListener actionListener) {
		addAddressButton.addActionListener(actionListener);
	}
	
	public void addChangeAddressButtonListener(ActionListener actionListener) {
		changeAddressButton.addActionListener(actionListener);
	}
	
	public void addDeleteAddressButtonListener(ActionListener actionListener) {
		deleteAddressButton.addActionListener(actionListener);
		
	}
	
	public void clearAddressTable() {
		clearTable(addressesTable);
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
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		deleteCustomerButton.setEnabled(false);
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
		disableBankAccountsTableButtons();
	}
	
	private void disableBankAccountsTableButtons() {
		addBankAccountButton.setEnabled(false);
		changeBankAccountButton.setEnabled(false);
		deleteBankAccountButton.setEnabled(false);
	}
	
	private void initializeAddressTable() {
		String[] addressesTableColumnNames = {"STREET", "CITY", "POSTCODE"};
		addressesTableModel = getInitializedTableModel(addressesTableColumnNames);
		addressesTable = new JTable(addressesTableModel);
		addressesTable.setEnabled(false);
		addressTableScrollPane = new JScrollPane();
		initializeScrollPane(addressTableScrollPane, addressesTable);
		disableAddressTableButtons();
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
	
	private void disableAddressTableButtons() {
		addAddressButton.setEnabled(false);
		changeAddressButton.setEnabled(false);
		deleteAddressButton.setEnabled(false);
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
	
	private void clearTable(JTable table) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		for(int rowNumber = tableModel.getRowCount() - 1; rowNumber >= 0; rowNumber--) {
			tableModel.removeRow(rowNumber);
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
		bankAccountsLabel.setBounds(516, 160, 191, 17);
		addCustomerButton.setBounds(47, 313, 105, 23);
		changeCustomerButton.setBounds(198, 313, 105, 23);
		deleteCustomerButton.setBounds(347, 313, 105, 23);
		addBankAccountButton.setBounds(478, 313, 80, 23);
		deleteBankAccountButton.setBounds(664, 313, 80, 23);
		changeBankAccountButton.setBounds(568, 313, 86, 23);
		customersTableScrollPane.setBounds(47, 58, 405, 244);
		bankAccountsTableScrollPane.setBounds(478, 187, 264, 115);
		addressTableScrollPane.setBounds(478, 58, 264, 39);
		addressLabel.setBounds(539, 31, 168, 14);
		addAddressButton.setBounds(478, 110, 80, 23);
		deleteAddressButton.setBounds(664, 110, 80, 23);
		changeAddressButton.setBounds(568, 110, 86, 23);
	}

}
