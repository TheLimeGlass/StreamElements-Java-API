package me.limeglass.streamelements.internals.dummy;

public interface Cancellable {

	/**
	 * @return Checks if the event is cancelled or not.
	 */
	boolean isCancelled();
	
	/**
	 * Cancels the event.
	 * @param cancel If the event should be cancelled or not.
	 */
	void setCancelled(boolean cancel);
	
}
