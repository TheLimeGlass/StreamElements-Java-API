package me.limeglass.streamelements.internals;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.stream.JsonReader;

public class ElementsRequest {

	/**
	 * Represents the URL request method operation.
	 */
	public enum HttpMethod {
		GET,
		PUT,
		POST,
		HEAD,
		TRACE,
		DELETE,
		OPTIONS
	}
	
	/**
	 * Makes a request to StreamElements.
	 * 
	 * @param <T>
	 * @parm timeout The connection timeout of the request.
	 * @param predicted The predicted ElementsResponse return.
	 * @param method The URL request method (Contained within this class).
	 * @param url The formatted URL to request to.
	 * @return The predicted response.
	 */
	public static <T extends ElementsResponse> List<ElementsOptional<T>> makeRequest(int timeout, Class<T> predicted, HttpMethod method, String url) throws IOException {
		URL request = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) request.openConnection();
		connection.setConnectTimeout(timeout);
		connection.setRequestMethod(method.name());
		InputStream input = connection.getInputStream();
		JsonReader reader = new JsonReader(new InputStreamReader(input, "UTF-8"));
		try {
			return ElementsReaderHandler.readPredicted(reader, predicted);
		} finally {
			reader.close();
			input.close();
		}
	}
	
	/**
	 * Return a Stream of a request to StreamElements.
	 * 
	 * @param <T>
	 * @parm timeout The connection timeout of the request.
	 * @param predicted The predicted ElementsResponse return.
	 * @param method The URL request method (Contained within this class).
	 * @param url The formatted URL to request to.
	 * @return The predicted response.
	 */
	public static <T extends ElementsResponse> Stream<ElementsOptional<T>> streamRequest(int timeout, Class<T> predicted, HttpMethod method, String url) throws IOException {
		URL request = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) request.openConnection();
		connection.setConnectTimeout(timeout);
		connection.setRequestMethod(method.name());
		InputStream input = connection.getInputStream();
		JsonReader reader = new JsonReader(new InputStreamReader(input, "UTF-8"));
		try {
			return ElementsReaderHandler.streamPredicted(reader, predicted);
		} finally {
			reader.close();
			input.close();
		}
	}

}
