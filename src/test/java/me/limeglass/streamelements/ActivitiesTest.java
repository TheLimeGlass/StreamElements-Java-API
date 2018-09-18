package me.limeglass.streamelements;

import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.objects.Points;

public class ActivitiesTest {

	public static void execute(StreamElements instance) {
		Points points = instance.getUserPoints("limeglass");
		System.out.println(points.getUser().getName()
				+ " is #" + points.getRank() + " in the leaderboard"
				+ " with " + points.getCurrentPoints() + " points! PogChamp");
	}
	
}
