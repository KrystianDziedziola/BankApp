package businessLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import dataLayer.Converter;
import presentationLayer.ManageWindow;

public class ManageWindowManager {

	private ManageWindow manageWindow = new ManageWindow();
	private CustomerManager customerManager = new CustomerManager();
	
	public ManageWindowManager() {
		connectToDatabase();
		sendCustomersInformationForTable();
		
		defineAddCustomerButtonAction();
		defineChangeCustomerButtonAction();
		defineDeleteCustomerButtonAction();
	}
	
	public void show() {
		manageWindow.show();
	}
	
	public void defineAddCustomerButtonAction() {
		manageWindow.addAddCustomerButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageWindow.displayAddCustomerDialog();
			}
		});
	}
	
	public void defineChangeCustomerButtonAction() {
		manageWindow.addChangeCustomerButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageWindow.displayChangeCustomerDialog();
			}
		});
	}
	
	public void defineDeleteCustomerButtonAction() {
		manageWindow.addDeleteCustomerButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageWindow.displayDeleteCustomerDialog();
			}
		});
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
		String[][] allCustomersInfoForTable = Converter.convertListToStringArray(
												customerManager.getAllCustomersList());
		manageWindow.putCustomersInfoIntoTable(allCustomersInfoForTable);
	}

}
