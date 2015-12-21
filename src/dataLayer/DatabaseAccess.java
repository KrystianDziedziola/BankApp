package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseAccess {
	
	private Connection connection;
	private Statement statement;
	
	 private PreparedStatement preparedStatement;
	
	private String databaseName = "BankAppDatabase";
	private String user = "user";
	private String password = "logitech1";
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/" + databaseName + "?user=" + user + "&password=" + password);
			
			statement = connection.createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public void create(Customer customer) {
		try {
			preparedStatement = connection.prepareStatement(
					"insert into "+ databaseName + ".customers values (?, ?, ?, ?)");
			preparedStatement.setLong(1, customer.getUserId());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getSurname());
			preparedStatement.setString(4, customer.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Customer findCustomerById(long customerId) {
		return null;
	}

	public void deleteCustomerById(long customerId) {
		try {
			preparedStatement = connection.prepareStatement(
					"delete from " + databaseName + ".customers where USER_ID= " + customerId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCustomerInfo() {
		
	}

	public ArrayList<Customer> getAllCustomersList() {
		return null;
	}
	
	public void closeConnection() {
		try {
			if(connection != null) {
			connection.close();
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

}
