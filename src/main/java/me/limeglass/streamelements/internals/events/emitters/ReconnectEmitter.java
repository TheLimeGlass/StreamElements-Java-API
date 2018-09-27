package me.limeglass.streamelements.internals.events.emitters;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.events.ReconnectEvent;
import me.limeglass.streamelements.internals.events.EventDispatcher;

public class ReconnectEmitter extends ElementsEmitter {
	
	public ReconnectEmitter() {
		super(Socket.EVENT_RECONNECT);
	}

	@Override
	public void call(Socket socket, Object... args) {
		EventDispatcher.dispatch(new ReconnectEvent());
	}

}
