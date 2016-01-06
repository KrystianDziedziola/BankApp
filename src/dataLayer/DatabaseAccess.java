package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseAccess {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
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

	public void createCustomer(Customer customer) {
		saveCustomerInfo(customer);
		saveCustomerAddress(customer);
	}
	
	private void saveCustomerInfo(Customer customer) {
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO "+ databaseName + ".customers VALUES (?, ?, ?, ?)");
			setCustomerInfo(preparedStatement, customer);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setCustomerInfo(PreparedStatement preparedStatement, Customer customer) throws SQLException {
		preparedStatement = getPreparedStatementWithSettedCustomerInfo(customer, preparedStatement);
		preparedStatement.executeUpdate();
	}

	private PreparedStatement getPreparedStatementWithSettedCustomerInfo(
				Customer customer, PreparedStatement preparedStatement) throws SQLException {
		int columnNumber = 1;
		preparedStatement.setLong(columnNumber++, customer.getUserId());
		preparedStatement.setString(columnNumber++, customer.getName());
		preparedStatement.setString(columnNumber++, customer.getSurname());
		preparedStatement.setString(columnNumber++, customer.getPassword());
		return preparedStatement;
	}
	
	private void saveCustomerAddress(Customer customer) {
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO "+ databaseName + ".addresses VALUES (default, ?, ?, ?, ?)");
			setAddresInfo(preparedStatement, customer);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setAddresInfo(PreparedStatement preparedStatement, Customer customer) throws SQLException {
		preparedStatement = getPreparedStatementWithSettedAddress(customer, preparedStatement);
		preparedStatement.executeUpdate();
	}
	
	private PreparedStatement getPreparedStatementWithSettedAddress(
			Customer customer, PreparedStatement preparedStatement) throws SQLException {
		Address address = customer.getAddress();
		int columnNumber = 1;
		preparedStatement.setString(columnNumber++, address.getStreet());
		preparedStatement.setString(columnNumber++, address.getCity());
		preparedStatement.setString(columnNumber++, address.getPostCode());
		preparedStatement.setLong(columnNumber++, customer.getUserId());
		return preparedStatement;
	}

	public Customer findCustomerById(long customerId) {
		try {
			resultSet = statement.executeQuery(
					"SELECT * FROM " + databaseName + ".customers WHERE USER_ID = " + customerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return convertResultSetToCustomerObject(resultSet);
	}

	private Customer convertResultSetToCustomerObject(ResultSet resultSet) {
		try {
			resultSet.next();
			Long userId = resultSet.getLong("user_id");
			String name = resultSet.getString("name");
			String surname = resultSet.getString("surname");
			String password = resultSet.getString("password");
			return new Customer(userId, name, surname, password, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

	public void deleteCustomerById(long customerId) {
		try {
			preparedStatement = connection.prepareStatement(
					"DELETE FROM " + databaseName + ".customers WHERE USER_ID = " + customerId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCustomerInformation(Customer customer) {
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE " + databaseName + ".customers SET " + 
					"user_id = ?, name = ?, surname = ?, password = ? WHERE USER_ID = " + customer.getUserId());
			setCustomerInfo(preparedStatement, customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Customer getCurrentCustomerInformation(Customer customer) {
		try {
			resultSet = statement.executeQuery(
					"SELECT * FROM " + databaseName + ".customers WHERE USER_ID = " + customer.getUserId());
			return convertResultSetToCustomerObject(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//TODO: add address
		return null;
	}

	public ArrayList<Customer> getAllCustomersList() {
		try {
			resultSet = statement.executeQuery("SELECT * FROM " + databaseName + ".customers");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getAllCustomersListFromResultSet(resultSet);
	}
	
	private ArrayList<Customer> getAllCustomersListFromResultSet(ResultSet resultSet) {
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();
		try {
			while(resultSet.next()) {
				Long userId = resultSet.getLong("user_id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String password = resultSet.getString("password");
				Customer customer = new Customer(userId, name, surname, password, null);
				// TODO: add address
				allCustomers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCustomers;
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

	public void createBankAccount(BankAccount bankAccount) {
		// TODO Auto-generated method stub
	}

	public BankAccount findBankAccount(long accountNumberToFind) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteBankAccount(long accountNumberToDelete) {
		// TODO Auto-generated method stub
		
	}

}
