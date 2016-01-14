package dataLayer.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dataLayer.Customer;

public interface CustomerDaoInterface {
	
	public void connect() throws ClassNotFoundException, SQLException;
	public void create(Customer customer);
	public Customer find(long customerId);
	public void delete(long customerId);
	public void update(Customer customer);
	public Customer getCurrentInformation(Customer customer);
	public ArrayList<Customer> getAllCustomersList();
	public void closeConnection();
	
}
