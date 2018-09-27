package me.limeglass.streamelements.internals.events.emitters.types;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.events.RedemptionEvent;
import me.limeglass.streamelements.api.objects.User;
import me.limeglass.streamelements.internals.events.EventDispatcher;
import me.limeglass.streamelements.internals.events.emitters.EventEmitter;
import me.limeglass.streamelements.internals.objects.UserImp;

public class RedemptionEmitter extends EventEmitter {

	public RedemptionEmitter() {
		super("redemption");
	}
	
	@Override
	protected void call(Socket socket, JSONObject data) {
		long amount = data.getLong("amount");
		String avatar = data.getString("avatar");
		String username = data.getString("username");
		String redemption = data.getString("redemption");
		User user = new UserImp(username, avatar);
		RedemptionEvent event = new RedemptionEvent(type, instant, provider, channel, amount, redemption, user);
		EventDispatcher.dispatch(event);
	}

}
