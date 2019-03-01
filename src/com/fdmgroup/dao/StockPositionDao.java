package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.fdmgroup.jpa.EntityManagerHelper;
import com.fdmgroup.model.Customer;
import com.fdmgroup.model.PlatformUser;
import com.fdmgroup.model.Stock;
import com.fdmgroup.model.StockPosition;

public class StockPositionDao implements IStockPositionDao{
	
	private EntityManagerHelper connection;
	
	public StockPositionDao() {
		super();
		// TODO Auto-generated constructor stub
		connection = EntityManagerHelper.getInstance();
	}

	@Override
	public boolean create(StockPosition sp) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(sp != null) {
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			//StockPosition managedStockPosition = em.merge(sp);
			PlatformUser user = em.find(PlatformUser.class, sp.getUser().getID());
			Customer customer = (Customer)user;
			List<StockPosition> userStockPositions = customer.getStockPositions();
			
			boolean isStockPositionExisting = false; 
			
			Stock foundStock = em.find(Stock.class, sp.getStock().getId());
			List<StockPosition> stockStockPositions = foundStock.getStockPositions();
			
			for (StockPosition stockPosition : userStockPositions) {
				if(stockPosition.getStock().getId() == foundStock.getId()) {
					isStockPositionExisting = true;
					stockPosition.setStockUnit(stockPosition.getStockUnit() + sp.getStockUnit());
				}
				/*for(StockPosition stockP: stockStockPositions) {
					if(stockPosition.getStock().getId() == stockP.getStock().getId() &&
							stockPosition.getUser() == stockP.getUser()) {
					stockP.setStockUnit(stockP.getStockUnit() + sp.getStockUnit());	
					}
				}*/
			}
			
			if(!isStockPositionExisting) {
				StockPosition managedStockPosition = em.merge(sp);
				customer.getStockPositions().add(managedStockPosition);
				foundStock.getStockPositions().add(managedStockPosition);
				em.persist(managedStockPosition);
			}
			
			
			em.getTransaction().commit();
			em.close();
			result = true;
		}
		
		return result;
	}

	@Override
	public void delete(StockPosition sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StockPosition readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockPosition> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(StockPosition sp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(StockPosition sp, boolean action) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(sp != null) {
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			//StockPosition managedStockPosition = em.merge(sp);
			PlatformUser user = em.find(PlatformUser.class, sp.getUser().getID());
			Customer customer = (Customer)user;
			List<StockPosition> userStockPositions = customer.getStockPositions();
			
			boolean isStockPositionExisting = false; 
			
			Stock foundStock = em.find(Stock.class, sp.getStock().getId());
			List<StockPosition> stockStockPositions = foundStock.getStockPositions();
			
			for (StockPosition stockPosition : userStockPositions) {
				if(stockPosition.getStock().getId() == foundStock.getId()) {
					isStockPositionExisting = true;
					if(!action)
					stockPosition.setStockUnit(stockPosition.getStockUnit() - sp.getStockUnit());
					if(action)
						stockPosition.setStockUnit(stockPosition.getStockUnit() + sp.getStockUnit());
				}
				/*for(StockPosition stockP: stockStockPositions) {
					if(stockPosition.getStock().getId() == stockP.getStock().getId() &&
							stockPosition.getUser() == stockP.getUser()) {
					stockP.setStockUnit(stockP.getStockUnit() + sp.getStockUnit());	
					}
				}*/
			}
			
			if(!isStockPositionExisting) {
				StockPosition managedStockPosition = em.merge(sp);
				customer.getStockPositions().add(managedStockPosition);
				foundStock.getStockPositions().add(managedStockPosition);
				em.persist(managedStockPosition);
			}
			
			
			em.getTransaction().commit();
			em.close();
			result = true;
		}
		
		return result;
	}

}
