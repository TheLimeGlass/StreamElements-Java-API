package me.limeglass.streamelements.internals.events.emitters.types;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.events.SubscriberEvent;
import me.limeglass.streamelements.api.objects.User;
import me.limeglass.streamelements.internals.events.EventDispatcher;
import me.limeglass.streamelements.internals.events.emitters.EventEmitter;
import me.limeglass.streamelements.internals.objects.UserImp;

public class SubscriberEmitter extends EventEmitter {

	public SubscriberEmitter() {
		super("subscriber");
	}

	@Override
	protected void call(Socket socket, JSONObject data) {
		long tier = data.getLong("tier");
		Number amount = data.getNumber("amount");
		String avatar = data.getString("avatar");
		String sender = data.getString("sender");
		boolean gifted = data.getBoolean("gifted");
		String message = data.getString("message");
		String username = data.getString("username");
		User user = new UserImp(username, avatar);
		User sendee = new UserImp(sender);
		SubscriberEvent event = new SubscriberEvent(type, instant, provider, channel, amount, tier, gifted, sendee, message, user);
		EventDispatcher.dispatch(event);
	}

}
