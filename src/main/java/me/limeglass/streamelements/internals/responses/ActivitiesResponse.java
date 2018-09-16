package me.limeglass.streamelements.internals.responses;

import me.limeglass.streamelements.internals.ElementsResponse;

public class ActivitiesResponse extends ElementsResponse {

	private final String type, provider, providerID, createdAt;
	private final Number channel;
	
	public ActivitiesResponse(Number channel, String type, String provider, String providerID, String createdAt) {
		this.providerID = providerID;
		this.createdAt = createdAt;
		this.provider = provider;
		this.channel = channel;
		this.type = type;
	}
	
	/**
	 * @return The providers ID of the Activity.
	 */
	public String getProviderID() {
		return providerID;
	}
	
	/**
	 * @return The time at which the Activity was created.
	 */
	public String getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * @return The providers name of the Activity.
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @return The channel ID of the Activity.
	 */
	public Number getChannel() {
		return channel;
	}

	/*
	-------------------------------------
	  	Type		|		Provider
	--------------------------------------
		follow				Twitch
		 tip			Twitch & Youtube
		 host				Twitch
	   subscriber		Twitch & YouTube
		cheer				Twitch
	   redemption			Twitch
		sponsor				YouTube
	   superchat			YouTube
	*/
	/**
	 * 
	 * @return The channel ID of the Activity.
	 */
	public String getType() {
		return type;
	}
	
}
