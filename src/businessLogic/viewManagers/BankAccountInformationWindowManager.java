package businessLogic.viewManagers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public void show(BankAccount bankAccount) {
		bankAccountInformationWindow.show(bankAccount);
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

	public void addAcceptButtonListener(ActionListener actionListener) {
		bankAccountInformationWindow.addAcceptButtonActionListener(actionListener);
	}

	public BankAccount getBankAccount() throws NumberFormatException{
		Long accountNumber = Long.parseLong(bankAccountInformationWindow.getAccountNumber());
		Long balance = Long.parseLong(bankAccountInformationWindow.getBalance());
		return new BankAccount(accountNumber, balance);
	}

	
	private void defineCancelButtonAction() {
		bankAccountInformationWindow.addCancelButtonActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clearAndClose();
			}
			
		});
	}

}
