package dataLayer;

import java.util.ArrayList;

public class Converter {

	static public String[][] convertCustomersListToTwoDimensionalStringArray(ArrayList<Customer> allCustomersList) {
		int numberOfCustomers = allCustomersList.size();
		int numberOfColumns = 4;
		String[][] allCustomersInfoForTable = new String[numberOfCustomers][numberOfColumns];
		for(int customerIndex = 0; customerIndex < numberOfCustomers; customerIndex++) {
			Customer customer = allCustomersList.get(customerIndex);
			int columnIndex = 0;
			allCustomersInfoForTable[customerIndex][columnIndex++] = String.valueOf(customer.getUserId());
			allCustomersInfoForTable[customerIndex][columnIndex++] = customer.getName();
			allCustomersInfoForTable[customerIndex][columnIndex++] = customer.getSurname();
			allCustomersInfoForTable[customerIndex][columnIndex++] = customer.getPassword();
		}
		return allCustomersInfoForTable;
	}
	
	static public Customer convertStringListToCustomerObject(ArrayList<String> customerInfo) {
		return new Customer(
			Long.parseLong(customerInfo.get(CustomerInfoIndex.ID)),
			customerInfo.get(CustomerInfoIndex.NAME),
			customerInfo.get(CustomerInfoIndex.SURNAME),
			customerInfo.get(CustomerInfoIndex.PASSWORD)
		);
	}

	public static String[] convertCustomerAddressToStringArray(Address address) {
		if(address != null) {
			return new String[] {address.getStreet(), address.getCity(), address.getPostcode()};
		} else {
			return null;
		}
		
	}

	public static String[][] convertBankAccountsListToTwoDimensionalArray(
			ArrayList<BankAccount> allBankAccounts) {
		String[][] allBankAccountsArray = new String[allBankAccounts.size()][];
		for(int bankAccountIndex = 0; bankAccountIndex < allBankAccounts.size(); bankAccountIndex++) {
			allBankAccountsArray[bankAccountIndex] = convertBankAccountToArray(
					allBankAccounts.get(bankAccountIndex));
		}
		return allBankAccountsArray;
	}

	public static String[] convertBankAccountToArray(BankAccount bankAccount) {
		return new String[] {
				String.valueOf(bankAccount.getAccountNumber()), 
				String.valueOf(bankAccount.getBalance())
		};
	}

	public static BankAccount convertStringListToBankAccountObject(ArrayList<String> bankAccountsTableSelectedRow) {
		Long accountNumber = Long.parseLong(bankAccountsTableSelectedRow.get(BankAccountInfoIndex.ACCOUNT_NUMBER));
		Long balance = Long.parseLong(bankAccountsTableSelectedRow.get(BankAccountInfoIndex.BALANCE));
		return new BankAccount(accountNumber, balance);
	}
}
