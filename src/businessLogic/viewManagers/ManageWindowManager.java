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

import businessLogic.dataManagers.CustomerManager;
import dataLayer.Converter;
import dataLayer.Customer;
import dataLayer.LoginInformation;
import presentationLayer.ManageWindow;

public class ManageWindowManager {

	protected static final int YES_CANCEL_OPTION = 0;
	private ManageWindow manageWindow = new ManageWindow();
	private CustomerManager customerManager = new CustomerManager();
	
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
		
		defineFrameExitButtonAction();
		defineAddCustomerButtonAction();
		defineChangeCustomerButtonAction();
		defineDeleteCustomerButtonAction();
		defineCustomerInformationWindowAcceptButtonAction();
		defineCustomerInformationWindowChangeButtonAction();
		defineCustomersTableSelectAction();
	}
	
	public void show() {
		manageWindow.show();
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
	
	private void defineFrameExitButtonAction() {
		manageWindow.addWindowListenerToFrame(new ExitWindowListener());
	}
	
	private void defineAddCustomerButtonAction() {
		manageWindow.addAddCustomerButtonListener(new AddCustomerButtonActionListener());
	}
	
	private void defineChangeCustomerButtonAction() {
		manageWindow.addChangeCustomerButtonListener(new ChangeCustomerButtonActionListener());
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
	
	private void fillCustomersTable() {
		String[][] allCustomersInfoForTable = Converter.convertCustomersListToTwoDimensionalStringArray(
												customerManager.getAllCustomersList());
		manageWindow.updateCustomersTable(allCustomersInfoForTable);
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
	
	private class AddCustomerButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			addCustomerInformationWindowManager.show();
		}
		
	}
	
	private class ChangeCustomerButtonActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> selectedCustomerInfo = manageWindow.getCustomersTableSelectedRow();
			Customer selectedCustomer = Converter.convertStringListToCustomerObject(selectedCustomerInfo);
			
			changeCustomerInformationWindowManager.show(selectedCustomer);
		}

	}
	
}