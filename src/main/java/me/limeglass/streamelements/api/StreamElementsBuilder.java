package me.limeglass.streamelements.api;

import me.limeglass.streamelements.internals.StreamElementsClient;
import me.limeglass.streamelements.internals.objects.StreamElementsException;

public final class StreamElementsBuilder {

	private final String token, accountID;
	private int timeout = 20 * 1000;
	
	/**
	 * The StreamElementsBuilder Constructor
	 * 
	 * @param token The JWT token used from StreamElements. (Found under account)
	 * @param accountID The account ID used from StreamElements. (Found under account)
	 */
	public StreamElementsBuilder(String token, String accountID) {
		this.accountID = accountID;
		this.token = token;
	}
	
	/**
	 * Set the connection timeout of the client.
	 * Recommended to set one.
	 */
	public StreamElementsBuilder withConnectionTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}

	/**
	 * @return The timeout defined by the client.
	 */
	public int getTimeout() {
		return timeout;
	}
	
	/**
	 * @return The StreamElements JWT token of the client.
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * @return the StreamElements Account ID of the client.
	 */
	public String getAccountID() {
		return accountID;
	}
	
	//TODO
	public StreamElements build() {
		if (token == null || accountID == null) throw new StreamElementsException("The StreamElements token or account ID was not set!");
		return new StreamElementsClient(token, accountID, timeout);
	}

}
