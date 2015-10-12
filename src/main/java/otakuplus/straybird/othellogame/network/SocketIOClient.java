package otakuplus.straybird.othellogame.network;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketIOClient {

	public static final String SERVER_URI = "http://localhost:8081";
	private Socket socket;
	
	public SocketIOClient() {
		createSocketIOClient();
	}

	public void createSocketIOClient() {
		try {
			socket = IO.socket(SERVER_URI);
		} catch (java.net.URISyntaxException urisyntaxexception) {

		}
	}

	public void setupSocketIOClient() {
		if(socket != null)
		{
			socket.on(Socket.EVENT_CONNECT, new ClientConnectListener());
			socket.on(Socket.EVENT_CONNECT_TIMEOUT, new ClientConnectTimeoutListener());
			socket.on("LoginSuccessEvent", new ClientLoginSuccessListener());
			socket.on("EnterHallEvent", new ClientEnterHallListener());
		}
	}

	public void connect() {
		if (socket != null) {
			socket.connect();
		}
	}

	public void disconnect() {
		if (socket != null) {
			socket.disconnect();
		}
	}
	
}
