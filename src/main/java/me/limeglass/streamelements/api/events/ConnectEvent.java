package me.limeglass.streamelements.api.events;

import me.limeglass.streamelements.internals.events.ElementsEvent;

public class ConnectEvent extends ElementsEvent {
	
	/**
	 * Called when the socket has made connection to the StreamElements host.
	 * The authentication happens at this time and will call the AuthenticatedEvent when successful.
	 */
	public ConnectEvent() {}
	
	@Override
	public String getName() {
		return "Connect";
	}
	
}
