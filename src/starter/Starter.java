package starter;

import presentationLayer.View;
import businessLogic.BankAccountManager;
import businessLogic.CustomerManager;
import dataLayer.Address;
import dataLayer.Customer;
import dataLayer.DatabaseAccess;

public class Starter {

	public static void main(String[] args) {
		new Starter();
	}
	
	public Starter() {
		DatabaseAccess databaseAccess = new DatabaseAccess();
		View view = new View();
		
		CustomerManager customerManager = new CustomerManager(databaseAccess, view);
		//customerManager.addCustomer(94010112345L, "Tomek", "Majewski", "asd123", "Podgorna", "Zielona Gora", "00-000");
		//customerManager.addCustomer(91234512132L, "Marcin", "Kaminski", "ddd333", "Krotka", "Zary", "68-200");
		//customerManager.deleteCustomerById(94010112345L);
		//customerManager.deleteCustomerById(91234512132L);
		//customerManager.printAllCustomersInfo();
		customerManager.updateCustomer(new Customer(94010112345L, "asd", "asd", "asd", new Address("asd", "asd", "asd")));
		customerManager.printCustomerInfoById(94010112345L);
		databaseAccess.closeConnection();
	}	

}