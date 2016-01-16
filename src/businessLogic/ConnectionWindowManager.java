package businessLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataLayer.LoginInformation;
import dataLayer.dao.MySqlDao;
import presentationLayer.ConnectionWindow;

public class ConnectionWindowManager {
	
	ConnectionWindow connectionWindow = new ConnectionWindow();
	MySqlDao mySqlDao = new MySqlDao();

	public ConnectionWindowManager() {
		defineConnectButtonAction();
	}
	
	public void show() {
		connectionWindow.show();
	}

	private void defineConnectButtonAction() {
		connectionWindow.addConnectButtonListener(new ConnectButtonListenener());
	}
	
	private class ConnectButtonListenener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			LoginInformation loginInformation = new LoginInformation(
					connectionWindow.getLogin(), connectionWindow.getPassword());
			try {
				mySqlDao.connect(loginInformation);
				connectionWindow.displayMessageDialog("Connected", "You've successfully connected to database.");
				mySqlDao.closeConnection();
				connectionWindow.close();
			} catch (Exception e) {
				connectionWindow.displayMessageDialog("Connection error", "You've put wrong login information or MySql database server is not running.");
			}
		}
		
	}
}
