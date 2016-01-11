package dataLayer.dao.customer;

import java.util.ArrayList;

import dataLayer.Customer;

public interface CustomerDaoInterface {
	public void connect();
	public void create(Customer customer);
	public Customer find(long customerId);
	public void delete(long customerId);
	public void update(Customer customer);
	public Customer getCurrentInformation(Customer customer);
	public ArrayList<Customer> getAllCustomersList();
	public void closeConnection();
}
