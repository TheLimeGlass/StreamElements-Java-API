package me.limeglass.streamelements.api;

import me.limeglass.streamelements.internals.StreamElementsClient;
import me.limeglass.streamelements.internals.objects.StreamElementsException;

public final class StreamElementsBuilder {

	private String token, accountID;
	private int timeout = 20 * 1000;
	private Class<?>[] listeners;
	private String[] emitters;
	
	/**
	 * For those whom love keeping clean methods per line.
	 * 
	 * This requires that you set the account ID and token within the method though.
	 * The building process will error when finishing if these values are not set.
	 */
	public StreamElementsBuilder() {}
	
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
	 * Register any listeners.
	 * If a method contains 1 parameter of an ElementsEvent and has the @EventListener annotation,
	 * it will be called when an event happens.
	 */
	public StreamElementsBuilder registerListeners(Class<?>... listeners) {
		this.listeners = listeners;
		return this;
	}
	
	/**
	 * Register any emitters you want.
	 * The parameter string is the class path of the emitter. Example: me.limeglass.streamelements.internal.events.emitters
	 */
	public StreamElementsBuilder registerEmitters(String... emitters) {
		this.emitters = emitters;
		return this;
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
	 * Set the account ID of the StreamElementsBuilder.
	 */
	public StreamElementsBuilder withAccountID(String accountID) {
		this.accountID = accountID;
		return this;
	}
	
	/**
	 * Set the token of the StreamElementsBuilder.
	 */
	public StreamElementsBuilder withToken(String token) {
		this.token = token;
		return this;
	}
	
	/**
	 * NOTE: These classes aren't registered until the build method has been triggered.
	 * 
	 * @return The classes that have been scanned and registered listeners for.
	 */
	public Class<?>[] getListeners() {
		return listeners;
	}
	
	/**
	 * @return The StreamElements Account ID of the client.
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * @return The StreamElements JWT token of the client.
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * @return The timeout defined by the client.
	 */
	public int getTimeout() {
		return timeout;
	}
	
	//TODO
	public StreamElements build() {
		if (token == null || accountID == null)
			throw new StreamElementsException("The StreamElements token or account ID was not set!");
		return new StreamElementsClient(token, accountID, timeout, listeners, emitters);
	}

}
