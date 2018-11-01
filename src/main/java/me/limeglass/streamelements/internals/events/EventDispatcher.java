package me.limeglass.streamelements.internals.events;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.stream.Stream;

import me.limeglass.streamelements.api.annotations.EventListener;

public class EventDispatcher {

	//Contains all registered MethodHandlers.
	private final static HashSet<MethodHandler> registered = new HashSet<MethodHandler>();
	private final static MethodHandles.Lookup lookup = MethodHandles.lookup();
	
	/**
	 * @param listener The class of which contains the EventListener annotation to then call that method when the Event parameter happens.
	 */
	public static Class<?>[] registerListeners(Class<?>... listeners) {
		for (Class<?> listener : listeners) registerListener(listener, null);
		return listeners;
	}
	
	/**
	 * @param listener The class of which contains the EventListener annotation to then call that method when the Event parameter happens.
	 */
	public static Class<?> registerListener(Class<?> listener) {
		return registerListener(listener, null);
	}
	
	/**
	 * @param clazz The class of which contains the EventListener annotation to then call that method when the Event parameter happens.
	 * @param The instance of the listener class.
	 */
	public static Class<?> registerListener(Class<?> clazz, Object listener) {
		Stream<Method> methods = Stream.of(clazz.getMethods()).filter(method -> method.isAnnotationPresent(EventListener.class));
		methods.forEach(method -> {
			if (method.getParameterCount() != 1)
				throw new IllegalArgumentException("An EventHandler method contains more than 1 parameter, it may only be one parameter that being of a StreamElements Event. Invalid method " + method);
			Class<?> event = method.getParameterTypes()[0];
			if (!ElementsEvent.class.isAssignableFrom(event))
				throw new IllegalArgumentException("Parameter type is not a ElementsEvent. Invalid method " + method);
			method.setAccessible(true);
			try {
				registered.add(new MethodHandler(lookup.unreflect(method), clazz, event, listener));
			} catch (IllegalAccessException e) {
				throw new IllegalStateException("Method " + method + " is not accessible", e);
			}
		});
		return clazz;
	}
	
	/**
	 * Used internally, call this when an event happens to trigger the API events.
	 * 
	 * @param event The ElementsEvent that just happened.
	 * @return 
	 */
	public static ElementsEvent dispatch(ElementsEvent event) {
		//TODO make this section cancellable
		for (MethodHandler handler : registered) {
			if (handler.accepts(event))
				try {
					return handler.handle(event);
				} catch (Throwable e) {
					System.out.println("Error dispatching event " + event.getClass().getSimpleName());
					e.printStackTrace();
				}
		}
		return event;
	}
	
	/**
	 * A class in which helps invoke registered methods that contain the EventListener annotation.
	 */
	private static class MethodHandler {

		private final Class<?> listenerClass, eventClass;
		private final MethodHandle handle;
		private Object listener;
		
		public MethodHandler(MethodHandle handle, Class<?> listenerClass, Class<?> eventClass, Object listener) {
			this.listenerClass = listenerClass;
			this.eventClass = eventClass;
			this.listener = listener;
			this.handle = handle;
		}

		public ElementsEvent handle(ElementsEvent event) throws Throwable {
			Constructor<?>[] constructors = listenerClass.getConstructors();
			if (constructors.length > 0 && constructors[0].getParameterCount() > 0 && listener == null)
				throw new IllegalArgumentException("The listener instance may not be null and have a constructor at the same time.\n Use registerListener(Listener.class, new SubClassListener())");
			if (listener == null)
				listener = listenerClass.newInstance();
			handle.invoke(event);
			return event;
		};
		
		public boolean accepts(ElementsEvent event) {
			return eventClass.equals(event.getClass());
		}

	}
	
}
