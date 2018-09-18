package me.limeglass.streamelements.internals.objects;

import me.limeglass.streamelements.api.objects.User;

public class UserImp implements User {

	private final String username;
	
	public UserImp(String username) {
		this.username = username;
	}
	
	@Override
	public String getName() {
		return username;
	}
	
	public String toString() {
		return "username=" + username;
	}

}
