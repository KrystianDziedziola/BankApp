package businessLogic.dataManagers;

import java.sql.SQLException;
import java.util.ArrayList;

import dataLayer.Address;
import dataLayer.Customer;
import dataLayer.LoginInformation;
import dataLayer.dao.CustomerDaoInterface;
import dataLayer.dao.CustomerMySqlDao;

public class CustomerManager {

	private CustomerDaoInterface customerDao = new CustomerMySqlDao();
	
	public void connect(LoginInformation loginInformation) throws ClassNotFoundException, SQLException {
			customerDao.connect(loginInformation);
	}

	public void add(long userId, String name, String surname, String password, String street, 
			String city, String postCode) throws SQLException {
		Address address = new Address(street, city, postCode);
		Customer customer = new Customer(userId, name, surname, password, address);
		customerDao.create(customer);
	}
	
	public void add(long userId, String name, String surname, String password) 
			throws SQLException {
		Customer customer = new Customer(userId, name, surname, password, null);
		customerDao.create(customer);
	}
	
	public Customer findById(long customerId) {
		return customerDao.find(customerId);
	}
	
	public void update(Customer customer) {
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
	
