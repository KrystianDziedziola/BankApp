package dataLayer;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private long userId;
	private String name;
	private String surname;
	private String password;
	
	private Address address;
	private ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	
	public Customer(long userIdRef, String nameRef, String surnameRef, String passwordRef) {
		userId = userIdRef;
		name = nameRef;
		surname = surnameRef;
		password = passwordRef;
	}
	
	public Customer(long userIdRef, String nameRef, String surnameRef, String passwordRef, Address addressRef) {
		this(userIdRef, nameRef, surnameRef, passwordRef);
		address = addressRef;
	}
	
	public void addBankAccount(BankAccount bankAccount) {
		bankAccounts.add(bankAccount);
	}
	
	public void deleteBankAccount(long accountNumberToDelete) {
		Iterator<BankAccount> iterator = bankAccounts.iterator();
		while(iterator.hasNext()) {
			BankAccount account = iterator.next();
			if(account.getAccountNumber() == accountNumberToDelete) {
				iterator.remove();
			}
		}
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", name=" + name + ", surname="
				+ surname + ", password=" + password + ", addresses="
				+ address + ", bankAccounts=" + bankAccounts + "]";
	}
	
}
