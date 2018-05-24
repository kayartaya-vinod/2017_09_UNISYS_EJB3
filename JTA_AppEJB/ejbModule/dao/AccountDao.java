package dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import entity.Account;
import entity.TransactionRecord;

@Remote
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public interface AccountDao {

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Integer create(Account account) throws DaoException;

	public Account retrieve(Integer accountNumber) throws DaoException;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Account account) throws DaoException;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void recordAccountTransaction(TransactionRecord record) throws DaoException;

	public List<Account> getAllAccounts() throws DaoException;

	public List<TransactionRecord> getAllTransactionRecords() throws DaoException;
}
