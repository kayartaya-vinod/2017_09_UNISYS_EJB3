package service;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import entity.Account;
import entity.TransactionRecord;

@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public interface AccountService {

	public List<Account> getAllAccounts() throws ServiceException;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void transferFunds(Integer toAccountNumber, Integer fromAccountNumber, Double amount)
			throws ServiceException;

	public List<TransactionRecord> getAllTransactionRecords() throws ServiceException;
}
