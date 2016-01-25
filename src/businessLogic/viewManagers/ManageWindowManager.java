package businessLogic.viewManagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLogic.dataManagers.CustomerManager;
import dataLayer.Converter;
import dataLayer.Customer;
import dataLayer.LoginInformation;
import presentationLayer.ManageWindow;

public class ManageWindowManager {

	protected static final int YES_CANCEL_OPTION = 0;
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
				if(isDeletingAccepted()) {
					customerManager.deleteCustomerById(currentlySelectedCustomer.getUserId());
					fillCustomersTable();
				}
			}

			private boolean isDeletingAccepted() {
				final int YES_BUTTON_ID = 0;
				if(getAnswer() == YES_BUTTON_ID) {
					return true;
				} else {
					return false;
				}
			}

			private int getAnswer() {
				return JOptionPane.showConfirmDialog(manageWindow.getFrame(), 
						"Are you sure that you want to delete this customer?", 
						"Delete", YES_CANCEL_OPTION);
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
				enableChangeAndDeleteCustomerButtonAfterRowSelection();
			}

			private void enableChangeAndDeleteCustomerButtonAfterRowSelection() {
				if(manageWindow.isAnyRowInCustomersTableSelected()) {
					manageWindow.setChangeCustomerButtonEnabled(true);
					manageWindow.setDeleteCustomerButtonEnabler(true);
				} else {
					manageWindow.setChangeCustomerButtonEnabled(false);
					manageWindow.setDeleteCustomerButtonEnabler(false);
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
