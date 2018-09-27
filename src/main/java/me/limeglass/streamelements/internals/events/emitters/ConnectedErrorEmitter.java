package me.limeglass.streamelements.internals.events.emitters;

import java.util.Arrays;

import io.socket.client.Socket;

public class ConnectedErrorEmitter extends ElementsEmitter {

	public ConnectedErrorEmitter() {
		super(Socket.EVENT_CONNECT_ERROR);
	}

	@Override
	public void call(Socket socket, Object... args) {
		System.out.println("Connect error: " + Arrays.toString(args));
	}

}
