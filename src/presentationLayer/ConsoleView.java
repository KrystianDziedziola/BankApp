package presentationLayer;

import dataLayer.BankAccount;
import dataLayer.Customer;

public class ConsoleView {
	
	public void printCustomerInformation(Customer customer) {
		System.out.println(customer);
	}
	
	public void printBankAccountInformation(BankAccount bankAccount) {
		System.out.println(bankAccount);
	}
}
