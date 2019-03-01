package com.fdmgroup.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.UserDao;
import com.fdmgroup.exceptions.UserDataException;

/**
 * Servlet implementation class OpenAccountServlet
 */
@WebServlet("/OpenAccountServlet")
public class OpenAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenAccountServlet() {
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
		//doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String accountType = request.getParameter("accountType");
		
		IUserDao userDao = new UserDao();
		AuthenticationBusinessLogic abl = new AuthenticationBusinessLogic();
		abl.setUserDao(userDao);
		try {
			abl.register(username, password, firstname, lastname, email, accountType);
			//RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			//rd.forward(request, response);
			response.sendRedirect("login.jsp");
			return;
		} catch (UserDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
