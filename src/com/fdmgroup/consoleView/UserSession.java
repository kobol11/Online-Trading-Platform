package com.fdmgroup.consoleView;

import com.fdmgroup.model.PlatformUser;

public class UserSession {

	private static PlatformUser user;

	public static boolean isLoggedIn() {
		return user != null;
	}

	public static void setUser(PlatformUser u) {
		if (isLoggedIn())
			throw new IllegalStateException("Cannot call login while someone is logged in already");

		user = u;
	}

	public static void logout() {
		user = null;
	}

	private UserSession() {
	}

	public static PlatformUser getLoggedInUser() {
		return user;
	}
	
}
