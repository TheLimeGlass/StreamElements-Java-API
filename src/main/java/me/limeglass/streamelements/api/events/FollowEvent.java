package me.limeglass.streamelements.api.events;

import java.time.Instant;

import me.limeglass.streamelements.api.objects.Channel;
import me.limeglass.streamelements.api.objects.User;

public class FollowEvent extends ActivityEvent {
	
	private final User user;
	
	/**
	 * Called when someone follows.
	 */
	public FollowEvent(String type, Instant instant, String provider, Channel channel, User user) {
		super(type, instant, provider, channel);
		this.user = user;
	}
	
	/**
	 * @return The User that initiated the Follow.
	 */
	public User getUser() {
		return user;
	}
	
}
