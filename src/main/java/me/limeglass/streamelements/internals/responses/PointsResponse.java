package me.limeglass.streamelements.internals.responses;

import me.limeglass.streamelements.api.objects.Points;
import me.limeglass.streamelements.internals.handlers.ElementsResponse;

public class PointsResponse extends ElementsResponse {
	
	protected final Points points;
	
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
	
	public class PointsUpdateResponse extends PointsResponse {
		
		public PointsUpdateResponse(Points points) {
			super(points);
		}
		
		//Add return statements.
		
		public String toString() {
			return points.toString();
		}

	}

}
