package me.limeglass.streamelements.internals.events.emitters.types;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.events.FollowEvent;
import me.limeglass.streamelements.api.objects.User;
import me.limeglass.streamelements.internals.events.EventDispatcher;
import me.limeglass.streamelements.internals.events.emitters.EventEmitter;
import me.limeglass.streamelements.internals.objects.UserImp;

public class FollowEmitter extends EventEmitter {

	public FollowEmitter() {
		super("follow");
	}

	@Override
	protected void call(Socket socket, JSONObject data) {
		String avatar = data.getString("avatar");
		String username = data.getString("username");
		User user = new UserImp(username, avatar);
		FollowEvent event = new FollowEvent(type, instant, provider, channel, user);
		EventDispatcher.dispatch(event);
	}

}
