package dataLayer;

public class BankAccount {
	
	private long accountNumber;
	private long balance;
	private long ownerId;
	
	public BankAccount(long accountNumberRef, long balanceRef) {
		accountNumber = accountNumberRef;
		balance = balanceRef;
	}
	
	public BankAccount(long accountNumberRef, long balanceRef, long ownerIdRef) {
		this(accountNumberRef, balanceRef);
		ownerId = ownerIdRef;
	}
	
	public void withdraw(long amount) {
		balance -= amount;
	}
	
	public void deposit(long amount) {
		balance += amount;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	public long getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", balance="
				+ balance + "]";
	}
	
}
