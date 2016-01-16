package dataLayer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dataLayer.LoginInformation;

public class MySqlDao {

	protected Connection connection;
	protected Statement statement;
	protected PreparedStatement preparedStatement;
	protected ResultSet resultSet;
	
	protected String databaseName = "BankAppDatabase";
	
	public void connect(LoginInformation loginInformation) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/" + databaseName + "?user=" + loginInformation.getLogin() + 
				"&password=" + loginInformation.getPassword()
		);
		statement = connection.createStatement();
	}
	
	public void closeConnection() {
		try {
			if(connection != null) {
			connection.close();
			}
			if(resultSet != null) {
				resultSet.close();
			}
			if(statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
}
