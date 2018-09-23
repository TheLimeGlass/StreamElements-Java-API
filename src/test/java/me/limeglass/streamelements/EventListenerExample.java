package me.limeglass.streamelements;

import me.limeglass.streamelements.api.annotations.EventListener;
import me.limeglass.streamelements.api.events.ConnectingEvent;

public class EventListenerExample {

	@EventListener
	public void onConnect(ConnectingEvent event) {
		System.out.println("Currently connecting...");
	}
	
}
