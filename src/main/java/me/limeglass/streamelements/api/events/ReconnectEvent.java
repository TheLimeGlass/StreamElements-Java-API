package me.limeglass.streamelements.api.events;

import me.limeglass.streamelements.internals.events.ElementsEvent;

public class ReconnectEvent extends ElementsEvent {
	
	/**
	 * Called when socket.io attempts to reconnect.
	 */
	public ReconnectEvent() {}
	
	@Override
	public String getName() {
		return "Reconnect";
	}
	
}
