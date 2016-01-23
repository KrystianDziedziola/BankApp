package businessLogic;

import dataLayer.BankAccount;
import dataLayer.LoginInformation;
import dataLayer.dao.BankAccountDaoInterface;
import dataLayer.dao.BankAccountMySqlDao;

public class BankAccountManager {

	private BankAccountDaoInterface bankAccountDao = new BankAccountMySqlDao();
	
	public void connect(LoginInformation loginInformation) throws Exception {
		bankAccountDao.connect(loginInformation);
	}

	public void create(long accountNumber, int startingBalance, long userId) {
		BankAccount bankAccount = new BankAccount(accountNumber, startingBalance, userId);
		bankAccountDao.create(bankAccount, userId);
	}

	public BankAccount find(long accountNumber) {
		return bankAccountDao.find(accountNumber);
	}
	
	public void delete(long accountNumber) {
		bankAccountDao.delete(accountNumber);
	}
	
	public BankAccount getCurrentInformation(long accountNumber) {
		return bankAccountDao.find(accountNumber);
	}
	
	public void update(BankAccount bankAccount) {
		bankAccountDao.update(bankAccount);
	}
	
	public void closeConnection() {
		bankAccountDao.closeConnection();
	}

	public void withdraw(BankAccount bankAccount, int amount) {
		bankAccount.withdraw(amount);
		bankAccountDao.update(bankAccount);
	}
	
	public void deposit(BankAccount bankAccount, int amount) {
		bankAccount.deposit(amount);
		bankAccountDao.update(bankAccount);
	}
	
	public void transferFunds(BankAccount from, BankAccount to, long amount) throws Exception {
		if(amount <= from.getBalance()) {
			from.withdraw(amount);
			to.deposit(amount);
			bankAccountDao.update(from);
			bankAccountDao.update(to);
		} else {
			throw new Exception("Not enough money for transfer");
		}
	}

}
