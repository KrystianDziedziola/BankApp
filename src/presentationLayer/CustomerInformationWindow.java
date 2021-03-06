package presentationLayer;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dataLayer.Customer;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class CustomerInformationWindow {
	
	private final Dimension frameSize = new Dimension(275, 230);
	
	private JFrame frame;
	private Container frameContentPane;
	
	private JLabel idLabel;
	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel passwordLabel;
	
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField surnameTextField;
	private JPasswordField passwordField;
	
	private JButton acceptButton;
	private JButton cancelButton;

	private JCheckBox showPasswordCheckBox;
	

	public CustomerInformationWindow() {
		initialize();
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void show(Customer customer) {
		fillInputBoxesWithCustomerInfo(customer);
		show();
	}
	
	public void setIdFieldEditable(boolean isIdFieldEditable) {
		idTextField.setEditable(isIdFieldEditable);
	}
 	
	private void fillInputBoxesWithCustomerInfo(Customer customer) {
		idTextField.setText(String.valueOf(customer.getUserId()));
		nameTextField.setText(customer.getName());
		surnameTextField.setText(customer.getSurname());
		passwordField.setText(customer.getPassword());
	}

	public void close() {
		frame.dispose();
	}
	
	public void clear() {
		idTextField.setText(null);
		nameTextField.setText(null);
		surnameTextField.setText(null);
		passwordField.setText(null);
		showPasswordCheckBox.setSelected(false);
	}
	
	public void addCustomerWindowAcceptButtonListener(ActionListener actionListener) {
		acceptButton.addActionListener(actionListener);
	}
	
	public void addCancelButtonListener(ActionListener actionListener) {
		cancelButton.addActionListener(actionListener);
	}
	
	public void addShowPasswordCheckBoxListenere(ActionListener actionListener) {
		showPasswordCheckBox.addActionListener(actionListener);
	}
	
	public void addWindowListenerToFrame(WindowListener windowListener) {
		frame.addWindowListener(windowListener);
	}
	
	public Object getShowPasswordCheckBox() {
		return showPasswordCheckBox;
	}
	
	public char getPasswordFieldDefaultEchoChar() {
		return passwordField.getEchoChar();
	}

	public boolean isShowPasswordCheckBoxSelected() {
		return showPasswordCheckBox.isSelected();
	}
	
	public void setPasswordFieldEcho(char noEcho) {
		passwordField.setEchoChar(noEcho);
	}
	
	public ArrayList<String> getCustomerInformation() {
		ArrayList<String> customerInfoList = new ArrayList<String>();
		customerInfoList.add(idTextField.getText());
		customerInfoList.add(nameTextField.getText());
		customerInfoList.add(surnameTextField.getText());
		customerInfoList.add(String.valueOf(passwordField.getPassword()));
		return customerInfoList;
	}
	
	public Component getFrame() {
		return frame;
	}

	private void initialize() {
		initializeFrame();
		initializeLabels();
		initializeInputFields();
		initializeButtons();
		initializeCheckBox();
		addComponents();
	}

	private void initializeFrame() {
		frame = new JFrame("Customer");
		frameContentPane = frame.getContentPane();
		frame.setResizable(false);
		frame.setSize(frameSize);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameContentPane.setLayout(null);
	}
	
	private void initializeLabels() {
		idLabel = new JLabel("ID:");
		formatLabel(idLabel);
		
		nameLabel = new JLabel("Name:");
		formatLabel(nameLabel);
		
		surnameLabel = new JLabel("Surname:");
		formatLabel(surnameLabel);
		
		passwordLabel = new JLabel("Password:");
		formatLabel(passwordLabel);
	}

	private void formatLabel(JLabel label) {
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	private void initializeInputFields() {
		idTextField = new JTextField();
		nameTextField = new JTextField();
		surnameTextField = new JTextField();
		passwordField = new JPasswordField();
	}
	
	private void initializeButtons() {
		acceptButton = new JButton("Accept");
		cancelButton = new JButton("Cancel");
	}
	
	private void initializeCheckBox() {
		showPasswordCheckBox = new JCheckBox("Show password");
		showPasswordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
	}
	
	private void addComponents() {
		setBounds();
		frameContentPane.add(idLabel);
		frameContentPane.add(nameLabel);
		frameContentPane.add(surnameLabel);
		frameContentPane.add(passwordLabel);
		
		frameContentPane.add(idTextField);
		frameContentPane.add(nameTextField);
		frameContentPane.add(surnameTextField);
		frameContentPane.add(passwordField);
		
		frameContentPane.add(acceptButton);
		frameContentPane.add(cancelButton);
		
		frameContentPane.add(showPasswordCheckBox);
	}

	private void setBounds() {
		//auto-generated by Window Designer
		idLabel.setBounds(24, 14, 46, 14);
		nameLabel.setBounds(24, 42, 46, 14);
		surnameLabel.setBounds(10, 70, 60, 14);
		passwordLabel.setBounds(10, 102, 60, 14);
		idTextField.setBounds(80, 11, 110, 20);
		nameTextField.setBounds(80, 39, 110, 20);
		surnameTextField.setBounds(80, 67, 110, 20);
		passwordField.setBounds(80, 98, 110, 20);
		acceptButton.setBounds(35, 157, 89, 23);
		cancelButton.setBounds(131, 157, 89, 23);
		showPasswordCheckBox.setBounds(80, 125, 97, 23);
	}

}
