package me.limeglass.streamelements.api;

import java.util.Collection;
import java.util.List;

import me.limeglass.streamelements.api.objects.Activity;
import me.limeglass.streamelements.api.objects.Points;
import me.limeglass.streamelements.api.objects.User;

public interface StreamElements {
	
	/**
	 * @return The StreamElements Account ID of the client.
	 */
	String getAccountID();
	
	/**
	 * @return The StreamElements JWT authentication token for the client.
	 */
	String getToken();
	
	/**
	 * @return The StreamElements connection timeout option.
	 */
	int getTimeout();
	
	/**
	 * @return The activities of the account.
	 */
	List<Activity> getActivities();
	
	/**
	 * @return Multiple points of users.
	 */
	List<Points> getUserPoints(Collection<User> users);
	
	/**
	 * @return The points of a user by string username.
	 */
	Points getUserPoints(String username);
	
	/**
	 * @return The points of a user.
	 */
	Points getUserPoints(User user);
	
}
