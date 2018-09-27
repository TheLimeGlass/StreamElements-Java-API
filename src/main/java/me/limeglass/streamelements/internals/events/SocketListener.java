package me.limeglass.streamelements.internals.events;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;

import com.google.common.collect.Sets;

import io.socket.client.IO;
import io.socket.client.Socket;
import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.internals.events.emitters.ElementsEmitter;
import me.limeglass.streamelements.internals.events.emitters.EventEmitter;

public class SocketListener {

	private static Set<ElementsEmitter> emitters = new HashSet<ElementsEmitter>();
	private static Set<Class<?>> ignored = Sets.newHashSet(EventEmitter.class);
	private static Socket socket;
	
	public static Socket registerSocket(StreamElements instance) {
		try {
			Reflections reflections = new Reflections("me.limeglass.streamelements.internals.events.emitters");
			for (Class<? extends ElementsEmitter> emitterClass : reflections.getSubTypesOf(ElementsEmitter.class)) {
				ElementsEmitter emitter;
				try {
					emitter = emitterClass.getDeclaredConstructor(StreamElements.class).newInstance(instance);
				} catch (NoSuchMethodException e) {
					if (ignored.contains(emitterClass))
						continue;
					emitter = emitterClass.newInstance();
				}
				emitters.add(emitter);
			}
			IO.Options options = new IO.Options();
			options.forceNew = true;
			options.reconnection = true;
			options.transports = new String[] {"websocket"};
			socket = IO.socket("https://realtime.streamelements.com", options);
			for (ElementsEmitter emitter : emitters) {
				if (emitter.isManagerInjection()) socket.io().on(emitter.getEventName(), emitter);
				else socket.on(emitter.getEventName(), emitter);
			}
			return socket.connect();
		} catch (URISyntaxException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Socket getSocket() {
		return socket;
	}
	
	/**
	 * Useful for adding emitters that shouldn't be read as a logical ElementsEmitter. Example being EventEmitter.
	 * @param emitter The class that extends ElementsEmitter.
	 */
	public static <T extends ElementsEmitter> void addIgnored(Class<T> emitter) {
		ignored.add(emitter);
	}
	
}
