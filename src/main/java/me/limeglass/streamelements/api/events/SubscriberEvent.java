package me.limeglass.streamelements.api.events;

import java.time.Instant;

import me.limeglass.streamelements.api.objects.User;

public class SubscriberEvent extends ActivityEvent {
	
	private final User user, sender;
	private final String message;
	private final boolean gifted;
	private final Number amount;
	private final long tier;
	
	/**
	 * Called when someone subscribes.
	 */
	public SubscriberEvent(String type, Instant instant, String provider, String channel, Number amount, long tier, boolean gifted, User sender, String message, User user) {
		super(type, instant, provider, channel);
		this.message = message;
		this.amount = amount;
		this.gifted = gifted;
		this.sender = sender;
		this.user = user;
		this.tier = tier;
	}
	
	/**
	 * @return The message used in the subscription.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return If the subscription was gifted.
	 */
	public boolean isGifted() {
		return gifted;
	}
	
	/**
	 * @return The User that received the subscription.
	 */
	public User getReceiver() {
		return user;
	}
	
	/**
	 * @return The amount of subscriptions, if it was a multiple gifted subscription it would be more.
	 */
	public Number getAmount() {
		return amount;
	}
	
	/**
	 * @return The sender that send the possible gifted subscription.
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * @return The tier of the subscription.
	 */
	public long getTier() {
		return tier;
	}
	
}
