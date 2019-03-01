package com.fdmgroup.servlets;

import java.io.IOException;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.dao.StockOrderDao;

import com.fdmgroup.model.Customer;
import com.fdmgroup.model.StockOrder;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String stockSymbol = request.getParameter("stockType");
		String action = request.getParameter("action");
		String shareUnit = request.getParameter("unit");
		String userPrice = request.getParameter("price_per_unit");
		
		StockOrder stockOrder = new StockOrder();
		HttpSession session = request.getSession();
		Customer user = ( Customer) session.getAttribute("STPUser");
		stockOrder.setUser(user);
		stockOrder.setTransactionDate(new Date());
		stockOrder.setStockUnit(Integer.parseInt(shareUnit));
		stockOrder.setUserQuotedPrice(Double.parseDouble(userPrice));
		stockOrder.setAction(action);
		//new StockOrderDao().create(stockOrder, stockSymbol);
		
		if(new StockOrderDao().create(stockOrder, stockSymbol)) {
		
			session.removeAttribute("STPUser");
			session.invalidate();
			
			response.sendRedirect("index.jsp");
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
