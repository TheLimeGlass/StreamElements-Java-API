package me.limeglass.streamelements.api.events;

import java.time.Instant;

import me.limeglass.streamelements.api.objects.Channel;
import me.limeglass.streamelements.api.objects.User;

public class RedemptionEvent extends ActivityEvent {
	
	private final String redemption;
	private final Number amount;
	private final User user;
	
	/**
	 * Called when someone uses a redemption.
	 */
	public RedemptionEvent(String type, Instant instant, String provider, Channel channel, Number amount, String redemption, User user) {
		super(type, instant, provider, channel);
		this.redemption = redemption;
		this.amount = amount;
		this.user = user;
	}
	
	/**
	 * @return The redemption item's name.
	 */
	public String getRedemption() {
		return redemption;
	}

	/**
	 * @return The amount the redemption item cost.
	 */
	public Number getAmount() {
		return amount;
	}
	
	/**
	 * @return The User that initiated the redemption.
	 */
	public User getUser() {
		return user;
	}
	
}
