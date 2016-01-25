package businessLogic.viewManagers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import businessLogic.dataManagers.CustomerManager;
import dataLayer.CustomerInfoIndex;
import presentationLayer.CustomerInformationWindow;

public class CustomerInformationWindowManager {
	
	private CustomerInformationWindow customerInformationWindow = new CustomerInformationWindow();
	
	private CustomerManager customerManager;

	public CustomerInformationWindowManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
		
		defineCancelButtonAction();
		defineShowPasswordCheckBoxAction();
	}
	
	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerInformationWindow.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void addAcceptButtonActionListener(ActionListener actionListener) {
		customerInformationWindow.addCustomerWindowAcceptButtonListener(actionListener);
	}
	
	public void defineAcceptButtonAction() {
		addCustomerToDatabase(customerInformationWindow.getCustomerInformation()); //TODO: add exceptions
		customerInformationWindow.clear();
		customerInformationWindow.close();
	}

	private void addCustomerToDatabase(ArrayList<String> customerInfoList) {
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

}
