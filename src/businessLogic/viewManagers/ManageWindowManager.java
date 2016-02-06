package businessLogic.viewManagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLogic.dataManagers.BankAccountManager;
import businessLogic.dataManagers.CustomerManager;
import dataLayer.BankAccount;
import dataLayer.Converter;
import dataLayer.Customer;
import dataLayer.LoginInformation;
import presentationLayer.ManageWindow;

public class ManageWindowManager {

	private ManageWindow manageWindow = new ManageWindow();
	private CustomerManager customerManager = new CustomerManager();
	private BankAccountManager bankAccountManager = new BankAccountManager();
	
	private CustomerInformationWindowManager addCustomerInformationWindowManager = 
		     new CustomerInformationWindowManager(customerManager);
	
	private CustomerInformationWindowManager changeCustomerInformationWindowManager = 
		     new CustomerInformationWindowManager(customerManager, false);
	
	private LoginInformation loginInformation;
	
	private Customer currentlySelectedCustomer;
	
	public ManageWindowManager(LoginInformation loginInformation) {
		this.loginInformation = loginInformation;
		connectToDatabase();
		fillCustomersTable();
		defineComponentsActions();
	}
	
	public void show() {
		manageWindow.show();
	}
	
	private void connectToDatabase() {
		try {
			customerManager.connect(loginInformation);
			bankAccountManager.connect(loginInformation);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void fillCustomersTable() {
		String[][] allCustomersInfoForTable = Converter.convertCustomersListToTwoDimensionalStringArray(
												customerManager.getAllCustomersList());
		manageWindow.updateCustomersTable(allCustomersInfoForTable);
	}
	
	private void defineComponentsActions() {
		defineFrameExitButtonAction();
		defineAddCustomerButtonAction();
		defineChangeCustomerButtonAction();
		defineDeleteCustomerButtonAction();
		defineCustomerInformationWindowAcceptButtonAction();
		defineCustomerInformationWindowChangeButtonAction();
		defineCustomersTableSelectAction();
	}
	
	private void defineFrameExitButtonAction() {
		manageWindow.addWindowListenerToFrame(new ExitWindowListener());
	}
	
	private void defineAddCustomerButtonAction() {
		manageWindow.addAddCustomerButtonListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				addCustomerInformationWindowManager.show();
			}
			
		});
	}
	
	private void defineChangeCustomerButtonAction() {
		manageWindow.addChangeCustomerButtonListener(new ChangeCustomerButtonListener());
	}
	
	private void defineDeleteCustomerButtonAction() {
		manageWindow.addDeleteCustomerButtonListener(new DeleteCustomerButtonListener());
	}
	
	private void defineCustomersTableSelectAction() {
		manageWindow.addCustomersTableSelectionListener(new CustomerSelectedListener());
	}
	
	private void defineCustomerInformationWindowAcceptButtonAction() {
		addCustomerInformationWindowManager.addAcceptButtonActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				addCustomerInformationWindowManager.defineAcceptAddButtonAction();
				fillCustomersTable();									
			}															
			
		});
	}
	
	private void defineCustomerInformationWindowChangeButtonAction() {
		changeCustomerInformationWindowManager.addAcceptButtonActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				changeCustomerInformationWindowManager.defineAcceptChangeButtonAction();
				fillCustomersTable();									
			}															
			
		});
	}
	
	private class ExitWindowListener extends WindowAdapter {
		
		@Override
		public void windowClosing(WindowEvent windowEvent) {
			if (isExitingConfirmedInDialog()){
		       	customerManager.closeConnection();
		       	bankAccountManager.closeConnection();
		       	System.exit(0);
		    }
		}

		private boolean isExitingConfirmedInDialog() {
			return getAnswerFromExitDialog() == JOptionPane.YES_OPTION ? true : false;
		}

		private int getAnswerFromExitDialog() {
			return JOptionPane.showConfirmDialog(manageWindow.getFrame(), "Are you sure to close this window?",
					"Exit Bank App", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		}
	}
	
	private class ChangeCustomerButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> selectedCustomerInfo = manageWindow.getCustomersTableSelectedRow();
			Customer selectedCustomer = Converter.convertStringListToCustomerObject(selectedCustomerInfo);
			
			changeCustomerInformationWindowManager.show(selectedCustomer);
		}

	}
	
	private class DeleteCustomerButtonListener implements ActionListener {
		
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
					"Delete", JOptionPane.YES_NO_OPTION);
		}
		
	}
	
	private class CustomerSelectedListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent event) {
			if(event.getValueIsAdjusting()) {
				currentlySelectedCustomer = Converter.convertStringListToCustomerObject(
						manageWindow.getCustomersTableSelectedRow());
					
				showCurrentlySelectedCustomerAddressInTable();
				showCurrentlySelectedCustomerBankAccountsInTable();
				enableChangeAndDeleteCustomerButtonAfterRowSelection();
				enableAppropriateAddressButtons();
			}
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
			
		private void enableAppropriateAddressButtons() {
			if(manageWindow.isAddressTableEmpty()) {
				manageWindow.setAddAddressButtonEnabled(true);
				manageWindow.setChangeAddressButtonEnabled(false);
				manageWindow.setDeleteAddressButtonEnabled(false);
			} else {
				manageWindow.setAddAddressButtonEnabled(false);
				manageWindow.setChangeAddressButtonEnabled(true);
				manageWindow.setDeleteAddressButtonEnabled(true);
			}
		}
			
		private void showCurrentlySelectedCustomerAddressInTable() {
			Customer customer = customerManager.findById(currentlySelectedCustomer.getUserId());
			
			String[] currentlySelectedCustomerAddress = Converter.convertCustomerAddressToStringArray(
					customer.getAddress());
			manageWindow.setAddressTableContent(currentlySelectedCustomerAddress);
		}
			
		private void showCurrentlySelectedCustomerBankAccountsInTable() {
			ArrayList<BankAccount> allBankAccounts = getCurrentCustomerBankAccountsList();
			String[][] allBankAccountsArray = Converter.convertBankAccountsListToTwoDimensionalArray(allBankAccounts);
			manageWindow.setBankAccountsTableContent(allBankAccountsArray);
		}

		private ArrayList<BankAccount> getCurrentCustomerBankAccountsList() {
			return bankAccountManager.getCustomerAllBankAccounts(currentlySelectedCustomer.getUserId());
		}
			
	}
	
}
