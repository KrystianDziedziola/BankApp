package dataLayer;

public class DaoBankAccount {

	DatabaseAccess databaseAccess;
	
	public DaoBankAccount(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess;
	}
	
	public void create(BankAccount bankAccount) {
		databaseAccess.createBankAccount(bankAccount);
	}

	public BankAccount find(long accountNumberToFind) {
		return databaseAccess.findBankAccount(accountNumberToFind);
	}

	public void delete(long accountNumberToDelete) {
		databaseAccess.deleteBankAccount(accountNumberToDelete);
	}
	
}
