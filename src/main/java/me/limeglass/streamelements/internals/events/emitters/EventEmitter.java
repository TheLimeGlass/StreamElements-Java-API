package me.limeglass.streamelements.internals.events.emitters;

import java.time.Instant;

import org.json.JSONObject;

import io.socket.client.Socket;
import me.limeglass.streamelements.api.objects.Channel;
import me.limeglass.streamelements.internals.objects.ChannelImp;

public abstract class EventEmitter extends ElementsEmitter {
	
	protected String type, provider;
	protected Instant instant;
	protected Channel channel;
	
	public EventEmitter(String type) {
		super("event");
		this.type = type;
	}
	/*
	TODO: event:test this is the format for them, support them overtime.
	{
		"listener":"follower-latest",
		"event":{
			"name":"citricbot",
			"avatar":"https://static-cdn.jtvnw.net/jtv_user_pictures/b7436820-af5d-44cf-a3b2-088407dec892-profile_image-300x300.png"
		}
	}
	
	{
		"listener":"host-latest",
		"event":{
			"amount":0,
			"name":"CitricBot",
			"avatar":"https://static-cdn.jtvnw.net/jtv_user_pictures/b7436820-af5d-44cf-a3b2-088407dec892-profile_image-300x300.png"
		}
	}
	*/

	@Override
	public void call(Socket socket, Object... args) {
		JSONObject object = (JSONObject)args[0];
		System.out.println("Event: " + object.toString());
		provider = object.getString("provider");
		instant = Instant.parse(object.getString("createdAt"));
		channel = new ChannelImp(Long.parseLong(object.getString("channel")));
		System.out.println("Event: " + object.toString());
		if (object.getString("type").equalsIgnoreCase(type))
			call(socket, object.getJSONObject("data"));
	}
	
	protected abstract void call(Socket socket, JSONObject data);

}
