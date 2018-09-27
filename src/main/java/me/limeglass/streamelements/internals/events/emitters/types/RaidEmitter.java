package me.limeglass.streamelements.internals.events.emitters.types;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.internals.events.emitters.EventEmitter;

public class RaidEmitter extends EventEmitter {

	public RaidEmitter() {
		super("raid");
	}

	@Override
	protected void call(Socket socket, JSONObject data) {
		System.out.println("Raid: " + data.toString());
		//String avatar = data.getString("avatar");
		//Number viewers = data.getNumber("amount");
		//String username = data.getString("username");
		//User user = new UserImp(username, avatar);
		//HostEvent event = new HostEvent(type, instant, provider, channel, viewers, user);
		//EventDispatcher.dispatch(event);
	}

}
