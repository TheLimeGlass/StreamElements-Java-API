package me.limeglass.streamelements.api;

import java.util.List;

import me.limeglass.streamelements.api.objects.Activity;

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
	
}
