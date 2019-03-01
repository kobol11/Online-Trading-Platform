package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.fdmgroup.jpa.EntityManagerHelper;
import com.fdmgroup.model.Company;
import com.fdmgroup.model.Stock;

public class StockDao implements IStockDao{
	private EntityManagerHelper connection;
	
	public StockDao() {
		super();
		// TODO Auto-generated constructor stub
		connection = EntityManagerHelper.getInstance();
	}

	@Override
	public boolean create(Stock stock) {
		// TODO Auto-generated method stub
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		Stock managedStock = em.merge(stock);
		em.persist(managedStock);
		Company company = em.find(Company.class, stock.getCompany().getID());
		company.setStock(managedStock);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public void delete(Stock stock) {
		// TODO Auto-generated method stub
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		Stock managedStock = em.merge(stock);
		Company company = em.find(Company.class, managedStock.getCompany().getID());
		company.setStock(null);
		em.remove(managedStock);
		em.getTransaction().commit();
		em.close();	
	}

	@Override
	public Stock readById(int id) {
		// TODO Auto-generated method stub
		Stock stock = null;
		EntityManager em = connection.getEntityManager();
		stock = em.find(Stock.class, id);
		em.close();
		return stock;
	}

	@Override
	public List<Stock> readAll() {
		// TODO Auto-generated method stub
		List<Stock> stocks = null;
		EntityManager em = connection.getEntityManager();
		stocks = em.createQuery("Select s from Stock s", Stock.class).getResultList();
		em.close();
		return stocks;
	}

	@Override
	public boolean update(Stock stock) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(stock != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			Stock managedStock = em.merge(stock);
			Stock foundStock = em.find(Stock.class, managedStock.getId());
			if(managedStock.getName() != null && !managedStock.getName().equals("") &&
				managedStock.getSymbol() != null && !managedStock.getSymbol().equals("") &&
				managedStock.getCompany() != null){
				foundStock.setName(managedStock.getName())
				.setSymbol(managedStock.getSymbol())
				.setCompany(managedStock.getCompany());
				result = true;
			}
			em.getTransaction().commit();
			em.close();
		}
		return result;
	}

}
