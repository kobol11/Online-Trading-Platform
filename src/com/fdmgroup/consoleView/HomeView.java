package com.fdmgroup.consoleView;

import java.util.Scanner;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.exceptions.UserDataException;
import com.fdmgroup.model.PlatformUser;

public class HomeView {

	private Scanner scanner;
	private AuthenticationBusinessLogic auth;

	public HomeView(Scanner scanner, AuthenticationBusinessLogic auth) {
		super();
		this.scanner = scanner;
		this.auth = auth;
	}

	public boolean showInitialOptions(boolean showLogoutMessage) {
		if (showLogoutMessage) {
			System.out.println("You logged out successfully.");
		}

		boolean userIsRunningApp = true;

		System.out.println("Welcome to the Trading Platform Version 1.0.0");
		System.out.println("Please choose one of the options below:");
		System.out.println("1) Login");
		System.out.println("2) Register");
		System.out.println("3) Exit");
		System.out.println("-------------------------------");
		System.out.print(">> ");
		String userInput = scanner.nextLine();

		switch (userInput) {
		case "1":
			showLoginOptions(false);
			break;
		case "2":
			showRegisterOptions();
			break;
		case "3":
			System.out.println("Thanks, Goodbye!");
			userIsRunningApp = false;
			break;
		default:
			System.out.println("The input is invalid.");
			showInitialOptions(false);
		}
		return userIsRunningApp;
	}

	private void showRegisterOptions() {
		System.out.println("Registering:");
		System.out.println("Enter username");
		System.out.print(">> ");
		String username = scanner.nextLine();

		System.out.println("Enter password, must be at least 6 characters, maximum of 10 characters");
		System.out.print(">> ");
		String password = scanner.nextLine();

		System.out.println("Enter firstname");
		System.out.print(">> ");
		String firstname = scanner.nextLine();

		System.out.println("Enter lastname");
		System.out.print(">> ");
		String lastname = scanner.nextLine();

		System.out.println("Enter email");
		System.out.print(">> ");
		String email = scanner.nextLine();
		
		System.out.println("Please select an account type option: 1) Cash Brokerage Account 2) Margin Brokerage Account");
		System.out.print(">> ");
		String option = scanner.nextLine();

		try {
			auth.register(username, password, firstname, lastname, email, option);

		} catch (UserDataException e) {
			System.out.println(e.getMessage());

		}

	}

	public void showLoginOptions(boolean showError) {
		if (showError) {
			System.out.println("Username/Password is wrong.");
		}

		System.out.println("Login");
		System.out.println("Please enter username: ");
		System.out.print(">> ");
		String username = scanner.nextLine();
		System.out.println("Please enter password: ");
		System.out.print(">> ");
		String password = scanner.nextLine();

		if (username.length() == 0 || password.length() < 6) {
			System.out.println("Invalid data");
			return;
		}

		// TODO add validation here

		PlatformUser login = auth.login(username, password);
		if (login != null) {
			UserSession.setUser(login);

		}
	}
}
