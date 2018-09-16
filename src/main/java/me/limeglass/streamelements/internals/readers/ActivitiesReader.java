package me.limeglass.streamelements.internals.readers;

import java.io.IOException;
import java.util.Optional;

import com.google.gson.stream.JsonReader;

import me.limeglass.streamelements.internals.ElementsOptional;
import me.limeglass.streamelements.internals.ElementsReader;
import me.limeglass.streamelements.internals.responses.ActivitiesResponse;

public class ActivitiesReader extends ElementsReader<ActivitiesResponse> {

	/*CHEAT-SHEET:
	--------------------------------------
	  	Type		|		Provider
	--------------------------------------
		follow				Twitch
		 tip			Twitch & Youtube
		 host				Twitch
	   subscriber		Twitch & YouTube
		cheer				Twitch
	   redemption			Twitch
		sponsor				YouTube
	   superchat			YouTube
	--------------------------------------
					JSON
	--------------------------------------
	{
		channel: Number;
		type: String;
		provider: String;
		providerId: String;
		createdAt: Date;
		flagged: Boolean;
		data: {
			username: String;
			tier: String;
			currency: String;
			amount: Number
			message: String;
			avatar: String;
			sender: String;
			gifted: Boolean;
		}
	}
	--------------------------------------
	*/
	
	@Override
	protected ElementsOptional<ActivitiesResponse> read(JsonReader reader) {
		String type, provider, providerID, createdAt;
		type = provider = providerID = createdAt = null;
		Number channel = null;
		//boolean flagged;
		try {
			reader.beginObject();
			while (reader.hasNext()) {
				String next = reader.nextName();
				switch (next) {
					case "channel":
						//TODO test numbers
						channel = reader.nextLong();
						break;
					case "type":
						type = reader.nextString();
						break;
					case "provider":
						provider = reader.nextString();
						break;
					case "providerId":
						providerID = reader.nextString();
						break;
					case "createdAt":
						//TODO test
						providerID = reader.nextString();
						break;
					//TODO make the data section an Activity instance.
					default:
						reader.skipValue();
						break;
				}
			}
		} catch (IOException exception) {
			return new ElementsOptional<ActivitiesResponse>(exception);
		}
		ActivitiesResponse response = new ActivitiesResponse(channel, type, provider, providerID, createdAt);
		Optional<ActivitiesResponse> optional = Optional.of(response);
		return new ElementsOptional<ActivitiesResponse>(optional);
	}

}
