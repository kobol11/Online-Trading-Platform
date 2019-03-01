package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.fdmgroup.jpa.EntityManagerHelper;

import com.fdmgroup.model.PlatformUser;

public class UserDao implements IUserDao{
	private EntityManagerHelper connection;

	public UserDao() {
		super();
		// TODO Auto-generated constructor stub
		connection = EntityManagerHelper.getInstance();
		//em.getTransaction().begin();
		
	}

	public boolean create(PlatformUser user) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(user != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
			
			result = true;
		}
		
		return result;
	}

	public PlatformUser readById(int id) {
		// TODO Auto-generated method stub
		PlatformUser user = null;
		EntityManager em = connection.getEntityManager();
		user = em.find(PlatformUser.class, id);
		em.close();
		return user;
	}

	public List<PlatformUser> readAll() {
		// TODO Auto-generated method stub
		List<PlatformUser> users = null;
		EntityManager em = connection.getEntityManager();
		users = em.createQuery("select u from PlatformUser u ", PlatformUser.class).getResultList();
		em.close();
		return users;
		
	}

	public boolean update(PlatformUser user) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(user != null){
			EntityManager em = connection.getEntityManager();
			PlatformUser foundUser = em.find(PlatformUser.class, user.getID());
			em.getTransaction().begin();
			
			/*if(user.getFirstname() != null && !user.getFirstname().equals("") &&
				user.getLastname() != null && !user.getLastname().equals("") &&
				user.getUsername() != null && !user.getUsername().equals("") &&
				user.getPassword() != null && !user.getPassword().equals("") && user.getPassword().length() >= 6 
			  ) {
				foundUser.setFirstname(user.getFirstname())
				.setLastname(user.getLastname())
				.setUsername(user.getUsername())
				.setPassword(user.getPassword());}*/
			
			    if(user.getFirstname() != null && !user.getFirstname().equals("")){
			    	foundUser.setFirstname(user.getFirstname());
			    	result = true;
			    }
			    
			    if(user.getLastname() != null && !user.getLastname().equals("")){
			    	foundUser.setLastname(user.getLastname());
			    	result = true;
			    }
			    
			    if(user.getUsername() != null && !user.getUsername().equals("")){
			    	foundUser.setUsername(user.getUsername());
			    	result = true;
			    }
			    
			    if(user.getPassword() != null && !user.getPassword().equals("") && user.getPassword().length() >= 6){
			    	foundUser.setPassword(user.getPassword());
			    	result = true;
			    }
			
				if(user.getEmail() != null && !user.getEmail().equals("")){
					foundUser.setEmail(user.getEmail());
					result = true;
				}
				
				if(user.getAddress().getStreetNumber() != 0){
					foundUser.getAddress().setStreetNumber(user.getAddress().getStreetNumber());
					result = true;
				}
				if(user.getAddress().getStreet() != null && !user.getAddress().getStreet().equals("")){
					foundUser.getAddress().setStreet(user.getAddress().getStreet());
					result = true;
				}
				if(user.getAddress().getPostalCode() != null && !user.getAddress().getPostalCode().equals("")){
					foundUser.getAddress().setPostalCode(user.getAddress().getPostalCode());
					result = true;
				}
				if(user.getAddress().getCity() != null && !user.getAddress().getCity().equals("")){
					foundUser.getAddress().setCity(user.getAddress().getCity());
					result = true;
				}
				if(user.getAddress().getCountry() != null && !user.getAddress().getCountry().equals("")){
					foundUser.getAddress().setCountry(user.getAddress().getCountry());
					result = true;
				}
			
			em.getTransaction().commit();
			em.close();
		}
		
		return result;
	}

	public void delete(PlatformUser user) {
		// TODO Auto-generated method stub
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}

	public PlatformUser readByUsername(String username) {
		// TODO Auto-generated method stub
		PlatformUser user = null;
		EntityManager em = connection.getEntityManager();
		//em.getTransaction().begin();
		List<PlatformUser> users = em.createQuery("select u from PlatformUser u ", PlatformUser.class).getResultList();
		for (PlatformUser platformUser : users) {
			if (platformUser.getUsername().equals(username)){
				user = platformUser;
			}
		}
		em.close();
		return user;
	}

	public PlatformUser readByEmail(String email) {
		// TODO Auto-generated method stub
		PlatformUser user = null;
		EntityManager em = connection.getEntityManager();
		//em.getTransaction().begin();
		List<PlatformUser> users = em.createQuery("select u from PlatformUser u ", PlatformUser.class).getResultList();
		for (PlatformUser platformUser : users) {
			if (platformUser.getEmail().equals(email)){
				user = platformUser;
			}
		}
		em.close();
		return user;
	}

}
