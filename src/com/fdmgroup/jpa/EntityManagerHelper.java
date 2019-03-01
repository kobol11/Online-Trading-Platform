package com.fdmgroup.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class EntityManagerHelper {
	private static final String JPA_UNIT_NAME = "TradingPlatform";
	private static EntityManagerHelper connection = null;
	private EntityManagerFactory emf;
	
	private EntityManagerHelper() {
		init();
	}

	private void init() {
		if(emf == null || !emf.isOpen()) {
			emf = Persistence.createEntityManagerFactory(JPA_UNIT_NAME);
		}
	}
	
	public static EntityManagerHelper getInstance() {
		if(connection == null) {
			connection = new EntityManagerHelper();
		}
		return connection;
	}
	
	public EntityManager getEntityManager() {
		init();
		return emf.createEntityManager();
	}
	
	public void close() {
		emf.close();
	}
}
