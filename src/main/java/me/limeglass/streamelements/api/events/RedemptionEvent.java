package me.limeglass.streamelements.api.events;

import java.time.Instant;

import me.limeglass.streamelements.internals.events.emitters.types.RedemptionEmitter.EventAlert;
import me.limeglass.streamelements.internals.events.emitters.types.RedemptionEmitter.Redeemer;
import me.limeglass.streamelements.internals.events.emitters.types.RedemptionEmitter.RedemptionItem;

public class RedemptionEvent extends ActivityEvent {

	private final String ID, redeemerType;
	private final RedemptionItem item;
	private final Redeemer redeemer;
	private final boolean completed;
	private final EventAlert alert;
	private final Instant updated;
	
	/**
	 * Called when someone uses a redemption.
	 */
	public RedemptionEvent(String type, Instant instant, String provider, String channel, EventAlert alert, RedemptionItem item, Redeemer redeemer, boolean completed, String ID, String redeemerType, Instant updated) {
		super(type, instant, provider, channel);
		this.redeemerType = redeemerType;
		this.completed = completed;
		this.redeemer = redeemer;
		this.updated = updated;
		this.alert = alert;
		this.item = item;
		this.ID = ID;
	}
	
	/**
	 * @return The redeemer type of the redemption event. This is not the name of the Redeemed item!
	 */
	public String getRedeemerType() {
		return redeemerType;
	}
	
	/**
	 * @return The item and all it's properties involved in the redemption event.
	 */
	public RedemptionItem getItem() {
		return item;
	}

	/**
	 * @return The redeemer of the redemption event.
	 */
	public Redeemer getRedeemer() {
		return redeemer;
	}

	/**
	 * @return Grab the alert setting for this redemption.
	 */
	public EventAlert getAlert() {
		return alert;
	}
	
	/**
	 * @return If the redemption was completed.
	 */
	public boolean isCompleted() {
		return completed;
	}
	
	/**
	 * @return The last Instant that this redemption item was updated.
	 */
	public Instant getUpdated() {
		return updated;
	}

	/**
	 * @return The ID of the redemption transaction.
	 */
	public String getID() {
		return ID;
	}
	
}
