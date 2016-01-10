package businessLogic;

import java.util.ArrayList;

import presentationLayer.View;
import dataLayer.Address;
import dataLayer.Customer;
import dataLayer.dao.CustomerDaoInterface;
import dataLayer.dao.CustomerMySqlDao;
import dataLayer.dao.CustomerXmlDao;

public class CustomerManager {

	private CustomerDaoInterface customerDao;
	private View view;
	
	public CustomerManager(View view) {
		customerDao = new CustomerMySqlDao();
//		customerDao = new XmlCustomerDao();
		this.view = view;
	}
	
	public void connect() {
		customerDao.connect();
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

	public void printCustomerInfoById(long customerId) {
		view.printCustomerInformation(customerDao.find(customerId));
	}
	
	public void printAllCustomersInfo() {
		ArrayList<Customer> allCustomers = customerDao.getAllCustomersList();
		for(Customer customer:allCustomers) {
			view.printCustomerInformation(customer);
		}
	}

}
	
