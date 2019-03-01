package com.fdmgroup.businessLogic;

import java.util.List;

import com.fdmgroup.dao.AccountDao;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.exceptions.UserDataException;
import com.fdmgroup.model.Account;
import com.fdmgroup.model.CashBrokerageAccount;
import com.fdmgroup.model.Customer;
import com.fdmgroup.model.PlatformUser;
import com.fdmgroup.model.StockOrder;
import com.fdmgroup.model.StockPosition;

import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

public class AuthenticationBusinessLogic {

	private IUserDao userDao;

	// TODO delete this setter when introducing spring and dependency injection
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public PlatformUser register(String username, String password, String firstname, String lastname, String email)
			throws UserDataException {
		String errMsg = null;
		if (username == null)
			errMsg = "Username cannot be null";
		else if (username.length() == 0)
			errMsg = "Username cannot be empty";
		else if (!username.matches("^[a-zA-Z_][0-9a-zA-Z_]*$"))
			errMsg = "Invalid Username format";
		else if (userDao.readByUsername(username) != null)
			errMsg = "Username is not unique";
		else if (password == null || password.length() < 6)
			errMsg = "Password length too short";

		if (errMsg != null)
			throw new UserDataException(errMsg);

		PlatformUser u = new Customer().setUsername(username).setPassword(password).setFirstname(firstname).setLastname(lastname)
				.setEmail(email);
		if (userDao.create(u))
			return u;

		return null;
	}
	
	public PlatformUser register(String username, String password, String firstname, String lastname, String email, String accountType)
			throws UserDataException {
		String errMsg = null;
		if (username == null)
			errMsg = "Username cannot be null";
		else if (username.length() == 0)
			errMsg = "Username cannot be empty";
		else if (!username.matches("^[a-zA-Z_][0-9a-zA-Z_]*$"))
			errMsg = "Invalid Username format";
		else if (userDao.readByUsername(username) != null)
			errMsg = "Username is not unique";
		else if (password == null || password.length() < 6)
			errMsg = "Password length too short";
		else if (accountType == null)
			errMsg = "Please select an accout type";

		if (errMsg != null)
			throw new UserDataException(errMsg);

		PlatformUser u = new Customer().setUsername(username).setPassword(password).setFirstname(firstname).setLastname(lastname)
				.setEmail(email);
		if (userDao.create(u)){
			if(accountType.trim().equals("Cash Account")){
				CashBrokerageAccount cba = new CashBrokerageAccount();
				cba.setUser(u);
				new AccountDao().create(cba);
			}
			return u;
		}
			

		return null;
	}

	public PlatformUser login(String username, String password) {
		PlatformUser user = userDao.readByUsername(username);
		if (user != null && user.getPassword().equals(password))
			return user;

		return null;
	}

	public boolean changePassword(PlatformUser u, String oldPw, String newPw, String confPw) throws UserDataException {

		if (!newPw.equals(confPw))
			throw new UserDataException("Passwords do not match");

		if (!oldPw.equals(u.getPassword()))
			throw new UserDataException("Invalid existing password");

		u.setPassword(newPw);
		return userDao.update(u);
	}
	
	public int validateOrder(StockOrder stockOrder, Customer customer, String stockSymbol){
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
			List<StockPosition> userStockPositions = customer.getStockPositions();
			for (StockPosition stockPosition : userStockPositions){
				if(stockPosition.getStock().getId() == stockOrder.getStock().getId()){
					if(stockPosition.getStockUnit() < stockOrder.getStockUnit()){
						result = 2;
					}
				}
			}
		}
		
		return result;
	}

}
