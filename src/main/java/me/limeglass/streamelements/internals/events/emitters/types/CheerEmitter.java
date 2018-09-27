package me.limeglass.streamelements.internals.events.emitters.types;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.events.CheerEvent;
import me.limeglass.streamelements.api.objects.User;
import me.limeglass.streamelements.internals.events.EventDispatcher;
import me.limeglass.streamelements.internals.events.emitters.EventEmitter;
import me.limeglass.streamelements.internals.objects.UserImp;

public class CheerEmitter extends EventEmitter {

	public CheerEmitter() {
		super("cheer");
	}

	@Override
	protected void call(Socket socket, JSONObject data) {
		String username = data.getString("username");
		String message = data.getString("message");
		Number amount = data.getNumber("amount");
		String avatar = data.getString("avatar");
		User user = new UserImp(username, avatar);
		CheerEvent event = new CheerEvent(type, instant, provider, channel, amount, user, message);
		EventDispatcher.dispatch(event);
	}

}
