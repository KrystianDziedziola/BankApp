package dataLayer.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataLayer.BankAccount;

public class BankAccountMySqlDao extends MySqlDao implements BankAccountDaoInterface {
	
	public void create(BankAccount bankAccount, long userId) {
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO "+ databaseName + ".bank_accounts VALUES (?, ?, ?)");
			setBankAccountInfo(bankAccount, preparedStatement);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
