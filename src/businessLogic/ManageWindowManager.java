package businessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import dataLayer.Customer;
import presentationLayer.ManageWindow;

public class ManageWindowManager {

	private ManageWindow manageWindow = new ManageWindow();
	private CustomerManager customerManager = new CustomerManager();
	
	public ManageWindowManager() {
		connectToDatabase();
		sendCustomersInformationForTable();
	}
	
	public void show() {
		manageWindow.show();
	}
	
	private void connectToDatabase() {
		try {
			customerManager.connect("user", "logitech1");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void sendCustomersInformationForTable() {
		String[][] allCustomersInfoForTable = convertListToStringArray(customerManager.getAllCustomersList());
		manageWindow.putCustomersInfoIntoTable(allCustomersInfoForTable);
	}

	private String[][] convertListToStringArray(ArrayList<Customer> allCustomersList) {
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
	
}
