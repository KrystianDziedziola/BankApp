package dataLayer.dao;

import java.sql.SQLException;

import dataLayer.BankAccount;
import dataLayer.LoginInformation;

public interface BankAccountDaoInterface {
	
	public void connect(LoginInformation loginInformation) throws ClassNotFoundException, SQLException;
	public void create(BankAccount bankAccount, long userId);
	public BankAccount find(long accountNumber);
	public void delete(long accountNumber);
	public void update(BankAccount bankAccount);
	public void closeConnection();
	
}
