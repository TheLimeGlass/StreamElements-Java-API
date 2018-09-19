package me.limeglass.streamelements.internals.handlers;

import java.io.OutputStreamWriter;
import java.util.stream.Stream;

import me.limeglass.streamelements.internals.ElementsOptional;
import me.limeglass.streamelements.internals.handlers.ElementsRequest.HttpMethod;

public class ElementsUploader extends ElementsReaderHandler {

	/**
	 * This will handle any supporting HttpMethod's that the predicted ElementsReader may accept.
	 * 
	 * @param method The HttpMethod to handle on the predicted readers.
	 * @param output The OutputStreamWriter that allows the ElementReader to use.
	 * @param response The predicted ElementsResponse to use as Stream. (The requests API is for Stream, so it's provided a Stream)
	 */
	public static <T extends ElementsResponse> Stream<ElementsOptional<T>> handle(HttpMethod method, String url, OutputStreamWriter output, Class<T> response) {
		return findPredicted(response).stream()
				.filter(reader -> reader.acceptMethod(method))
				.map(reader -> reader.update(method, url, output));
	}
	
}
