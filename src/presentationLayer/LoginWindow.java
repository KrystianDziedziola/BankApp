package presentationLayer;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.Font;

public class LoginWindow {

	private final int FRAME_WIDTH = 360;
	private final int FRAME_HEIGHT = 220;
	
	private JFrame frame;
	private Container frameContentPane;
	SpringLayout frameSpringLayout;
	private JLabel infoLabel;
	private JLabel loginLabel, passwordLabel;
	private JTextField loginTextField;
	private JPasswordField passwordField;
	private JButton loginButton, createUserButton;

	public LoginWindow() {
		initialize();
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	private void initialize() {
		createFrame();
		createFrameLayout();
		createInfoLabel();
		createInputComponents();		
		createButtons();
		addComponentsToLayout();
	}

	private void createFrame() {
		frame = new JFrame("Log in");
		frameContentPane = frame.getContentPane();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerFrame(frame);
	}
	
	private void centerFrame(JFrame frame) {
		frame.setLocationRelativeTo(null);
	}
	
	private void createFrameLayout() {
		frameSpringLayout = new SpringLayout();
		frameContentPane.setLayout(frameSpringLayout);
	}
	
	private void createInfoLabel() {
		infoLabel = new JLabel("Welcome! Log into MySql database.");
		frameSpringLayout.putConstraint(SpringLayout.NORTH, infoLabel, 23, SpringLayout.NORTH, frameContentPane);
	}
	
	private void createInputComponents() {
		createLoginInputComponents();
		createPasswordInputComponents();
	}

	private void createLoginInputComponents() {
		loginLabel = new JLabel("Login: ");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		loginTextField = new JTextField(15);
		loginTextField.setColumns(10);
	}
	
	private void createPasswordInputComponents() {
		passwordLabel = new JLabel("Password: ");		
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passwordField = new JPasswordField(15);
		passwordField.setColumns(10);
	}

	private void createButtons() {
		Dimension buttonSize = new Dimension(150, 30);
		loginButton = new JButton("Log in");
		loginButton.setSize(buttonSize);
		createUserButton = new JButton("Create user");
		createUserButton.setSize(buttonSize);
	}
	
	private void addComponentsToLayout() {
		putLayoutConstraints();
		frameContentPane.add(infoLabel);
		frameContentPane.add(loginLabel);
		frameContentPane.add(loginTextField);
		frameContentPane.add(passwordLabel);
		frameContentPane.add(passwordField);
		frameContentPane.add(loginButton);
		frameContentPane.add(createUserButton);
	}
	
	private void putLayoutConstraints() {
		//code auto-generated by Window Builder
		frameSpringLayout.putConstraint(SpringLayout.WEST, loginLabel, 85, SpringLayout.WEST, frameContentPane);
		frameSpringLayout.putConstraint(SpringLayout.NORTH, loginLabel, 3, SpringLayout.NORTH, loginTextField);
		frameSpringLayout.putConstraint(SpringLayout.EAST, loginLabel, -19, SpringLayout.WEST, loginTextField);
		frameSpringLayout.putConstraint(SpringLayout.NORTH, loginTextField, 64, SpringLayout.NORTH, frameContentPane);
		frameSpringLayout.putConstraint(SpringLayout.WEST, loginTextField, 136, SpringLayout.WEST, frameContentPane);
		frameSpringLayout.putConstraint(SpringLayout.WEST, infoLabel, 0, SpringLayout.WEST, passwordLabel);
		frameSpringLayout.putConstraint(SpringLayout.WEST, passwordLabel, 64, SpringLayout.WEST, frameContentPane);
		frameSpringLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, loginLabel);
		frameSpringLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 5, SpringLayout.NORTH, passwordField);
		frameSpringLayout.putConstraint(SpringLayout.NORTH, passwordField, 6, SpringLayout.SOUTH, loginTextField);
		frameSpringLayout.putConstraint(SpringLayout.WEST, passwordField, 136, SpringLayout.WEST, frameContentPane);
		frameSpringLayout.putConstraint(SpringLayout.NORTH, loginButton, 23, SpringLayout.SOUTH, passwordField);
		frameSpringLayout.putConstraint(SpringLayout.EAST, loginButton, -191, SpringLayout.EAST, frameContentPane);
		frameSpringLayout.putConstraint(SpringLayout.WEST, loginButton, 0, SpringLayout.WEST, infoLabel);
		frameSpringLayout.putConstraint(SpringLayout.NORTH, createUserButton, 23, SpringLayout.SOUTH, passwordField);
		frameSpringLayout.putConstraint(SpringLayout.WEST, createUserButton, 24, SpringLayout.EAST, loginButton);
	}
}