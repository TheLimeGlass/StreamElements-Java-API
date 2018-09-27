package me.limeglass.streamelements.api.events;

import me.limeglass.streamelements.internals.events.ElementsEvent;

public class AuthenticatedEvent extends ElementsEvent {
	
	private final String clientID, message, channelID;
	
	/**
	 * Called when the authentication has been successful.
	 */
	public AuthenticatedEvent(String clientID, String message, String channelID) {
		this.channelID = channelID;
		this.clientID = clientID;
		this.message = message;
	}

	/**
	 * @return The channel ID that has been connected too.
	 */
	public String getChannelID() {
		return channelID;
	}
	
	/**
	 * @return A randomly generated ID per new instance of authentication. Pretty useless for us.
	 */
	public String getClientID() {
		return clientID;
	}

	/**
	 * @return The message from StreamElements when authenticated. It's just "Welcome XD"
	 */
	public String getMessage() {
		return message;
	}
	
}
