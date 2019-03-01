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
import com.fdmgroup.model.Address;
import com.fdmgroup.model.Customer;

/**
 * Servlet implementation class UpdateUserProfileServlet
 */
@WebServlet("/UpdateUserProfileServlet")
public class UpdateUserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//RequestDispatcher rd = request.getRequestDispatcher("update_user.jsp");
		//rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String streetNumber = request.getParameter("streetNumber");
		String street = request.getParameter("street");
		String postalCode = request.getParameter("postalCode");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		
		HttpSession session = request.getSession();
		Customer newUser = (Customer) session.getAttribute("STPUser");
		
		if(firstname != null && !firstname.trim().equals("")){
			newUser.setFirstname(firstname);
		}
		if(lastname != null && !lastname.trim().equals("")){
			newUser.setLastname(lastname);
		}
		if(username != null && !username.trim().equals("") && username.trim().matches("^[a-zA-Z_][0-9a-zA-Z_]*$")){
			newUser.setUsername(username);
		}
		if(password != null && !password.trim().equals("") && password.trim().length() >= 6){
			newUser.setPassword(password);
		}
		if(email != null && !email.trim().equals("")){
			newUser.setEmail(email);
		}
		
		Address address = new Address();
		if(streetNumber != null && !streetNumber.trim().equals("")){
			address.setStreetNumber(Integer.parseInt(streetNumber));
		}
		if(street != null && !street.trim().equals("")){
			address.setStreet(street);
		}
		if(postalCode != null && !postalCode.trim().equals("")){
			address.setPostalCode(postalCode);
		}
		if(city != null && !city.trim().equals("")){
			address.setCity(city);
		}
		if(country != null && !country.trim().equals("")){
			address.setCountry(country);
		}
		
		if(address.getStreetNumber() > 0 || (address.getStreet() != null && !address.getStreet().trim().equals("")) || 
				(address.getPostalCode() != null && !address.getPostalCode().trim().equals(""))
				|| (address.getCity() != null && !address.getCity().trim().equals("")) || 
				(address.getCountry() != null && !address.getCountry().trim().equals("")) ||
				(firstname != null && !firstname.trim().equals("")) || (lastname != null && !lastname.trim().equals("")) ||
				(username != null && !username.trim().equals("") && username.trim().matches("^[a-zA-Z_][0-9a-zA-Z_]*$")) ||
				(password != null && !password.trim().equals("") && password.trim().length() >= 6) ||
				(email != null && !email.trim().equals(""))){
			newUser.setAddress(address);
			session.removeAttribute("STPUser");
			session.invalidate();
			new UserDao().update(newUser);
			response.sendRedirect("login.jsp");
		}
		else{
			//fail
			request.setAttribute("updateUserInfo", "User update form cannot be empty!");
			RequestDispatcher rd = request.getRequestDispatcher("update_user.jsp");
			rd.forward(request, response);
		}
	}

}
