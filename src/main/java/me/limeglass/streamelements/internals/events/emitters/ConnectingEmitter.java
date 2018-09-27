package me.limeglass.streamelements.internals.events.emitters;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.events.ConnectingEvent;
import me.limeglass.streamelements.internals.events.EventDispatcher;

public class ConnectingEmitter extends ElementsEmitter {
	
	public ConnectingEmitter() {
		super(Socket.EVENT_CONNECTING);
	}

	@Override
	public void call(Socket socket, Object... args) {
		EventDispatcher.dispatch(new ConnectingEvent());
	}

}
