package me.limeglass.streamelements.internals.events;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter.Listener;
import me.limeglass.streamelements.api.StreamElements;

public class SocketListener {

	private static Set<ElementsEmitter> emitters = new HashSet<ElementsEmitter>();
	private static Socket socket;
	
	public static Socket registerSocket(StreamElements instance) {
		try {
			IO.Options options = new IO.Options();
			options.forceNew = true;
			options.reconnection = false;
			Reflections reflections = new Reflections("me.limeglass.streamelements.internals.events.emitters");
			for (Class<? extends ElementsEmitter> emitterClass : reflections.getSubTypesOf(ElementsEmitter.class)) {
				ElementsEmitter emitter;
				try {
					emitter = emitterClass.getDeclaredConstructor(StreamElements.class).newInstance(instance);
				} catch (NoSuchMethodException e) {
					emitter = emitterClass.newInstance();
				}
				emitters.add(emitter);
			}
			socket = IO.socket("https://realtime.streamelements.com", options);
			for (ElementsEmitter emitter : emitters) {
				socket.on(emitter.getEventName(), emitter);
			}
			socket.connect();
			return socket;
		} catch (URISyntaxException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static abstract class ElementsEmitter implements Listener {
		
		protected Socket socket = SocketListener.socket;
		protected String event;
		
		public ElementsEmitter(String event) {
			this.event = event;
		}
		
		public String getEventName() {
			return event;
		}
		
	}
	
}
