package service.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.AccountDao;
import dao.DaoException;
import entity.Account;
import entity.TransactionRecord;
import service.AccountService;
import service.ServiceException;

@Stateless
public class AccountServiceBean implements AccountService {

	@EJB
	AccountDao dao;

	@Override
	public List<Account> getAllAccounts() throws ServiceException {
		try {
			return dao.getAllAccounts();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void transferFunds(Integer toAccountNumber, Integer fromAccountNumber, Double amount)
			throws ServiceException {
		try {
			TransactionRecord rec = new TransactionRecord();
			rec.setAmount(amount);
			rec.setDescription(String.format("Rs.%.2f transferred from a/c %d to a/c %d", amount, fromAccountNumber,
					toAccountNumber));
			rec.setTransactionDate(new Date());
			dao.recordAccountTransaction(rec);

			Account to = dao.retrieve(toAccountNumber);
			to.setBalance(to.getBalance() + amount);
			dao.update(to);

			Account from = dao.retrieve(fromAccountNumber);
			from.setBalance(from.getBalance() - amount);
			dao.update(from);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<TransactionRecord> getAllTransactionRecords() throws ServiceException {
		try {
			return dao.getAllTransactionRecords();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
