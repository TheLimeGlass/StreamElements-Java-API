package me.limeglass.streamelements.api.events;

import java.time.Instant;

import me.limeglass.streamelements.api.objects.User;

public class HostEvent extends ActivityEvent {
	
	private final Number viewers;
	private final User user;
	
	/**
	 * Called when someone hosts.
	 */
	public HostEvent(String type, Instant instant, String provider, String channel, Number viewers, User user) {
		super(type, instant, provider, channel);
		this.viewers = viewers;
		this.user = user;
	}

	/**
	 * @return The amount of viewers involved in the Host.
	 */
	public Number getViewerCount() {
		return viewers;
	}
	
	/**
	 * @return The User that initiated the Host.
	 */
	public User getUser() {
		return user;
	}
	
}
