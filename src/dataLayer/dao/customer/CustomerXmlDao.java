package dataLayer.dao.customer;

import java.util.ArrayList;

import dataLayer.Customer;
import dataLayer.dao.XmlDao;

public class CustomerXmlDao extends XmlDao implements CustomerDaoInterface {

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

}
