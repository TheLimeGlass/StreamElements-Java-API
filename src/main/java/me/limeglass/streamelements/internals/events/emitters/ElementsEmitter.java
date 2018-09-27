package me.limeglass.streamelements.internals.events.emitters;

import io.socket.client.Socket;
import io.socket.emitter.Emitter.Listener;
import me.limeglass.streamelements.internals.events.SocketListener;

public abstract class ElementsEmitter implements Listener {
	
	private boolean manager = false;
	protected String event;
	private Socket socket;
	
	public ElementsEmitter(String event) {
		this.event = event;
	}
	
	public ElementsEmitter(String event, boolean manager) {
		this.manager = manager;
		this.event = event;
	}
	
	public String getEventName() {
		return event;
	}

	public boolean isManagerInjection() {
		return manager;
	}
	
	@Override
	public void call(Object... args) {
		if (socket == null) socket = SocketListener.getSocket();
		call(socket, args);
	}
	
	protected abstract void call(Socket socket, Object... args);
	
}
