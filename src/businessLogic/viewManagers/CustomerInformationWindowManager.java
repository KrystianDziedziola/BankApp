package businessLogic.viewManagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businessLogic.dataManagers.CustomerManager;
import dataLayer.Converter;
import dataLayer.Customer;
import dataLayer.CustomerInfoIndex;
import presentationLayer.CustomerInformationWindow;

public class CustomerInformationWindowManager {
	
	private CustomerInformationWindow customerInformationWindow = new CustomerInformationWindow();
	
	private CustomerManager customerManager;

	public CustomerInformationWindowManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
		
		defineCancelButtonAction();
		defineShowPasswordCheckBoxAction();
		defineFrameExitButtonAction();
	}
	public CustomerInformationWindowManager(CustomerManager customerManager, boolean isIdFieldEditable) {
		this(customerManager);
		customerInformationWindow.setIdFieldEditable(isIdFieldEditable);
	}
	
	public void show() {
		customerInformationWindow.show();

	}
	
	public void show(Customer customer) {
		customerInformationWindow.show(customer);
	}
	
	public void addAcceptButtonActionListener(ActionListener actionListener) {
		customerInformationWindow.addCustomerWindowAcceptButtonListener(actionListener);
	}
	
	public void defineAcceptAddButtonAction() {
		try {
			addCustomerToDatabase(customerInformationWindow.getCustomerInformation());
			customerInformationWindow.clear();
			customerInformationWindow.close();
		} catch(NumberFormatException e) {
			showInfoDialog("ID has to be a number.");
		} catch(SQLException e) {
			showInfoDialog("Customer with this ID already exists. Please choose another one.");
		}
		
	}
	
	public void defineAcceptChangeButtonAction() {
		ArrayList<String> customerInformation = customerInformationWindow.getCustomerInformation();
		Customer customer = Converter.convertStringListToCustomerObject(customerInformation);
		customerManager.update(customer);
		customerInformationWindow.clear();
		customerInformationWindow.close();
	}
	
	private void showInfoDialog(String message) {
		JOptionPane.showMessageDialog(customerInformationWindow.getFrame(), message);
	}

	private void addCustomerToDatabase(ArrayList<String> customerInfoList) 
			throws NumberFormatException, SQLException {
		customerManager.add(
				Long.parseLong(customerInfoList.get(CustomerInfoIndex.ID)),
				customerInfoList.get(CustomerInfoIndex.NAME),
				customerInfoList.get(CustomerInfoIndex.SURNAME),
				customerInfoList.get(CustomerInfoIndex.PASSWORD)
		);
	}
	
	private void defineCancelButtonAction() {
		customerInformationWindow.addCancelButtonListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				customerInformationWindow.close();
			}
			
		});
	}
	
	private void defineShowPasswordCheckBoxAction() {
		customerInformationWindow.addShowPasswordCheckBoxListenere(new ActionListener() {

			private char defaultEcho = customerInformationWindow.getPasswordFieldDefaultEchoChar();
			private char noEcho = (char)0;
			
			public void actionPerformed(ActionEvent arg0) {
				if(customerInformationWindow.isShowPasswordCheckBoxSelected()) {
					customerInformationWindow.setPasswordFieldEcho(noEcho);
				} else {
					customerInformationWindow.setPasswordFieldEcho(defaultEcho);
				}
			}
			
		});
	}
	
	private void defineFrameExitButtonAction() {
		customerInformationWindow.addWindowListenerToFrame(new ExitButtonListener());
	}
	
	private class ExitButtonListener extends WindowAdapter {
		
		@Override
		public void windowClosing(WindowEvent windowEvent) {
			customerInformationWindow.clear();
			customerInformationWindow.close();
		}
	}

}
