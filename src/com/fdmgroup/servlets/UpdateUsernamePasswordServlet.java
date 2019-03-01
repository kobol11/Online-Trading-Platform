package com.fdmgroup.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.dao.UserDao;

import com.fdmgroup.model.Customer;

/**
 * Servlet implementation class UpdateUsernamePasswordServlet
 */
@WebServlet("/UpdateUsernamePasswordServlet")
public class UpdateUsernamePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUsernamePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		HttpSession session = request.getSession();
		Customer newUser = ( Customer) session.getAttribute("STPUser");
		boolean error = false;
		
		if(username != null && !(username.trim().equals("")) && username.trim().matches("^[a-zA-Z_][0-9a-zA-Z_]*$")){
			newUser.setUsername(username);
		}else{
			error = true;
			
		}
		if(password != null && !(password.trim().equals("")) && password.trim().length() >= 6){
			newUser.setPassword(password);
		}else{
			error = true;
			
		}
		
		if(!error){
			session.removeAttribute("STPUser");
			session.invalidate();
			new UserDao().update(newUser);
			response.sendRedirect("login.jsp");
		}else{
			
			request.setAttribute("updateUserInfo", "Valid username and password required!");
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		}
		
	}

}
