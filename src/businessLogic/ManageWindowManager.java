package businessLogic;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import dataLayer.Converter;
import dataLayer.LoginInformation;
import presentationLayer.CustomerInformationWindow;
import presentationLayer.ManageWindow;

public class ManageWindowManager {

	private ManageWindow manageWindow = new ManageWindow();
	private CustomerManager customerManager = new CustomerManager();
	
	private LoginInformation loginInformation;
	
	public ManageWindowManager(LoginInformation loginInformation) {
		this.loginInformation = loginInformation;
		
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
		manageWindow.addAddCustomerButtonListener(new ModifyCustomerButtonActionListener());
	}
	
	public void defineChangeCustomerButtonAction() {
		manageWindow.addChangeCustomerButtonListener(new ModifyCustomerButtonActionListener());
	}
	
	public void defineDeleteCustomerButtonAction() {
		manageWindow.addDeleteCustomerButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	
	private void sendCustomersInformationForTable() {
		String[][] allCustomersInfoForTable = Converter.convertListToStringArray(
												customerManager.getAllCustomersList());
		manageWindow.putCustomersInfoIntoTable(allCustomersInfoForTable);
	}
	
	private class ModifyCustomerButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			showCustomerInformationWindow();
		}

		private void showCustomerInformationWindow() {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new CustomerInformationWindow().show();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
	}

}
