package me.limeglass.streamelements;

import java.io.File;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.StreamElementsBuilder;

public class ApplicationTest {

	//Example usage
	
	private static PropertiesConfiguration config;
	private static StreamElements instance;
	
	public static void main(String[] args) {
		try {
			config = new Configurations().properties(new File("config.properties"));
		}
		catch (ConfigurationException exception) {}
		instance = new StreamElementsBuilder()
				.withAccountID(config.getString("client.account"))
				.withToken(config.getString("client.token"))
				.withConnectionTimeout(10000)
				.build();
		ActivitiesTest.execute(instance);
	}

	public static StreamElements getStreamElements() {
		return instance;
	}

}
