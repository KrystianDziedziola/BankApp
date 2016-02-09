package starter;

import java.awt.EventQueue;

import businessLogic.viewManagers.ManageWindowManager;
import dataLayer.LoginInformation;

public class Starter {

	public static void main(String[] args) {
		new Starter();
	}
	
	public Starter() {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				new ConnectionWindowManager().show();
				new ManageWindowManager(new LoginInformation("user", "logitech1")).show();
			}
		});
		
		
		/*CustomerManager customerManager = new CustomerManager();
		BankAccountManager bankAccountManager = new BankAccountManager();
		try {
			bankAccountManager.connect(new LoginInformation("user", "logitech1"));
			bankAccountManager.create(1243, 12443, 12341234L);
			bankAccountManager.closeConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
	}	
	
}