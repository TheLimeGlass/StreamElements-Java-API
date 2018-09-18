package me.limeglass.streamelements.internals.responses;

import me.limeglass.streamelements.api.objects.Points;
import me.limeglass.streamelements.internals.ElementsResponse;

public class PointsResponse extends ElementsResponse {
	
	private final Points points;
	
	public PointsResponse(Points points) {
		this.points = points;
	}
	
	/**
	 * @return The points from the request.
	 */
	public Points getPoints() {
		return points;
	}
	
	public String toString() {
		return points.toString();
	}

}
