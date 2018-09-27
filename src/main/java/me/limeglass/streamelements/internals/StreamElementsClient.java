package me.limeglass.streamelements.internals;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.objects.Activity;
import me.limeglass.streamelements.api.objects.Points;
import me.limeglass.streamelements.api.objects.User;
import me.limeglass.streamelements.internals.events.EventDispatcher;
import me.limeglass.streamelements.internals.events.SocketListener;
import me.limeglass.streamelements.internals.handlers.ElementsReaderHandler;
import me.limeglass.streamelements.internals.handlers.ElementsRequest;
import me.limeglass.streamelements.internals.handlers.ElementsRequest.HttpMethod;
import me.limeglass.streamelements.internals.responses.ActivitiesResponse;
import me.limeglass.streamelements.internals.responses.PointsResponse;

public class StreamElementsClient implements StreamElements {
	
	private final String token, account;
	private Class<?>[] listeners;
	private String[] emitters;
	private final int timeout;
	private Socket socket;
	
	/**
	 * The StreamElements client Constructor
	 * 
	 * @param token The JWT token used from StreamElements. (Found under account)
	 * @param accountID The account ID used from StreamElements. (Found under account)
	 */
	public StreamElementsClient(String token, String account, int timeout, Class<?>[] listeners, String... emitters) {
		ElementsReaderHandler.load("me.limeglass.streamelements.internals.readers");
		if (listeners != null && listeners.length > 0)
			this.listeners = EventDispatcher.registerListeners(listeners);
		SocketListener.registerEmitters(this, "me.limeglass.streamelements.events.emitters.types");
		if (emitters != null && emitters.length > 0)
			this.emitters = SocketListener.registerEmitters(this, emitters);
		this.socket = SocketListener.registerSocket(this);
		this.account = account;
		this.timeout = timeout;
		this.token = token;
	}
	
	/**
	 * @return The classes that have been scanned and registered listeners for.
	 */
	public Class<?>[] getListeners() {
		return listeners;
	}
	
	/**
	 * @return The emitters to be registered.
	 */
	public String[] getEmitters() {
		return emitters;
	}

	/**
	 * @return the StreamElements Account ID of the client.
	 */
	@Override
	public String getAccountID() {
		return account;
	}
	
	/**
	 * @return The Socket.IO for realtime.streamelements.com
	 */
	public Socket getSocket() {
		return socket;
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
		if (points < 0) return; //TODO throw custom exception?
		long current = getUserPoints(username).getCurrentPoints();
		String update = "/" + (current >= points ? "-" + (current - points) : points - current);
		ElementsRequest.makeRequest(this, PointsResponse.class, HttpMethod.PUT, ElementsEndpoints.POINTS + account + "/" + username + update);
	}
	
	@Override
	public void setCurrentUserPoints(long points, Points... existing) {
		for (Points user : existing) setCurrentUserPoints(user.getUser(), points);
	}

	@Override
	public void setCurrentUserPoints(User user, long points) {
		setCurrentUserPoints(user.getName(), points);
	}

	@Override
	public void subtractPoints(String username, long points) {
		ElementsRequest.makeRequest(this, PointsResponse.class, HttpMethod.PUT, ElementsEndpoints.POINTS + account + "/" + username + "/-" + Math.abs(points));
	}

	@Override
	public void subtractPoints(User user, long points) {
		subtractPoints(user.getName(), points);
	}

	@Override
	public void addPoints(String username, long points) {
		ElementsRequest.makeRequest(this, PointsResponse.class, HttpMethod.PUT, ElementsEndpoints.POINTS + account + "/" + username + "/" + Math.abs(points));
	}

	@Override
	public void addPoints(User user, long points) {
		addPoints(user.getName(), points);
	}

	@Override
	public void removeUserPoints(User user) {
		ElementsRequest.makeRequest(this, PointsResponse.class, HttpMethod.DELETE, ElementsEndpoints.POINTS + account + "/" + user.getName());
	}

	@Override
	public void resetCurrentPoints() {
		ElementsRequest.makeRequest(this, PointsResponse.class, HttpMethod.DELETE, ElementsEndpoints.POINTS + account + "/reset/current");
	}

	@Override
	public void resetAllPoints() {
		ElementsRequest.makeRequest(this, PointsResponse.class, HttpMethod.DELETE, ElementsEndpoints.POINTS + account + "/reset/alltime");
	}

}
