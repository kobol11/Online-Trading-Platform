package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.jpa.EntityManagerHelper;
import com.fdmgroup.model.Account;
import com.fdmgroup.model.Customer;
import com.fdmgroup.model.PlatformUser;

public class AccountDao implements IAccountDao{
	private EntityManagerHelper connection;
	
	public AccountDao() {
		super();
		// TODO Auto-generated constructor stub
		connection = EntityManagerHelper.getInstance();
	}

	@Override
	public boolean create(Account account) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(account != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			Account managedAccount = em.merge(account);
			PlatformUser user = em.find(PlatformUser.class, account.getUser().getID());
			Customer customer = (Customer)user;
			customer.getAccounts().add(managedAccount);
			em.persist(managedAccount);
			em.getTransaction().commit();
			em.close();
			result = true;
		}
		
		return result;
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		if(account != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			Account managedAccount = em.merge(account);
			PlatformUser user = em.find(PlatformUser.class, account.getUser().getID());
			if(user != null){
				Customer customer = (Customer)user;
				customer.getAccounts().remove(account);
			}
			em.remove(managedAccount);
			em.getTransaction().commit();
			em.close();
		}
		
	}

	@Override
	public Account readById(int id) {
		// TODO Auto-generated method stub
		Account account = null;
		EntityManager em = connection.getEntityManager();
		account = em.find(Account.class, id);
		em.close();
		return account;
	}

	@Override
	public List<Account> readAll() {
		// TODO Auto-generated method stub
		List<Account> accounts = null;
		EntityManager em = connection.getEntityManager();
		accounts = em.createQuery("select acc from Account acc ", Account.class).getResultList();
		em.close();
		return accounts;
	}

	@Override
	public boolean update(Account account) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		if(account != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			Account foundAccount = em.find(Account.class, account.getID());

			PlatformUser user = em.find(PlatformUser.class, account.getUser().getID());
			
			if(account.getAccountNumber() > 0 && account.getUser() != null && user != null){
				Customer customer = (Customer)user;
				customer.getAccounts().remove(foundAccount);
				foundAccount.setAccountNumber(account.getAccountNumber())
				.setUser(account.getUser())
				.setCashBalance(account.getCashBalance());
				customer.getAccounts().add(foundAccount);
				result = true;
			}
			em.getTransaction().commit();
			em.close();
		}
		return result;
	}

	@Override
	public void fundAccount(String accountNum, String fund) {
		// TODO Auto-generated method stub
		long accNum = Long.parseLong(accountNum);
		double amount = Double.parseDouble(fund);
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
        TypedQuery<Account> q = em.createNamedQuery("acc.findByAccNum", Account.class);
		q.setParameter("accNumParam", accNum);
		Account foundAccount = q.getResultList().get(0);
		//Account foundAccount = (Account) em.createQuery("select acc from Account acc where acc.accountNumber = :accountNum ", Account.class).getResultList().get(0);
		//PlatformUser user = em.find(PlatformUser.class, foundAccount.getUser().getID());
		//Customer customer = (Customer)user;
		//int index = customer.getAccounts().indexOf(foundAccount);
		//Account userAccount = customer.getAccounts().get(index);
	
		//Account foundAccount = em.find(Account.class, account.getID());
		foundAccount.setCashBalance(foundAccount.getCashBalance() + amount);
		//userAccount.setCashBalance(foundAccount.getCashBalance() + amount);
		em.getTransaction().commit();
		em.close();
		
	}

}
