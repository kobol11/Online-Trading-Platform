package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.fdmgroup.jpa.EntityManagerHelper;
import com.fdmgroup.model.OrderStatus;

public class OrderStatusDao implements IOrderStatusDao{
	
	private EntityManagerHelper connection;

	public OrderStatusDao() {
		super();
		// TODO Auto-generated constructor stub
		connection = EntityManagerHelper.getInstance();
	}

	@Override
	public boolean create(OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		em.persist(orderStatus);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public void delete(OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		em.remove(orderStatus);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public OrderStatus readById(int id) {
		// TODO Auto-generated method stub
		OrderStatus orderStatus = null;
		EntityManager em = connection.getEntityManager();
		orderStatus = em.find(OrderStatus.class, id);
		em.close();
		return orderStatus;
	}

	@Override
	public List<OrderStatus> readAll() {
		// TODO Auto-generated method stub
		List<OrderStatus> orderStatus = null;
		EntityManager em = connection.getEntityManager();
		orderStatus = em.createQuery("Select os from OrderStatus os", OrderStatus.class).getResultList();
		return orderStatus;
	}

	@Override
	public boolean update(OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(orderStatus != null){
			EntityManager em = connection.getEntityManager();
			OrderStatus foundOrderStatus = em.find(OrderStatus.class, orderStatus.getID());
			if(orderStatus.getStatus() != null && !orderStatus.getStatus().equals("")){
				foundOrderStatus.setStatus(orderStatus.getStatus());
				result = true;
			}
			em.getTransaction().commit();
			em.close();
		}
		return result;
	}

}
