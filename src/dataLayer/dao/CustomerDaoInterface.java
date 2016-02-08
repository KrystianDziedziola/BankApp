package dataLayer.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dataLayer.Address;
import dataLayer.Customer;
import dataLayer.LoginInformation;

public interface CustomerDaoInterface {
	
	public void connect(LoginInformation loginInformation) throws ClassNotFoundException, SQLException;
	public void create(Customer customer) throws SQLException;
	public Customer find(long customerId);
	public void delete(long customerId);
	public void update(Customer customer);
	public Customer getCurrentInformation(Customer customer);
	public ArrayList<Customer> getAllCustomersList();
	public void closeConnection();
	public void addAddress(Address address, long customerId);
	
}
