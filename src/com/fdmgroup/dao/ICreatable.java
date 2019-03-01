package com.fdmgroup.dao;

import com.fdmgroup.model.IStorable;

public interface ICreatable<T extends IStorable> {

	boolean create(T t);

}