package me.limeglass.streamelements.internals.events;

import io.socket.client.Socket;

public class ElementsEvent {

	protected static Socket socket;
	
	public static void setSocket(Socket s) {
		socket = s;
	}
	
}
