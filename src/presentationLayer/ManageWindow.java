package presentationLayer;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;

public class ManageWindow {

	private final Dimension frameSize = new Dimension(800, 600);
	
	private JFrame frame;
	private Container frameContentPane;
	private JLabel cutomersLabel;
	private JLabel bankAccountsLabel;
	private JButton addCustomerButton;
	private JButton changeCustomerButton;
	private JButton deleteCustomerButton;
	private JTable customersTable;
	private JTable bankAccountsTable;
	
	private SpringLayout springLayout;

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
		frame.setSize(frameSize);
		centerFrame(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void centerFrame(JFrame frame) {
		frame.setLocationRelativeTo(null);
	}
	
	private void setupFrameLayout() {
		springLayout = new SpringLayout();
		frameContentPane.setLayout(springLayout);
	}
	
	private void initializeLabels() {
		cutomersLabel = new JLabel("Customers");
		springLayout.putConstraint(SpringLayout.WEST, cutomersLabel, 170, SpringLayout.WEST, frame.getContentPane());
		cutomersLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		bankAccountsLabel = new JLabel("Bank accounts");
		springLayout.putConstraint(SpringLayout.NORTH, bankAccountsLabel, 0, SpringLayout.NORTH, cutomersLabel);
		bankAccountsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	}
	
	private void initializeButtons() {
		addCustomerButton = new JButton("Add");
		springLayout.putConstraint(SpringLayout.NORTH, addCustomerButton, 509, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, addCustomerButton, 47, SpringLayout.WEST, frame.getContentPane());
		changeCustomerButton = new JButton("Change");
		springLayout.putConstraint(SpringLayout.NORTH, changeCustomerButton, 509, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, changeCustomerButton, 269, SpringLayout.WEST, frame.getContentPane());
		deleteCustomerButton = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.EAST, addCustomerButton, -6, SpringLayout.WEST, deleteCustomerButton);
		springLayout.putConstraint(SpringLayout.WEST, deleteCustomerButton, 158, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, deleteCustomerButton, -6, SpringLayout.WEST, changeCustomerButton);
	}
	
	private void initializeTables() {
		customersTable = new JTable();
		springLayout.putConstraint(SpringLayout.NORTH, customersTable, 56, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, cutomersLabel, -15, SpringLayout.NORTH, customersTable);
		springLayout.putConstraint(SpringLayout.WEST, customersTable, 47, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, deleteCustomerButton, 6, SpringLayout.SOUTH, customersTable);
		springLayout.putConstraint(SpringLayout.SOUTH, customersTable, -6, SpringLayout.NORTH, addCustomerButton);
		bankAccountsTable = new JTable();
		springLayout.putConstraint(SpringLayout.EAST, customersTable, -37, SpringLayout.WEST, bankAccountsTable);
		springLayout.putConstraint(SpringLayout.NORTH, bankAccountsTable, 58, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bankAccountsTable, 411, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, bankAccountsTable, -46, SpringLayout.EAST, frame.getContentPane());
	}
	
	private void addComponentsToFrame() {
		putConstraints();
		frameContentPane.add(cutomersLabel);
		frameContentPane.add(bankAccountsLabel);
		frameContentPane.add(addCustomerButton);
		frameContentPane.add(changeCustomerButton);
		frameContentPane.add(deleteCustomerButton);
		frameContentPane.add(customersTable);
		frameContentPane.add(bankAccountsTable);
		
		JButton addBankAccountButton = new JButton("Add");
		springLayout.putConstraint(SpringLayout.NORTH, addBankAccountButton, 509, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, bankAccountsTable, -6, SpringLayout.NORTH, addBankAccountButton);
		springLayout.putConstraint(SpringLayout.WEST, addBankAccountButton, 411, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, changeCustomerButton, -37, SpringLayout.WEST, addBankAccountButton);
		frameContentPane.add(addBankAccountButton);
		
		JButton deleteBankAccountButton = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.WEST, bankAccountsLabel, 0, SpringLayout.WEST, deleteBankAccountButton);
		springLayout.putConstraint(SpringLayout.NORTH, deleteBankAccountButton, 6, SpringLayout.SOUTH, bankAccountsTable);
		springLayout.putConstraint(SpringLayout.WEST, deleteBankAccountButton, 521, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, addBankAccountButton, -5, SpringLayout.WEST, deleteBankAccountButton);
		frameContentPane.add(deleteBankAccountButton);
		
		JButton changeBankAccountButton = new JButton("Change");
		springLayout.putConstraint(SpringLayout.NORTH, changeBankAccountButton, 6, SpringLayout.SOUTH, bankAccountsTable);
		springLayout.putConstraint(SpringLayout.WEST, changeBankAccountButton, 633, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, deleteBankAccountButton, -7, SpringLayout.WEST, changeBankAccountButton);
		springLayout.putConstraint(SpringLayout.EAST, changeBankAccountButton, -46, SpringLayout.EAST, frame.getContentPane());
		frameContentPane.add(changeBankAccountButton);
	}

	private void putConstraints() {
	}
}
