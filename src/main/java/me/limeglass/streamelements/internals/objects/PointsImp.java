package me.limeglass.streamelements.internals.objects;

import me.limeglass.streamelements.api.objects.Channel;
import me.limeglass.streamelements.api.objects.Points;
import me.limeglass.streamelements.api.objects.User;

public class PointsImp implements Points {

	private final long points, pointsAlltime;
	private final Channel channel;
	private final User user;
	private final int rank;
	
	public PointsImp(User user, Channel channel, long points, long pointsAlltime, int rank) {
		this.pointsAlltime = pointsAlltime;
		this.channel = channel;
		this.points = points;
		this.user = user;
		this.rank = rank;
	}

	@Override
	public long getCurrentPoints() {
		return points;
	}

	@Override
	public long getTotalPoints() {
		return pointsAlltime;
	}
	
	@Override
	public Channel getChannel() {
		return channel;
	}
	
	@Override
	public User getUser() {
		return user;
	}

	@Override
	public int getRank() {
		return rank;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("pointsAlltime=" + pointsAlltime);
		builder.append(",user=" + user.getName());
		builder.append(",channel=" + channel);
		builder.append(",points=" + points);
		builder.append(",rank=" + rank);
		return builder.toString();
	}

}
