package businessLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import dataLayer.Converter;
import dataLayer.LoginInformation;
import presentationLayer.ManageWindow;

public class ManageWindowManager {

	private ManageWindow manageWindow = new ManageWindow();
	private CustomerManager customerManager = new CustomerManager();
	
	private CustomerInformationWindowManager customerInformationWindowManager = 
		     new CustomerInformationWindowManager(customerManager);
	
	private LoginInformation loginInformation;
	
	public ManageWindowManager(LoginInformation loginInformation) {
		this.loginInformation = loginInformation;
		
		connectToDatabase();
		fillCustomersTable();
		
		defineAddCustomerButtonAction();
		defineChangeCustomerButtonAction();
		defineDeleteCustomerButtonAction();
		defineCustomerInformationWindowAcceptButtonAction();
	}
	
	public void show() {
		manageWindow.show();
	}
	
	public void defineAddCustomerButtonAction() {
		manageWindow.addAddCustomerButtonListener(new ModifyCustomerButtonActionListener());
	}
	
	public void defineChangeCustomerButtonAction() {
		manageWindow.addChangeCustomerButtonListener(new ModifyCustomerButtonActionListener());
	}
	
	public void defineDeleteCustomerButtonAction() {
		manageWindow.addDeleteCustomerButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//delete
			}
		});
	}
	
	private void connectToDatabase() {
		try {
			customerManager.connect(loginInformation);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void defineCustomerInformationWindowAcceptButtonAction() {
		customerInformationWindowManager.addAcceptButtonActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				customerInformationWindowManager.defineAcceptButtonAction();
				fillCustomersTable();
			}
			
		});
	}
	
	private class ModifyCustomerButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			customerInformationWindowManager.show();
		}
		
	}
	
	private void fillCustomersTable() {
		String[][] allCustomersInfoForTable = Converter.convertListToStringArray(
												customerManager.getAllCustomersList());
		manageWindow.updateCustomersTable(allCustomersInfoForTable);
	}
	
}
