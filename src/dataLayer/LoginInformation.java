package dataLayer;

public class LoginInformation {

	private String login;
	private String password;
	
	public LoginInformation(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
}
