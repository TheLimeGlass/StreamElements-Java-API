package me.limeglass.streamelements.api.objects;

public interface Channel {
	
	/**
	 * @return The owner user of the channel.
	 */
	User getOwner();
	
	/**
	 * @return The ID of the channel.
	 */
	long getID();

}
