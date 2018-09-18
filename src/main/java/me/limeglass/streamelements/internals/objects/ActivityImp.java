package me.limeglass.streamelements.internals.objects;

import me.limeglass.streamelements.api.objects.Activity;

public class ActivityImp implements Activity {

	private final String type, provider, providerID, createdAt;
	private final Number channel;
	
	public ActivityImp(Number channel, String type, String provider, String providerID, String createdAt) {
		this.providerID = providerID;
		this.createdAt = createdAt;
		this.provider = provider;
		this.channel = channel;
		this.type = type;
	}
	
	@Override
	public String getProviderID() {
		return providerID;
	}

	@Override
	public String getTimestamp() {
		return createdAt;
	}

	@Override
	public String getProvider() {
		return provider;
	}
	
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
	public String getType() {
		return type;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("providerID=" + providerID);
		builder.append(",timestamp=" + createdAt);
		builder.append(",provider=" + provider);
		builder.append(",channel=" + channel);
		builder.append(",type=" + type);
		return builder.toString();
	}

}
