package com.fdmgroup.dao;

import com.fdmgroup.model.StockPosition;

public interface IStockPositionDao extends ICreatable<StockPosition>, IDeletable<StockPosition>, IReadable<StockPosition>, IUpdatable<StockPosition>{
  public abstract boolean create(StockPosition sp, boolean action);
}
