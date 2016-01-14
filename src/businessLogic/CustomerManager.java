package businessLogic;

import java.util.ArrayList;

import dataLayer.Address;
import dataLayer.Customer;
import dataLayer.dao.CustomerDaoInterface;
import dataLayer.dao.CustomerMySqlDao;

public class CustomerManager {

	private CustomerDaoInterface customerDao = new CustomerMySqlDao();;
	
	public void connect() throws Exception {
		if(customerDao == null) {
			throw new Exception("No dao type selected");
		} else {
			customerDao.connect();
		}
	}

	public void addCustomer(long userId, String name, String surname, String password,
							String street, String city, String postCode) {
		Address address = new Address(street, city, postCode);
		Customer customer = new Customer(userId, name, surname, password, address);
		customerDao.create(customer);
	}
	
	public Customer findCustomerById(long customerId) {
		return customerDao.find(customerId);
	}
	
	public void updateCustomer(Customer customer) {
		customerDao.update(customer);
	}
	
	public Customer getCurrentCustomerInformation(Customer customer) {
		return customerDao.getCurrentInformation(customer);
	}
	
	public void deleteCustomerById(long customerId) {
		customerDao.delete(customerId);
	}
	
	public ArrayList<Customer> getAllCustomersList() {
		return customerDao.getAllCustomersList();
	}	
	
	public void closeConnection() {
		customerDao.closeConnection();
	}

}
	
