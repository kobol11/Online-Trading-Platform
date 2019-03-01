package com.fdmgroup.controller;

import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.dao.AccountDao;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.StockOrderDao;
import com.fdmgroup.dao.UserDao;
import com.fdmgroup.exceptions.UserDataException;
import com.fdmgroup.model.Account;
import com.fdmgroup.model.Address;
import com.fdmgroup.model.Customer;
import com.fdmgroup.model.PlatformUser;
//import com.fdmgroup.model.PlatformUser;
import com.fdmgroup.model.StockOrder;

@Controller
@SessionAttributes(value = {"user"}, types = {PlatformUser.class})
public class AuthenticationController {
	
	@RequestMapping(value = "/login")
	public String showLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("STPUser") != null){
			return "dashboard";
		}else{
			return "login";
		}
		
	}

	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String showDashboard(HttpServletRequest request) {
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
			
			}else{
				session.setAttribute("STPUser", user);
			}
			session.setMaxInactiveInterval(0); //An interval value of 0 or less indicates session should not time out.
			return "dashboard";
		}
		
		else {
			//fail
			request.setAttribute("logoutInfo", "INVALID LOG IN CREDENTIALS");
			return "login";
		}
	}
	
	@RequestMapping(value = "/open_account")
	public String openAccount() {
		
		return "open_account";
	}
	
	@RequestMapping(value = "/fundaccount",  method=RequestMethod.POST)
	public String fundAccount(HttpServletRequest request) {
		String accountNumber = request.getParameter("acctnumber");
		System.out.println(accountNumber);
		String fund = request.getParameter("fund");
		new AccountDao().fundAccount(accountNumber, fund);
		HttpSession session = request.getSession();
		Customer newUser = ( Customer) session.getAttribute("STPUser");
		Customer newuser = (Customer) new UserDao().readById(newUser.getID());
		//new UserDao().update(newUser);
		session.setAttribute("STPUser", newuser);
		return "dashboard";
	}
	
	@RequestMapping(value = "/openaccount", method=RequestMethod.POST)
	public String processAccount(HttpServletRequest request) {
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
			
			return "login";
		} catch (UserDataException e) {
			// TODO Auto-generated catch block
			request.setAttribute("invalidParam", "Invalid username/password.");
			return "open_account";
		}
	
	}
	
	@RequestMapping(value = "/update_user_info")
	public String updateUser() {
		
		return "update_user_info";
	}
	
	@RequestMapping(value = "/updateuser")
	public String updateUser(HttpServletRequest request) {
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
			return "login";
		}else{
			
			request.setAttribute("updateUserInfo", "Valid username and password required!");
			return "dashboard";
			
		}
	
	}
	
	@RequestMapping(value = "/updateusercontactinfo")
	public String updateUserContactInfo(HttpServletRequest request) {
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
			return "dashboard";
			
		}
		else{
			//fail
			request.setAttribute("updateUserInfo", "User contact information form cannot be empty!");
			return "dashboard";
		}
		
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("STPUser");
		session.removeAttribute("STPUserAccounts");
		session.removeAttribute("Orders");
		session.invalidate();
		return "index";
	}
	
	@RequestMapping(value = "/order")
	public String order(HttpServletRequest request) {
		
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
		StockOrderDao stockODAO = new StockOrderDao();
		int result = stockODAO.validateOrder(stockOrder, user, stockSymbol);
		if(result == 1){
			request.setAttribute("updateUserInfo", "Dear valued customer, you do not have sufficient fund in your account for this transaction. Kindly fund your account to start making money.");
		    return "dashboard";
		}
		if(result == 2){
			request.setAttribute("updateUserInfo", "Dear valued customer, you do not have sufficient stock units in your accout to sell. You might want to buy before selling.");
			return "dashboard";
		}
		if(new StockOrderDao().create(stockOrder, stockSymbol)) {
			Customer newuser = (Customer) new UserDao().readById(user.getID());
			session.setAttribute("STPUser", newuser);
			return "dashboard";
		}
		return "dashboard";
	}
	
}
