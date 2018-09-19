package me.limeglass.streamelements.internals;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.objects.Activity;
import me.limeglass.streamelements.api.objects.Points;
import me.limeglass.streamelements.api.objects.User;
import me.limeglass.streamelements.internals.handlers.ElementsReaderHandler;
import me.limeglass.streamelements.internals.handlers.ElementsRequest;
import me.limeglass.streamelements.internals.handlers.ElementsRequest.HttpMethod;
import me.limeglass.streamelements.internals.responses.ActivitiesResponse;
import me.limeglass.streamelements.internals.responses.PointsResponse;

public class StreamElementsClient implements StreamElements {
	
	private final String token, account;
	private final int timeout;
	
	/**
	 * The StreamElements client Constructor
	 * 
	 * @param token The JWT token used from StreamElements. (Found under account)
	 * @param accountID The account ID used from StreamElements. (Found under account)
	 */
	public StreamElementsClient(String token, String account, int timeout) {
		ElementsReaderHandler.load("me.limeglass.streamelements.internals.readers");
		this.account = account;
		this.timeout = timeout;
		this.token = token;
	}

	/**
	 * @return the StreamElements Account ID of the client.
	 */
	@Override
	public String getAccountID() {
		return account;
	}
	
	/**
	 * @return The StreamElements JWT token of the client.
	 */
	@Override
	public String getToken() {
		return token;
	}

	/**
	 * @return The timeout defined by the client.
	 */
	@Override
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @return Grab all activities. Activities include, follows, subscriptions, etc.
	 */
	@Override
	public List<Activity> getActivities() {
		//TODO
		ElementsRequest.streamRequest(this, ActivitiesResponse.class, HttpMethod.GET, ElementsEndpoints.ACTIVITIES + account);
		return null;
	}

	@Override
	public List<Points> getUserPoints(Collection<User> users) {
		return users.stream().map(user -> getUserPoints(user)).collect(Collectors.toList());
	}
	
	@Override
	public Points getUserPoints(User user) {
		return getUserPoints(user.getName());
	}

	@Override
	public Points getUserPoints(String username) {
		return ElementsRequest.streamRequest(this, PointsResponse.class, HttpMethod.GET, ElementsEndpoints.POINTS + account + "/" + username)
				.filter(optional -> optional.isPresent())
				.map(optional -> optional.get().getPoints())
				.findFirst().get();
	}

	@Override
	public void setCurrentUserPoints(String username, long points) {
		ElementsRequest.makeRequest(this, PointsResponse.class, HttpMethod.PUT, ElementsEndpoints.POINTS + account + "/" + username + "/" + points);
	}

	@Override
	public void setCurrentUserPoints(User user, long points) {
		setCurrentUserPoints(user.getName(), points);
	}

}
