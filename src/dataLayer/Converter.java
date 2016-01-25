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
}
