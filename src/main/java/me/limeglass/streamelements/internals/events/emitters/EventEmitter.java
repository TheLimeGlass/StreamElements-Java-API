package me.limeglass.streamelements.internals.events.emitters;

import java.time.Instant;

import org.json.JSONObject;

import io.socket.client.Socket;

public abstract class EventEmitter extends ElementsEmitter {
	
	protected String type, provider;
	protected Instant instant;
	protected String channel;
	
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
	public String getEventName() {
		return type;
	}

	@Override
	public void call(Socket socket, Object... args) {
		JSONObject object = (JSONObject)args[0];
		if (object.has("provider"))
			provider = object.getString("provider");
		else 
			provider = "twitch";
		instant = Instant.parse(object.getString("createdAt"));
		channel = object.getString("channel");
		if (object.has("type") && object.getString("type").equalsIgnoreCase(type))
			call(socket, object.getJSONObject("data"));
		else if (object.has("redeemerType") && type.equalsIgnoreCase("redemption"))
			call(socket, object);
	}
	
	protected abstract void call(Socket socket, JSONObject data);

}
