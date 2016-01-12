package presentationLayer;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import javax.swing.SpringLayout;

public class LoginWindow {

	private JFrame frame;
	SpringLayout frameSpringLayout;
	private JLabel infoLabel;
	private JLabel loginLabel, passwordlabel;
	private JTextField loginTextField, passwordTextField;
	private JButton loginButton, createUserButton;
	private JTextField textField;
	private JTextField textField_1;

	public LoginWindow() {
		initialize();
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	private void initialize() {
		createFrame();
		createInfoLabel();
		createInputComponents();		
		createButtons();
		
		addComponentsToLayout();
	}

	private void createFrame() {
		frame = new JFrame("Login");
		final int FRAME_WIDTH = 450,
				  FRAME_HEIGHT = 300;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		centerFrame(frame);
//		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSpringLayout = new SpringLayout();
		frame.getContentPane().setLayout(frameSpringLayout);
	}
	
	private void centerFrame(JFrame frame) {
		frame.setLocationRelativeTo(null);
	}
	
	private void createInfoLabel() {
		infoLabel = new JLabel("Welcome! Login into MySql database.");
	}
	
	private void createInputComponents() {
		loginLabel = new JLabel("Login: ");
		loginTextField = new JTextField(15);
		loginTextField.setSize(150, 20);
		passwordlabel = new JLabel("Password: ");
		passwordTextField = new JTextField(15);
		passwordTextField.setSize(150, 20);
	}
	
	private void createButtons() {
		Dimension buttonSize = new Dimension(150, 30);
		loginButton = new JButton("Login");
		loginButton.setSize(buttonSize);
		createUserButton = new JButton("Create user");
		createUserButton.setSize(buttonSize);
	}
	
	private void addComponentsToLayout() {
		frameSpringLayout.putConstraint(SpringLayout.NORTH, infoLabel, 10, SpringLayout.NORTH, frame.getContentPane());
		frameSpringLayout.putConstraint(SpringLayout.WEST, infoLabel, 91, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(infoLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1");
		frameSpringLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 45, SpringLayout.NORTH, frame.getContentPane());
		frameSpringLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 36, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		frameSpringLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, infoLabel);
		frameSpringLayout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, lblNewLabel_1);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("2");
		frameSpringLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 12, SpringLayout.SOUTH, lblNewLabel_1);
		frameSpringLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel_1);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		frameSpringLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, infoLabel);
		frameSpringLayout.putConstraint(SpringLayout.SOUTH, textField_1, 0, SpringLayout.SOUTH, lblNewLabel_2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("1");
		frameSpringLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 23, SpringLayout.SOUTH, textField_1);
		frameSpringLayout.putConstraint(SpringLayout.WEST, btnNewButton, 51, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("2");
		frameSpringLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 23, SpringLayout.SOUTH, textField_1);
		frameSpringLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 24, SpringLayout.EAST, btnNewButton);
		frameSpringLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 0, SpringLayout.SOUTH, btnNewButton);
		frame.getContentPane().add(btnNewButton_1);
	}
}
