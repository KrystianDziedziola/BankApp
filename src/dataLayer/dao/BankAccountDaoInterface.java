package dataLayer.dao;

import dataLayer.BankAccount;

public interface BankAccountDaoInterface {
	public void connect();
	public void create(BankAccount bankAccount, long userId);
	public BankAccount find(long accountNumber);
	public void delete(long accountNumber);
	public void update(BankAccount bankAccount);
	public void closeConnection();
}
