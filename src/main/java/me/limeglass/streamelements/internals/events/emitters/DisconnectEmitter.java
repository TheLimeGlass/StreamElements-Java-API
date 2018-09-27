package me.limeglass.streamelements.internals.events.emitters;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.events.DisconnectEvent;
import me.limeglass.streamelements.internals.events.EventDispatcher;

public class DisconnectEmitter extends ElementsEmitter {

	public DisconnectEmitter(StreamElements instance) {
		super(Socket.EVENT_DISCONNECT);
	}

	@Override
	public void call(Socket socket, Object... args) {
		EventDispatcher.dispatch(new DisconnectEvent());
	}

}
