package dataLayer.dao;

import java.util.ArrayList;

import dataLayer.Customer;

public class CustomerXmlDao implements CustomerDaoInterface {

	public void connect() {
		System.out.println("connect");
	}

	public void create(Customer customer) {
		System.out.println("create");		
	}

	public Customer find(long customerId) {
		System.out.println("find");
		return null;
	}

	public void delete(long customerId) {
		System.out.println("delete");
	}

	public void update(Customer customer) {
		System.out.println("update");		
	}

	public Customer getCurrentInformation(Customer customer) {
		System.out.println("getCurrentInformation");
		return null;
	}

	public ArrayList<Customer> getAllCustomersList() {
		System.out.println("getAllCustomersList");
		return null;
	}

	public void closeConnection() {
		System.out.println("closeConnection");
	}

}
