package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.jpa.EntityManagerHelper;
import com.fdmgroup.model.Account;
import com.fdmgroup.model.Customer;
import com.fdmgroup.model.PlatformUser;
import com.fdmgroup.model.Stock;
import com.fdmgroup.model.StockOrder;
import com.fdmgroup.model.StockPosition;

import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

public class StockOrderDao implements IStockOrderDao{
	
	private EntityManagerHelper connection;

	public StockOrderDao() {
		super();
		// TODO Auto-generated constructor stub
		connection = EntityManagerHelper.getInstance();
	}

	@Override
	public boolean create(StockOrder stockOrder) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		if(stockOrder != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			StockOrder managedStockOrder = em.merge(stockOrder);
			PlatformUser user = em.find(PlatformUser.class, stockOrder.getUser().getID());
			Customer customer = (Customer)user;
			customer.getOrders().add(managedStockOrder);
			em.persist(managedStockOrder);
			em.getTransaction().commit();
			em.close();
			result = true;
		}
		
		return result;
	}
	
	public boolean create(StockOrder stockOrder, String symbol) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		if(stockOrder != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			StockOrder managedStockOrder = em.merge(stockOrder);
			PlatformUser user = em.find(PlatformUser.class, stockOrder.getUser().getID());
			Customer customer = (Customer)user;
			
			 TypedQuery<Stock> q = em.createNamedQuery("s.findBySymbol", Stock.class);
				q.setParameter("stockSymbol", symbol);
				Stock foundStock = q.getResultList().get(0);
				System.out.println(foundStock.getId());
				
				
				final IEXTradingClient iexTradingClient = IEXTradingClient.create();
				final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
				        .withSymbol(symbol)
				        .build());
				double latestPrice = quote.getLatestPrice().doubleValue();
				if(managedStockOrder.getAction().equalsIgnoreCase("Buy")){
					if(latestPrice <= (managedStockOrder).getUserQuotedPrice()){
						managedStockOrder.setOrderStatus(2);
						Account account = customer.getAccounts().get(0);
						double cost = latestPrice * managedStockOrder.getStockUnit();
						double userCashBalance = account.getCashBalance();
						account.setCashBalance(userCashBalance - cost);
					}else{
						managedStockOrder.setOrderStatus(1);	
					}
				}
				
				if(managedStockOrder.getAction().equalsIgnoreCase("Sell")){
					if(latestPrice >= (managedStockOrder).getUserQuotedPrice()){
						managedStockOrder.setOrderStatus(2);
						Account account = customer.getAccounts().get(0);
						double cash = latestPrice * managedStockOrder.getStockUnit();
						double userCashBalance = account.getCashBalance();
						account.setCashBalance(userCashBalance + cash);
					}else {
						managedStockOrder.setOrderStatus(1);
					}
					
				}
				
			foundStock.getOrders().add(managedStockOrder);
			//em.merge(foundStock);
			
			customer.getOrders().add(managedStockOrder);
			managedStockOrder.setStock(foundStock);
			
			if(managedStockOrder.getOrderStatusId() == 2 && managedStockOrder.getAction().equalsIgnoreCase("Buy")){
				StockPositionDao spDao = new StockPositionDao();
				StockPosition sp = new StockPosition();
				sp.setStock(foundStock);
				sp.setUser(customer);
				sp.setStockUnit(managedStockOrder.getStockUnit());
				spDao.create(sp, true);
			}
			
			if(managedStockOrder.getOrderStatusId() == 2 && managedStockOrder.getAction().equalsIgnoreCase("Sell")){
				StockPositionDao spDao = new StockPositionDao();
				StockPosition sp = new StockPosition();
				sp.setStock(foundStock);
				sp.setUser(customer);
				sp.setStockUnit(managedStockOrder.getStockUnit());
				spDao.create(sp, false);
			}
			
			
			em.persist(managedStockOrder);
			em.getTransaction().commit();
			em.close();
			result = true;
		}
		
		return result;
	}

	@Override
	public void delete(StockOrder stockOrder) {
		// TODO Auto-generated method stub
		if(stockOrder != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			StockOrder managedStockOrder = em.merge(stockOrder);
			PlatformUser user = em.find(PlatformUser.class, stockOrder.getUser().getID());
			if (user != null){
				Customer customer = (Customer)user;
				customer.getOrders().remove(managedStockOrder);
			}
			em.remove(managedStockOrder);
			em.getTransaction().commit();
			em.close();
		}
		
	}

	@Override
	public StockOrder readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockOrder> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(StockOrder stockOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int validateOrder(StockOrder stockOrder, Customer customer, String stockSymbol) {
		// TODO Auto-generated method stub
        int result = 0;
		
		final IEXTradingClient iexTradingClient = IEXTradingClient.create();
		final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
		        .withSymbol(stockSymbol)
		        .build());
		double latestPrice = quote.getLatestPrice().doubleValue();
		
		if(stockOrder.getAction().equalsIgnoreCase("buy")){
			Account account = customer.getAccounts().get(0);
			double userCashBalance = account.getCashBalance();
			if(userCashBalance < (latestPrice * stockOrder.getStockUnit())){
				result = 1;
			}
		}
		
		if(stockOrder.getAction().equalsIgnoreCase("sell")){
			List<StockPosition> userStockPositions = ((Customer)stockOrder.getUser()).getStockPositions();
			//List<StockPosition> userStockPositions = customer.getStockPositions();
			//System.out.println(userStockPositions.size());
			if(userStockPositions.size() == 0){
				result = 2;
			}
			for (StockPosition stockPosition : userStockPositions){
				if(stockPosition.getStock().getSymbol().equalsIgnoreCase(stockSymbol)){
					if(stockPosition.getStockUnit() < stockOrder.getStockUnit()){
						result = 2;
					}
				}
			}
		}
		
		return result;
	}

}
