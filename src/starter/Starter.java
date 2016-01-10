package starter;

import presentationLayer.View;
import businessLogic.CustomerManager;
import dataLayer.Customer;

public class Starter {

	public static void main(String[] args) {
		new Starter();
	}
	
	public Starter() {		
		View view = new View();
		CustomerManager customerManager = new CustomerManager(view);
		customerManager.connect();
		
//		customerManager.addCustomer(94010112345L, "Tomek", "Majewski", "asd123", "Podgorna", "Zielona Gora", "00-000");
//		customerManager.addCustomer(91234512132L, "Marcin", "Kaminski", "ddd333", "Krotka", "Zary", "68-200");
//		Customer customer = customerManager.findCustomerById(94010112345L);
		
		//System.out.println(customer);
		//System.out.println(customerManager.getCurrentCustomerInformation(customer));
		//customerManager.updateCustomer(new Customer(94010112345L, "111sd", "asd", "asd", new Address("asd", "asd", "asd")));
		//System.out.println(customerManager.getCurrentCustomerInformation(customer));
		customerManager.printAllCustomersInfo(); //FIXME:shows only one, last customer
//		customerManager.printCustomerInfoById(94010112345L);
//		customerManager.printCustomerInfoById(91234512132L);
//		customerManager.deleteCustomerById(94010112345L);
//		customerManager.deleteCustomerById(91234512132L);
		
		customerManager.closeConnection();
	}	

}