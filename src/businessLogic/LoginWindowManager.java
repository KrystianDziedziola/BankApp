package businessLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataLayer.Customer;
import presentationLayer.LoginWindow;

public class LoginWindowManager {
	
	LoginWindow loginWindow = new LoginWindow();
	CustomerManager customerManager = new CustomerManager();

	public LoginWindowManager() {
		try {
			customerManager.connect();
		} catch (Exception e) {
			loginWindow.displayMessageDialog("Connection error", "Check your connection with database.");
			loginWindow.close();
			System.exit(0);
		}
		defineLoginButtonAction();
	}
	
	public void show() {
		loginWindow.show();
	}

	private void defineLoginButtonAction() {
		loginWindow.addLoginButtonListener(new LoginButtonListenener());
	}
	
	private boolean isLoginSucceed() {
		Long login;
		try {
			login = Long.parseLong(loginWindow.getLogin());
		} catch(NumberFormatException e) {
			return false;
		}
		String password = loginWindow.getPassword();
		
		Customer customer = customerManager.findCustomerById(login);
		if(customer == null) {
			return false;
		} else {
			if(customer.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	private class LoginButtonListenener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			if(isLoginSucceed()) {
				loginWindow.displayMessageDialog("Log in information", "Correct! You are logged in.");
				loginWindow.close();
				//open new window
			} else {
				loginWindow.displayMessageDialog("Log in information", "Incorrect! Wrong login or password.");
			}
		}
		
	}
	
}




