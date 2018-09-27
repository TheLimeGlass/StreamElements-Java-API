package me.limeglass.streamelements.internals.objects;

import me.limeglass.streamelements.api.objects.User;

public class UserImp implements User {

	private final String username;
	private String picture;
	
	public UserImp(String username) {
		this.username = username;
	}
	
	public UserImp(String username, String picture) {
		this.username = username;
		this.picture = picture;
	}
	
	@Override
	public String getAvatar() {
		return picture;
	}
	
	@Override
	public String getName() {
		return username;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("username=" + username);
		builder.append(",avatar=" + picture);
		return builder.toString();
	}

}
