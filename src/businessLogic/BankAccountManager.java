package businessLogic;

import presentationLayer.View;
import dataLayer.BankAccount;
import dataLayer.Customer;
import dataLayer.DaoBankAccount;
import dataLayer.DatabaseAccess;

public class BankAccountManager {

	private View view;
	
	private DaoBankAccount daoBankAccount;
	
	public BankAccountManager(DatabaseAccess databaseAccess, View view) {
		daoBankAccount= new DaoBankAccount(databaseAccess);
		this.view = view;
	}

	public void createBankAccount(long userId, long accountNumber, int startingBalance) {
		BankAccount bankAccount = new BankAccount(accountNumber, startingBalance, userId);
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
