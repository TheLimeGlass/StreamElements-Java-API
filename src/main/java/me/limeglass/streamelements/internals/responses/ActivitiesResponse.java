package me.limeglass.streamelements.internals.responses;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import me.limeglass.streamelements.api.objects.Activity;
import me.limeglass.streamelements.internals.dummy.ElementsResponse;

public class ActivitiesResponse extends ElementsResponse {
	
	private final List<Activity> activities = new ArrayList<Activity>();
	
	public ActivitiesResponse(Activity... activities) {
		this.activities.addAll(Lists.newArrayList(activities));
	}
	
	/**
	 * @return The activities for the request.
	 */
	public List<Activity> getActivities() {
		return activities;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		activities.forEach(activity -> builder.append(activity.toString()));
		return builder.toString();
	}

}
