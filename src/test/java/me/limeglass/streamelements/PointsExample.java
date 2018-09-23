package me.limeglass.streamelements;

import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.objects.Points;
import me.limeglass.streamelements.api.objects.User;

public class PointsExample {

	public static void execute(StreamElements instance) {
		
		//Grab the Points instance.
		Points points = instance.getUserPoints("limeglass");
		
		//Grab the user instance.
		User user = points.getUser();
		
		//Example usage.
		System.out.println(user.getName()
				+ " is #" + points.getRank() + " in the leaderboard"
				+ " with " + points.getCurrentPoints() + " points! PogChamp");
		
		//Set points of the user.
		instance.setCurrentUserPoints(user, 1500);
		//Update the points instance.
		points = instance.getUserPoints(user);
		System.out.println(user.getName() + " now has " + points.getCurrentPoints() + " points!");
		
		//Remove the player from the points system.
		instance.removeUserPoints(user);
		points = instance.getUserPoints(user);
		System.out.println(user.getName() + " now has " + points.getCurrentPoints() + " points!"); //Should not say 0.
	}
	
}
