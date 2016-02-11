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
import dataLayer.Address;
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
	
	private AddressInformationWindowManager addAddressInformationWindowManager = 
			new AddressInformationWindowManager();
	private AddressInformationWindowManager changeAddressInformationWindowManager = 
			new AddressInformationWindowManager();
	
	private BankAccountInformationWindowManager addBankAccountInformationWindowManager = 
			new BankAccountInformationWindowManager();
	
	private LoginInformation loginInformation;
	
	private Customer currentlySelectedCustomer;
	private BankAccount currentlySelectedBankAccount;
	
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
		defineMainWindowComponentsActions();
		defineInformationWindowsComponentsActions();
	}
	
	private void defineMainWindowComponentsActions() {
		defineFrameExitButtonAction();
		defineCustomerButtonsActions();
		defineCustomersTableSelectAction();
		defineAddressButtonsActions();
		defineBankAccountButtonsActions();
		defineBankAccountsTableSelectAction();
	}
	
	private void defineInformationWindowsComponentsActions() {
		defineCustomerInformationWindowButtonsActions();
		defineAddressInformationWindowButtonsActions();
		defineBankAccountInformationWindowButtonsActions();
	}

	private void defineCustomerButtonsActions() {
		defineAddCustomerButtonAction();
		defineChangeCustomerButtonAction();
		defineDeleteCustomerButtonAction();
	}
	
	private void defineCustomerInformationWindowButtonsActions() {
		defineCustomerInformationWindowAcceptButtonAction();
		defineCustomerInformationWindowChangeButtonAction();
	}
	
	private void defineAddressInformationWindowButtonsActions() {
		defineAddAddressInformationWindowAcceptButtonAction();
		defineChangeAddressInformationWindowAcceptButtonAction();
	}

	private void defineAddressButtonsActions() {
		defineAddressAddButtonAction();
		definceAddressChangeButtonAction();
		defineAddressDeleteButtonAction();
	}
	
	private void defineAddressAddButtonAction() {
		manageWindow.addAddAddressButtonListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				addAddressInformationWindowManager.show();
			}
			
		});
	}
	
	private void definceAddressChangeButtonAction() {
		manageWindow.addChangeAddressButtonListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				changeAddressInformationWindowManager.show(currentlySelectedCustomer.getAddress());
			}
			
		});
	}

	private void defineAddressDeleteButtonAction() {
		manageWindow.addDeleteAddressButtonListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(getAnswer() == JOptionPane.YES_OPTION) {
					delete();
				}
			}
			
			private void delete() {
				customerManager.deleteAddress(currentlySelectedCustomer.getUserId());
				manageWindow.clearAddressTable();
				enableOnlyAddAddressButton();
			}

			private int getAnswer() {
				return JOptionPane.showConfirmDialog(manageWindow.getFrame(), 
						"Are you sure that you want to delete this address?", 
						"Delete", JOptionPane.YES_NO_OPTION);
			}
		});
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
	
	private void defineAddAddressInformationWindowAcceptButtonAction() {
		addAddressInformationWindowManager.addAcceptAddressButtonActionListener(new ActionListener() {

			private Address address;
			
			public void actionPerformed(ActionEvent e) {
				addAddress();
				updateTables();
				enableOnlyChangeAndDeleteAddressButtons();
				closeAndClearWindow();
			}

			private void addAddress() {
				address = addAddressInformationWindowManager.getAddress();
				long customerId = currentlySelectedCustomer.getUserId();
				customerManager.addAddress(address, customerId);
			}
			
			private void updateTables() {
				fillAddressTable(address);
			}
			
			private void closeAndClearWindow() {
				addAddressInformationWindowManager.clear();
				addAddressInformationWindowManager.close();
			}

		});
	}
	
	private void defineChangeAddressInformationWindowAcceptButtonAction() {
		changeAddressInformationWindowManager.addAcceptAddressButtonActionListener(new ActionListener() {

			Address address;
			
			public void actionPerformed(ActionEvent e) {
				updateAddress();
				clearAndCloseWindow();
				updateTable();
			}

			private void updateAddress() {
				address = changeAddressInformationWindowManager.getAddress();
				customerManager.updateAddress(address, currentlySelectedCustomer.getUserId());
				currentlySelectedCustomer.setAddress(address);
			}
			
			private void clearAndCloseWindow() {
				changeAddressInformationWindowManager.clear();
				changeAddressInformationWindowManager.close();
			}
			
			private void updateTable() {
				manageWindow.setAddressTableContent(Converter.convertCustomerAddressToStringArray(address));
				
			}
			
		});
	}
	
	private void fillAddressTable(Address address) {
		String[] addressInformation = Converter.convertCustomerAddressToStringArray(address);
		manageWindow.setAddressTableContent(addressInformation);
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
				if(!manageWindow.isAddressTableEmpty()) {
					enableOnlyAddAddressButton();
					manageWindow.clearAddressTable();
				}
				
			}
		}

		private boolean isDeletingAccepted() {
			return getAnswer() == JOptionPane.YES_OPTION ? true : false;
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
				addAddressToCurrentlySelectedCustomer();	
				showCurrentlySelectedCustomerAddressInTable();
				showCurrentlySelectedCustomerBankAccountsInTable();
				enableChangeAndDeleteCustomerButtonAfterRowSelection();
				enableAppropriateAddressButtons();
				enableOnlyAddBankAccountButton();
			}
		}
		
		private void addAddressToCurrentlySelectedCustomer() {
			Customer customer = customerManager.findById(currentlySelectedCustomer.getUserId());
			currentlySelectedCustomer.setAddress(customer.getAddress());
		}

		private void showCurrentlySelectedCustomerAddressInTable() {
			String[] currentlySelectedCustomerAddress = Converter.convertCustomerAddressToStringArray(
					currentlySelectedCustomer.getAddress());
			manageWindow.setAddressTableContent(currentlySelectedCustomerAddress);
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
			
	}
	
	private void showCurrentlySelectedCustomerBankAccountsInTable() {
		ArrayList<BankAccount> allBankAccounts = getCurrentCustomerBankAccountsList();
		String[][] allBankAccountsArray = Converter.convertBankAccountsListToTwoDimensionalArray(allBankAccounts);
		manageWindow.setBankAccountsTableContent(allBankAccountsArray);
	}
	
	private ArrayList<BankAccount> getCurrentCustomerBankAccountsList() {
		return bankAccountManager.getCustomerAllBankAccounts(currentlySelectedCustomer.getUserId());
	}
	
	private void enableAppropriateAddressButtons() {
		if(manageWindow.isAddressTableEmpty()) {
			enableOnlyAddAddressButton();
		} else {
			enableOnlyChangeAndDeleteAddressButtons();
		}
	}
	
	private void enableOnlyAddBankAccountButton() {
		manageWindow.setAddBankAccountButtonEnabled(true);
		manageWindow.setChangeBankAccountButtonEnabled(false);
		manageWindow.setDeleteBankAccountButtonEnabled(false);
		
	}
	
	private void enableOnlyAddAddressButton() {
		manageWindow.setAddAddressButtonEnabled(true);
		manageWindow.setChangeAddressButtonEnabled(false);
		manageWindow.setDeleteAddressButtonEnabled(false);
	}
	
	private void enableOnlyChangeAndDeleteAddressButtons() {
		manageWindow.setAddAddressButtonEnabled(false);
		manageWindow.setChangeAddressButtonEnabled(true);
		manageWindow.setDeleteAddressButtonEnabled(true);
	}
	
	private void defineBankAccountButtonsActions() {
		defineAddBankAccountButtonAction();
		defineDeleteBankAccountButtonAction();
	}
	
	private void defineAddBankAccountButtonAction() {
		manageWindow.addAddBankAccountButtonListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				addBankAccountInformationWindowManager.show();
			}
			
		});
	}
	
	private void defineDeleteBankAccountButtonAction() {
		manageWindow.addDeleteBankAccountButtonListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(getAnswer() == JOptionPane.OK_OPTION) {
					bankAccountManager.delete(currentlySelectedBankAccount.getAccountNumber());
					showCurrentlySelectedCustomerBankAccountsInTable();
					enableOnlyAddBankAccountButton();
				}
			}
			
			private int getAnswer() {
				return JOptionPane.showConfirmDialog(manageWindow.getFrame(), 
						"Are you sure that you want to delete this bank account?", 
						"Delete", JOptionPane.YES_NO_OPTION);
			}
			
		});
	}
	
	private void defineBankAccountInformationWindowButtonsActions() {
		defineAddBankAccountInformationWindowAcceptButton();
	}
	
	private void defineAddBankAccountInformationWindowAcceptButton() {
		addBankAccountInformationWindowManager.defineAcceptButtonAction(new ActionListener() {

			private BankAccount bankAccount;
			
			public void actionPerformed(ActionEvent e) {
				try {
					bankAccount = addBankAccountInformationWindowManager.getBankAccount();
					bankAccountManager.create(bankAccount.getAccountNumber(), 
							bankAccount.getBalance(), currentlySelectedCustomer.getUserId());
					manageWindow.addToBankAccountsTable(Converter.convertBankAccountToArray(bankAccount));
					addBankAccountInformationWindowManager.clearAndClose();
				} catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(addBankAccountInformationWindowManager.getFrame(),
							"Both values have to be a number.");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(addBankAccountInformationWindowManager.getFrame(), 
							"Account with this number already exists. Please choose another one.");
				}
			}
			
		});
	}
	
	private void defineBankAccountsTableSelectAction() {
		manageWindow.addBankAccountsTableSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent event) {
				if(event.getValueIsAdjusting()) {
					enableChangeAndDeleteBankAccountButtons();
					currentlySelectedBankAccount = getSelectedBankAccount();
				}
			}

			private BankAccount getSelectedBankAccount() {
				return Converter.convertStringListToBankAccountObject(
						manageWindow.getBankAccountsTableSelectedRow());
			}
		});
		
	}
	
	private void enableChangeAndDeleteBankAccountButtons() {
		manageWindow.setChangeBankAccountButtonEnabled(true);
		manageWindow.setDeleteBankAccountButtonEnabled(true);
	}
	
}
