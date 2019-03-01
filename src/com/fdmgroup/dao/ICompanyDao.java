package com.fdmgroup.dao;

import com.fdmgroup.model.Company;

public interface ICompanyDao extends ICreatable<Company>, IDeletable<Company>, IReadable<Company>, IUpdatable<Company>{
	Company readByName(String companyName);
}
