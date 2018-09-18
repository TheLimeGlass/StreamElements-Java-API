package me.limeglass.streamelements.internals.readers;

import java.io.IOException;
import java.util.Optional;

import com.google.gson.stream.JsonReader;
import me.limeglass.streamelements.api.objects.User;
import me.limeglass.streamelements.internals.ElementsOptional;
import me.limeglass.streamelements.internals.ElementsReader;
import me.limeglass.streamelements.internals.objects.PointsImp;
import me.limeglass.streamelements.internals.objects.UserImp;
import me.limeglass.streamelements.internals.responses.PointsResponse;

public class PointsReader extends ElementsReader<PointsResponse> {
	
	/*CHEAT-SHEET:
	--------------------------------------
	{
	  "channel": "59d3fd1ac0b6073f852b80b1",
	  "username": "example",
	  "points": 0,
	  "pointsAlltime": 0,
	  "rank": 1
	}
	--------------------------------------
	*/
	
	@Override
	protected ElementsOptional<PointsResponse> read(JsonReader reader) {
		@SuppressWarnings("unused")
		String username = null, channel = null;
		long current = 0, pointsAlltime = 0;
		int rank = -1;
		try {
			reader.beginObject();
			while (reader.hasNext()) {
				String next = reader.nextName();
				switch (next) {
					case "channel":
						channel = reader.nextString();
						break;
					case "username":
						username = reader.nextString();
						break;
					case "points":
						current = reader.nextLong();
						break;
					case "pointsAlltime":
						pointsAlltime = reader.nextLong();
						break;
					case "rank":
						rank = reader.nextInt();
						break;
					default:
						reader.skipValue();
						break;
				}
			}
			reader.endObject();
		} catch (IOException exception) {
			return new ElementsOptional<PointsResponse>(exception);
		}
		User user = new UserImp(username);
		//Channel channel = new //TODO
		PointsImp points = new PointsImp(user, null, current, pointsAlltime, rank); 
		PointsResponse response = new PointsResponse(points);
		Optional<PointsResponse> optional = Optional.of(response);
		return new ElementsOptional<PointsResponse>(optional);
	}

}
