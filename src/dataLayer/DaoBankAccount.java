package dataLayer;

import java.util.ArrayList;
import java.util.Iterator;

public class DaoBankAccount {

	private ArrayList<BankAccount> allBankAccounts = new ArrayList<BankAccount>();
	
	public void create(BankAccount bankAccount) {
		allBankAccounts.add(bankAccount);
	}

	public BankAccount find(long accountNumberToFind) {
		for(BankAccount bankAccount:allBankAccounts) {
			if(bankAccount.getAccountNumber() == accountNumberToFind) {
				return bankAccount;				
			}
		}			
		return null;
	}

	public void delete(long accountNumberToDelete) {
		Iterator<BankAccount> iterator = allBankAccounts.iterator();
		while(iterator.hasNext()) {
			BankAccount bankAccount = iterator.next();
			if(bankAccount.getAccountNumber() == accountNumberToDelete) {
				iterator.remove();
				break;
			}
		}
	}
	
}
