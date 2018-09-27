package me.limeglass.streamelements.internals.handlers;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.stream.JsonReader;

import me.limeglass.streamelements.internals.ElementsOptional;
import me.limeglass.streamelements.internals.dummy.ElementsResponse;
import me.limeglass.streamelements.internals.handlers.ElementsRequest.HttpMethod;

public abstract class ElementsReader<T extends ElementsResponse> {
	
	/**
	 * Grabs the wanted ElementsResponse from the ElementReader.
	 * 
	 * @return Read the current ElementsResponse.
	 */
	protected abstract ElementsOptional<T> read(JsonReader reader);
	
	/**
	 * Checks if the reader can accept other HttpMethods besides get.
	 * Override this method along with the `execute` method to add support.
	 * 
	 * @return Boolean if the reader accepted the HttpMethod.
	 */
	protected boolean acceptMethod(HttpMethod method) {
		return false;
	}
	
	/**
	 * Triggers the execute method of the reader, after checking if the acceptMethod method returned true.
	 * If you write to the OutputStreamWriter make sure you flush, gross.
	 */
	protected ElementsOptional<T> update(HttpMethod method, String url, OutputStreamWriter output) {
		return new ElementsOptional<T>();
	}

	/**
	 * Prime the JsonReader up to the defined named value.
	 */
	protected JsonReader prime(String prime, JsonReader reader) throws IOException {
		while (reader.hasNext())
			if (reader.nextName().equalsIgnoreCase(prime)) break;
		return reader;
	}

}
