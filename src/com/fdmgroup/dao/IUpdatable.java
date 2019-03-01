package com.fdmgroup.dao;

import com.fdmgroup.model.IStorable;

public interface IUpdatable<T extends IStorable> {
	boolean update(T t);

}
