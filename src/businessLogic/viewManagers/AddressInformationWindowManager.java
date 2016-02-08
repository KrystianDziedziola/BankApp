package businessLogic.viewManagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataLayer.Address;
import presentationLayer.AddressInformationWindow;

public class AddressInformationWindowManager {

	private AddressInformationWindow addressInformationWindow = new AddressInformationWindow();
	
	public AddressInformationWindowManager() {
		defineCancelButtonAction();
	}
	
	public void show() {
		addressInformationWindow.show();
	}
	
	public void close() {
		addressInformationWindow.close();
	}
	
	private void defineCancelButtonAction() {
		addressInformationWindow.addCancelButtonActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				addressInformationWindow.getFrame().dispose();
			}
			
		});
	}

	public void addAcceptAddressButtonActionListener(ActionListener actionListener) {
		addressInformationWindow.addAcceptButtonActionListener(actionListener);
	}

	public Address getAddress() {
		String street = addressInformationWindow.getStreet();
		String city = addressInformationWindow.getCity();
		String postcode = addressInformationWindow.getPostcode();
		return new Address(street, city, postcode);
	}

	public void clear() {
		addressInformationWindow.clear();
	}

}
