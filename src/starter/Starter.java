package starter;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import businessLogic.dataManagers.BankAccountManager;
import businessLogic.dataManagers.CustomerManager;
import businessLogic.viewManagers.ConnectionWindowManager;
import businessLogic.viewManagers.ManageWindowManager;
import dataLayer.Address;
import dataLayer.BankAccount;
import dataLayer.Customer;
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