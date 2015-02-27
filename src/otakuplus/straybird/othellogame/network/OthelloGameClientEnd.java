package otakuplus.straybird.othellogame.network;

import java.io.IOException;

import otakuplus.straybird.othellogame.model.User;
import otakuplus.straybird.othellogame.model.UserInformation;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class OthelloGameClientEnd {

	private Client client = null;
	private ClientManager clientManager = null;
	private boolean isConnected = false;

	public OthelloGameClientEnd() {
		client = new Client();
		client.start();

		KryonetUtil.register(client);

		client.addListener(new Listener() {
			public void connected(Connection connection) {
			}

			public void received(Connection connection, Object object) {
				if (object instanceof User) {
					User user = (User) object;
					
				} else if (object instanceof UserInformation) {
					UserInformation userInformation = (UserInformation) object;
				/*	
				} else if (object instanceof LoginSuccess) {
					
				} else if (object instanceof LoginFaild) {
				*/
				}
			}
		});
	}

	public void connect() {
		try {
			client.connect(5000, "localhost", KryonetUtil.SERVER_PORT);
			isConnected = true;
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void login(String username, String password) {
		if (username == null || password == null) {
			return;
		} else {
			Login login = new Login();
			login.setUsername(username);
			login.setPassword(password);
			client.sendTCP(login);
		}
	}

	public void logout(int userId) {
		Logout logout = new Logout();
		logout.setUserId(userId);
		client.sendTCP(logout);
	}

	public boolean getIsConnected() {
		return isConnected;
	}

	public static void main(String[] args) {
		Log.set(Log.LEVEL_DEBUG);
		OthelloGameClientEnd clientEnd = new OthelloGameClientEnd();
		clientEnd.connect();
		clientEnd.login("TestUser", "TestUser");
	}

}
