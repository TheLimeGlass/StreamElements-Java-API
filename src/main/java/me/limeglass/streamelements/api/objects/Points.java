package me.limeglass.streamelements.api.objects;

public interface Points {
	
	/**
	 * @return The amount of points this instance has.
	 */
	long getCurrentPoints();
	
	/**
	 * @return The amount of points every earned from.
	 */
	long getTotalPoints();
	
	/**
	 * @return The channel that this points instance is allocated too.
	 */
	Channel getChannel();
	
	/**
	 * @return The user involved in the points.
	 */
	User getUser();
	
	/**
	 * @return The ranking position of this points.
	 */
	int getRank();
	
}
