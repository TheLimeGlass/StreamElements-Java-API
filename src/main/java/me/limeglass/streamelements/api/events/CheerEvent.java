package me.limeglass.streamelements.api.events;

import java.time.Instant;

import me.limeglass.streamelements.api.objects.User;

public class CheerEvent extends ActivityEvent {
	
	private final String message;
	private final Number amount;
	private final User user;
	
	/**
	 * Called when someone cheers.
	 */
	public CheerEvent(String type, Instant instant, String provider, String channel, Number amount, User user, String message) {
		super(type, instant, provider, channel);
		this.message = message;
		this.amount = amount;
		this.user = user;
	}

	/**
	 * @return The amount of bits 
	 */
	public Number getAmount() {
		return amount;
	}
	
	/**
	 * @return The message involved in the cheer.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return The User involved in the cheer event.
	 */
	public User getuser() {
		return user;
	}
	
}
