package me.limeglass.streamelements.internals.events.emitters;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.events.ConnectEvent;
import me.limeglass.streamelements.internals.events.EventDispatcher;

public class ConnectEmitter extends ElementsEmitter {

	private StreamElements instance;
	
	public ConnectEmitter(StreamElements instance) {
		super(Socket.EVENT_CONNECT);
		this.instance = instance;
	}

	@Override
	public void call(Socket socket, Object... args) {
		JSONObject object = new JSONObject();
		object.put("method", "jwt");
		object.put("token", instance.getToken());
		socket.emit("authenticate", object); //Does not return anything so we can't tell until the authentication event sadly.
		EventDispatcher.dispatch(new ConnectEvent());
	}

}
