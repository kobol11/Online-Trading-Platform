package com.fdmgroup.dao;

import com.fdmgroup.model.Customer;
import com.fdmgroup.model.StockOrder;

public interface IStockOrderDao extends ICreatable<StockOrder>, IDeletable<StockOrder>, IReadable<StockOrder>, IUpdatable<StockOrder>{
	public abstract boolean create (StockOrder stockorder, String symbol);
	public abstract int validateOrder(StockOrder stockOrder, Customer customer, String stockSymbol);
}
