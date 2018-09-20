package me.limeglass.streamelements.internals.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.stream.JsonReader;

import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.internals.ElementsOptional;

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
	 * Used internally.
	 */
	private static HttpURLConnection getConnection(StreamElements instance, HttpMethod method, String url) throws IOException {
		URL request = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) request.openConnection();
		connection.setRequestProperty("Authorization", "Bearer " + instance.getToken());
		connection.setConnectTimeout(instance.getTimeout());
		connection.setRequestMethod(method.name());
		connection.setDoOutput(true);
		return connection;
	}
	
	/**
	 * Makes a request to StreamElements.
	 * 
	 * @param <T>
	 * @parm timeout The connection timeout of the request.
	 * @param predicted The predicted ElementsResponse return.
	 * @param method The URL request method (Contained within this class).
	 * @param url The formatted URL to request to.
	 * @return The predicted response as a List.
	 */
	public static <T extends ElementsResponse> List<ElementsOptional<T>> makeRequest(StreamElements instance, Class<T> predicted, HttpMethod method, String url) {
		return streamRequest(instance, predicted, method, url).collect(Collectors.toList());
	}
	
	/**
	 * Return a Stream of a request to StreamElements.
	 * 
	 * @param <T>
	 * @parm timeout The connection timeout of the request.
	 * @param predicted The predicted ElementsResponse return.
	 * @param method The URL request method (Contained within this class).
	 * @param url The formatted URL to request to.
	 * @return The predicted response. Only returns a valid response for GET HttpMethod calling.
	 */
	public static <T extends ElementsResponse> Stream<ElementsOptional<T>> streamRequest(StreamElements instance, Class<T> predicted, HttpMethod method, String url) {
		try {
			HttpURLConnection connection = getConnection(instance, method, url);
			if (method != HttpMethod.GET) {
				OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
				try {
					return ElementsUploader.handle(method, url, output, predicted);
				} finally {
					output.close();
					//TODO Handle this with the error messages.
					connection.getResponseCode();
				}
			} else {
				InputStream input = connection.getInputStream();
				JsonReader reader = new JsonReader(new InputStreamReader(input, "UTF-8"));
				try {
					return ElementsReaderHandler.streamPredicted(reader, predicted);
				} finally {
					reader.close();
					input.close();
				}
			}
		} catch (IOException e) {
			return Stream.of(new ElementsOptional<T>(e));
		}
	}

}
