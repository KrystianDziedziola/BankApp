package dataLayer;

import java.util.ArrayList;

public class CustomerDao {

	DaoInterface dao;
	
	public CustomerDao(DaoInterface dao) {
		this.dao = dao; 
		dao.connect();
	}

	public void create(Customer customer) {
		dao.createCustomer(customer);
	}
	
	public Customer find(long customerId) {
		return dao.findCustomerById(customerId);
	}
	
	public void delete(long customerId) {
		dao.deleteCustomerById(customerId);
	}
	
	public void update(Customer customer) {
		dao.updateCustomerInformation(customer);
	}
	
	public Customer getCurrentCustomerInformation(Customer customer) {
		return dao.getCurrentCustomerInformation(customer);
	}
	
	public ArrayList<Customer> getAllCustomersList() {
		return dao.getAllCustomersList();
	}

}
