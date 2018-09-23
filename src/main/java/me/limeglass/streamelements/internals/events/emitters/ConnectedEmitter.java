package me.limeglass.streamelements.internals.events.emitters;

import java.util.Arrays;

import org.json.JSONObject;

import io.socket.client.Ack;
import io.socket.client.Socket;
import me.limeglass.streamelements.api.StreamElements;
import me.limeglass.streamelements.api.events.ConnectionEvent;
import me.limeglass.streamelements.internals.events.EventDispatcher;
import me.limeglass.streamelements.internals.events.SocketListener.ElementsEmitter;

public class ConnectedEmitter extends ElementsEmitter {

	private StreamElements instance;
	
	public ConnectedEmitter(StreamElements instance) {
		super(Socket.EVENT_CONNECTING);
		this.instance = instance;
	}

	@Override
	public void call(Object... args) {
		//System.out.println("test " + Arrays.toString(args));
		JSONObject object = new JSONObject();
		object.put("method", "jwt");
		object.put("token", instance.getToken());
		/*socket.emit("authenticate", object.toString(), new Ack() {
			@Override
			public void call(Object... args) {
				System.out.print("Callback: " + Arrays.toString(args));
				EventDispatcher.dispatch(new ConnectionEvent(false));
			}
		});*/
	}

}
