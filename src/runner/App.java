package runner;

import java.util.Scanner;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.consoleView.DashboardView;
import com.fdmgroup.consoleView.HomeView;
import com.fdmgroup.consoleView.RootView;
import com.fdmgroup.dao.CompanyDao;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.OrderStatusDao;
import com.fdmgroup.dao.StockDao;
import com.fdmgroup.dao.UserDao;
import com.fdmgroup.model.Address;
import com.fdmgroup.model.Company;
import com.fdmgroup.model.OrderStatus;
import com.fdmgroup.model.Stock;

import pl.zankowski.iextrading4j.api.stocks.BatchStocks;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.BatchStocksRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.BatchStocksType;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;


public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*Scanner scanner = new Scanner(System.in);

		IUserDao userDao = new UserDao();
		AuthenticationBusinessLogic abl = new AuthenticationBusinessLogic();
		abl.setUserDao(userDao);
		
		HomeView hv = new HomeView(scanner, abl);
		DashboardView dv = new DashboardView(scanner, abl);
		
		new RootView(scanner, hv, dv).doApp();

		scanner.close();
		
		System.out.println("Resources closed!");*/
		/*final IEXTradingClient iexTradingClient = IEXTradingClient.create();
		final BatchStocks batchStocks = iexTradingClient.executeRequest(new BatchStocksRequestBuilder()
		        .withSymbol("GOOGL")
		        .addType(BatchStocksType.BOOK)
		        .addType(BatchStocksType.COMPANY)
		        .addType(BatchStocksType.EARNINGS)
		        .build());
		System.out.println(batchStocks);*/
		
		final IEXTradingClient iexTradingClient = IEXTradingClient.create();
		final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
		        .withSymbol("AAPL")
		        .build());
		System.out.println(quote.getLatestPrice());
		System.out.println(quote.getIexRealtimePrice());
		
		
		
		
	}

}
