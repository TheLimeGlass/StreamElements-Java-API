package me.limeglass.streamelements.internals.objects;

public class ActivityData {
		
	private final String username, tier, currency, message, avatar, sender;
	private final boolean gifted;
	private final Number amount;
	
	public ActivityData(String username, String tier, String currency, String message, String avatar, String sender, Number amount, boolean gifted) {
		this.username = username;
		this.currency = currency;
		this.message = message;
		this.avatar = avatar;
		this.gifted = gifted;
		this.sender = sender;
		this.amount = amount;
		this.tier = tier;
	}
	
	public String getCurrency() {
		return currency;
	}

	public String getUsername() {
		return username;
	}
	
	public boolean wasGifted() {
		return gifted;
	}

	public String getMessage() {
		return message;
	}

	public String getAvatar() {
		return avatar;
	}
	
	public Number getAmount() {
		return amount;
	}

	public String getSender() {
		return sender;
	}
	
	public String getTier() {
		return tier;
	}

}
