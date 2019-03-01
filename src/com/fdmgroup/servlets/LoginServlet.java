package com.fdmgroup.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.UserDao;
import com.fdmgroup.model.Account;
import com.fdmgroup.model.Customer;
import com.fdmgroup.model.PlatformUser;
import com.fdmgroup.model.StockOrder;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("user-pw");
		IUserDao userDao = new UserDao();
		AuthenticationBusinessLogic abl = new AuthenticationBusinessLogic();
		abl.setUserDao(userDao);
		PlatformUser user = abl.login(username, password);
		if(user != null){
			//success
			String test = "";
			HttpSession session = request.getSession();
			if(user.isAdmin() == 'N'){
				Customer customer = (Customer) user;
				List<Account> accounts = customer.getAccounts();
				//long[] accountnumbers = new long[accounts.size()];
				for (int i = 0; i < accounts.size(); i++){
					test +=  accounts.get(i).getAccountNumber() + " ";
				}
				List<StockOrder> stockOrders = customer.getOrders();
				session.setAttribute("STPUser", customer);
				session.setAttribute("STPUserAccounts", test);
				session.setAttribute("Orders", stockOrders);
				request.setAttribute("Orders", stockOrders);
				request.setAttribute("STPUser", customer);
				request.setAttribute("STPUserAccounts", test);
			}else{
				session.setAttribute("STPUser", user);
			}
				
			
			
			session.setMaxInactiveInterval(0); //An interval value of 0 or less indicates session should not time out.
			
			//RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			//rd.forward(request, response);
			//response.sendRedirect("dashboard.jsp");
			return;
		}
		else {
			//fail
			request.setAttribute("logoutInfo", "INVALID LOG IN CREDENTIALS");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		return;
		//}
		///doGet(request, response);
	}

}
