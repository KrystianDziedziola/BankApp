package dataLayer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataLayer.Address;
import dataLayer.Customer;

public class CustomerMySqlDao implements CustomerDaoInterface {
	
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

	public void create(Customer customer) {
		saveCustomerInfo(customer);
		saveCustomerAddress(customer);
	}

	public Customer find(long customerId) {
		Customer customer = getCustomer(customerId);
		Address address = getAddress(customerId);
		customer.setAddress(address);
		return customer;
	}

	public void delete(long customerId) {
		try {
			preparedStatement = connection.prepareStatement(
					"DELETE FROM " + databaseName + ".customers WHERE USER_ID = " + customerId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Customer customer) {
		try {
			updateCustomer(customer);
			updateAddress(customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Customer getCurrentInformation(Customer customer) {
		return find(customer.getUserId());
	}

	//FIXME:returns only one, last customer
	public ArrayList<Customer> getAllCustomersList() {
		try {
			ArrayList<Customer> allCustomersList = new ArrayList<Customer>();
			resultSet = statement.executeQuery("SELECT * FROM " + databaseName + ".customers");
			while(resultSet.next()) {
				Long userId = resultSet.getLong("user_id");
				allCustomersList.add(find(userId));
			}
			return allCustomersList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
			setAddressInfo(preparedStatement, customer);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setAddressInfo(PreparedStatement preparedStatement, Customer customer) throws SQLException {
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

	private Customer getCustomer(long customerId) {
		try {
			resultSet = statement.executeQuery(
					"SELECT * FROM " + databaseName + ".customers WHERE user_id = " + customerId);	
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
	
	private Address getAddress(long customerId) {
		try {
			resultSet = statement.executeQuery(
					"SELECT * FROM " + databaseName + ".addresses WHERE user_id = " + customerId);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return convertResultSetToAddressObject(resultSet);
	}

	private Address convertResultSetToAddressObject(ResultSet resultSet) {
		try {
			resultSet.next();
			String street = resultSet.getString("street");
			String city = resultSet.getString("city");
			String postcode = resultSet.getString("postcode");
			return new Address(street, city, postcode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void updateCustomer(Customer customer) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"UPDATE " + databaseName + ".customers SET " + 
				"user_id = ?, name = ?, surname = ?, password = ? WHERE USER_ID = " + customer.getUserId());
		setCustomerInfo(preparedStatement, customer);
	}
	
	private void updateAddress(Customer customer) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"UPDATE " + databaseName + ".addresses SET " + 
				"id = default, street = ?, city = ?, postcode = ?, user_id = ? WHERE USER_ID = " + customer.getUserId());
		setAddressInfo(preparedStatement, customer);
	}
	
}
