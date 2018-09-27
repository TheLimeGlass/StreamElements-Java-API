package me.limeglass.streamelements.internals.events.emitters;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.events.AuthenticatedEvent;
import me.limeglass.streamelements.internals.events.EventDispatcher;

public class AuthenticateEmitter extends ElementsEmitter {
	
	public AuthenticateEmitter() {
		super("authenticated");
	}

	@Override
	public void call(Socket socket, Object... args) {
		JSONObject object = (JSONObject)args[0];
		AuthenticatedEvent event = new AuthenticatedEvent(object.getString("clientId"), object.getString("message"), object.getString("channelId"));
		EventDispatcher.dispatch(event);
	}

}
