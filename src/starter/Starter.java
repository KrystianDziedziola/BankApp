package starter;

import presentationLayer.ConsoleView;
import businessLogic.BankAccountManager;
import businessLogic.CustomerManager;
import businessLogic.LoginWindowManager;
import dataLayer.BankAccount;
import dataLayer.Customer;

public class Starter {

	public static void main(String[] args) {
		new Starter();
	}
	
	public Starter() {		
		try {
			/*CustomerManager customerManager = new CustomerManager();
			customerManager.connect();
			
			customerManager.addCustomer(94010112345L, "Tomek", "Majewski", "asd123", "Podgorna", "Zielona Gora", "00-000");
			customerManager.addCustomer(91234512132L, "Marcin", "Kaminski", "ddd333", "Krotka", "Zary", "68-200");

			BankAccountManager bankAccountManager = new BankAccountManager();
			bankAccountManager.connect();
			bankAccountManager.create(111111111112L, 1000, 94010112345L);
//			bankAccountManager.update(new BankAccount(111111111112L, 2000, 94010112345L));
			bankAccountManager.create(222222222222L, 0, 91234512132L);
//			bankAccountManager.deposit(bankAccountManager.find(111111111112L), 50);
//			bankAccountManager.transferFunds(
//					bankAccountManager.find(111111111112L),
//					bankAccountManager.find(222222222222L), 1000L);
//			bankAccountManager.withdraw(bankAccountManager.find(222222222222L), 100);
//			bankAccountManager.delete(111111111112L);
//			bankAccountManager.delete(222222222222L);
			
			bankAccountManager.closeConnection();
			
//			Customer customer = customerManager.findCustomerById(94010112345L);
			//System.out.println(customer);
			//System.out.println(customerManager.getCurrentCustomerInformation(customer));
			//customerManager.updateCustomer(new Customer(94010112345L, "111sd", "asd", "asd", new Address("asd", "asd", "asd")));
			//System.out.println(customerManager.getCurrentCustomerInformation(customer));
//			customerManager.printAllCustomersInfo(); //FIXME:shows only one, last customer
//			customerManager.deleteCustomerById(94010112345L);
//			customerManager.deleteCustomerById(91234512132L);
			
			customerManager.closeConnection();*/
			
			new LoginWindowManager().show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}