package me.limeglass.streamelements.internals.events;

import io.socket.client.Socket;

public abstract class ElementsEvent {

	protected static Socket socket;
	
	public static void setSocket(Socket s) {
		socket = s;
	}
	
	public abstract String getName();
	
}
