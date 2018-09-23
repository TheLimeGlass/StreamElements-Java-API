package me.limeglass.streamelements.internals.events;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import me.limeglass.streamelements.api.annotations.EventListener;

public class EventDispatcher {

	//Contains all registered MethodHandlers.
	private final static Set<MethodHandler> registered = new HashSet<MethodHandler>();
	private final static MethodHandles.Lookup lookup = MethodHandles.lookup();
	
	/**
	 * @param listener The class of which contains the EventListener annotation to then call that method when the Event parameter happens.
	 */
	public static Class<?>[] registerListeners(Class<?>... listeners) {
		for (Class<?> listener : listeners) {
			Stream<Method> methods = Stream.of(listener.getMethods()).filter(method -> method.isAnnotationPresent(EventListener.class));
			methods.forEach(method -> {
				if (method.getParameterCount() != 1)
					throw new IllegalArgumentException("An EventHandler method contains more than 1 parameter, it may only be one parameter that being of a StreamElements Event. Invalid method " + method);
				Class<?> event = method.getParameterTypes()[0];
				if (!ElementsEvent.class.isAssignableFrom(event))
					throw new IllegalArgumentException("Parameter type is not a ElementsEvent. Invalid method " + method);
				method.setAccessible(true);
				try {
					registered.add(new MethodHandler(lookup.unreflect(method)));
				} catch (IllegalAccessException e) {
					throw new IllegalStateException("Method " + method + " is not accessible", e);
				}
			});
		}
		return listeners;
	}
	
	/**
	 * Used internally, call this when an event happens to trigger the API events.
	 * 
	 * @param event The ElementsEvent that just happened.
	 */
	public static void dispatch(ElementsEvent event) {
		registered.stream()
				.filter(check -> check.accepts(event))
				.forEach(handler -> {
					try {
						handler.handle(event);
					} catch (Throwable e) {
						System.out.println("Error dispatching event " + event.getClass().getSimpleName());
						e.printStackTrace();
					}
				});
	}
	
	/**
	 * A class in which helps invoke registered methods that contain the EventListener annotation.
	 */
	private static class MethodHandler {

		private final MethodHandle handle;
		
		public MethodHandler(MethodHandle handle) {
			this.handle = handle;
		}
		
		public void handle(ElementsEvent event) throws Throwable {
			handle.invoke((ElementsEvent)event);
		};
		
		public boolean accepts(ElementsEvent event) {
			return event.getClass().isInstance(event);
		}

	}
	
}
