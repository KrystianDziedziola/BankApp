package businessLogic;

import presentationLayer.View;
import dataLayer.BankAccount;
import dataLayer.Customer;
import dataLayer.DaoBankAccount;

public class BankAccountManager {

	private View view;
	
	private DaoBankAccount daoBankAccount = new DaoBankAccount();
	
	public BankAccountManager(View view) {
		this.view = view;
	}

	public void createBankAccount(Customer customer, long accountNumber, int startingBalance) {
		BankAccount bankAccount = new BankAccount(accountNumber, startingBalance, customer.getUserId());
		daoBankAccount.create(bankAccount);
	}

	public BankAccount findBankAccountByAccountNumber(long accountNumberToFind) {
		return daoBankAccount.find(accountNumberToFind);
	}
	
	public void deleteBankAccountByAccountNumber(long accountNumberToDelete) {
		daoBankAccount.delete(accountNumberToDelete);
	}
	
	public void transferFunds(BankAccount from, BankAccount to, long amount) throws Exception {
		if(amount <= from.getBalance()) {
			from.withdraw(amount);
			to.deposit(amount);
		} else {
			throw new Exception("Not enough money for transfer");
		}
		
	}

	public void printBankAccountInformation(BankAccount bankAccount) {
		view.printBankAccountInformation(bankAccount);
	}

}
