package me.limeglass.streamelements.internals.events.emitters;

import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Transport;
import me.limeglass.streamelements.api.StreamElements;

public class TransportEmitter extends ElementsEmitter {

	@SuppressWarnings("unused")
	private StreamElements instance;
	
	public TransportEmitter(StreamElements instance) {
		super(Manager.EVENT_TRANSPORT, true);
		this.instance = instance;
	}

	//TODO Currently only used for debugging.
	
	@Override
	public void call(Socket socket, Object... args) {
		Transport transport = (Transport)args[0];
		transport.on(Transport.EVENT_REQUEST_HEADERS, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				//@SuppressWarnings("unchecked")
				//Map<String, List<String>> headers = (Map<String, List<String>>)args[0];
				//headers.entrySet().forEach(entry -> System.out.println("[" + entry.getKey() + "] values: " + entry.getValue().toString()));
			}
		});
		transport.on(Transport.EVENT_RESPONSE_HEADERS, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				//@SuppressWarnings("unchecked")
				//Map<String, List<String>> headers = (Map<String, List<String>>)args[0];
				//headers.entrySet().forEach(entry -> System.out.println("[" + entry.getKey() + "] values: " + entry.getValue().toString()));
			}
		});
	}

}
