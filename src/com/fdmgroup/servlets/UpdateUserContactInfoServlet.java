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
 * Servlet implementation class UpdateUserContactInfoServlet
 */
@WebServlet("/UpdateUserContactInfoServlet")
public class UpdateUserContactInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserContactInfoServlet() {
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
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String streetNumber = request.getParameter("streetNumber");
		String street = request.getParameter("street");
		String postalCode = request.getParameter("postalCode");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		
		HttpSession session = request.getSession();
		Customer newUser = ( Customer) session.getAttribute("STPUser");
		boolean error = false;
		
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
		
		if(address.getStreetNumber() > 0 && (address.getStreet() != null && !address.getStreet().trim().equals("")) && 
				(address.getPostalCode() != null && !address.getPostalCode().trim().equals(""))
				&& (address.getCity() != null && !address.getCity().trim().equals("")) && 
				(address.getCountry() != null && !address.getCountry().trim().equals("")) &&
				(firstname != null && !firstname.trim().equals("")) && (lastname != null && !lastname.trim().equals("")) &&
				(email != null && !email.trim().equals(""))){
			newUser.setAddress(address);
			newUser.setFirstname(firstname).setLastname(lastname);
			new UserDao().update(newUser);
			session.setAttribute("STPUser", newUser);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		}
		else{
			//fail
			request.setAttribute("updateUserInfo", "User contact information form cannot be empty!");
			RequestDispatcher rd = request.getRequestDispatcher("update_user.jsp");
			rd.forward(request, response);
		}
	}

}
