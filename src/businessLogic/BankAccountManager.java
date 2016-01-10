package businessLogic;

import presentationLayer.View;
import dataLayer.BankAccount;
import dataLayer.dao.BankAccountDaoInterface;
import dataLayer.dao.BankAccountMySqlDao;

public class BankAccountManager {

	private View view;
	
	private BankAccountDaoInterface bankAccountDao;
	
	public BankAccountManager(View view) {
		bankAccountDao= new BankAccountMySqlDao();
		this.view = view;
	}

	public void createBankAccount(long userId, long accountNumber, int startingBalance) {
		BankAccount bankAccount = new BankAccount(accountNumber, startingBalance, userId);
		bankAccountDao.create(bankAccount);
	}

	public BankAccount findBankAccountByAccountNumber(long accountNumberToFind) {
		return bankAccountDao.find(accountNumberToFind);
	}
	
	public void deleteBankAccountByAccountNumber(long accountNumberToDelete) {
		bankAccountDao.delete(accountNumberToDelete);
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
