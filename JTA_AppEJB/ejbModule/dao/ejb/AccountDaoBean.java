package dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.AccountDao;
import dao.DaoException;
import entity.Account;
import entity.TransactionRecord;

@Stateless
public class AccountDaoBean implements AccountDao {

	@PersistenceContext(unitName = "jta-app-pu")
	EntityManager em;

	@Override
	public Integer create(Account account) throws DaoException {
		try {
			em.persist(account);
			return account.getAccountNumber();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Account retrieve(Integer accountNumber) throws DaoException {
		try {
			return em.find(Account.class, accountNumber);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void update(Account account) throws DaoException {
		if (account.getBalance() < Account.MIN_BALANCE) {
			throw new DaoException("Balance must be >= Rs." + Account.MIN_BALANCE);
		}
		try {
			em.merge(account);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void recordAccountTransaction(TransactionRecord record) throws DaoException {
		try {
			em.persist(record);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAllAccounts() throws DaoException {
		try {
			return em.createQuery("from Account").getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransactionRecord> getAllTransactionRecords() throws DaoException {
		try {
			return em.createQuery("from TransactionRecord order by id desc").getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
