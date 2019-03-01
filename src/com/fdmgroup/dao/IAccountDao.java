package com.fdmgroup.dao;

import com.fdmgroup.model.Account;

public interface IAccountDao extends ICreatable<Account>, IDeletable<Account>, IReadable<Account>, IUpdatable<Account>{
     public void fundAccount(String accountNum, String fund);
}
