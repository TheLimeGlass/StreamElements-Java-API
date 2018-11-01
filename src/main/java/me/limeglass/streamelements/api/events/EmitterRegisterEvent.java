package me.limeglass.streamelements.api.events;

import me.limeglass.streamelements.internals.dummy.Cancellable;
import me.limeglass.streamelements.internals.events.ElementsEvent;
import me.limeglass.streamelements.internals.events.emitters.ElementsEmitter;

public class EmitterRegisterEvent extends ElementsEvent implements Cancellable {
	
	private final ElementsEmitter emitter;
	private boolean cancelled = false;

	/**
	 * Called when an emitter gets registered.
	 */
	public EmitterRegisterEvent(ElementsEmitter emitter) {
		this.emitter = emitter;
	}

	/**
	 * @return The emitter involved in the registering event.
	 */
	public ElementsEmitter getEmitter() {
		return emitter;
	}
	
	/**
	 * Cancels the event.
	 * @param cancel If the event should be cancelled or not.
	 */
	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	/**
	 * @return Checks if the event is cancelled or not.
	 */
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public String getName() {
		return "EmitterRegister: " + emitter.getEventName();
	}
	
}
