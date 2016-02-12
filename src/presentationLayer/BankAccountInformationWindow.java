package presentationLayer;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import dataLayer.BankAccount;

public class BankAccountInformationWindow {

	private JFrame frame;
	private Container contentPane;
	
	private Dimension frameSize = new Dimension(294, 146);
	
	private JTextField accountNumberTextField;
	private JTextField balanceTextField;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	private JLabel accountNumberLabel;
	private JLabel balanceLabel;
	
	public BankAccountInformationWindow() {
		initialize();
		addComponentsToFrame();
	}

	public void show() {
		frame.setVisible(true);
	}
	
	public void show(BankAccount bankAccount) {
		accountNumberTextField.setText(String.valueOf(bankAccount.getAccountNumber()));
		balanceTextField.setText(String.valueOf(bankAccount.getBalance()));
		accountNumberTextField.setEditable(false);
		show();
	}
	
	public void addCancelButtonActionListener(ActionListener actionListener) {
		cancelButton.addActionListener(actionListener);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void addAcceptButtonActionListener(ActionListener actionListener) {
		acceptButton.addActionListener(actionListener);
	}
	
	public String getAccountNumber() {
		return accountNumberTextField.getText();
	}

	public String getBalance() {
		return balanceTextField.getText();
	}

	public void close() {
		frame.dispose();
	}
	
	public void clear() {
		accountNumberTextField.setText(null);
		balanceTextField.setText(null);
	}
	
	private void initialize() {
		initializeFrame();
		initializeLabels();
		initializeTextFields();
		initializeButtons();
	}

	private void initializeFrame() {
		frame = new JFrame("Bank account");
		contentPane = frame.getContentPane();
		contentPane.setLayout(null);
		frame.setSize(frameSize);
		centerFrame(frame);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void initializeLabels() {
		accountNumberLabel = new JLabel("Account number:");
		accountNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setLabelFont(accountNumberLabel);
		
		balanceLabel = new JLabel("Balance:");
		balanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		setLabelFont(balanceLabel);
	}
	
	private void initializeTextFields() {
		accountNumberTextField = new JTextField();
		balanceTextField = new JTextField();
	}
	
	private void initializeButtons() {
		acceptButton = new JButton("Accept");
		cancelButton = new JButton("Cancel");
	}
	
	private void centerFrame(JFrame frame) {
		frame.setLocationRelativeTo(null);
	}
	
	private void setLabelFont(JLabel label) {
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
	}
	
	private void addComponentsToFrame() {
		setBounds();
		contentPane.add(accountNumberTextField);
		contentPane.add(accountNumberLabel);
		contentPane.add(balanceTextField);
		contentPane.add(balanceLabel);
		contentPane.add(acceptButton);
		contentPane.add(cancelButton);
	}

	private void setBounds() {
		accountNumberLabel.setBounds(0, 14, 100, 14);
		balanceLabel.setBounds(10, 45, 90, 14);
		
		accountNumberTextField.setBounds(110, 11, 106, 20);
		balanceTextField.setBounds(110, 42, 106, 20);
		
		acceptButton.setBounds(42, 83, 90, 23);
		cancelButton.setBounds(142, 83, 90, 23);
	}

}
