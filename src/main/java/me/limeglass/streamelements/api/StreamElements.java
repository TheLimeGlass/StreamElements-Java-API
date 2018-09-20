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
	 * @param users Collection of Users to get points from.
	 * @return Multiple points of users.
	 */
	List<Points> getUserPoints(Collection<User> users);
	
	/**
	 * May only be a positive number.
	 * 
	 * @param username The username to set points of.
	 * @param points The amount of current points to set.
	 */
	void setCurrentUserPoints(String username, long points);
	
	/**
	 * May only be a positive number.
	 * 
	 * @param user The User to set points of.
	 * @param points The amount of current points to set.
	 */
	void setCurrentUserPoints(User user, long points);
	
	/**
	 * @param user The username to set points of.
	 * @param points The amount of current points to subtract.
	 */
	void subtractPoints(String username, long points);
	
	/**
	 * @param user The User to set points of.
	 * @param points The amount of current points to subtract.
	 */
	void subtractPoints(User user, long points);
	
	/**
	 * @param user The username to set points of.
	 * @param points The amount of current points to add.
	 */
	void addPoints(String username, long points);
	
	/**
	 * @param user The User to set points of.
	 * @param points The amount of current points to add.
	 */
	void addPoints(User user, long points);
	
	/**
	 * @param username The username to get points from.
	 * @return The points of a user by string username.
	 */
	Points getUserPoints(String username);
	
	/**
	 * @param user The User to get points from.
	 * @return The points of a user.
	 */
	Points getUserPoints(User user);
	
}
