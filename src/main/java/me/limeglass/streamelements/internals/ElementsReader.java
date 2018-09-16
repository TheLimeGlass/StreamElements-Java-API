package me.limeglass.streamelements.internals;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class ElementsReader<T extends ElementsResponse> {
	
	/**
	 * Grabs the wanted ElementsResponse from the ElementReader.
	 * 
	 * @return Read the current ElementsResponse.
	 */
	protected abstract ElementsOptional<T> read(JsonReader reader);

	/**
	 * Prime the JsonReader up to the defined named value.
	 */
	protected JsonReader prime(String prime, JsonReader reader) throws IOException {
		while (reader.hasNext())
			if (reader.nextName().equalsIgnoreCase(prime)) break;
		return reader;
	}

}
