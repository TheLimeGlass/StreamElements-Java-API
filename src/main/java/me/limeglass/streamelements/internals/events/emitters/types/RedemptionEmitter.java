package me.limeglass.streamelements.internals.events.emitters.types;

import java.time.Instant;

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
	
	/*
	{
		"input":[],
		"createdAt":"2018-09-27T06:37:29.638Z",
		"item":{
			"cost":0,
			"alert":{
				"graphics":{
					"duration":8
				},
				"audio":{
					"volume":0,
					"src":"https://cdn.streamelements.com/uploads/9d6f795f-c19d-4423-8c79-3f9be00b3a17.mp3"
				},
				"enabled":false
			},
			"name":"Test",
			"userInput":[],
			"_id":"5bac5fef89739da12e01d465",
			"type":"perk"
		},
		"redeemer":{
			"inactive":false,
			"_id":"59a8a4ab4123360b55996d7e",
			"avatar":"https://static-cdn.jtvnw.net/jtv_user_pictures/limeglass-profile_image-dccc7544cb89eef6-300x300.png",
			"username":"limeglass"
		},
		"channel":"5982c439bca1893b307cc5be",
		"completed":false,
		"_id":"5bac7aa96a52e36a155ad778",
		"redeemerType":"Channel",
		"updatedAt":"2018-09-27T06:37:29.638Z"
	}
	*/
	
	//TODO support userInput and input.
	@Override
	protected void call(Socket socket, JSONObject data) {
		JSONObject item = data.getJSONObject("item");
		JSONObject alert = item.getJSONObject("alert");
		int duration = alert.getJSONObject("graphics").getInt("duration");
		JSONObject audio = alert.getJSONObject("audio");
		Number volume = audio.getInt("volume");
		String src = audio.getString("src");
		boolean enabled = alert.getBoolean("enabled");
		EventAlert eventAlert = new EventAlert(duration, volume, src, enabled);
		long cost = item.getLong("cost");
		String name = item.getString("name");
		String type = item.getString("type");
		String itemID = item.getString("_id");
		RedemptionItem redemptionItem = new RedemptionItem(eventAlert, cost, name, type, itemID);
		JSONObject redeemer = data.getJSONObject("redeemer");
		boolean inactive = redeemer.getBoolean("inactive");
		String redeemerID = redeemer.getString("_id");
		String avatar = redeemer.getString("avatar");
		String username = redeemer.getString("username");
		User user = new UserImp(username, avatar);
		Redeemer redeemerEvent = new Redeemer(inactive, redeemerID, user);
		boolean completed = data.getBoolean("completed");
		String ID = data.getString("_id");
		String redeemerType = data.getString("redeemerType");
		Instant updated = Instant.parse(data.getString("updatedAt"));
		RedemptionEvent event = new RedemptionEvent(this.type, instant, provider, channel, eventAlert, redemptionItem, redeemerEvent, completed, ID, redeemerType, updated);
		EventDispatcher.dispatch(event);
	}
	
	public class Redeemer {
		
		private final boolean inactive;
		private final User user;
		private final String ID;

		public Redeemer(boolean inactive, String ID, User user) {
			this.inactive = inactive;
			this.user = user;
			this.ID = ID;
		}
		
		/**
		 * @return A boolean if the user is inactive.
		 */
		public boolean isInactive() {
			return inactive;
		}

		/**
		 * @return The user that was redeeming the item.
		 */
		public User getUser() {
			return user;
		}

		/**
		 * @return A generated ID for the user redeeming the item.
		 */
		public String getID() {
			return ID;
		}
		
	}
	
	public class RedemptionItem {
		
		private final String name, type, ID;
		private final EventAlert alert;
		private final long cost;

		public RedemptionItem(EventAlert alert, long cost, String name, String type, String ID) {
			this.alert = alert;
			this.cost = cost;
			this.name = name;
			this.type = type;
			this.ID = ID;
		}

		/**
		 * @return The alert involved in the redemption item.
		 */
		public EventAlert getAlert() {
			return alert;
		}
		
		/**
		 * @return The name of this redemption item.
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * @return The type of this redemption item. Sound effect, perk, access code etc.
		 */
		public String getType() {
			return this.type;
		}

		/**
		 * @return The cost of the redemption item.
		 */
		public long getCost() {
			return cost;
		}

		/**
		 * @return The ID of the redemption item transaction.
		 */
		public String getID() {
			return ID;
		}
		
	}
	
	public class EventAlert {
		
		private final boolean enabled;
		private final Number volume;
		private final int duration;
		private final String audio;

		public EventAlert(int duration, Number volume, String audio, boolean enabled) {
			this.duration = duration;
			this.enabled = enabled;
			this.volume = volume;
			this.audio = audio;
		}

		/**
		 * @return The duration that the alert lasts for.
		 */
		public int getDuration() {
			return duration;
		}

		/**
		 * @return The volume setting of the alert.
		 */
		public Number getVolume() {
			return volume;
		}

		/**
		 * @return The state of the alert, if it's enabled or not.
		 */
		public boolean isEnabled() {
			return enabled;
		}

		/**
		 * @return The audio source folder of the mp3.
		 */
		public String getAudio() {
			return audio;
		}
		
	}

}
