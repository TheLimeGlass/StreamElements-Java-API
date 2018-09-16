package me.limeglass.streamelements.internals;

import java.io.IOException;
import java.util.List;

import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.objects.Activity;
import me.limeglass.streamelements.internals.ElementsRequest.HttpMethod;
import me.limeglass.streamelements.internals.responses.ActivitiesResponse;

public class StreamElementsClient implements StreamElements {
	
	private final String token, account;
	private final int timeout;
	
	/**
	 * The StreamElements client Constructor
	 * 
	 * @param token The JWT token used from StreamElements. (Found under account)
	 * @param accountID The account ID used from StreamElements. (Found under account)
	 */
	public StreamElementsClient(String token, String account, int timeout) {
		this.account = account;
		this.timeout = timeout;
		this.token = token;
		ElementsReaderHandler.load("me.limeglass.streamelements.internals.readers");
	}

	/**
	 * @return the StreamElements Account ID of the client.
	 */
	@Override
	public String getAccountID() {
		return account;
	}
	
	/**
	 * @return The StreamElements JWT token of the client.
	 */
	@Override
	public String getToken() {
		return token;
	}

	/**
	 * @return The timeout defined by the client.
	 */
	@Override
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @return The timeout defined by the client.
	 */
	@Override
	public List<Activity> getActivities() {
		try {
			List<ElementsOptional<ActivitiesResponse>> response = ElementsRequest.makeRequest(timeout, ActivitiesResponse.class, HttpMethod.GET, ElementsEndpoints.ACTIVITIES + account);
			response.stream()
					.filter(optional -> optional.isPresent())
					.map(optional -> optional.getOptional().get());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
