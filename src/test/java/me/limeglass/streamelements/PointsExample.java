package me.limeglass.streamelements;

import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.objects.Points;

public class PointsExample {

	public static void execute(StreamElements instance) {
		
		//Grab the Points instance
		Points points = instance.getUserPoints("limeglass");
		
		//Example usage
		System.out.println(points.getUser().getName()
				+ " is #" + points.getRank() + " in the leaderboard"
				+ " with " + points.getCurrentPoints() + " points! PogChamp");
		
		//Add one point to the user.
		instance.setCurrentUserPoints(points.getUser(), 1500);
		points = instance.getUserPoints("limeglass");
		System.out.println(points.getUser().getName() + " now has " + points.getCurrentPoints() + " points!");
	}
	
}
