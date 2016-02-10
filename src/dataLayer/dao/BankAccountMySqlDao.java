package dataLayer.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataLayer.BankAccount;

public class BankAccountMySqlDao extends MySqlDao implements BankAccountDaoInterface {
	
	public void create(BankAccount bankAccount, long userId) throws SQLException {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO "+ databaseName + ".bank_accounts VALUES (?, ?, ?)");
			setBankAccountInfo(bankAccount, preparedStatement);		
	}

	public BankAccount find(long accountNumber) {
		try {
			resultSet = statement.executeQuery(
					"SELECT * FROM " + databaseName + ".bank_accounts WHERE account_number = " + accountNumber);
			return convertResultSetToBankAccountObject(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

	public void delete(long accountNumber) {
		try {
			preparedStatement = connection.prepareStatement(
					"DELETE FROM " + databaseName + ".bank_accounts WHERE account_number = " + accountNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(BankAccount bankAccount) {
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE " + databaseName + ".bank_accounts SET " + 
					"account_number = ?, balance = ?, user_id = ? WHERE account_number = " + bankAccount.getAccountNumber());
			setBankAccountInfo(bankAccount, preparedStatement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public ArrayList<BankAccount> getAllAccounts(long userId) {
		try {
			ResultSet resultSet = statement.executeQuery(
					"SELECT * FROM " + databaseName + ".bank_accounts WHERE user_id = " + userId);
			return convertResultSetToBankAccountsList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList<BankAccount> convertResultSetToBankAccountsList(ResultSet resultSet) throws SQLException {
		ArrayList<BankAccount> allBankAccountsList = new ArrayList<BankAccount>();
		while(resultSet.next()) {
			allBankAccountsList.add(getSingleBankAccountFromResultSet(resultSet));
		}
		return allBankAccountsList;
	}

	private BankAccount getSingleBankAccountFromResultSet(ResultSet resultSet) throws SQLException {
		long accountNumber = resultSet.getLong("account_number");
		long balance = resultSet.getLong("balance");
		long owner = resultSet.getLong("user_id");
		return new BankAccount(accountNumber, balance, owner);
	}

	private void setBankAccountInfo(BankAccount bankAccount, PreparedStatement preparedStatement) 
			throws SQLException {
		preparedStatement = getPreparedStatementWithSettedBankAccountInfo(bankAccount, preparedStatement);
		preparedStatement.executeUpdate();
	}

	private PreparedStatement getPreparedStatementWithSettedBankAccountInfo(
				BankAccount bankAccount, PreparedStatement preparedStatement) throws SQLException {
		int columnNumber = 1;
		preparedStatement.setLong(columnNumber++, bankAccount.getAccountNumber());
		preparedStatement.setLong(columnNumber++, bankAccount.getBalance());
		preparedStatement.setLong(columnNumber++, bankAccount.getOwnerId());
		return preparedStatement;
	}
	
	private BankAccount convertResultSetToBankAccountObject(ResultSet resultSet) throws SQLException{
		resultSet.next();
		Long accountNumber = resultSet.getLong("account_number");
		Long balance = resultSet.getLong("balance");
		Long userId = resultSet.getLong("user_id");
		return new BankAccount(accountNumber, balance, userId);
	}

}
