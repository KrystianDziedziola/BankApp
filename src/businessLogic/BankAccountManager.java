package businessLogic;

import presentationLayer.View;
import dataLayer.BankAccount;
import dataLayer.dao.bankAccount.BankAccountDaoInterface;
import dataLayer.dao.bankAccount.BankAccountMySqlDao;
import dataLayer.dao.bankAccount.BankAccountXmlDao;

public class BankAccountManager {

	private View view;
	
	private BankAccountDaoInterface bankAccountDao;
	
	public BankAccountManager(View view) {
		this.view = view;
	}
	
	public void setDaoToMySql() {
		bankAccountDao = new BankAccountMySqlDao();
	}
	
	public void setDaoToXml() {
		bankAccountDao = new BankAccountXmlDao();
	}
	
	public void connect() throws Exception {
		if(bankAccountDao == null) {
			throw new Exception("No dao type selected");
		} else {
			bankAccountDao.connect();
		}
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

	public void printBankAccountInformation(BankAccount bankAccount) {
		view.printBankAccountInformation(bankAccount);
	}

}
