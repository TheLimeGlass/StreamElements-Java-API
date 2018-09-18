package me.limeglass.streamelements.internals.objects;

import me.limeglass.streamelements.api.objects.Channel;
import me.limeglass.streamelements.api.objects.User;

public class ChannelImp implements Channel {

	private final User owner;
	private final long ID;
	
	public ChannelImp(User owner, long ID) {
		this.owner = owner;
		this.ID = ID;
	}
	
	@Override
	public User getOwner() {
		return owner;
	}

	@Override
	public long getID() {
		return ID;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("owner=" + owner);
		builder.append(",ID=" + ID);
		return builder.toString();
	}

}
