package otakuplus.straybird.othellogame.network;

import java.io.IOException;

import otakuplus.straybird.othellogame.model.User;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class OthelloGameClientEnd {

	private Client client = null;

	public OthelloGameClientEnd() {
		client = new Client();
		client.start();

		KryonetUtil.register(client);

		client.addListener(new Listener() {
			public void connected(Connection connection) {

			}

			public void received(Connection connection, Object object) {
				if (object instanceof User) {

				}
			}
		});
	}

	public void connect() {
		try {
			client.connect(5000, "localhost", KryonetUtil.SERVER_PORT);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void login(String username, String password) {
		if (username == null || password == null) {
			return;
		} else {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			client.sendTCP(user);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.set(Log.LEVEL_DEBUG);
		OthelloGameClientEnd clientEnd = new OthelloGameClientEnd();
		clientEnd.connect();
		clientEnd.login("TestUser", "TestUser");
	}

}
