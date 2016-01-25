package businessLogic.viewManagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLogic.dataManagers.CustomerManager;
import dataLayer.Converter;
import dataLayer.Customer;
import dataLayer.LoginInformation;
import presentationLayer.ManageWindow;

public class ManageWindowManager {

	private ManageWindow manageWindow = new ManageWindow();
	private CustomerManager customerManager = new CustomerManager();
	
	private CustomerInformationWindowManager customerInformationWindowManager = 
		     new CustomerInformationWindowManager(customerManager);
	
	private LoginInformation loginInformation;
	
	private Customer currentlySelectedCustomer;
	
	public ManageWindowManager(LoginInformation loginInformation) {
		this.loginInformation = loginInformation;
		
		connectToDatabase();
		fillCustomersTable();
		
		defineAddCustomerButtonAction();
		defineChangeCustomerButtonAction();
		defineDeleteCustomerButtonAction();
		defineCustomerInformationWindowAcceptButtonAction();
		defineCustomersTableSelectAction();
	}
	
	public void show() {
		manageWindow.show();
	}
	
	private void defineAddCustomerButtonAction() {
		manageWindow.addAddCustomerButtonListener(new ModifyCustomerButtonActionListener());
	}
	
	private void defineChangeCustomerButtonAction() {
		manageWindow.addChangeCustomerButtonListener(new ModifyCustomerButtonActionListener());
	}
	
	private void defineDeleteCustomerButtonAction() {
		manageWindow.addDeleteCustomerButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerManager.deleteCustomerById(currentlySelectedCustomer.getUserId());
				fillCustomersTable();
			}
		});
	}
	
	private void defineCustomersTableSelectAction() {
		manageWindow.addCustomersTableSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				if(event.getValueIsAdjusting()) {
					currentlySelectedCustomer = Converter.convertStringListToCustomerObject(
							manageWindow.getCustomersTableSelectedRow());
				}
				enableChangeCustomerButtonAfterRowSelection();
			}

			private void enableChangeCustomerButtonAfterRowSelection() {
				if(!manageWindow.isChangeCustomerButtonEnabled()) {
					manageWindow.setChangeCustomerButtonEnabled(true);
				}
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
				fillCustomersTable();									//TODO: maybe change this to adding only one row
			}															//instead of clearing and filling everything
			
		});
	}
	
	private class ModifyCustomerButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			customerInformationWindowManager.show();
		}
		
	}
	
	private void fillCustomersTable() {
		String[][] allCustomersInfoForTable = Converter.convertCustomersListToTwoDimensionalStringArray(
												customerManager.getAllCustomersList());
		manageWindow.updateCustomersTable(allCustomersInfoForTable);
	}
	
}
