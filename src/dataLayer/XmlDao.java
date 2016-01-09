package dataLayer;

import java.util.ArrayList;

public class XmlDao implements DaoInterface {

	public void connect() {
		System.out.println("XML connect");
	}

	public void createCustomer(Customer customer) {
		System.out.println("XML create customer");
	}

	public Customer findCustomerById(long customerId) {
		System.out.println("XML find customer");
		return null;
	}

	public void deleteCustomerById(long customerId) {
		// TODO Auto-generated method stub
		
	}

	public void updateCustomerInformation(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	public Customer getCurrentCustomerInformation(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Customer> getAllCustomersList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void closeConnection() {
		System.out.println("XML find close connection");
		
	}

	public void createBankAccount(BankAccount bankAccount) {
		// TODO Auto-generated method stub
		
	}

	public BankAccount findBankAccount(long accountNumberToFind) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteBankAccount(long accountNumberToDelete) {
		// TODO Auto-generated method stub
		
	}

}
