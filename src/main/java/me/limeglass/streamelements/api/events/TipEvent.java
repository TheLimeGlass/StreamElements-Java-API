package me.limeglass.streamelements.api.events;

import java.time.Instant;

import me.limeglass.streamelements.api.objects.Channel;
import me.limeglass.streamelements.api.objects.User;

public class TipEvent extends ActivityEvent {
	
	private final String tipID, currency, message;
	private final Number amount;
	private final User user;
	
	/**
	 * Called when someone Donates/Tips.
	 */
	public TipEvent(String type, Instant instant, String provider, Channel channel, Number amount, String tipID, String currency, String message, User user) {
		super(type, instant, provider, channel);
		this.currency = currency;
		this.message = message;
		this.amount = amount;
		this.tipID = tipID;
		this.user = user;
	}
	
	/**
	 * @return The currency name used in the donation/tip.
	 */
	public String getCurrency() {
		return currency;
	}
	
	/**
	 * @return The message used in the Donation/Tip
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return The amount donated/tipped.
	 */
	public Number getAmount() {
		return amount;
	}
	
	/**
	 * @return The tip ID generated as the donation happens.
	 */
	public String getTipID() {
		return tipID;
	}
	
	/**
	 * @return The User that initiated the Donation.
	 */
	public User getUser() {
		return user;
	}
	
}
