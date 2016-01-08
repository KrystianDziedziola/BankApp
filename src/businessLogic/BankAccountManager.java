package businessLogic;

import presentationLayer.View;
import dataLayer.BankAccount;
import dataLayer.Customer;
import dataLayer.BankAccountDaoFactory;
import dataLayer.DaoInterface;
import dataLayer.DatabaseDao;

public class BankAccountManager {

	private View view;
	
	private BankAccountDaoFactory daoBankAccount;
	
	public BankAccountManager(DaoInterface dao, View view) {
		daoBankAccount= new BankAccountDaoFactory(dao);
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
