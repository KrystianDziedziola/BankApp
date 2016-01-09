package dataLayer;

public class BankAccountDao {

	DaoInterface dao;
	
	public BankAccountDao(DaoInterface dao) {
		this.dao = dao;
	}
	
	public void create(BankAccount bankAccount) {
		dao.createBankAccount(bankAccount);
	}

	public BankAccount find(long accountNumberToFind) {
		return dao.findBankAccount(accountNumberToFind);
	}

	public void delete(long accountNumberToDelete) {
		dao.deleteBankAccount(accountNumberToDelete);
	}
	
}
