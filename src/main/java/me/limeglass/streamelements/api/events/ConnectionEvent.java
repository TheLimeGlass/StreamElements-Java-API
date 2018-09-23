package me.limeglass.streamelements.api.events;

import me.limeglass.streamelements.internals.events.ElementsEvent;

public class ConnectionEvent extends ElementsEvent {

	private final boolean successful;
	
	public ConnectionEvent(boolean successful) {
		this.successful = successful;
	}

	/**
	 * @return If the connection is successful.
	 */
	public boolean isSuccessful() {
		return successful;
	}
	
}
