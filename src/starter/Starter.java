package starter;

import java.awt.EventQueue;

import businessLogic.ConnectionWindowManager;

public class Starter {

	public static void main(String[] args) {
		new Starter();
	}
	
	public Starter() {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					new ConnectionWindowManager().show();
			}
		});
	}	

}