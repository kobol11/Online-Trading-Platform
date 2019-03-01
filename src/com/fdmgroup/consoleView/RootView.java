package com.fdmgroup.consoleView;

import java.util.Scanner;

public class RootView {

	private HomeView homeView;
	private DashboardView dashboardView;

	public RootView(Scanner scanner, HomeView homeView, DashboardView dashboardView) {
		super();
		this.homeView = homeView;
		this.dashboardView = dashboardView;
	}

	public void doApp() {
		boolean userIsRunningApp = true;
		while(userIsRunningApp) {
			if(UserSession.isLoggedIn()) 		
				dashboardView.showDashboard();
			else
				userIsRunningApp = homeView.showInitialOptions(false);			
		}
		
	}
	
}
