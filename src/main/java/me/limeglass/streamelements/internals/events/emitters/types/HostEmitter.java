package me.limeglass.streamelements.internals.events.emitters.types;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.events.HostEvent;
import me.limeglass.streamelements.api.objects.User;
import me.limeglass.streamelements.internals.events.EventDispatcher;
import me.limeglass.streamelements.internals.events.emitters.EventEmitter;
import me.limeglass.streamelements.internals.objects.UserImp;

public class HostEmitter extends EventEmitter {

	public HostEmitter() {
		super("host");
	}

	@Override
	protected void call(Socket socket, JSONObject data) {
		String avatar = data.getString("avatar");
		Number viewers = data.getNumber("amount");
		String username = data.getString("username");
		User user = new UserImp(username, avatar);
		HostEvent event = new HostEvent(type, instant, provider, channel, viewers, user);
		EventDispatcher.dispatch(event);
	}

}
