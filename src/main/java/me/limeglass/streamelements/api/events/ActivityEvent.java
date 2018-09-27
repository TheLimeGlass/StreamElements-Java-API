package me.limeglass.streamelements.api.events;

import java.time.Instant;

import me.limeglass.streamelements.api.objects.Channel;
import me.limeglass.streamelements.internals.events.ElementsEvent;

public class ActivityEvent extends ElementsEvent {

	private final String provider, type;
	private final Instant instant;
	private final Channel channel;
	
	/**
	 * Called when an Activity happens.
	 */
	public ActivityEvent(String type, Instant instant, String provider, Channel channel) {
		this.provider = provider;
		this.channel = channel;
		this.instant = instant;
		this.type = type;
	}
	
	/**
	 * @return The provider used from the event, E.g: Twitch, Restream etc.
	 */
	public String getProvider() {
		return provider;
	}
	
	/**
	 * @return The Channel where the Activity came from.
	 */
	public Channel getChannel() {
		return channel;
	}
	
	/**
	 * @return The Instant when the Activity was initiated.
	 */
	public Instant getInstant() {
		return instant;
	}
	
	/**
	 * @return The type of the activity, raid, follow, cheer etc.
	 */
	public String getType() {
		return type;
	}
	
}
