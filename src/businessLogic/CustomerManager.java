package businessLogic;

import java.util.ArrayList;

import presentationLayer.View;
import dataLayer.Address;
import dataLayer.Customer;
import dataLayer.CustomerDaoFactory;
import dataLayer.DaoInterface;

public class CustomerManager {

	private CustomerDaoFactory daoCustomer;
	private View view;
	
	public CustomerManager(DaoInterface dao, View view) {
		daoCustomer = new CustomerDaoFactory(dao);
		this.view = view;
	}

	public void addCustomer(long userId, String name, String surname, String password,
							String street, String city, String postCode) {
		Address address = new Address(street, city, postCode);
		Customer customer = new Customer(userId, name, surname, password, address);
		daoCustomer.create(customer);
	}
	
	public Customer findCustomerById(long customerId) {
		return daoCustomer.find(customerId);
	}
	
	public void updateCustomer(Customer customer) {
		daoCustomer.update(customer);
	}
	
	public Customer getCurrentCustomerInformation(Customer customer) {
		return daoCustomer.getCurrentCustomerInformation(customer);
	}
	
	public void deleteCustomerById(long customerId) {
		daoCustomer.delete(customerId);
	}
	
	public ArrayList<Customer> getAllCustomersList() {
		return daoCustomer.getAllCustomersList();
	}	

	public void printCustomerInfoById(long customerId) {
		view.printCustomerInformation(daoCustomer.find(customerId));
	}
	
	public void printAllCustomersInfo() {
		ArrayList<Customer> allCustomers = daoCustomer.getAllCustomersList();
		for(Customer customer:allCustomers) {
			view.printCustomerInformation(customer);
		}
	}

}
	
