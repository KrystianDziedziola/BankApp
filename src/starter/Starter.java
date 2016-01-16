package starter;

import businessLogic.ConnectionWindowManager;

public class Starter {

	public static void main(String[] args) {
		new Starter();
	}
	
	public Starter() {		
		try {
			new ConnectionWindowManager().show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}