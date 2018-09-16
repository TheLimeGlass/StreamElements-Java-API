package me.limeglass.streamelements.internals;

public class ElementsEndpoints {

	/**
	 * The main URL.
	 */
	public static final String API = "https://api.streamelements.com/kappa/v2";
	
	/**
	 * The main chat bot endpoint.
	 */
	public static final String BOT = API + "/bot/";
	
	//ChatBot endpoints
	/**
	 * The main chat bot timers endpoint.
	 */
	public static final String BOT_TIMERS = BOT + "timers/";
	
	/**
	 * The main chat bot modules endpoint.
	 */
	public static final String BOT_MODULES = BOT + "modules/";
	
	/**
	 * The main chat bot commands endpoint.
	 */
	public static final String BOT_COMMANDS = BOT + "commands/";
	
	//other endpoints
	/**
	 * The tips endpoint.
	 */
	public static final String TIPS = API + "/tips/";
	
	/**
	 * The logs endpoint.
	 */
	public static final String LOGS = API + "/logs/";
	
	/**
	 * The statistics endpoint.
	 */
	public static final String STATS = API + "/stats/";
	
	/**
	 * The store endpoint.
	 */
	public static final String STORE = API + "/store/";
	
	/**
	 * The points/soft currency endpoint.
	 */
	public static final String POINTS = API + "/points/";
	
	/**
	 * The streams endpoint.
	 */
	public static final String STREAMS = API + "/streams/";
	
	/**
	 * The uploads endpoint.
	 */
	public static final String UPLOADS = API + "/uploads/";
	
	/**
	 * The loyalty endpoint.
	 */
	public static final String LOYALTY = API + "/loyalty/";
	
	/**
	 * The sessions endpoint.
	 */
	public static final String SESSIONS = API + "/sessions/";
	
	/**
	 * The contests endpoint.
	 */
	public static final String CONTESTS = API + "/contests/";
	
	/**
	 * The overlays endpoint.
	 */
	public static final String OVERLAYS = API + "/overlays/";
	
	/**
	 * The giveaways endpoint.
	 */
	public static final String GIVEAWAYS = API + "/giveaways/";
	
	/**
	 * The activities endpoint.
	 */
	public static final String ACTIVITIES = API + "/activities/";
	
	/**
	 * The songrequests endpoint.
	 */
	public static final String SONG_REQUESTS = API + "/songrequest/";
	
	/**
	 * The themes endpoint.
	 */
	public static final String THEMES = API + "/themes/";
	public static final String THEMES_API = API + "/themes";
	
	/**
	 * The channels endpoint.
	 */
	public static final String CHANNELS = API + "/channels/";
	public static final String OWN_CHANNELS = CHANNELS + "me";
	
	/**
	 * The tipping endpoint.
	 */
	public static final String TIPPING = API + "/tipping/";
	public static final String TIPPING_RATES = TIPPING + "rates";
	
	/**
	 * The users endpoint.
	 */
	public static final String USERS = API + "/users/";
	public static final String USERS_ACCESS = USERS + "access";
	public static final String USERS_CURRENT = USERS + "current";
	public static final String USERS_CHANNELS = USERS + "channels";
	
	/**
	 * The speech endpoint.
	 */
	public static final String SPEECH_API = API + "/speech";
	public static final String SPEECH_VOICES = API + "/speech/voices";
	
	/**
	 * The change logs endpoint.
	 */
	public static final String CHANGE_LOGS = API + "/changelogs/";
	public static final String CHANGE_LOGS_FIRST = API + "/changelogs/first";
	public static final String CHANGE_LOGS_LATEST = API + "/changelogs/latest";

}
