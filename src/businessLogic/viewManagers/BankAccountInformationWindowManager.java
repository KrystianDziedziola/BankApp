package businessLogic.viewManagers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dataLayer.BankAccount;
import presentationLayer.BankAccountInformationWindow;

public class BankAccountInformationWindowManager {
	
	BankAccountInformationWindow bankAccountInformationWindow = new BankAccountInformationWindow();
	
	public BankAccountInformationWindowManager() {
		defineCancelButtonAction();
	}
	
	public void show() {
		bankAccountInformationWindow.show();
	}
	
	public void close() {
		bankAccountInformationWindow.close();
	}
	
	public void clearAndClose() {
		clear();
		close();
	}
	
	public Component getFrame() {
		return bankAccountInformationWindow.getFrame();
	}

	private void clear() {
		bankAccountInformationWindow.clear();
	}

	public void defineAcceptButtonAction(ActionListener actionListener) {
		bankAccountInformationWindow.addAcceptButtonActionListener(actionListener);
	}

	public BankAccount getBankAccount() {
		try {
			Long accountNumber = Long.parseLong(bankAccountInformationWindow.getAccountNumber());
			Long balance = Long.parseLong(bankAccountInformationWindow.getBalance());
			return new BankAccount(accountNumber, balance);
		} catch(NumberFormatException e) {
			showWrongInputWindow();
			return null;
		}
		
	}

	private void showWrongInputWindow() {
		String message = "Both values have to be a number.";
		JOptionPane.showMessageDialog(bankAccountInformationWindow.getFrame(), message);
	}
	
	private void defineCancelButtonAction() {
		bankAccountInformationWindow.addCancelButtonActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clearAndClose();
			}
			
		});
	}

}
