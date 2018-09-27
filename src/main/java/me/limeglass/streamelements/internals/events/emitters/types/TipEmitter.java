package me.limeglass.streamelements.internals.events.emitters.types;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.events.TipEvent;
import me.limeglass.streamelements.api.objects.User;
import me.limeglass.streamelements.internals.events.EventDispatcher;
import me.limeglass.streamelements.internals.events.emitters.EventEmitter;
import me.limeglass.streamelements.internals.objects.UserImp;

public class TipEmitter extends EventEmitter {

	public TipEmitter() {
		super("tip");
	}

	@Override
	protected void call(Socket socket, JSONObject data) {
		String tipID = data.getString("tipId");
		String avatar = data.getString("avatar");
		Number amount = data.getNumber("amount");
		String message = data.getString("message");
		String currency = data.getString("currency");
		String username = data.getString("username");
		User user = new UserImp(username, avatar);
		TipEvent event = new TipEvent(type, instant, provider, channel, amount, tipID, currency, message, user);
		EventDispatcher.dispatch(event);
	}

}
