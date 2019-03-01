package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.fdmgroup.jpa.EntityManagerHelper;
import com.fdmgroup.model.Company;
import com.fdmgroup.model.Stock;

public class CompanyDao implements ICompanyDao{
	private EntityManagerHelper connection;

	public CompanyDao() {
		super();
		// TODO Auto-generated constructor stub
		connection = EntityManagerHelper.getInstance();
	}

	@Override
	public boolean create(Company company) {
		// TODO Auto-generated method stub
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		em.persist(company);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public void delete(Company company) {
		// TODO Auto-generated method stub
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		Stock stock = em.find(Stock.class, company.getStock().getId());
		if(stock != null){
			em.remove(stock);
		}
		Company managedCompany = em.merge(company);
		em.remove(managedCompany);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Company readById(int id) {
		// TODO Auto-generated method stub
		Company company = null;
		EntityManager em = connection.getEntityManager();
		company = em.find(Company.class, id);
		em.close();
		return company;
	}

	@Override
	public List<Company> readAll() {
		// TODO Auto-generated method stub
		List<Company> companies = null;
		EntityManager em = connection.getEntityManager();
		companies = em.createQuery("Select c from Company c", Company.class).getResultList();
		em.close();
		return companies;
	}

	@Override
	public boolean update(Company company) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		if(company != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			Company foundCompany = em.find(Company.class, company.getID());
			if(company.getName() != null && !company.getName().equals("") &&
				company.getAddress() != null && company.getAddress().getCity() != null && !company.getAddress().getCity().equals("") &&
				company.getAddress().getCountry() != null && !company.getAddress().getCountry().equals("")){
				foundCompany.setName(company.getName())
				.setAddress(company.getAddress())
				.setStock(company.getStock());
				
				result = true;
			}
			em.getTransaction().commit();
			em.close();
		}
		return result;
	}

	@Override
	public Company readByName(String companyName) {
		// TODO Auto-generated method stub
		return null;
	}

}
