package me.limeglass.streamelements;

import me.limeglass.streamelements.api.annotations.EventListener;
import me.limeglass.streamelements.api.events.AuthenticatedEvent;
import me.limeglass.streamelements.api.events.ConnectEvent;
import me.limeglass.streamelements.api.events.ConnectingEvent;
import me.limeglass.streamelements.api.events.FollowEvent;
import me.limeglass.streamelements.api.events.HostEvent;

public class EventListenerExample {
	
	@EventListener
	public void onConnect(ConnectingEvent event) {
		System.out.println("Currently connecting...");
	}
	
	@EventListener
	public void onConnection(ConnectEvent event) {
		System.out.println("Connected to socket.");
	}
	
	@EventListener
	public void onAuthenticated(AuthenticatedEvent event) {
		StringBuilder builder = new StringBuilder();
		builder.append("Authenticated with StreamElements.");
		builder.append("\nchannel ID: " + event.getChannelID());
		builder.append("\nclient ID: " + event.getClientID());
		builder.append("\nmessage: " + event.getMessage());
		System.out.println(builder.toString());
	}
	
	@EventListener
	public void onHost(HostEvent event) {
		System.out.println("Host from " + event.getUser().getName() + " at " + event.getInstant() + " with " + event.getViewerCount() + " viewers!");
	}
	
	@EventListener
	public void onFollow(FollowEvent event) {
		System.out.println("User " + event.getUser().getName() + " has just followed at " + event.getInstant());
	}
	
}
