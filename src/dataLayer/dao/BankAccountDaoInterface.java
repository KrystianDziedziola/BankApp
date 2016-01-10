package dataLayer.dao;

import dataLayer.BankAccount;

public interface BankAccountDaoInterface {

	public void create(BankAccount bankAccount);
	public BankAccount find(long accountNumberToFind);
	public void delete(long accountNumberToDelete);
	
}
