package me.limeglass.streamelements.api.objects;

public interface Activity {

	/**
	 * @return The providers ID of the Activity.
	 */
	String getProviderID();
	
	/**
	 * @return The time at which the Activity was created.
	 */
	String getTimestamp();
	
	/**
	 * @return The providers name of the Activity.
	 */
	String getProvider();
	
	/**
	 * @return The channel ID of the Activity.
	 */
	Number getChannel();
	
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
	String getType();

}
