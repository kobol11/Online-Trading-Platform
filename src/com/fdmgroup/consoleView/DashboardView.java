package com.fdmgroup.consoleView;

import java.util.Scanner;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.exceptions.UserDataException;


public class DashboardView {

	private Scanner scanner;
	private AuthenticationBusinessLogic auth;

	public DashboardView() {
		super();
	}

	public DashboardView(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public DashboardView(Scanner scanner, AuthenticationBusinessLogic auth) {
		super();
		this.scanner = scanner;
		this.auth = auth;
	}

	public void showDashboard() {
		System.out.println("Welcome " + UserSession.getLoggedInUser().getFirstname() + " "
				+ UserSession.getLoggedInUser().getLastname() + "!");
		System.out.println("Please select one of the options below: ");
		System.out.println("1) Logout");
		System.out.println("2) Change password");
		System.out.print(">> ");
		String userInput = scanner.nextLine();

		switch (userInput) {
		case "1":
			UserSession.logout();
			break;
		case "2":{
			System.out.println("Please enter existing password:");
			System.out.print(">> ");
			String oldPw = scanner.nextLine();
			System.out.println("Please enter new password, must be at least 6 chars, not longer than 10 chars");
			System.out.print(">> ");
			String newPw = scanner.nextLine();
			System.out.println("Please confirm new password");
			System.out.print(">> ");
			String confPw = scanner.nextLine();

			try {
				auth.changePassword(UserSession.getLoggedInUser(), oldPw, newPw, confPw);
			} catch (UserDataException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		/*case "3":{
			System.out.println("Please select an account type option: 1) Cash Brokerage Account 2) Margin Brokerage Account");
			System.out.print(">> ");
			String option = scanner.nextLine();
			if(option.trim().equals("1")){
				CashBrokerageAccount cba = new CashBrokerageAccount();
				cba.setUser(UserSession.getLoggedInUser());
				new AccountDao().create(cba);
				UserSession.logout();
				break;
			}
		}*/
		default:
			System.out.println("The input was invalid.");
			// showDashboard();
		}
	}
	
}
