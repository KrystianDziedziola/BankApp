package dataLayer;

import java.util.ArrayList;

public class DaoCustomer {

	DatabaseAccess databaseAccess;
	
	public DaoCustomer(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess; 
		databaseAccess.connect();
	}

	public void create(Customer customer) {
		databaseAccess.createCustomer(customer);
	}
	
	public Customer find(long customerId) {
		return databaseAccess.findCustomerById(customerId);
	}
	
	public void delete(long customerId) {
		databaseAccess.deleteCustomerById(customerId);
	}
	
	public void update(Customer customer) {
		databaseAccess.updateCustomerInformation(customer);
	}
	
	public Customer getCurrentCustomerInformation(Customer customer) {
		return databaseAccess.getCurrentCustomerInformation(customer);
	}
	
	public ArrayList<Customer> getAllCustomersList() {
		return databaseAccess.getAllCustomersList();
	}

}
