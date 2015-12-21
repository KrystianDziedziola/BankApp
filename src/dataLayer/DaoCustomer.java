package dataLayer;

import java.util.ArrayList;

public class DaoCustomer {

	DatabaseAccess databaseAccess;
	
	public DaoCustomer(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess; 
		databaseAccess.connect();
	}

	public void create(Customer customer) {
		databaseAccess.create(customer);
	}
	
	public Customer find(long customerId) {
		return databaseAccess.findCustomerById(customerId);
	}
	
	public void delete(long customerId) {
		databaseAccess.deleteCustomerById(customerId);
	}
	
	public void update() {
		databaseAccess.updateCustomerInfo();
	}
	
	public ArrayList<Customer> getAllCustomersList() {
		return databaseAccess.getAllCustomersList();
	}

}
