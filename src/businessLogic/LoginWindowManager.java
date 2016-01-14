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
			e.printStackTrace();
		}
	}
	
	public void show() {
		loginWindow.show();
		defineLoginButtonAction();
	}

	private void defineLoginButtonAction() {
		loginWindow.addLoginButtonListener(new LoginButtonListenener());
	}
	
	private boolean isLoginSucceed() {
		Long login = Long.parseLong(loginWindow.getLogin());
		System.out.println(login);
		
		String password = loginWindow.getPassword();
		System.out.println(password);
		
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
				System.out.println("ok");
			} else {
				System.out.println("not");
			}
		}
		
	}
	
}




