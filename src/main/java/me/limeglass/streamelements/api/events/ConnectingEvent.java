package me.limeglass.streamelements.api.events;

import me.limeglass.streamelements.internals.events.ElementsEvent;

public class ConnectingEvent extends ElementsEvent {
	
	/**
	 * Called when the socket first attempts to connect.
	 */
	public ConnectingEvent() {}
	
	@Override
	public String getName() {
		return "Connecting";
	}
	
}
