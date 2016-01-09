package dataLayer;

import java.util.ArrayList;

public interface DaoInterface {
	
	public void connect();
	public void createCustomer(Customer customer);
	public Customer findCustomerById(long customerId);
	public void deleteCustomerById(long customerId);
	public void updateCustomerInformation(Customer customer);
	public Customer getCurrentCustomerInformation(Customer customer);
	public ArrayList<Customer> getAllCustomersList();
	public void closeConnection();
	public void createBankAccount(BankAccount bankAccount);
	public BankAccount findBankAccount(long accountNumberToFind);
	public void deleteBankAccount(long accountNumberToDelete);
	
}
